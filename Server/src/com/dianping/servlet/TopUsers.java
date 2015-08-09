package com.dianping.servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dianping.recommend.RecommendDao;
import com.dianping.recommend.User;

import net.sf.json.JSONArray;


//点评达人前10名
public class TopUsers extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
        OutputStream ps = resp.getOutputStream(); 
		
		RecommendDao rd = new RecommendDao();
		List<User> users = rd.queryUserByRank();
		
		JSONArray ja = JSONArray.fromObject(users);
		
		resp.setHeader("Content-type", "text/html;charset=UTF-8");
		resp.setCharacterEncoding("UTF-8");
		ps.write(ja.toString().getBytes("UTF-8"));
		ps.flush();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

}
