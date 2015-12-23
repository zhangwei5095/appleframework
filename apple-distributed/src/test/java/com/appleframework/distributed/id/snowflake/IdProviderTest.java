package com.appleframework.distributed.id.snowflake;

import com.appleframework.distributed.id.snowflake.IdProvider;
import com.appleframework.distributed.id.snowflake.IdProviderFactory;

public class IdProviderTest {

	public static void main(String[] args) throws Exception {
		IdProviderFactory idProviderFactory = new IdProviderFactory("localhost:2181");
		final IdProvider idProvider1 = idProviderFactory.getProvider();
		final IdProvider idProvider2 = idProviderFactory.getProvider();
		for (int i = 0; i < 1000; i++) {
			System.out.println(idProvider1.getId());
			System.out.println(idProvider2.getId());
		}
	}
}
