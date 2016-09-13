package com.tianyl.rssfeed.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class RssFeedUtil {

	public static final String xmlDir = "/mnt/usb_1/logs/rssfeed/";

	public static String getRssDate(Date date) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss 'GMT'", Locale.ENGLISH);
		return sdf.format(date);
	}

}
