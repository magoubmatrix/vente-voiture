package com.app.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Component
public class MailContentBuilder {

	@Autowired
	private TemplateEngine engine;
	
	public String  build(String message) {
		Context context = new Context();
		context.setVariable("message", message);
		return engine.process("emailTemplates", context);
	}
}
