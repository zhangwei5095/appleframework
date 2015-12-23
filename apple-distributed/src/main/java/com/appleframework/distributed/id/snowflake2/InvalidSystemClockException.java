package com.appleframework.distributed.id.snowflake2;

/**
 * Created with IntelliJ IDEA.
 * User: predictor
 * Date: 25.01.13
 * Time: 11:18
 * To change this template use File | Settings | File Templates.
 */
public class InvalidSystemClockException extends Exception {

	private static final long serialVersionUID = -6316859791216585229L;

	public InvalidSystemClockException(String message){
        super(message);
    }
}
