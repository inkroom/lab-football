package com.nsu.action.fileupload;

/**
 * 功能描述：当前台没有提交文件参数或者提交文件参数名不为file时抛出此异常
 * 
 * @author RenQiang
 * @version 1.0 Create Date: 2016-04-18
 */
public class NullFileParamException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3016425477859158657L;

	public NullFileParamException(String message) {
		super(message);
	}
}
