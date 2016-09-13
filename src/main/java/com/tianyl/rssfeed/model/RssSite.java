package com.tianyl.rssfeed.model;

import com.tianyl.core.orm.annotation.Table;

@Table("rss_site")
public class RssSite {

	private Integer id;

	private String name;

	private String url;

	private String desc;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
