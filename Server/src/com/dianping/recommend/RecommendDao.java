package com.dianping.recommend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RecommendDao {
	private Connection connection ;
	private Statement statement ;
	final public static int indexUserNum = 10; 
	final public static int darenScore = 5000;
	
	
	public List<User> queryUserByRank(){
		List<User> users = new ArrayList<User>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
		
		connection = DriverManager.getConnection
				("jdbc:mysql://localhost/dianping", "root", "xurong");
			
		statement = connection.createStatement();
		
		
		ResultSet resultset = statement.executeQuery
				("select * from user u where u.score>="+darenScore);
		
		while (resultset.next()){
			User user = new User();
			user.setId(resultset.getInt("id"));
			user.setScore(resultset.getInt("score"));
			user.setPic(resultset.getString("pic"));
			user.setNicName(resultset.getString("nicname"));
			user.setLocation(resultset.getString("location"));
			String label = resultset.getString("label");
			String[] labels = label.split(",");
			List<String> labList = new ArrayList<String>();
			for (String lab :labels){
				labList.add(lab);
			}
			user.setLabel(labList);
			users.add(user);
		}
		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (users.size()==0)
			return new ArrayList<User>();
		
		for (int i=users.size()-1;i>0;i--)
			for (int j=0;j<i;j++)
			{
				if (users.get(j).score<users.get(j+1).score)
				{
					User tempU = users.get(j);
					users.set(j, users.get(j+1));
					users.set(j+1,tempU);
				}
			}
		
		if (users.size()>indexUserNum)
			users.subList(0, indexUserNum);
		
		return users;
	}     
	
	public List<ShopRecommend> getShopByLabel(String label,int page,int size){
		
		List<ShopRecommend> SRs = new ArrayList<ShopRecommend>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
		
		connection = DriverManager.getConnection
				("jdbc:mysql://localhost/dianping", "root", "xurong");
			
		statement = connection.createStatement();
		
		ResultSet resultset = statement.executeQuery
				("select * from shop s where s.label0="+label+" or "+"s.label1="+label+" or "+"s.label2="+label+" or "+"s.label3="+label);
		
		while (resultset.next()){
			ShopRecommend sr = new ShopRecommend();
			sr.setShopId(resultset.getInt("id"));
			sr.setShopName(resultset.getString("shopname"));
			sr.setAddress(resultset.getString("address"));
			sr.setPic(resultset.getString("pic"));
			sr.setRecommendNum(resultset.getInt("recommendnum"));
			sr.setShopURL(resultset.getString("URL"));
			
			SRs.add(sr);
		}
		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (SRs.size()==0)
			return new ArrayList<ShopRecommend>();
		
		for (int i=SRs.size()-1;i>0;i--)
			for (int j=0;j<i;j++)
			{
				if (SRs.get(j).recommendNum<SRs.get(j+1).recommendNum)
				{
					ShopRecommend tempU = SRs.get(j);
					SRs.set(j, SRs.get(j+1));
					SRs.set(j+1,tempU);
				}
			}
		int index = (page-1)*size;
		if (index>=SRs.size())
			return new ArrayList<ShopRecommend>();
		
		if (index+size<SRs.size())
			SRs.subList(index, index+size-1);
		else
			SRs.subList(index, SRs.size()-1);
		return SRs;
	}
	
	public User getUserById(int id){
		User user = new User();
		try {
			Class.forName("com.mysql.jdbc.Driver");
		
		connection = DriverManager.getConnection
				("jdbc:mysql://localhost/dianping", "root", "xurong");
			
		statement = connection.createStatement();
		
		
		ResultSet resultset = statement.executeQuery
				("select * from user u where u.id="+id);
		
		while (resultset.next()){
			user.setId(resultset.getInt("id"));
			user.setScore(resultset.getInt("score"));
			user.setPic(resultset.getString("pic"));
			user.setNicName(resultset.getString("nicname"));
			user.setLocation(resultset.getString("location"));
			String label = resultset.getString("label");
			String[] labels = label.split(",");
			List<String> labList = new ArrayList<String>();
			for (String lab :labels){
				labList.add(lab);
			}
			user.setLabel(labList);
			ResultSet res = statement.executeQuery
					("select shopid from userfoot u, foot f where u.id="+id);
			
			while (res.next()){
				ShopRecommend sr = getShopById(res.getInt("id"));
				user.footList.add(sr);
			}
		}
		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return user;
	}
	
	public ShopRecommend getShopById(int id){
		ShopRecommend sr = new ShopRecommend();
		try {
			Class.forName("com.mysql.jdbc.Driver");
		
		connection = DriverManager.getConnection
				("jdbc:mysql://localhost/dianping", "root", "xurong");
			
		statement = connection.createStatement();
		
		ResultSet resultset = statement.executeQuery
				("select * from shop s where s.id="+id);
		
		while (resultset.next()){
			
			sr.setShopId(resultset.getInt("id"));
			sr.setShopName(resultset.getString("shopname"));
			sr.setAddress(resultset.getString("address"));
			sr.setPic(resultset.getString("pic"));
			sr.setRecommendNum(resultset.getInt("recommendnum"));
			sr.setShopURL(resultset.getString("URL"));
		}
		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sr;
	}
	
	public List<ShopRecommend> getUserByShopId(int shopId){
		List<ShopRecommend> SRs = new ArrayList<ShopRecommend>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
		
		connection = DriverManager.getConnection
				("jdbc:mysql://localhost/dianping", "root", "xurong");
			
		statement = connection.createStatement();
		
		ResultSet resultset = statement.executeQuery
				("select u from user u,recommend r where r.shopid="+shopId+" and "+"r.uid=u.id");
		
		while (resultset.next()){
			ShopRecommend sr = new ShopRecommend();
			sr.setShopId(resultset.getInt("id"));
			sr.setShopName(resultset.getString("shopname"));
			sr.setAddress(resultset.getString("address"));
			sr.setPic(resultset.getString("pic"));
			sr.setRecommendNum(resultset.getInt("recommendnum"));
			sr.setShopURL(resultset.getString("URL"));
			
			SRs.add(sr);
		}
		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (SRs.size()==0)
			return new ArrayList<ShopRecommend>();
		
		for (int i=SRs.size()-1;i>0;i--)
			for (int j=0;j<i;j++)
			{
				if (SRs.get(j).recommendNum<SRs.get(j+1).recommendNum)
				{
					ShopRecommend tempU = SRs.get(j);
					SRs.set(j, SRs.get(j+1));
					SRs.set(j+1,tempU);
				}
			}
		
		return SRs;
	}
}
