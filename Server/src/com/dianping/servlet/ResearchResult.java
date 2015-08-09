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

public class ResearchResult extends HttpServlet
{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse resp)
			throws ServletException, IOException {
		//请求查询的标签label，第page页(1开始)，每页size个
		String label = request.getParameter("label");
		int page = Integer.valueOf(request.getParameter("page"));
		int size = Integer.valueOf(request.getParameter("size"));
		
		label = new String(label.getBytes("iso8859-1"),"UTF-8");
		
        OutputStream ps = resp.getOutputStream();
        RecommendDao rd = RecommendDao.getInstance();
        System.out.println("搜索结果：label-"+label+",page-"+page+",size-"+size);
        List<ShopRecommend> shopRecommends = rd.getShopByLabel(label, page, size);
		
		JSONArray ja = JSONArray.fromObject(shopRecommends);
		
		resp.setHeader("Content-type", "text/html;charset=UTF-8");
		resp.setCharacterEncoding("UTF-8");
		ps.write(ja.toString().getBytes("UTF-8"));
		ps.flush();
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
