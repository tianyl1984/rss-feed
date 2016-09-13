package com.tianyl.rssfeed.service.crawler;

import java.util.List;

import com.tianyl.rssfeed.model.ArticleItem;
import com.tianyl.rssfeed.model.RssSite;

public interface Crawler {

	public Integer getRssSiteId();

	public List<ArticleItem> parseItem(String html, RssSite rssSite);

}
