package com.appleframework.distributed.id.snowflake2;

public class EntityIdGeneratorTest {

	public static void main(String[] args) throws Exception {
		EntityIdGenerator idGenerator = BasicEntityIdGenerator.getInstance();
		for (int i = 0; i < 1000; i++) {
			System.out.println(idGenerator.generateLongId());
		}
	}
}
