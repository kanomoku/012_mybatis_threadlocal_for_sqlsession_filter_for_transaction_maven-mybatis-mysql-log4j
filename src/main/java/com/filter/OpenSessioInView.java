package com.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.apache.ibatis.session.SqlSession;

import com.util.MyBatisUtil;

@WebFilter("/*")
public class OpenSessioInView implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
//		InputStream resourceAsStream = Resources.getResourceAsStream("mybatis.xml");
//		SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resourceAsStream);
//		SqlSession session = build.openSession();
		SqlSession session = MyBatisUtil.getSession();
		System.out.println("opensession");
		try {
			chain.doFilter(request, response);
			session.commit();
			System.out.println("commit");
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
			System.out.println("roolback");
		} finally {
			MyBatisUtil.closeSession();
			System.out.println("closesession");
		}
//		session.commit();
//		session.close();
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

}
