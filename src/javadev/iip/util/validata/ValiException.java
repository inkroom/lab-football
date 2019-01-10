package javadev.iip.util.validata;



/**
 * 数据校验异常
 */
public class ValiException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ValiException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ValiException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public ValiException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public ValiException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public ValiException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
}
