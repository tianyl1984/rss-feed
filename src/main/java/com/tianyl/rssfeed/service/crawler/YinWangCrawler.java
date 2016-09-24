package com.tianyl.rssfeed.service.crawler;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.tianyl.core.util.DateUtil;
import com.tianyl.core.util.JsoupUtil;
import com.tianyl.rssfeed.model.ArticleItem;
import com.tianyl.rssfeed.model.RssSite;

public class YinWangCrawler implements Crawler {

	@Override
	public Integer getRssSiteId() {
		return 2;
	}

	@Override
	public List<ArticleItem> parseItem(String html, RssSite rssSite) {
		List<ArticleItem> articleItems = new ArrayList<>();
		Document docu = Jsoup.parse(html);
		Elements eles = docu.getElementsByClass("list-group-item");
		for (int i = 0; i < eles.size(); i++) {
			// <li class="list-group-item title">
			// <a href="http://yinwang.org/blog-cn/2016/09/18/rust">对 Rust
			// 语言的分析</a>
			// </li>
			Element ele = eles.get(i);
			String title = ele.text().trim();
			String url = JsoupUtil.getValue(ele, "a[href]");
			String dateStr = url.substring(url.indexOf("blog-cn") + 8, url.indexOf("blog-cn") + 8 + 10);
			ArticleItem item = new ArticleItem();
			item.setTitle(title);
			item.setCategory("all");
			item.setDescription(title);
			item.setGuid(url);
			item.setLink(url);
			item.setPubDate(DateUtil.parseDate(dateStr));
			item.setTitle(title);

			articleItems.add(item);
		}
		return articleItems;
	}

}
