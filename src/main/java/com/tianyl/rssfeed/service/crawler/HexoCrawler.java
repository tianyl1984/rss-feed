package com.tianyl.rssfeed.service.crawler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.tianyl.core.util.DateUtil;
import com.tianyl.core.util.JsoupUtil;
import com.tianyl.rssfeed.model.ArticleItem;
import com.tianyl.rssfeed.model.RssSite;

public class HexoCrawler implements Crawler {

	public List<ArticleItem> parseItem(String html, RssSite rs) {
		Document docu = Jsoup.parse(html);
		Elements articleEles = docu.getElementsByTag("article");
		List<ArticleItem> result = new ArrayList<ArticleItem>();
		for (int index = 0; index < articleEles.size(); index++) {
			ArticleItem item = new ArticleItem();
			Element artiEle = articleEles.get(index);
			String title = JsoupUtil.getTagValue(artiEle, "h1");
			String tempUrl = JsoupUtil.getValue(artiEle, "h1 a[href]");
			String link = rs.getUrl() + tempUrl;
			String category = JsoupUtil.getValue(artiEle, ".post-category");
			if (category != null) {
				category = category.replace("|", "").replace("分类于", "").trim();
			}
			Element bodyEle = JsoupUtil.getByClass(artiEle, "post-body");
			String description = bodyEle == null ? "" : bodyEle.html();
			String guid = link;
			if (tempUrl.length() >= 11) {
				Date pubDate = DateUtil.parseDate(tempUrl.substring(1, 11));
				item.setPubDate(pubDate);
			}
			item.setCategory(category);
			item.setDescription(description);
			item.setGuid(guid);
			item.setTitle(title);
			item.setLink(link);
			result.add(item);
		}
		return result;
	}

	public Integer getRssSiteId() {
		return 1;
	}
}
