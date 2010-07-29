package org.kyerp.utils;

public class MyExceptionMessageExceptionHandler extends org.springframework.web.servlet.view.json.exception.ExceptionMessageExceptionHandler {
	@Override
	public void setModelKey(String modelKey) {
		modelKey = "msg";
		super.setModelKey(modelKey);
	}
}
