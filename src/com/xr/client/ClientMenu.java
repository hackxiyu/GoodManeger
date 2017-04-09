/**
 * �ͻ���
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
		//��ʾ���棬��ȡ����
		showMenu();
		Scanner input = new Scanner(System.in);
		int choice = input.nextInt();
		
		//���ӷ�����
		Socket socket = new Socket("127.0.0.1", 10010);
		
		//ѡ���֧
		if(choice == 0){
			//����֪ͨ��������
			OutputStream os = socket.getOutputStream();
			os.write(choice);
			socket.shutdownOutput();
			break;
		}
		switch(choice){
			case 1:{
				//����������ѡ���¶���
				System.out.println("��Ʒ��ţ�");
				int id = input.nextInt();
				System.out.println("��Ʒ���֣�");
				String name = input.next();
				System.out.println("��Ʒ�۸�");
				Double price = input.nextDouble();
				//�½�����
				Good good = new Good(id, name, price);
				//�����������
				OutputStream os = socket.getOutputStream();
				os.write(choice);
				ObjectOutputStream oos = new ObjectOutputStream(os);
				oos.writeObject(good);
				//�ر�
				socket.shutdownOutput();
				
				//���շ���
				InputStream is = socket.getInputStream();
				int result = is.read();
				if(result==0){
					System.out.println("δ��ɣ�");
				}else{
					System.out.println("����ɣ�");
				}
				
				//�ر�
				socket.shutdownInput();
				break;
			}
			case 2:{
				//ɾ�������ͱ��--���շ���
				System.out.println("��Ʒ��ţ�");
				int id = input.nextInt();
				//�����������
				OutputStream os = socket.getOutputStream();
				os.write(choice);
				os.write(id);
				//�ر�
				socket.shutdownOutput();
				
				//���շ���
				InputStream is = socket.getInputStream();
				int result = is.read();
				if(result==0){
					System.out.println("δ��ɣ�");
				}else{
					System.out.println("����ɣ�");
				}
				
				//�ر�
				socket.shutdownInput();
				break;
			}
			case 3:{
				//���£�����ѡ����¶���--���շ���
				System.out.println("��Ʒ��ţ�");
				int id = input.nextInt();
				System.out.println("��Ʒ���֣�");
				String name = input.next();
				System.out.println("��Ʒ�۸�");
				Double price = input.nextDouble();
				//�½�����
				Good good = new Good(id, name, price);
				//�����������
				OutputStream os = socket.getOutputStream();
				os.write(choice);
				ObjectOutputStream oos = new ObjectOutputStream(os);
				oos.writeObject(good);
				//�ر�
				socket.shutdownOutput();
				
				//���շ���
				InputStream is = socket.getInputStream();
				int result = is.read();
				if(result==0){
					System.out.println("δ��ɣ�");
				}else{
					System.out.println("����ɣ�");
				}
				
				//�ر�
				socket.shutdownInput();
				break;
			}
			case 4:{
				//��ѯ������ѡ��ͱ��--���ն���
				System.out.println("��Ʒ��ţ�");
				int id = input.nextInt();
				//�����������
				OutputStream os = socket.getOutputStream();
				os.write(choice);
				os.write(id);
				//�ر�
				socket.shutdownOutput();
				
				//���շ���
				InputStream is = socket.getInputStream();
				ObjectInputStream ois = new ObjectInputStream(is);
				Good good = (Good) ois.readObject();
				if(good==null){
					System.out.println("δ��ɣ�");
				}else{
					System.out.println(good);
				}
				
				//�ر�
				socket.shutdownInput();
				break;
			}
			case 5:{
				//�б�����ѡ��--�����б�
				OutputStream os = socket.getOutputStream();
				os.write(choice);
				//�ر�
				socket.shutdownOutput();
				
				//���շ���
				InputStream is = socket.getInputStream();
				ObjectInputStream ois = new ObjectInputStream(is);
				List<Good> goodlist = (List<Good>) ois.readObject();;
				for (Good good : goodlist) {
					System.out.println(good);
				}
				
				//�ر�
				socket.shutdownInput();
				break;
			}
		}//switch
		
		}//while
		
	}
	
	//����
	public static void showMenu(){
		System.out.println("===��Ʒ����ϵͳ===");
		System.out.println("===1-������Ʒ===");
		System.out.println("===2-ɾ����Ʒ===");
		System.out.println("===3-������Ʒ===");
		System.out.println("===4-��ѯ��Ʒ===");
		System.out.println("===5-��Ʒ�б�===");
		System.out.println("===0-�˳�ϵͳ===");
	}
}
