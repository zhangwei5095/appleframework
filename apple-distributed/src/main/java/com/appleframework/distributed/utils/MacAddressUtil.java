package com.appleframework.distributed.utils;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import org.apache.log4j.Logger;

/**
 * @title: MacAddressUtil
 * @description：获取MAC地址 @date： 2013-5-5 下午04:42:50
 */
public class MacAddressUtil {
	
	protected static final Logger logger = Logger.getLogger(MacAddressUtil.class);
	
	public static String hexByte(byte b) {
		String s = "000000" + Integer.toHexString(b);
		return s.substring(s.length() - 2);
	}

	public static byte[] getMAC() {
		Enumeration<NetworkInterface> el;
		byte[] hardMac = null;
		try {
			el = NetworkInterface.getNetworkInterfaces();
			while (el.hasMoreElements()) {
				byte[] mac = el.nextElement().getHardwareAddress();
				if (mac == null || mac.length == 0)
					continue;
				if(hexByte(mac[0]).equals("00") &&
					hexByte(mac[1]).equals("00") &&
					hexByte(mac[2]).equals("00") &&
					hexByte(mac[3]).equals("00") && 
					hexByte(mac[4]).equals("00") && 
					hexByte(mac[5]).equals("00")) {
					continue;
				}
				hardMac = mac;
				return hardMac;
			}
		} catch (SocketException e) {
			logger.error("找不到mac地址", e);
		}
		return hardMac;
	}

	public static void main(String[] args) {
		byte[] mac = MacAddressUtil.getMAC();
		String mac_s = hexByte(mac[0]) + "-" + hexByte(mac[1]) + "-"
                + hexByte(mac[2]) + "-" + hexByte(mac[3]) + "-"
                + hexByte(mac[4]) + "-" + hexByte(mac[5]);
        System.out.println("MAC地址：" + mac_s);

	}
}
