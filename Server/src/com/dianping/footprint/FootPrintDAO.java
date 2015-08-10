package com.dianping.footprint;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FootPrintDAO {
	private static FootPrintDAO fd = new FootPrintDAO();
	private FootPrintDAO()
	{
		init();
	}

	public static FootPrintDAO getInstance()
	{
		return fd;
	}
	
	private Connection connection = null;
	private Statement statement = null;

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

	public Foot getFootByFootId(int id){
		init();
		Foot foot = new Foot();
		try {
			ResultSet resultset = statement.executeQuery
					("select * from foot where id="+id);

			while (resultset.next()){
				foot.setId(id);
				foot.setName(resultset.getString("footname"));
				Statement statement0 = connection.createStatement();
				ResultSet res = statement0.executeQuery
						("select * from footshop where fid="+id);
				List<Shop> shops = new ArrayList<Shop>();
				List<Double> weidu = new ArrayList<Double>();
				List<Double> jingdu = new ArrayList<Double>();
				while (res.next()){
					Shop shop = getShopById(res.getInt("shopid"));
					weidu.add(shop.getWeidu());
					jingdu.add(shop.getJingdu());
					//shops.add(res.getInt("priority"), shop);
				}
				foot.setJingdu(jingdu);
				foot.setWeidu(weidu);
				//foot.setShops(shops);
				;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return foot;
	}

	public List<Shop> getShopByUserId(int userId,int offset,int limit){
		init();
		List<Shop> shops = new ArrayList<Shop>();
		try {
			ResultSet resultset = statement.executeQuery
					("select shopid from review where userid="+userId+" limit "+offset+" , "+limit);

			while (resultset.next()){
				Shop shop = getShopById(resultset.getInt("shopid"));
				shops.add(shop);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return shops;
	}

	//0-success
	public int addFootList(int userId,String footname,List<Integer> shopId){
		init();
		try {

			statement.executeUpdate("insert into foot value(footname)");
			ResultSet resultset = statement.executeQuery
					("select max(id) from foot");
			int fid;
			if (resultset.next())
			{
				fid = resultset.getInt("id");
			}else return 0;

			int p=0;
			for (Integer sid : shopId){
				statement.executeUpdate("insert into footshop value("+fid+","+sid+","+p +")");
				p++;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		return 1;
	}

	private Shop getShopById(int id){
		Shop sr = new Shop();
		init();
		try {
			Statement statement0 = connection.createStatement();
			ResultSet resultset = statement0.executeQuery
					("select * from shop s where s.id="+id);
System.out.println("select * from shop s where s.id="+id);
			while (resultset.next()){

				sr.setShopId(resultset.getInt("id"));
				sr.setShopName(resultset.getString("shopname"));
				sr.setAddress(resultset.getString("address"));
				sr.setPic(resultset.getString("pic"));
				sr.setRecommendNum(resultset.getInt("recommendnum"));
				sr.setShopURL(resultset.getString("URL"));
				sr.setJingdu(resultset.getDouble("jingdu"));
				System.out.println(resultset.getDouble("jingdu"));
				sr.setWeidu(resultset.getDouble("weidu"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sr;
	}


}
