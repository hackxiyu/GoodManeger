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
	
	//属性
	private Socket socket;
	//构造和访问
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
	
	
	//重写run
	@Override
	public void run() {
		try {
			//实例化接口实现
			GoodDaoImpl gdi = new GoodDaoImpl();
			
			//获取接口输入流
			InputStream is = this.socket.getInputStream();
			int choice = is.read();
			//分支执行
			if(choice == 0){
				System.out.println("用户退出！");
			}
			switch(choice){
				case 1:{
					//新增：接收发送对象--返回结果
					ObjectInputStream ois = new ObjectInputStream(is);
					Good good = (Good) ois.readObject();
					//关闭
					this.socket.shutdownInput();
					
					//新增
					int result = gdi.addGood(good);
					
					//发送结果
					OutputStream os = this.socket.getOutputStream();
					os.write(result);
					//关闭
					this.socket.shutdownOutput();
					break;
				}
				case 2:{
					//删除:读取编号--删除--返回结果
					int id = is.read();
					//关闭
					this.socket.shutdownInput();
					
					//删除
					int result = gdi.deleteGood(id);
					
					//发送结果
					OutputStream os = this.socket.getOutputStream();
					os.write(result);
					//关闭
					this.socket.shutdownOutput();
					break;
				}
				case 3:{
					//更新：接收对象--更新--发送结果
					ObjectInputStream ois = new ObjectInputStream(is);
					Good good = (Good) ois.readObject();
					//关闭
					this.socket.shutdownInput();
					
					//更新
					int result = gdi.updateGood(good);
					
					//发送结果
					OutputStream os = this.socket.getOutputStream();
					os.write(result);
					//关闭
					this.socket.shutdownOutput();
					break;
				}
				case 4:{
					//查询：接收编号--查询--返回对象
					int id = is.read();
					//关闭
					this.socket.shutdownInput();
					
					//查询
					Good good = gdi.findGood(id);
					
					//发送对象
					OutputStream os = this.socket.getOutputStream();
					ObjectOutputStream oos = new ObjectOutputStream(os);
					oos.writeObject(good);
					//关闭
					this.socket.shutdownOutput();
					break;
				}
				case 5:{
					//列表：发送列表
					OutputStream os = this.socket.getOutputStream();
					ObjectOutputStream oos = new ObjectOutputStream(os);
					
					//列表
					List<Good> goodList = gdi.allGood();
					
					oos.writeObject(goodList);
					//关闭
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
