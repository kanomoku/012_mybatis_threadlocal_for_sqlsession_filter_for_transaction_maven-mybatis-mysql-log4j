package com.service.imp;

import org.apache.ibatis.session.SqlSession;

import com.mapper.LogMapper;
import com.pojo.LogInfo;
import com.service.LogService;
import com.util.MyBatisUtil;

public class LogServiceImpl implements LogService {

	@Override
	public int insert(LogInfo loginfo) {
		SqlSession session = MyBatisUtil.getSession();
		LogMapper logmapper = session.getMapper(LogMapper.class);
		int index = logmapper.ins(loginfo);
		return index;
	}
}
