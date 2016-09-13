package com.tianyl.rssfeed.web;

import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.tianyl.core.util.log.LogManager;
import com.tianyl.rssfeed.service.RssCrawlerManager;

@WebListener
public class RssFeedContextListener implements ServletContextListener {

	private Timer timer = new Timer();

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				LogManager.log("start read feed");
				try {
					RssCrawlerManager.crawl();
				} catch (Exception e) {
					e.printStackTrace();
					LogManager.log("crawl error");
					LogManager.log(e);
				}
				LogManager.log("end read feed");
			}
		}, 5 * 1000, 1000 * 60 * 60 * 10);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		timer.cancel();
	}

}
