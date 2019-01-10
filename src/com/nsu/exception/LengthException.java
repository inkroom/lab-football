package com.nsu.exception;

/**
 * 功能描述：当用户上传的文件大小不符合的时候抛出此异常
 * 
 * @author RenQiang
 * @version 1.0 Create Date: 2016-04-18
 */
public class LengthException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4010266284466050687L;

	public LengthException(String message) {
		super(message);
	}
}
