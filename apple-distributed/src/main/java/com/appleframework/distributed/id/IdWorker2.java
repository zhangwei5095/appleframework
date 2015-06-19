package com.appleframework.distributed.id;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

public class IdWorker2 implements IdWorker {
	
    protected static final Logger LOG = Logger.getLogger(IdWorker2.class);

	private final long workerId;
	private final static long twepoch = 1288834974657L;
	private long sequence = 0L;
	private final static long workerIdBits = 4L;
	public final static long maxWorkerId = -1L ^ -1L << workerIdBits;
	private final static long sequenceBits = 10L;

	private final static long workerIdShift = sequenceBits;
	private final static long timestampLeftShift = sequenceBits + workerIdBits;
	public final static long sequenceMask = -1L ^ -1L << sequenceBits;

	private long lastTimestamp = -1L;

	public IdWorker2(final long workerId) {
		super();
		if (workerId > maxWorkerId || workerId < 0) {
			throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
		}
		this.workerId = workerId;
	}

	public synchronized long nextId() {
		long timestamp = this.timeGen();
		if (this.lastTimestamp == timestamp) {
			this.sequence = (this.sequence + 1) & sequenceMask;
			if (this.sequence == 0) {
				System.out.println("###########" + sequenceMask);
				timestamp = this.tilNextMillis(this.lastTimestamp);
			}
		} else {
			this.sequence = 0;
		}
		if (timestamp < this.lastTimestamp) {
			try {
				LOG.error(String.format("clock is moving backwards.  Rejecting requests until %d.", lastTimestamp));
				throw new Exception(String.format(
								"Clock moved backwards.  Refusing to generate id for %d milliseconds",
								this.lastTimestamp - timestamp));
			} catch (Exception e) {
				LOG.error(e.getMessage());
			}
		}

		this.lastTimestamp = timestamp;
		long nextId = ((timestamp - twepoch << timestampLeftShift)) | (this.workerId << workerIdShift) | (this.sequence);
		LOG.info("timestamp:" + timestamp + ",timestampLeftShift:"
				+ timestampLeftShift + ",nextId:" + nextId + ",workerId:"
				+ workerId + ",sequence:" + sequence);
		return nextId;

	}

	private long tilNextMillis(final long lastTimestamp) {
		long timestamp = this.timeGen();
		while (timestamp <= lastTimestamp) {
			timestamp = this.timeGen();
		}
		return timestamp;
	}

	private long timeGen() {
		return System.currentTimeMillis();
	}

	public static void main(String[] args) {
		IdWorker2 worker = new IdWorker2(14);
		Set<Long> sets = new HashSet<Long>();
		for (int i = 1; i <= 100000; i++) {
			long id = worker.nextId();
			sets.add(id);
		}
		System.out.println(sets.size());
	}

}
