package com.OrderService.OrderService.configs;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class FeignClientsRequestInterceptor implements RequestInterceptor {

	@Override
	public void apply(RequestTemplate template) {
		System.out.println("in request interceptor ");
		// TODO Auto-generated method stub
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

		if (attributes != null) {
			HttpServletRequest request = attributes.getRequest();
			String header = request.getHeader(HttpHeaders.AUTHORIZATION);
			template.header(HttpHeaders.AUTHORIZATION, header);
		}
	}

}
