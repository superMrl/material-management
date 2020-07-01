package com.jckj.materialmanagement.config.error;

import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 错误代码及错误提示语的数据提取公共类
 * @author changjinlin
 * @version 2.0.0 2016-11-04
 */
@Component
public class ErrorCodeProperties {

	private static final String ERROR_MESSAGE_PATH = "classpath:errorMessage";
	private static ErrorCodeProperties errorCodeProperties = new ErrorCodeProperties();
	static ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();

	@PostConstruct
	private static void getErrorProperties() {
		messageSource.setBasename(ERROR_MESSAGE_PATH);
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setUseCodeAsDefaultMessage(true);
	}

	public static ErrorCodeProperties init() {
		return errorCodeProperties;
	}

	public static String getErrorMessage(Integer errorCode) {
		getErrorProperties();//临时设置
		String errorMessage = messageSource.getMessage(errorCode.toString(), null, null);
		if (errorMessage != null) {
			return errorMessage;
		}
		return "";
	}

	public static String getErrorMessages(Integer errorCode,Object[] obj) {
		getErrorProperties();//临时设置
		String errorMessage = messageSource.getMessage(errorCode.toString(), obj, null);
		if (errorMessage != null) {
			return errorMessage;
		}
		return "";
	}
}
