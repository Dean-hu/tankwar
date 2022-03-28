package Server;


import Client.Msg;
import Client.MsgType;
import M.GameModel;
import tank.Direction;
import tank.Group;
import tank.bullet;

import java.awt.print.Pageable;
import java.io.*;
import java.util.UUID;

public class BulletNewMsg extends Msg {

	UUID playerID;
	UUID id;
	int x, y;
	Direction dir;
	Group group;
	
	public BulletNewMsg(bullet bullet) {
		this.playerID = bullet.playerId;
		this.id = bullet.id;
		this.x = bullet.x;
		this.y = bullet.y;
		this.dir = bullet.dri;
		this.group = bullet.getGroup();
	}
	
	public BulletNewMsg() {
		
	}
	
	@Override
	public byte[] toBytes() {
		ByteArrayOutputStream baos = null;
		DataOutputStream dos = null;
		byte[] bytes = null;
		try {
			baos = new ByteArrayOutputStream();
			dos = new DataOutputStream(baos);
			//先写主战坦克id
			dos.writeLong(this.playerID.getMostSignificantBits());
			dos.writeLong(this.playerID.getLeastSignificantBits());
			//写子弹id
			dos.writeLong(id.getMostSignificantBits());
			dos.writeLong(id.getLeastSignificantBits());
			dos.writeInt(x);
			dos.writeInt(y);
			dos.writeInt(dir.ordinal());
			dos.writeInt(group.ordinal());
			dos.flush();
			bytes = baos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(baos != null) {
					baos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if(dos != null) {
					dos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return bytes;
	}
	
	@Override
	public void parse(byte[] bytes) {
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(bytes));
		try {
			this.playerID = new UUID(dis.readLong(), dis.readLong());
			this.id = new UUID(dis.readLong(), dis.readLong());
			this.x = dis.readInt();
			this.y = dis.readInt();
			this.dir = Direction.values()[dis.readInt()];
			this.group = Group.values()[dis.readInt()];
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				dis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
	}
	
	@Override
	public void handle() {
		if (this.playerID.equals(GameModel.getInstance().MyTank.id))
			return;
		
		bullet bullet = new bullet(x, y, dir, group,this.playerID);
		bullet.id=this.id;
		GameModel.getInstance().objects.put(this.id,bullet);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(this.getClass().getName())
			   .append("[")
			   .append("playerid=" + playerID + " | ")
			   .append("uuid=" + id + " | ")
			   .append("x=" + x + " | ")
			   .append("y=" + y + " | ")
			   .append("dir=" + dir + " | ")
			   .append("group=" + group + " | ")
			   .append("]");
		return builder.toString();
	}

	@Override
	public MsgType getMsgType() {
		return MsgType.BulletNew;
	}

	

}
