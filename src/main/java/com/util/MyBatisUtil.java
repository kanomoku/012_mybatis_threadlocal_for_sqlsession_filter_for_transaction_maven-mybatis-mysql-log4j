package com.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisUtil {
	// factory 实例化的过程是一个很费性能的过程
	// 保证有且仅有一个factory
	private static SqlSessionFactory factory;
	private static ThreadLocal<SqlSession> tl = new ThreadLocal<>();
	static {
		try {
			InputStream resourceAsStream = Resources.getResourceAsStream("mybatis.xml");
			factory = new SqlSessionFactoryBuilder().build(resourceAsStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static SqlSession getSession() {
		SqlSession sin = tl.get();
		if (sin == null) {
			SqlSession session = factory.openSession();
			tl.set(session);
		}
		return tl.get();
	}

	public static void closeSession() {
		SqlSession sl = tl.get();
		if (sl != null) {
			sl.close();
		}
		tl.set(null);
	}
}
