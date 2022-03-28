package Server;

import Client.Msg;
import Client.MsgType;
import M.GameModel;
import tank.Tank;
import tank.bullet;

import javax.swing.plaf.metal.MetalBorders;
import java.io.*;
import java.util.UUID;

public class TankDieMsg extends Msg {
     UUID bulletId;//谁杀了我
     UUID id;

     public TankDieMsg(UUID playId,UUID id){
         this.bulletId= playId;
         this.id=id;
     }
     public TankDieMsg(){}


    @Override
    public void handle() {
        System.out.println("we got a tank die:"+id);
        System.out.println("and my tank is:"+ GameModel.getInstance().MyTank.id);
        Tank tt=GameModel.getInstance().findTankByUUID(id);
        System.out.println("i find a tank with this id:"+tt);
        bullet b=GameModel.getInstance().findBulletByUUID(bulletId);
        if(b!=null){
            GameModel.getInstance().objects.remove(b.id);
        }
        if(this.id.equals(GameModel.getInstance().MyTank.id)){
            GameModel.getInstance().MyTank.die();
        } else {
            Tank t =GameModel.getInstance().findTankByUUID(id);
            if(t!=null) t.die();
        }
    }

    @Override
    public byte[] toBytes() {
        ByteArrayOutputStream baos=null;
        DataOutputStream dos=null;
        byte[] bytes=null;
        try {
        baos=new ByteArrayOutputStream();
        dos=new DataOutputStream(baos);
        //写入uuid的高8位和低8位
        dos.writeLong(bulletId.getMostSignificantBits());
        dos.writeLong(bulletId.getLeastSignificantBits());
            dos.writeLong(id.getMostSignificantBits());
            dos.writeLong(id.getLeastSignificantBits());
        dos.flush();
        bytes=baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                 if(baos!=null) {
                     baos.close();
                 }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            try {
                if(dos!=null) {
                        dos.close();}
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        return bytes;
    }

    @Override
    public void parse(byte[] bytes) {
        DataInputStream dis=new DataInputStream(new ByteArrayInputStream(bytes));
        try{
             this.bulletId=new UUID(dis.readLong(),dis.readLong());
             this.id=new UUID(dis.readLong(),dis.readLong());
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                dis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public MsgType getMsgType() {
        return null;
    }
}
