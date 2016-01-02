package com.appleframework.distributed.id.snowflake2;

import com.appleframework.distributed.id.IdentityGenerator;

public class EntityIdGeneratorTest {

	public static void main(String[] args) throws Exception {
		IdentityGenerator idGenerator = BasicEntityIdentityGenerator.getInstance();
		for (int i = 0; i < 1000; i++) {
			System.out.println(idGenerator.generateId());
		}
	}
}
