package com.dianping.recommend;

import java.util.ArrayList;
import java.util.List;

public class User {
	int id;
	int score;
	String pic;
	String nicName;
	String location;
	List<String> labels = new ArrayList<String>();
	List<ShopRecommend> footList = new ArrayList<ShopRecommend>();
	
	public int getId(){return id;}
	public void setId(int i){id =i;}
	public int getScore(){return score;}
	public void setScore(int s) {score = s;}
	public String getPic() {return pic;}
	public void setPic(String p) {pic = p;}
	public String getNicName() {return nicName;}
	public void setNicName(String nn) {nicName = nn;}
	public String getLocation() {return location;}
	public void setLocation(String loc) {location = loc;}
	public List<String> getLabel() {return labels;}
	public void setLabel(List<String> l) {labels = l;}
	public List<ShopRecommend> getFootList(){return footList;}
	public void setFootList(List<ShopRecommend> fl){footList=fl;}
	
}
