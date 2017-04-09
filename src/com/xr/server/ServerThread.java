package com.xr.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;

import com.xr.bean.Good;
import com.xr.server.dao.impl.GoodDaoImpl;

public class ServerThread extends Thread{
	
	//����
	private Socket socket;
	//����ͷ���
	public ServerThread(Socket socket) {
		super();
		this.socket = socket;
	}
	public Socket getSocket() {
		return socket;
	}
	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	
	
	//��дrun
	@Override
	public void run() {
		try {
			//ʵ�����ӿ�ʵ��
			GoodDaoImpl gdi = new GoodDaoImpl();
			
			//��ȡ�ӿ�������
			InputStream is = this.socket.getInputStream();
			int choice = is.read();
			//��ִ֧��
			if(choice == 0){
				System.out.println("�û��˳���");
			}
			switch(choice){
				case 1:{
					//���������շ��Ͷ���--���ؽ��
					ObjectInputStream ois = new ObjectInputStream(is);
					Good good = (Good) ois.readObject();
					//�ر�
					this.socket.shutdownInput();
					
					//����
					int result = gdi.addGood(good);
					
					//���ͽ��
					OutputStream os = this.socket.getOutputStream();
					os.write(result);
					//�ر�
					this.socket.shutdownOutput();
					break;
				}
				case 2:{
					//ɾ��:��ȡ���--ɾ��--���ؽ��
					int id = is.read();
					//�ر�
					this.socket.shutdownInput();
					
					//ɾ��
					int result = gdi.deleteGood(id);
					
					//���ͽ��
					OutputStream os = this.socket.getOutputStream();
					os.write(result);
					//�ر�
					this.socket.shutdownOutput();
					break;
				}
				case 3:{
					//���£����ն���--����--���ͽ��
					ObjectInputStream ois = new ObjectInputStream(is);
					Good good = (Good) ois.readObject();
					//�ر�
					this.socket.shutdownInput();
					
					//����
					int result = gdi.updateGood(good);
					
					//���ͽ��
					OutputStream os = this.socket.getOutputStream();
					os.write(result);
					//�ر�
					this.socket.shutdownOutput();
					break;
				}
				case 4:{
					//��ѯ�����ձ��--��ѯ--���ض���
					int id = is.read();
					//�ر�
					this.socket.shutdownInput();
					
					//��ѯ
					Good good = gdi.findGood(id);
					
					//���Ͷ���
					OutputStream os = this.socket.getOutputStream();
					ObjectOutputStream oos = new ObjectOutputStream(os);
					oos.writeObject(good);
					//�ر�
					this.socket.shutdownOutput();
					break;
				}
				case 5:{
					//�б������б�
					OutputStream os = this.socket.getOutputStream();
					ObjectOutputStream oos = new ObjectOutputStream(os);
					
					//�б�
					List<Good> goodList = gdi.allGood();
					
					oos.writeObject(goodList);
					//�ر�
					this.socket.shutdownOutput();
					break;
				}
			}//switch
		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}//run
	
}
