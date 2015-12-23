package com.appleframework.distributed.id.snowflake2;

/**
 * Created with IntelliJ IDEA.
 * User: predictor
 * Date: 25.01.13
 * Time: 11:32
 * To change this template use File | Settings | File Templates.
 */
public class GetHardwareIdFailedException extends Exception {

	private static final long serialVersionUID = 7341841346341997390L;

	GetHardwareIdFailedException(String reason){
        super(reason);
    }
	
    GetHardwareIdFailedException(Exception e){
        super(e);
    }
}
