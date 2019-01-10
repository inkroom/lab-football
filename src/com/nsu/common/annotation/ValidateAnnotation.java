package com.nsu.common.annotation;

import java.lang.annotation.*;

/**
 * <p>自定义验证注解</p>
 * 验证类型：
 * 数字、小数、正整数、中文、英文、中英文
 * 、正常文本、人的姓名、电话、邮箱、身份证
 * @author ljl
 * @createData 2017/07/03/08:57 am 
 * @version 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Documented
public @interface ValidateAnnotation {
	
	/**
	 * 实数
	 */
	public static final int VALIDATE_DECIMAL = 1;
	/**
	 * 正整数
	 */
	public static final int VALIDATE_POSITIVE_INTEGER = 2;
	/**
	 * 数字
	 */
	public static final int VALIDATE_NUMBER = 3;
	/**
	 * 中文
	 */
	public static final int VALIDATE_CN = 4;
	/**
	 * 英文
	 */
	public static final int VALIDATE_EN = 5;
	/**
	 * 中英文
	 */
	public static final int VALIDATE_CN_EN = 6;
	/**
	 * 邮箱
	 */
	public static final int VALIDATE_EMAIL = 7;
	/**
	 * 手机号码
	 */
	public static final int VALIDATE_PHONE_NUMBER = 8;
	/**
	 * 人名
	 */
	public static final int VALIDATE_PERSON_NAME = 9;
	/**
	 * 文本
	 */
	public static final int VALIDATE_TEXT = 10;
	/**
	 * 中英文数字
	 */
	public static final int VALIDATE_CN_EN_NUMBER = 11;
	/**
	 * 自定义验证
	 */
	public static final int CUSTOM_VALIDATE= 12;
	/**
	 * 身份证号
	 */
	public static final int VALIDATE_IDCARD = 13;
	
	/**
	 * 自定义验证的正则表达式
	 * @return
	 */
	String customRegex() default "";
	
	/**
	 * <p>验证类型</p>
	 * <p>验证类型参数说明</p>
	 * <p>1. 实数</p>
	 * <p>2. 正整数</p>
	 * <p>3. 数字，特别说明：数字是指只包含0~9这些数字</p>
	 * <p>4. 中文</p>
	 * <p>5. 英文</p>
	 * <p>6. 中英文</p>
	 * <p>7. 邮箱</p>
	 * <p>8. 手机号码</p>
	 * <p>9. 人名</p>
	 * <p>10. 文本</p>
	 * <p>11. 中英文数字</p>
	 * <p>12. 自定义验证</p>
	 * <p>13. 身份证号</p>
	 * @return
	 */
	int validateType() default 1;
	
	/**
	 * 是否允许为空
	 * @return
	 */
	boolean isAllowEmpty() default false;
	
	/**
	 * 错误提示信息
	 * @return
	 */
	String errorMsg() default "";
	
	/**
	 * 特殊标记
	 * <p>用于对于一个bean中在某些字段在某些情况下不需要验证使用</p>
	 * <p>存在多个标记时请用英文逗号分隔</p>
	 */
	String specialMark() default "";
}
