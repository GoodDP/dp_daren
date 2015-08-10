package com.dianping.footprint;

public class Shop {
	int id;
	int recommendNum;
	String shopName;
	String address;
	String pic;
	String shopURL;
	double jingdu;
	double weidu;
	
	public int getShopId(){return id;}
	public void setShopId(int sid){id = sid;}
	public int getRecommendNum() {return recommendNum;}
	public void setRecommendNum(int rnum) {recommendNum = rnum;}
	public String getShopName() {return shopName;}
	public void setShopName(String sn) {shopName = sn;}
	public String getAddress() {return address;}
	public void setAddress(String a){address = a;}
	public String getPic(){return pic;}
	public void setPic(String p) {pic = p;}
	public String getShopURL(){return shopURL;}
	public void setShopURL(String su){shopURL = su;}
	public double getJingdu() {return jingdu;}
	public void setJingdu(double jd) {jingdu = jd;}
	public double getWeidu() {return weidu;}
	public void setWeidu(double wd) {weidu = wd;}
}
