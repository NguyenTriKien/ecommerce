package com.ecommerce.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ecommerce.dao.UserAccountDAO;
import com.ecommerce.dao.OrderDAO;
import com.ecommerce.dao.ProducerDAO;
import com.ecommerce.dao.ProductDAO;
import com.ecommerce.entity.UserAccount;
import com.ecommerce.entity.Order;
import com.ecommerce.entity.Producer;
import com.ecommerce.entity.Product;
import com.ecommerce.model.CartInfo;
import com.ecommerce.model.CartLineInfo;
import com.ecommerce.model.CustomerInfo;
import com.ecommerce.model.OrderDetailInfo;
import com.ecommerce.model.OrderInfo;
import com.ecommerce.model.PaginationResult;
import com.ecommerce.model.ProductInfo;
import com.ecommerce.model.UserAccountInfo;
import com.ecommerce.util.LoginUtils;
import com.ecommerce.util.Utils;
import com.ecommerce.validator.CustomerInfoValidator;
import com.ecommerce.validator.LoginValidator;
import com.ecommerce.validator.RegisterValidator;


@Controller
public class ClientController {

	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private OrderDAO orderDAO;
	
	@Autowired
	private UserAccountDAO userAccountDAO;
	
	@Autowired
	private CustomerInfoValidator customerInfoValidator;
	
	@Autowired
	private UserAccountDAO gmailDAO;
	
	@Autowired
	private ProducerDAO producerDAO;
	
	@Autowired
	private LoginUtils loginUtils;
	
	@Autowired
	private RegisterValidator registerValidator;
	
	@Autowired
	private LoginValidator loginValidator;

	@RequestMapping({ "/" })
	public String home() {
		return "home";
	}
	
	@RequestMapping({ "/quantityNotification" })
	public String quantityNotification() {
		return "quantityNotification";
	}
	

	@RequestMapping({ "/statusNotification" })
	public String statusNotification() {
		return "statusNotification";
	}
	
	@RequestMapping({"/productList"})
	public String getAllProductInfos(Model model, @RequestParam(value = "name", defaultValue = "") String likeName,
			 @RequestParam(value = "producers", defaultValue = "") String producers,
			@RequestParam(value = "price", defaultValue = "0") double price,
			@RequestParam(value = "page", defaultValue = "1") int page, 
			@RequestParam(value = "producer", defaultValue = "") String producerid) {
		final int maxResult = 15;
		final int maxNavigationPage = 10;
		PaginationResult<ProductInfo> productInfos = productDAO.getAllProductInfos(page, maxResult, maxNavigationPage, likeName, price, producers);
		List<Producer> producers2 = producerDAO.getAllProducer(producerid);
		productInfos.setProducers(producers2);
		model.addAttribute("paginationProductInfos", productInfos);
		return "productList";
	}
	
	@RequestMapping({"/productList/producttype"})
	public String getAllProductInfosByType(Model model, @RequestParam(value = "type", defaultValue = "") String type,
			@RequestParam(value = "page", defaultValue = "1") int page) {
		final int maxResult = 15;
		final int maxNavigationPage = 10;
		PaginationResult<ProductInfo> productInfos = productDAO.getAllProductInfoByType(page, maxResult, maxNavigationPage, type);
		
		model.addAttribute("paginationProductInfos", productInfos);
		return "productList";
	}
	
	@RequestMapping(value = {"/productImage"}, method = RequestMethod.GET)
	public void productImage(HttpServletRequest request, HttpServletResponse response, Model model,
			@RequestParam("code") String code)throws IOException {
		Product product = null;
		if(code != null) {
			product = productDAO.getProductByCode(code);
		}
		
		if(product != null && product.getImage() != null) {
			response.setContentType("image/jge, image/jpg, image/png, image/gif");
			response.getOutputStream().write(product.getImage());
		}
		response.getOutputStream().close();
	}
	
	@RequestMapping(value = {"/productDetail"})
	public String productDetail(HttpServletRequest request, HttpServletResponse response, Model model,
			@RequestParam("code") String code) throws IOException {
		Product product = null;
		if(code != null) {
			product = productDAO.getProductByCode(code);
		}
		if(product == null) {
			return "redirect:/productList";
		}
		model.addAttribute("productInfo", product);
		return "productDetail";
	}
	
	@RequestMapping({"/buyProduct"})
	public String buyProductHandler(HttpServletRequest request, Model model,
			@RequestParam(value = "code", defaultValue = "") String code) {
		Product product = null;
		if(code != null && code.length() > 0) {
			product = productDAO.getProductByCode(code);
		}
		
		if(product != null && product.getStatus().equals("Available")) {
			CartInfo cartInfo = Utils.getCartInfoInSession(request);
			ProductInfo productInfo = new ProductInfo(product);
			cartInfo.addProduct(productInfo, 1);
		}
		if(product != null && product.getStatus().equals("Not available")) {
			return "redirect:/statusNotification";
		}
		return "redirect:/shoppingCart";
	}
	
	@RequestMapping(value = {"/shoppingCart"}, method = RequestMethod.GET)
	public String shoppingCartHandler(HttpServletRequest request,  Model model) {
		CartInfo cartInfo = Utils.getCartInfoInSession(request);
		
		model.addAttribute("cartForm",cartInfo);
		return "shoppingCart";
	}
	
	@RequestMapping(value = {"/shoppingCart"}, method = RequestMethod.POST)
	public String shoppingCartUpdateQuantity(HttpServletRequest request,  Model model,
			@ModelAttribute("cartForm") CartInfo cartForm) {
		CartInfo cartInfo = Utils.getCartInfoInSession(request);
		
		cartInfo.updateQuantity(cartForm);
		return "redirect:/shoppingCart";
	}
	
	@RequestMapping({"/shoppingCartRemoveProduct"})
	public String removeProductHandler(HttpServletRequest request, Model model,
			@RequestParam(value = "code", defaultValue = "") String code) {
		Product product = null;
		if(code != null && code.length() > 0) {
			product = productDAO.getProductByCode(code);
		}
		
		if(product != null) {
			CartInfo cartInfo = Utils.getCartInfoInSession(request);
			ProductInfo productInfo = new ProductInfo(product);
			cartInfo.removeProduct(productInfo);
		}
		
		return "redirect:/shoppingCart";
	}
	
	@RequestMapping(value = {"/shoppingCartCustomer"}, method = RequestMethod.GET)
	public String shoppingCartCustomerForm(HttpServletRequest request,  Model model) {
		CartInfo cartInfo = Utils.getCartInfoInSession(request); 
		List<CartLineInfo> cartLineInfo = cartInfo.getCartLineInfos();
		for(int i = 0; i < cartLineInfo.size(); i++) {
			String code = cartLineInfo.get(i).getProductInfo().getCode();
			Product product = productDAO.getProductByCode(code);
			int proquantity = product.getQuantity();
			int cartquantity = cartLineInfo.get(i).getQuantity();
			//System.out.println("Product quantity: " + proquantity);
			if(cartquantity > proquantity) {
				return "redirect:/quantityNotification";
			}
		}
		if(cartInfo.isEmpty()) {
			return "redirect:/shoppingCart";
		}
		
		CustomerInfo customerInfo = cartInfo.getCustomerInfo();
		if(customerInfo == null) {
			customerInfo = new CustomerInfo();
		}
		
		model.addAttribute("customerForm",customerInfo);
		return "shoppingCartCustomer";
	}
	
	@RequestMapping(value = {"/shoppingCartCustomer"}, method = RequestMethod.POST)
	public String shoppingCartCustomer(HttpServletRequest request,  Model model,
			@ModelAttribute("customerForm") @Validated CustomerInfo customerForm, BindingResult result) {
		customerInfoValidator.validate(customerForm, result);

		if(result.hasErrors()) {
			customerForm.setValid(false);

			return "shoppingCartCustomer";
		}
		
		customerForm.setValid(true);
		CartInfo cartInfo = Utils.getCartInfoInSession(request);
		cartInfo.setCustomerInfo(customerForm);

		return "redirect:/shoppingCartConfirmation";
	}
	
	@RequestMapping(value = {"/shoppingCartConfirmation"}, method = RequestMethod.GET)
	public String shoppingCartConfirmationReview(HttpServletRequest request,  Model model) {
		CartInfo cartInfo = Utils.getCartInfoInSession(request);
		if(cartInfo.isEmpty()) {

			return "redirect:/shoppingCart";
		}else if (!cartInfo.isValidCustomer()) {

			return "redirect:/shoppingCartCustomer";
		}
		
		return "shoppingCartConfirmation";
	}
	
	@RequestMapping(value = {"/shoppingCartConfirmation"}, method = RequestMethod.POST)
	public String shoppingCartConfirmationSave(HttpServletRequest request,  Model model) {
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
		
		return "redirect:/shoppingCartFinalize";
	}
	
	@RequestMapping(value = {"/shoppingCartFinalize"}, method = RequestMethod.GET)
	public String shoppingCartFinalize(HttpServletRequest request,  Model model) {
		CartInfo lastOrderdCart = Utils.getLastOrderedCartInfoInSession(request);
		
		if(lastOrderdCart == null) {
			return "redirect:/shoppingCart";
		}
		return "shoppingCartFinalize";
	}
	
	//http://localhost:8080/ecommerce/myOrder?gmail=www@gmail.com
	//Dung de xem order
	@RequestMapping(value = { "/myOrder" }, method = RequestMethod.GET)
	public String orderView(Model model, @RequestParam("orderId") String orderId) {
		OrderInfo orderInfo = null;
		//Order order;
		if (orderId != null) {
			orderInfo = orderDAO.getOrderInfoById(orderId);
			//nhớ chuyển hàm getOrderById trong getOrderInfoById thành getOrderByGoogleId
		}
		if (orderInfo == null) {
			return "myOrderList";
		}
		List<OrderDetailInfo> orderDetailInfos = orderDAO.getAllDetailInfos(orderId);
		orderInfo.setOrderDetailInfos(orderDetailInfos);
		model.addAttribute("orderInfo", orderInfo);
		return "myOrder";
	}
	
	@RequestMapping(value = { "/myOrderList" }, method = RequestMethod.GET)
	public String orderList(Model model, @RequestParam(value = "page", defaultValue = "1") String pageStr,
			 @RequestParam("username") String username) {
		// đặt thêm điều kiện cho phân trang cho orderlist
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		
		int page = 1;
		try {
			page = Integer.parseInt(pageStr);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		final int MAX_RESULT = 10;
		final int MAX_NAVIGATION_PAGE = 10;
		if(username.equals(currentPrincipalName)) {
			PaginationResult<OrderInfo> paginationOrderInfos = orderDAO.getAllOrderInfosByEmail(page, MAX_RESULT,
					MAX_NAVIGATION_PAGE, username);
			model.addAttribute("paginationOrderInfos", paginationOrderInfos);
		}
		return "myOrderList";
	}
	
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(Model model, @RequestParam(value = "username", defaultValue = "") String username) {
		UserAccount userAccount = null;
		if(username != null) {
			userAccount = userAccountDAO.getAccountByUsername(username);
		}
		if(username.isEmpty()) {
			userAccount = new UserAccount();
		}
		model.addAttribute("user", userAccount);
		return "register";
		
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerSave(Model model, @ModelAttribute("user") UserAccountInfo userAccountInfo, 
			BindingResult result) {
		registerValidator.validate(userAccountInfo, result);
		if(result.hasErrors()) {
			return "register";
		}try {
			userAccountDAO.saveUserAccount(userAccountInfo);
		}catch(Exception e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "register";
		}
		return "redirect:/userLogin";
		
	}
	
	//Dung de huy don hang cua nguoi dung
	@RequestMapping({"/cancelOrder"})
	public String cancelOrder(HttpServletRequest request, Model model,
			@RequestParam(value = "status", defaultValue = "") String status,  
			@RequestParam(value = "orderId", defaultValue = "") String orderId) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		String username = currentPrincipalName;
		Order order = null;
		if(status.equals("SHIPPED")) {
			return "redirect:/myOrderList?username=" + username;
		}
		if(status.equals("CANCELLED")) {
			return "redirect:/myOrderList?username=" + username;
		}
		if(status.equals("SHIPPING")) {
			order = orderDAO.getOrderById(orderId);
			orderDAO.updateOrderStatus(orderId);
		}
		return "redirect:/myOrderList?username=" + username;//chỗ này chưa gọi đc do thiếu @
		
	}
	
	@GetMapping("/userLogin")
	public String displayLogin(Model model) {
		UserAccountInfo userAccountInfo = new UserAccountInfo();
		model.addAttribute("userAccount", userAccountInfo);
		return "userLogin";
		
	}
	
	@PostMapping("/userLogin")
	public String processLogin(@ModelAttribute("userAccount") UserAccountInfo userAccountInfo, Model model, BindingResult result) {
		loginValidator.validate(userAccountInfo, result);
		if(result.hasErrors()) {
			return "userLogin";
		}try {
		UserDetails userDetail = loginUtils.buildUser(userAccountInfo);
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetail, null,
		userDetail.getAuthorities());
		    //authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		}catch(Exception e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "userLogin";
		}
		return "home";
		
	}
	 
}
