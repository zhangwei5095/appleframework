package com.appleframework.distributed.id.snowflake2;


import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

import org.apache.log4j.Logger;

import com.appleframework.distributed.id.IdentityGenerator;

/**
 * BasicEntityIdGenerator
 *
 * @author Maxim Khodanovich
 * @version 21.01.13 17:16
 *          <p/>
 *          id is composed of:
 *          time - 41 bits (millisecond precision w/ a custom epoch gives us 69 years)
 *          configured machine id - 10 bits - gives us up to 1024 machines
 *          sequence number - 12 bits - rolls over every 4096 per machine (with protection to avoid rollover in the same ms)
 *
 *
 */
public class BasicEntityIdentityGenerator implements IdentityGenerator {

    protected static final Logger logger = Logger.getLogger(BasicEntityIdentityGenerator.class);

//   id format  =>
//   timestamp |datacenter | sequence
//   41        |10         |  12
    private final long sequenceBits = 12;
    private final long datacenterIdBits = 10L;
    private final long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);

    private final long datacenterIdShift = sequenceBits;
    private final long timestampLeftShift = sequenceBits + datacenterIdBits;

    private final long twepoch = 1288834974657L;
    private final long datacenterId;
    private final long sequenceMax = 4096;

    private volatile long lastTimestamp = -1L;
    private volatile long sequence = 0L;

    private static IdentityGenerator basicEntityIdentityGenerator;

    public static IdentityGenerator getInstance() throws GetHardwareIdFailedException {
        if (basicEntityIdentityGenerator == null) basicEntityIdentityGenerator = new BasicEntityIdentityGenerator();
        return basicEntityIdentityGenerator;
    }
    
    private BasicEntityIdentityGenerator() {
		datacenterId = getDatacenterId();
        if (datacenterId > maxDatacenterId || datacenterId < 0) {
        	logger.error("datacenterId > maxDatacenterId");
        }
    }

    @Override
    public synchronized Long generateId() {
        long timestamp = System.currentTimeMillis();
        if (timestamp < lastTimestamp) {
            throw new RuntimeException("Clock moved backwards.  Refusing to generate id for " + (
                    lastTimestamp - timestamp) + " milliseconds.");
        }
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) % sequenceMax;
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0;
        }
        lastTimestamp = timestamp;
        Long id = ((timestamp - twepoch) << timestampLeftShift) | (datacenterId << datacenterIdShift) | sequence;
        return id;
    }

    protected long tilNextMillis(long lastTimestamp) {
        long timestamp = System.currentTimeMillis();
        while (timestamp <= lastTimestamp) {
            timestamp = System.currentTimeMillis();
        }
        return timestamp;
    }

    protected long getDatacenterId() {
        try {
            InetAddress ip = InetAddress.getLocalHost();
            NetworkInterface network = NetworkInterface.getByInetAddress(ip);
            long id;
            if (network == null) {
                id = 1;
            } else {
                byte[] mac = network.getHardwareAddress();
                id = ((0x000000FF & (long) mac[mac.length - 1]) | (0x0000FF00 & (((long) mac[mac.length - 2]) << 8))) >> 6;
            }
            return id;
        } catch (SocketException e) {
        	logger.error(e);
        	return -1L;
        } catch (UnknownHostException e) {
        	logger.error(e);
        	return -1L;
        }
    }

}
