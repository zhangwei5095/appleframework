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

package com.appleframework.distributed.id.snowflake;

import com.appleframework.distributed.id.IdentityGenerator;

public class RandomIdentityGenerator implements IdentityGenerator {
	
	 private static IdentityGenerator randomIdentityGenerator;
	
	public static IdentityGenerator getInstance() {
        if (randomIdentityGenerator == null) randomIdentityGenerator = new RandomIdentityGenerator();
        return randomIdentityGenerator;
    }

	private java.util.Random rng;

	RandomIdentityGenerator() {
		rng = new java.util.Random();
	}

	RandomIdentityGenerator(long seed) {
		rng = new java.util.Random(seed);
	}

	@Override
	public Long generateId() {
		//rng.nextInt(10)
		return rng.nextLong();
	}
}
