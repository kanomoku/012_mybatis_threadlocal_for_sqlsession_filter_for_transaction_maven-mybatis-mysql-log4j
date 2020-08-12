package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pojo.LogInfo;
import com.service.LogService;
import com.service.imp.LogServiceImpl;
@WebServlet("/inssssss")
public class LogServlet extends HttpServlet {
	LogService logService = new LogServiceImpl();

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		
		String accOut = req.getParameter("accOut");
		String accIn = req.getParameter("accIn");
		String money = req.getParameter("money");
		
		LogInfo loginfo = new LogInfo();
		loginfo.setAccOut(accOut);
		loginfo.setAccIn(accIn);
		loginfo.setMoney(Double.parseDouble(money));
		
		int index = logService.insert(loginfo);
		
		if (index > 0) {
			resp.sendRedirect("success.jsp");
		} else {
			resp.sendRedirect("error.jsp");
		}
	}

}
