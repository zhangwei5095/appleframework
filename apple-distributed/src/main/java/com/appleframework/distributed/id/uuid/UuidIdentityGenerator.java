package com.appleframework.distributed.id.uuid;

import java.util.UUID;

import org.apache.log4j.Logger;

import com.appleframework.distributed.id.IdentityGenerator;
import com.appleframework.distributed.utils.MacAddressUtil;

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
public class UuidIdentityGenerator implements IdentityGenerator {

    protected static final Logger logger = Logger.getLogger(UuidIdentityGenerator.class);
    
    private final long datacenterIdBits = 10L;
    private final long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);
    private final long datacenterId;

    private static IdentityGenerator basicEntityIdentityGenerator;

    public static IdentityGenerator getInstance() {
        if (basicEntityIdentityGenerator == null) basicEntityIdentityGenerator = new UuidIdentityGenerator();
        return basicEntityIdentityGenerator;
    }
    
    private UuidIdentityGenerator() {
		datacenterId = getDatacenterId();
        if (datacenterId > maxDatacenterId || datacenterId < 0) {
        	logger.error("datacenterId生成错误");
        }
    }

    @Override
    public synchronized Long generateId() {
		int hashCodeV = UUID.randomUUID().toString().hashCode();
		if (hashCodeV < 0) {// 有可能是负数
			hashCodeV = -hashCodeV;
		}
		// 0 代表前面补充0
		// 4 代表长度为4
		// d 代表参数为正数型
		return Long.parseLong(datacenterId + String.format("%015d", hashCodeV));
    }

    protected long getDatacenterId() {
     	byte[] mac = MacAddressUtil.getMAC();
     	if(null != mac) {
            long id = ((0x000000FF & (long) mac[mac.length - 1]) | (0x0000FF00 & (((long) mac[mac.length - 2]) << 8))) >> 6;
            return id;
        } else {
        	return -1L;
        }
    }

}
