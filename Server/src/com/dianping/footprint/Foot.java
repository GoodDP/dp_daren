package com.dianping.footprint;

import java.util.List;
import java.util.ArrayList;

public class Foot {
	int id;
	String name;
	int userId;
	//List<Shop> shops = new ArrayList<Shop>();
	List<Double> weidu;
	List<Double> jingdu;
	List<String> shopNames;
	List<String> address;
	
	public int getId() {return id;}
	public void setId(int i) {id = i;}
	public int getUserId() {return userId;}
	public void setUserId(int u) {userId = u;}
	public String getName() {return name;}
	public void setName(String n) {name = n;}
	//public List<Shop> getShops() {return shops;}
	//public void setShops(List<Shop> s) {shops =  s;}
	public List<Double> getWeidu() {return weidu;}
	public void setWeidu(List<Double> w) {weidu=w;}
	public List<Double> getJingdu() {return jingdu;}
	public void setJingdu(List<Double> j) {jingdu = j;}
	public List<String> getShopName() {return shopNames;}
	public void setShopName(List<String> sn) {shopNames = sn;}
	public List<String> getAddress() {return address;}
	public void setAddress(List<String> a) {address = a;}
}
