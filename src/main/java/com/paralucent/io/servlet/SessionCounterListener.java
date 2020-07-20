package com.paralucent.io.servlet;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionCounterListener implements HttpSessionListener {
	private static long onlineNumber = 0;
	private ServletContext servletContext = null;

	public static long getOnlineNumber() {
//		System.out.println("CounterListener: \n取得線上人數=" + onlineNumber);
		return onlineNumber;
	}

	public SessionCounterListener() {
		super();
		// TODO Auto-generated constructor stub

	}

	public void sessionCreated(HttpSessionEvent se) {
		if (servletContext == null) {
			servletContext = se.getSession().getServletContext();
		}
		onlineNumber++;
		String sessionId = se.getSession().getId();
		int maxII = se.getSession().getMaxInactiveInterval();
		long ct = se.getSession().getCreationTime();
		long lacct = se.getSession().getLastAccessedTime();
		printInfomation(se, sessionId, maxII, ct, lacct, null, "Session已被創建");
	}

	public void sessionDestroyed(HttpSessionEvent se) {
		if (servletContext == null) {
			servletContext = se.getSession().getServletContext();
		}
		if (onlineNumber > 0) {
			onlineNumber--;
		}
		String sessionId = se.getSession().getId();
		int maxII = se.getSession().getMaxInactiveInterval();
		long ct = se.getSession().getCreationTime();
		long lacct = se.getSession().getLastAccessedTime();
		String td = (String) se.getSession().getAttribute("tenantdomain");

		printInfomation(se, sessionId, maxII, ct, lacct, td, "Session已被終止");
	}

	private void printInfomation(HttpSessionEvent se, String sessionId, int maxII, long ct, long lacct, String td, String type) {
	    	se.getSession().setAttribute("onlineCount", onlineNumber);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH點mm分ss秒");
//		if (td != null) {
//			System.out.println("CounterListener: \nremove uo by td=" + td + " \nsessionID=" + sessionId);
//		}
		BigDecimal ONE_DAY = new BigDecimal(1000 * 60 * 60 * 24); // 1天的毫秒數
		BigDecimal ONE_HOUR = new BigDecimal(1000 * 60 * 60); // 1小時的毫秒數
		BigDecimal ONE_MIN = new BigDecimal(1000 * 60); // 1分鐘的毫秒數
		BigDecimal ONE_SEC = new BigDecimal(1000); // 1秒的毫秒數

		BigDecimal crt = new BigDecimal(ct);
		BigDecimal lacrt = new BigDecimal(lacct);

		BigDecimal useSec = lacrt.subtract(crt);
		String useTime = "";

//		System.out.println("useSec= " + useSec);

		if (useSec.compareTo(ONE_DAY) > 0) {
			useTime += divide(useSec, ONE_DAY).toString() + "天";
			useSec = useSec.subtract(useSec.multiply(ONE_DAY));
		}
		if (useSec.compareTo(ONE_HOUR) > 0) {
			useTime += divide(useSec, ONE_HOUR).toString() + "點";
			useSec = useSec.subtract(useSec.multiply(ONE_HOUR));
		}
		if (useSec.compareTo(ONE_MIN) > 0) {
			useTime += divide(useSec, ONE_MIN).toString() + "分";
			useSec = useSec.subtract(useSec.multiply(ONE_MIN));
		}
		if (useSec.compareTo(ONE_MIN) < 0) {
			useTime += divide(useSec, ONE_SEC).toString() + "秒";
		}

		// System.out.println("====>CounterListener: Session" + type + "=" +
		// sessionId + " online count=" + onlineNumber);
//		System.out.println("CounterListener: \n" + type + "\n線上人數=" + onlineNumber);
//		System.out.println("CounterListener:\n生命週期(秒)=" + maxII + "\n創建時間=" + sdf.format(new Date(ct)) + "\n結束時間= "
//				+ sdf.format(new Date(lacct)) + "\n使用時間長度=" + useTime);
//		System.out.println("==================================================================================");
	}

	private BigDecimal divide(BigDecimal arg1, BigDecimal divideNum) {
		return arg1.divide(divideNum, 0, RoundingMode.HALF_UP);
	}
}