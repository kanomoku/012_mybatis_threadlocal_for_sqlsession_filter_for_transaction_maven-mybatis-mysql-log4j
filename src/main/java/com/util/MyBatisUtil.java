package com.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisUtil {
	// 3. factory实例化的过程是一个比较费性能的过程，做项目的时候都是保证有且仅有一个factory，因为没必要频繁创建工厂
	private static SqlSessionFactory factory; //2.静态代码块调用的东西也是静态的，所以这里是 static
	private static ThreadLocal<SqlSession> tl = new ThreadLocal<>();
	// 1. 希望类在加载的时候就会产生factory对象，所以用静态块
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
