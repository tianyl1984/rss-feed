package com.tianyl.rssfeed.dao;

import java.util.List;

import com.tianyl.core.ioc.annotation.Component;
import com.tianyl.core.orm.JdbcUtil;
import com.tianyl.rssfeed.model.RssSite;

@Component
public class RssSiteDAO {

	public List<RssSite> findAll() {
		return JdbcUtil.queryAll(RssSite.class);
	}

}
