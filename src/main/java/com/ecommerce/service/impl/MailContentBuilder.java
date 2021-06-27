package com.ecommerce.service.impl;

import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.ecommerce.model.response.OrderResponse;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MailContentBuilder {
	
	 private  final TemplateEngine templateEngine;
	 
	 String build(String message){
	        Context context=new Context();
	        context.setVariable("message",message);
	        return  templateEngine.process("mailTemplate",context);
	 }
	 
	 String buildOrder(OrderResponse orderResponse){
	        Context context=new Context();
	        context.setVariable("order",orderResponse);
	        return  templateEngine.process("mailOrder",context);
	 }
}
