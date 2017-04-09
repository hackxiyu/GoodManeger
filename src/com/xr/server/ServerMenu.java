package com.xr.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMenu {
	public static void main(String[] args) throws IOException {
		
		//监听端口
		ServerSocket serverSocket = new ServerSocket(10010);
		System.out.println("run...");
		
		while(true){
			Socket socket = serverSocket.accept();
			//实例化线程
			ServerThread st = new ServerThread(socket);
			st.start();
		}
		
		
	}
}
