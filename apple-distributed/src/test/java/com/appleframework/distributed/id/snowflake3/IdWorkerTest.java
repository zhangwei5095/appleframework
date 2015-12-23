package com.appleframework.distributed.id.snowflake3;

import java.util.HashSet;
import java.util.Set;

import com.appleframework.distributed.id.snowflake.IdProvider;
import com.appleframework.distributed.id.snowflake.IdProviderFactory;
import com.appleframework.distributed.id.snowflake.InvalidSystemClockException;
import com.appleframework.distributed.id.snowflake.SequenceExhaustedException;

public class IdWorkerTest {
	
	static class IdWorkThread implements Runnable {
		private Set<Long> set;
		private IdProvider idProvider;

		public IdWorkThread(Set<Long> set, IdProvider idProvider) {
			this.set = set;
			this.idProvider = idProvider;
		}

		@Override
		public void run() {
			while (true) {
				long id = 0L;
				try {
					id = idProvider.getId();
				} catch (InvalidSystemClockException e) {
					e.printStackTrace();
				} catch (SequenceExhaustedException e) {
					e.printStackTrace();
				}
				
				System.out.println(id);
				if (!set.add(id)) {
					System.out.println("duplicate:" + id);
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		IdProviderFactory idProviderFactory = new IdProviderFactory("localhost:2181");
		Set<Long> set = new HashSet<Long>();
		final IdProvider idProvider1 = idProviderFactory.getProvider();
		final IdProvider idProvider2 = idProviderFactory.getProvider();
		Thread t1 = new Thread(new IdWorkThread(set, idProvider1));
		Thread t2 = new Thread(new IdWorkThread(set, idProvider2));
		t1.setDaemon(true);
		t2.setDaemon(true);
		t1.start();
		t2.start();
		try {
			Thread.sleep(30000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
