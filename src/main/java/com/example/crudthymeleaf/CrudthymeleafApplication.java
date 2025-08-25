package com.example.crudthymeleaf;

import com.example.crudthymeleaf.springBootBasic.OrderService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class CrudthymeleafApplication {

	public static void main(String[] args) {
//		SpringApplication.run(CrudthymeleafApplication.class, args);

//		var orderService = new OrderService(new StripePaymentService());

		//setter injection
//		var orderService = new OrderService();
//		orderService.setPaymentService(new PaypalPaymentService());
		// inversion of control
		ApplicationContext context = SpringApplication.run(CrudthymeleafApplication.class, args);
		var orderService = context.getBean(OrderService.class);
		orderService.placeOrder();


	}

}
