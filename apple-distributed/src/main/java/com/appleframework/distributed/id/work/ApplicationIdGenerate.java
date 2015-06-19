package com.appleframework.distributed.id.work;

import java.net.SocketException;

import com.appleframework.distributed.utils.IpUtility;

public class ApplicationIdGenerate implements IWorkIdGenerate {

	private String applicationName;

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	@Override
	public int genWorkId() {
		String ip = "127.0.0.1";
		try {
			ip = IpUtility.getIp();
		} catch (SocketException e) {
		}
		return (ip + "_" + applicationName).hashCode();
	}

}