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
	List<Integer> footIds;
	List<String> footNames;
	
	public User(){
		labels = new ArrayList<String>();
		footIds = new ArrayList<Integer>();
		footNames = new ArrayList<String>();
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
	public List<Integer> getFootIds() {return footIds;}
	public void setFootIds(List<Integer> fi) {footIds = fi;}
	public List<String> getFootName() {return footNames;}
	public void setFootName(List<String> fn) {footNames = fn;}
	
}
