package com.tianyl.rssfeed.model;

import java.util.Date;

import com.tianyl.rssfeed.util.RssFeedUtil;

public class ArticleItem {

	private String title;

	private String link;

	private String category;

	private String description;

	private Date pubDate;

	private String guid;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getPubDate() {
		return pubDate;
	}

	public void setPubDate(Date pubDate) {
		this.pubDate = pubDate;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String toXml() {
		StringBuilder xml = new StringBuilder();
		xml.append("<item>");
		xml.append("<title><![CDATA[%s]]></title>");
		xml.append("<link>%s</link>");
		xml.append("<category>%s</category>");
		xml.append("<description><![CDATA[%s]]></description>");
		xml.append("<pubDate>%s</pubDate>");
		xml.append("<guid>%s</guid>");
		xml.append("</item>");
		return String.format(xml.toString(), title, link, category, description, RssFeedUtil.getRssDate(pubDate), guid);
	}

}
