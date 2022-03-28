package Client;

import M.GameModel;
import Server.TankJoinMsg;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class Client{

     public static final  Client INSTANCE =new Client();
     private Channel channel =null;

     private Client(){}

     public void connect(){
         EventLoopGroup group=new NioEventLoopGroup(1);
         Bootstrap b=new Bootstrap();
         try {
         ChannelFuture f =b.group(group)
                 .channel(NioSocketChannel.class)
                 .handler(new ClientChannelInitializer())
                 .connect("localhost",8888);
           f.addListener(new ChannelFutureListener() {
               @Override
               public void operationComplete(ChannelFuture channelFuture) throws Exception {
                    if(channelFuture.isSuccess())
                    { log.debug("connect.....");
                        channel =channelFuture.channel();}
                    else
                        log.debug("not connect ....");
               }
           });
             f.sync();
             f.channel().closeFuture().sync();
             System.out.println("connecting closed....");
         } catch (InterruptedException e) {
             e.printStackTrace();
         }finally {
             group.shutdownGracefully();
         }

     }
    public void send(Msg msg){
        log.debug("SEND："+msg);
        channel.writeAndFlush(msg);
    }

}

class ClientChannelInitializer extends ChannelInitializer<SocketChannel>{

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        socketChannel.pipeline()
                .addLast(new MsgEncode())
                .addLast(new MsgDecoder())
                .addLast(new ClientHandler());
    }
}
class ClientHandler extends  SimpleChannelInboundHandler<Msg>{
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Msg msg) throws Exception {
        System.out.println(msg);
        msg.handle();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(new TankJoinMsg(GameModel.getInstance().MyTank));
    }
}