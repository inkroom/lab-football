package com.nsu.action.fileupload;

/**
 * 功能描述：当用户上传的文件后缀名不符合的时候抛出此异常
 * 
 * @author RenQiang
 * @version 1.0 Create Date: 2016-04-18
 */
public class ExtensionsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4487489477113285480L;

	public ExtensionsException(String message) {
		super(message);
	}
}