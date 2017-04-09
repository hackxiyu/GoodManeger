package com.xr.server.dao.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.xr.bean.Good;
import com.xr.server.dao.GoodDao;

public class GoodDaoImpl implements GoodDao{
	
	//ʵ���������ļ���xmlFun
	File f = new File("db/good.xml");
	XmlFun xf = new XmlFun(f);
	
	@Override
	public int addGood(Good good) {
		//���巵�ؽ��
		int result = 0;
		try {
			//��ȡxml,ɾ��ָ��Ԫ��
			Document document = xf.toDocument();
			Element root = document.getRootElement();
			
			//�½�
			Element goodEle = DocumentHelper.createElement("good");
			
			Element idEle = DocumentHelper.createElement("id");
			idEle.setText(good.getId()+"");
			Element nameEle = DocumentHelper.createElement("name");
			nameEle.setText(good.getName());
			Element priceEle = DocumentHelper.createElement("price");
			priceEle.setText(good.getPrice()+"");
			
			goodEle.add(idEle);
			goodEle.add(nameEle);
			goodEle.add(priceEle);
			root.add(goodEle);
			
			xf.saveXml(document);
			result = 1;
			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public int deleteGood(int id) {
		//��ʼ������ֵ
		int result = 0;
		try {
			//��ȡxml,ɾ��ָ��Ԫ��
			Document document = xf.toDocument();
			Element root = document.getRootElement();
			List<Element> elements = root.elements();
			for (Element element : elements) {
				List<Element> childs = element.elements();
				for (Element child : childs) {
					if(child.getName().equals("id")&&
							child.getText().equals(id+"")){
						root.remove(element);
						result = 1;
					}
				}
			}
			//����
			xf.saveXml(document);
			
			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public int updateGood(Good good) {
		//��ʼ������ֵ
		int result = 0;
		//ɾ��������
		result = deleteGood(good.getId());
		if(result == 1){
			addGood(good);
		}
		
		return result;
	}

	@Override
	public Good findGood(int id) {
		
		//ʵ��������
		Good good = new Good();
		
		try {
			//��ȡxmlΪlist
			List<Good> goodList = xf.toList();
			for (Good g : goodList) {
				if(g.getId() == id){
					good = g;
				}
			}
			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return good;
		
	}

	@Override
	public List<Good> allGood() {
		
		//��ȡxmlΪlist
		List<Good> goodList = null;
		
		try {
			//��ȡxmlΪlist
			goodList = xf.toList();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return goodList;
	}
	

}
