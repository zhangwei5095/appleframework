package com.appleframework.distributed.id.work;

import java.net.SocketException;

import com.appleframework.distributed.utils.IpUtility;

public class ApplicationId implements IWorkId {

	private String applicationName;

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	@Override
	public int getWorkId() {
		String ip = "127.0.0.1";
		try {
			ip = IpUtility.getIp();
		} catch (SocketException e) {
		}
		return (ip + "_" + applicationName).hashCode();
	}

}