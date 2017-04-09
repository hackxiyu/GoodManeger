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
	
	//实例化保存文件和xmlFun
	File f = new File("db/good.xml");
	XmlFun xf = new XmlFun(f);
	
	@Override
	public int addGood(Good good) {
		//定义返回结果
		int result = 0;
		try {
			//读取xml,删除指定元素
			Document document = xf.toDocument();
			Element root = document.getRootElement();
			
			//新建
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
		//初始化返回值
		int result = 0;
		try {
			//读取xml,删除指定元素
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
			//保存
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
		//初始化返回值
		int result = 0;
		//删除后增加
		result = deleteGood(good.getId());
		if(result == 1){
			addGood(good);
		}
		
		return result;
	}

	@Override
	public Good findGood(int id) {
		
		//实例化对象
		Good good = new Good();
		
		try {
			//读取xml为list
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
		
		//读取xml为list
		List<Good> goodList = null;
		
		try {
			//读取xml为list
			goodList = xf.toList();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return goodList;
	}
	

}
