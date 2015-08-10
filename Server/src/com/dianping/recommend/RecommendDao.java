package com.dianping.recommend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;


public class RecommendDao {
	
	private static RecommendDao fd = new RecommendDao();
	private RecommendDao()
	{
		init();
	}

	public static RecommendDao getInstance()
	{
		return fd;
	}
	private Connection connection  = null;
	private Statement statement  = null;
	final public static int indexUserNum = 10; 
	final public static int darenScore = 5000;
	
	private void init(){
		if (connection==null || statement==null)
		{
			try {
				Class.forName("com.mysql.jdbc.Driver");
			
			connection = DriverManager.getConnection
					("jdbc:mysql://localhost/dianping", "root", "xurong");
				
			statement = connection.createStatement();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else;
		
	}
	
	public List<User> queryUserByRank(){
		List<User> users = new ArrayList<User>();
		try {
		
		ResultSet resultset = statement.executeQuery
				("select * from user u where u.score>="+darenScore);
		
		while (resultset.next()){
			User user = new User();
			int uid = resultset.getInt("id");
			user.setId(uid);
			user.setScore(resultset.getInt("score"));
			//没有头像，根据用户id生成头像
			String pic_url = resultset.getString("pic");
			if(pic_url==null) pic_url = "photos/PIC_10"+String.format("%02d", uid%23+1)+".jpg";
			user.setPic(pic_url);
			user.setNicName(resultset.getString("nicname"));
			user.setLocation(resultset.getString("location"));
			String label = resultset.getString("label");
			
			if(label!=null)
			{
				String[] labels = label.split(",");
				List<String> labList = new ArrayList<String>();
				for (String lab :labels){
					labList.add(lab);
				}
				user.setLabel(labList);
			}
			users.add(user);
		}
		
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
		//String sts = "select * from shop s where s.label0 like '%"+label+"%' or s.label1 like '%"+label+"%' or s.label2 like '%"+label+"%' or s.label3 like '%"+label+"%' or s.shopname like '%"+label+"%';";
		//System.out.println(label);
		ResultSet resultset = statement.executeQuery
				("select * from shop s where s.label0 like '%"+label+"%' or s.label1 like '%"+label+"%' or s.label2 like '%"+label+"%' or s.label3 like '%"+label+"%' or s.shopname like '%"+label+"%';");
		
		while (resultset.next()){
			ShopRecommend sr = new ShopRecommend();
			sr.setShopId(resultset.getInt("id"));
			sr.setShopName(resultset.getString("shopname"));
			sr.setAddress(resultset.getString("address"));
			sr.setPic(resultset.getString("pic"));
			sr.setRecommendNum(resultset.getInt("recommendnum"));
			sr.setShopURL(resultset.getString("URL"));
			List<String> rs = new ArrayList<String>();
			rs.add(resultset.getString("comment1"));
			rs.add(resultset.getString("comment2"));
			rs.add(resultset.getString("comment3"));
			sr.setReviews(rs);
			SRs.add(sr);
		}
		
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
		
		//System.out.println(index+"-"+size+"-"+SRs.size());
		if (index+size<SRs.size())
			return SRs.subList(index, index+size);
		else
			return SRs.subList(index, SRs.size());
	}
	
	public User getUserById(int id){
		User user = new User();
		try {
		
		ResultSet resultset = statement.executeQuery
				("select * from user u where u.id="+id);
		
		if (resultset.next()){
			int uid = resultset.getInt("id");
			user.setId(uid);
			user.setScore(resultset.getInt("score"));
			
			//没有头像，根据用户id生成头像
			String pic_url = resultset.getString("pic");
			if(pic_url==null) pic_url = "photos/PIC_10"+String.format("%02d", uid%23+1)+".jpg";
			user.setPic(pic_url);
			
			user.setNicName(resultset.getString("nicname"));
			user.setLocation(resultset.getString("location"));
			String label = resultset.getString("label");
			if (label!=null){
				String[] labels = label.split(",");
				List<String> labList = new ArrayList<String>();
				for (String lab :labels){
					labList.add(lab);
				}
				user.setLabel(labList);
			}
			ResultSet res = statement.executeQuery
					("select f.id,f.footname from userfoot u, foot f where u.uid="+id+" and u.fid=f.id");
			while (res.next()){
				user.footIds.add(res.getInt("id"));
				user.footNames.add(res.getString("footname"));
			}
		}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return user;
	}
	
	public List<User> getUserByShopId(int shopId){
		List<User> SRs = new ArrayList<User>();
		try {
		ResultSet resultset = statement.executeQuery
				("select u.* from user u,review r where r.shopid="+shopId+" and "+"r.userid=u.id");
		
		while (resultset.next()){
			User sr = new User();
			int uid = resultset.getInt("id");
			sr.setId(uid);
			sr.setScore(resultset.getInt("score"));
			sr.setNicName(resultset.getString("nicname"));
			
			//没有头像，根据用户id生成头像
			String pic_url = resultset.getString("pic");
			if(pic_url==null) pic_url = "photos/PIC_10"+String.format("%02d", uid%23+1)+".jpg";
			sr.setPic(pic_url);
			sr.setLocation(resultset.getString("location"));
			
			Statement statement0 = connection.createStatement();
			ResultSet result = statement0.executeQuery
					("select comment1,comment2,comment3 from shop where id="+shopId);
			if (result.next()){
				List<String> srreviews = new ArrayList<String>();
				srreviews.add(result.getString("comment1"));
				srreviews.add(result.getString("comment2"));
				srreviews.add(result.getString("comment3"));
				sr.setReviews(srreviews);	
			}
			SRs.add(sr);
		}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (SRs.size()==0)
			return new ArrayList<User>();
		
		if (SRs.size()>3)
			return SRs.subList(0, 3);
		
		return SRs;
	}
	
	public ShopRecommend getShopById(int id){
		ShopRecommend sr = new ShopRecommend();
		try {
		
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
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sr;
	}
	
	
}
