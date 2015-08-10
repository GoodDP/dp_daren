package com.dianping.servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.dianping.recommend.RecommendDao;
import com.dianping.recommend.ShopRecommend;
import com.dianping.recommend.User;

public class UserInfo extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse resp)
			throws ServletException, IOException {
		//请求查询的标签label，第page页(1开始)，每页size个
		int id = Integer.valueOf(request.getParameter("id"));
		
        OutputStream ps = resp.getOutputStream();
        RecommendDao rd = RecommendDao.getInstance();
        //System.out.println("搜索结果：label-"+label+",page-"+page+",size-"+size);
        User user = rd.getUserById(id);
        
		JSONArray ja = JSONArray.fromObject(user);
		
		resp.setHeader("Content-type", "text/html;charset=UTF-8");
		resp.setCharacterEncoding("UTF-8");
		ps.write(ja.toString().getBytes("UTF-8"));
		ps.flush();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
