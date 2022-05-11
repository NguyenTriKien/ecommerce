package com.ecommerce.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecommerce.config.PaypalPaymentIntent;
import com.ecommerce.config.PaypalPaymentMethod;
import com.ecommerce.dao.OrderDAO;
import com.ecommerce.dao.ProductDAO;
import com.ecommerce.entity.Product;
import com.ecommerce.model.CartInfo;
import com.ecommerce.model.CartLineInfo;
import com.ecommerce.model.PaypalService;
import com.ecommerce.util.PaymentUtil;
import com.ecommerce.util.Utils;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

@Controller
public class PaymentController {
	public static final String URL_PAYPAL_SUCCESS = "pay/success";
	public static final String URL_PAYPAL_CANCEL = "pay/cancel";
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private PaypalService paypalService;
	
	@Autowired
	private OrderDAO orderDAO;
	
	@Autowired
	private ProductDAO productDAO;
	
	@PostMapping("/pay")
	public String pay(HttpServletRequest request,@RequestParam("price") double price ){
		String cancelUrl = PaymentUtil.getBaseURL(request) + "/" + URL_PAYPAL_CANCEL;
		String successUrl = PaymentUtil.getBaseURL(request) + "/" + URL_PAYPAL_SUCCESS;
		
		
		
		try {
			Payment payment = paypalService.createPayment(
					price,
					"USD",
					PaypalPaymentMethod.paypal,
					PaypalPaymentIntent.sale,
					"payment description",
					cancelUrl,
					successUrl);
			
			for(Links links : payment.getLinks()){
				if(links.getRel().equals("approval_url")){
					return "redirect:" + links.getHref();
				}
			}
		} catch (PayPalRESTException e) {
			log.error(e.getMessage());
		
		}
		
		return "redirect:/ecommerce";
	
	}
	@GetMapping(URL_PAYPAL_CANCEL)
	public String cancelPay(){
		return "cancel";
	}
	@GetMapping(URL_PAYPAL_SUCCESS)
	public String successPay(HttpServletRequest request ,@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId){
		try {
			Payment payment = paypalService.executePayment(paymentId, payerId);
			if(payment.getState().equals("approved")){
				CartInfo cartInfo = Utils.getCartInfoInSession(request);
				List<CartLineInfo> cartLineInfo = cartInfo.getCartLineInfos();
				if(cartInfo.isEmpty()) {

					return "redirect:/shoppingCart";
				}else if (!cartInfo.isValidCustomer()) {

					return "redirect:/shoppingCartCustomer";
				}
				
				try {
					for(int i = 0; i < cartLineInfo.size(); i++) {
						String code = cartLineInfo.get(i).getProductInfo().getCode();
						Product product = productDAO.getProductByCode(code);
						int proquantity = product.getQuantity();
						int cartquantity = cartLineInfo.get(i).getQuantity();
						//System.out.println("Product quantity: " + proquantity);
						int newQuantity = proquantity - cartquantity;
						//System.out.println("New quantity:" + newQuantity);
						productDAO.updateProductQuantity(code, newQuantity);
					
					}
					orderDAO.saveOrder(cartInfo);
					
				} catch (Exception e) {
					return "shoppingCartConfirmation";
				}
				
		        Utils.removeCartInfoInSession(request);
				
				Utils.storeLastOrderedCartInfoInSession(request, cartInfo);
				return "success";
			}
			
		} catch (PayPalRESTException e) {
			log.error(e.getMessage());
		}
		
		return "redirect:/success";
	}
}
