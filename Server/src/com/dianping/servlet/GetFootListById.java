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

import com.dianping.footprint.Foot;
import com.dianping.footprint.FootPrintDAO;
import com.dianping.footprint.Shop;
import com.dianping.recommend.RecommendDao;
import com.dianping.recommend.User;

public class GetFootListById extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse resp)
			throws ServletException, IOException {
		//请求查询的标签label，第page页(1开始)，每页size个
		int footId = Integer.valueOf(request.getParameter("footId"));
		
        OutputStream ps = resp.getOutputStream();
        FootPrintDAO fd = FootPrintDAO.getInstance();
        //System.out.println("搜索结果：label-"+label+",page-"+page+",size-"+size);
        Foot foot= fd.getFootByFootId(footId);
        //JSONArray jashops = JSONArray.fromObject(foot.getShops());
		JSONArray ja = JSONArray.fromObject(foot);
		
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
