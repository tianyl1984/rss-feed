package com.tianyl.rssfeed.service;

import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.tianyl.core.ioc.ApplicationContext;
import com.tianyl.core.util.io.IOUtils;
import com.tianyl.core.util.log.LogManager;
import com.tianyl.core.util.webClient.RequestResult;
import com.tianyl.core.util.webClient.WebUtil;
import com.tianyl.rssfeed.dao.RssSiteDAO;
import com.tianyl.rssfeed.model.ArticleItem;
import com.tianyl.rssfeed.model.RssSite;
import com.tianyl.rssfeed.service.crawler.Crawler;
import com.tianyl.rssfeed.service.crawler.HexoCrawler;
import com.tianyl.rssfeed.service.crawler.YinWangCrawler;
import com.tianyl.rssfeed.util.RssFeedUtil;

public class RssCrawlerManager {

	public static void crawl() {
		RssSiteDAO rssSiteDAO = ApplicationContext.getBean(RssSiteDAO.class);
		List<RssSite> rssSites = rssSiteDAO.findAll();
		List<Crawler> crawlers = new ArrayList<Crawler>();
		crawlers.add(new HexoCrawler());
		crawlers.add(new YinWangCrawler());
		for (Crawler crawler : crawlers) {
			Integer rsId = crawler.getRssSiteId();
			RssSite rs = findRs(rssSites, rsId);
			LogManager.log("start request webpage.url:" + rs.getUrl());
			RequestResult request = WebUtil.getUrlResponse(rs.getUrl(), null, null, true);
			if (!request.isOk()) {
				LogManager.log("request webpage fail:" + rs.getUrl());
				continue;
			}
			String html = request.getResultStr();
			LogManager.log("start parse webpage");
			List<ArticleItem> articleItems = crawler.parseItem(html, rs);
			String xml = assembleRssXml(rs, articleItems);
			String targetPath = RssFeedUtil.xmlDir + rs.getId() + ".xml";
			LogManager.log("start save xml file to " + targetPath);
			IOUtils.saveToFile(xml.getBytes(Charset.forName("utf-8")), new File(targetPath));
		}
	}

	private static RssSite findRs(List<RssSite> rssSites, Integer rsId) {
		for (RssSite rs : rssSites) {
			if (rs.getId().equals(rsId)) {
				return rs;
			}
		}
		return null;
	}

	private static String assembleRssXml(RssSite rs, List<ArticleItem> articleItems) {
		StringBuilder xmlBuilder = new StringBuilder();
		xmlBuilder.append("<rss version=\"2.0\">");
		xmlBuilder.append("<channel>");
		xmlBuilder.append("<title>" + rs.getName() + "</title>");
		xmlBuilder.append("<link>" + rs.getUrl() + "</link>");
		xmlBuilder.append("<description>" + rs.getDesc() + "</description>");
		xmlBuilder.append("<language>zh-CN</language>");
		xmlBuilder.append("<pubDate>" + RssFeedUtil.getRssDate(new Date()) + "</pubDate>");
		for (ArticleItem ai : articleItems) {
			xmlBuilder.append(ai.toXml());
		}
		xmlBuilder.append("</channel>");
		xmlBuilder.append("</rss>");
		return xmlBuilder.toString();
	}

	public static void main(String[] args) {
		LogManager.LOGGER_NAME = "rsslog";
		RssCrawlerManager.crawl();
	}
}
