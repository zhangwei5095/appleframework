/*
 * Copyright (C) 2010-2012 LShift Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.appleframework.distributed.id;

import com.appleframework.distributed.snowflake.MachineIdAssigner;
import com.appleframework.distributed.snowflake.RandomIdentityGenerator;
import com.appleframework.distributed.snowflake.SnowflakeIdProvider;


/**
 * An IdProvider that uses ZooKeeper to co-ordinate node identifiers which are
 * generated randomly.
 */
public class AppleIdProvider extends SnowflakeIdProvider {
	
	public AppleIdProvider(String zookeeperCluster) throws Exception {
		super(MachineIdAssigner.getInstance(zookeeperCluster).assign(RandomIdentityGenerator.unseeded()));
	}

	public static void main(String[] args) {
		String zookeeperCluster = "localhost:2181";
		try {
			AppleIdProvider provider = new AppleIdProvider(zookeeperCluster);
			for (int i = 0; i < 1000000000; i++) {
				System.out.println(provider.getId());
			}
			System.in.read();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}