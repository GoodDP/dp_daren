package com.dianping.recommend;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class User {
	int id;
	int score;
	String pic;
	String nicName;
	String location;
	List<String> labels;
	List<Map<String,Object>> footList;
	
	public User(){
		labels = new ArrayList<String>();
		footList = new ArrayList<Map<String,Object>>();
	}
	
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
	public List<Map<String,Object>> getFootList(){return footList;}
	public void setFootList(List<Map<String,Object>> fl){footList=fl;}
	
}
