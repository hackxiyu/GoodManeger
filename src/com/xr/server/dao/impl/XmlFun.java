package com.xr.server.dao.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.xr.bean.Good;


public class XmlFun {
	//属性
	private File file;
	//访问和构造
	public XmlFun() {
		super();
		// TODO Auto-generated constructor stub
	}
	public XmlFun(File file) {
		super();
		this.file = file;
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	
	
	//解析xml
	public Document toDocument() throws DocumentException{
		//创建解析器
		SAXReader sr = new SAXReader();
		Document document = sr.read(this.file);
		
		return document;
	}
	
	//xml保存格式
	public void saveXml(Document document) throws IOException{
		//输出流
		FileOutputStream fos = new FileOutputStream(this.file);
		
		OutputFormat format = new OutputFormat();
		format.setEncoding("GBK");
		format.setNewlines(true);
		format.setIndentSize(4);
		
		XMLWriter xw = new XMLWriter(fos, format);
		xw.write(document);
		
		//关闭
		fos.close();
		xw.close();
	}
	
	//转list
	public List<Good> toList() throws DocumentException{
		//新建商品列表
		List<Good> goodList = new ArrayList<Good>();
		
		//解析
		Document document = this.toDocument();
		Element root = document.getRootElement();
		List<Element> goodEles = root.elements();
		for (Element goodEle : goodEles) {
			List<Element> childs = goodEle.elements();
			Good good = new Good();
			for (Element child : childs) {
				if(child.getName().equals("id")){
					int id = Integer.parseInt(child.getText());
					good.setId(id);
				}
				if(child.getName().equals("name")){
					String name = child.getText();
					good.setName(name);
				}
				if(child.getName().equals("price")){
					Double price = Double.parseDouble(child.getText());
					good.setPrice(price);
				}
			}
			goodList.add(good);
		}
		
		return goodList;
		
		
	}
		
		
}
