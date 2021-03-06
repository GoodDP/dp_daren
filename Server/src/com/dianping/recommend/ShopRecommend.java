package com.dianping.recommend;

import java.util.List;
import java.util.ArrayList;

public class ShopRecommend {
	int shopId;
	int recommendNum;
	String shopName;
	String address;
	String pic;
	String shopURL;
	double jingdu;
	double weidu;
	List<String> reviews;
	
	public ShopRecommend(){
		reviews = new ArrayList<String>();
	}
	
	public int getShopId(){return shopId;}
	public void setShopId(int sid){shopId = sid;}
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
	public List<String> getReviews(){return reviews;}
	public void setReviews(List<String> uid){reviews = uid;}
}
