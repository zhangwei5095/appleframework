package com.appleframework.distributed.utils;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import com.alibaba.dubbo.common.utils.StringUtils;

import sun.net.util.IPAddressUtil;

public class IpUtility {

	/**
	 * 获取本地ip  非公网ip
	 * @return
	 * @throws SocketException 
	 */
	@SuppressWarnings("rawtypes")
	public static String getIp() throws SocketException {
		Enumeration allNetInterfaces = NetworkInterface.getNetworkInterfaces();
		InetAddress ip = null;
		String iphost = "";
		while (allNetInterfaces.hasMoreElements()) {
			if(StringUtils.isEmpty(iphost)){
				NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
				Enumeration addresses = netInterface.getInetAddresses();
				while (addresses.hasMoreElements()) {
					ip = (InetAddress) addresses.nextElement();
					if (ip != null && ip instanceof Inet4Address && !ip.isLoopbackAddress() && internalIp(ip.getHostAddress())) {
						iphost = ip.getHostAddress();
						break;
					}
				}
			}else{
				break;
			}
		}
		return iphost;
	}
	
	
	public static boolean internalIp(String ip) {
	    byte[] addr = IPAddressUtil.textToNumericFormatV4(ip);
	    return internalIp(addr);
	}
	
	public static boolean internalIp(byte[] addr) {
	    final byte b0 = addr[0];
	    final byte b1 = addr[1];
	    //10.x.x.x/8
	    final byte SECTION_1 = 0x0A;
	    //172.16.x.x/12
	    final byte SECTION_2 = (byte) 0xAC;
	    final byte SECTION_3 = (byte) 0x10;
	    final byte SECTION_4 = (byte) 0x1F;
	    //192.168.x.x/16
	    final byte SECTION_5 = (byte) 0xC0;
	    final byte SECTION_6 = (byte) 0xA8;
	    switch (b0) {
	        case SECTION_1:
	            return true;
	        case SECTION_2:
	            if (b1 >= SECTION_3 && b1 <= SECTION_4) {
	                return true;
	            }
	        case SECTION_5:
	            switch (b1) {
	                case SECTION_6:
	                    return true;
	            }
	        default:
	            return false;
	    }
	}
	
	public static void main(String[] args) throws SocketException {
		System.out.println(getIp());
	}

}
