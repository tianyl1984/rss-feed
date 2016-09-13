package com.tianyl.rssfeed.web;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tianyl.core.mvc.annotation.Controller;
import com.tianyl.core.util.io.FileUtil;
import com.tianyl.core.util.log.LogManager;
import com.tianyl.rssfeed.util.RssFeedUtil;

@Controller("rss")
public class RssController {

	public void feed(Integer id, HttpServletRequest request, HttpServletResponse response) throws IOException {
		LogManager.log("request rss id:" + id);
		String filePath = RssFeedUtil.xmlDir + id + ".xml";
		if (!new File(filePath).exists()) {
			throw new RuntimeException("rss not exist");
		}
		String str = FileUtil.read(new File(filePath));
		response.getWriter().write(str);
	}

}
