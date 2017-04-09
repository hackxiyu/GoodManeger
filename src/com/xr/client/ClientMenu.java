/**
 * 客户端
 */
package com.xr.client;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

import com.xr.bean.Good;

public class ClientMenu {
	public static void main(String[] args) throws Exception{
		while(true){
		//显示界面，获取输入
		showMenu();
		Scanner input = new Scanner(System.in);
		int choice = input.nextInt();
		
		//连接服务器
		Socket socket = new Socket("127.0.0.1", 10010);
		
		//选择分支
		if(choice == 0){
			//发送通知到服务器
			OutputStream os = socket.getOutputStream();
			os.write(choice);
			socket.shutdownOutput();
			break;
		}
		switch(choice){
			case 1:{
				//新增：发送选择，新对象
				System.out.println("商品编号：");
				int id = input.nextInt();
				System.out.println("商品名字：");
				String name = input.next();
				System.out.println("商品价格：");
				Double price = input.nextDouble();
				//新建对象
				Good good = new Good(id, name, price);
				//输出到服务器
				OutputStream os = socket.getOutputStream();
				os.write(choice);
				ObjectOutputStream oos = new ObjectOutputStream(os);
				oos.writeObject(good);
				//关闭
				socket.shutdownOutput();
				
				//接收返回
				InputStream is = socket.getInputStream();
				int result = is.read();
				if(result==0){
					System.out.println("未完成！");
				}else{
					System.out.println("已完成！");
				}
				
				//关闭
				socket.shutdownInput();
				break;
			}
			case 2:{
				//删除：发送编号--接收返回
				System.out.println("商品编号：");
				int id = input.nextInt();
				//输出到服务器
				OutputStream os = socket.getOutputStream();
				os.write(choice);
				os.write(id);
				//关闭
				socket.shutdownOutput();
				
				//接收返回
				InputStream is = socket.getInputStream();
				int result = is.read();
				if(result==0){
					System.out.println("未完成！");
				}else{
					System.out.println("已完成！");
				}
				
				//关闭
				socket.shutdownInput();
				break;
			}
			case 3:{
				//更新：发送选择和新对象--接收返回
				System.out.println("商品编号：");
				int id = input.nextInt();
				System.out.println("商品名字：");
				String name = input.next();
				System.out.println("商品价格：");
				Double price = input.nextDouble();
				//新建对象
				Good good = new Good(id, name, price);
				//输出到服务器
				OutputStream os = socket.getOutputStream();
				os.write(choice);
				ObjectOutputStream oos = new ObjectOutputStream(os);
				oos.writeObject(good);
				//关闭
				socket.shutdownOutput();
				
				//接收返回
				InputStream is = socket.getInputStream();
				int result = is.read();
				if(result==0){
					System.out.println("未完成！");
				}else{
					System.out.println("已完成！");
				}
				
				//关闭
				socket.shutdownInput();
				break;
			}
			case 4:{
				//查询：发送选择和编号--接收对象
				System.out.println("商品编号：");
				int id = input.nextInt();
				//输出到服务器
				OutputStream os = socket.getOutputStream();
				os.write(choice);
				os.write(id);
				//关闭
				socket.shutdownOutput();
				
				//接收返回
				InputStream is = socket.getInputStream();
				ObjectInputStream ois = new ObjectInputStream(is);
				Good good = (Good) ois.readObject();
				if(good==null){
					System.out.println("未完成！");
				}else{
					System.out.println(good);
				}
				
				//关闭
				socket.shutdownInput();
				break;
			}
			case 5:{
				//列表：发送选择--接收列表
				OutputStream os = socket.getOutputStream();
				os.write(choice);
				//关闭
				socket.shutdownOutput();
				
				//接收返回
				InputStream is = socket.getInputStream();
				ObjectInputStream ois = new ObjectInputStream(is);
				List<Good> goodlist = (List<Good>) ois.readObject();;
				for (Good good : goodlist) {
					System.out.println(good);
				}
				
				//关闭
				socket.shutdownInput();
				break;
			}
		}//switch
		
		}//while
		
	}
	
	//界面
	public static void showMenu(){
		System.out.println("===商品管理系统===");
		System.out.println("===1-增加商品===");
		System.out.println("===2-删除商品===");
		System.out.println("===3-更改商品===");
		System.out.println("===4-查询商品===");
		System.out.println("===5-商品列表===");
		System.out.println("===0-退出系统===");
	}
}
