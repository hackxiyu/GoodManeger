package com.xr.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMenu {
	public static void main(String[] args) throws IOException {
		
		//�����˿�
		ServerSocket serverSocket = new ServerSocket(10010);
		System.out.println("run...");
		
		while(true){
			Socket socket = serverSocket.accept();
			//ʵ�����߳�
			ServerThread st = new ServerThread(socket);
			st.start();
		}
		
		
	}
}
