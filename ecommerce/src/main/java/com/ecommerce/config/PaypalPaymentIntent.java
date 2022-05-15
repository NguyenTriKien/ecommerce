package com.ecommerce.config;

public enum PaypalPaymentIntent {
	sale, authorize, order
	/*
	  sale. Makes an immediate payment.
      authorize. Authorizes a payment for capture later.
      order. Creates an order.
    */
}
