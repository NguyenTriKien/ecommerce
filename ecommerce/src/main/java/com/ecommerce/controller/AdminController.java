package com.ecommerce.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecommerce.dao.AccountDAO;
import com.ecommerce.dao.OrderDAO;
import com.ecommerce.dao.ProducerDAO;
//import com.ecommerce.dao.OrderDAO;
import com.ecommerce.dao.ProductDAO;
import com.ecommerce.dao.TypeDAO;
import com.ecommerce.entity.Account;
import com.ecommerce.entity.Order;
import com.ecommerce.entity.Producer;
import com.ecommerce.entity.Product;
import com.ecommerce.entity.Type;
import com.ecommerce.model.AccountInfo;
import com.ecommerce.model.OrderDetailInfo;
import com.ecommerce.model.OrderInfo;
import com.ecommerce.model.PaginationResult;
import com.ecommerce.model.ProducerInfo;
import com.ecommerce.model.ProductInfo;
import com.ecommerce.model.TypeInfo;
import com.ecommerce.validator.AccountInfoValidator;
import com.ecommerce.validator.ProducerValidator;
import com.ecommerce.validator.ProductValidator;

@Controller
public class AdminController {

	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private TypeDAO typeDAO;
	
	@Autowired
	private OrderDAO orderDAO;
	
	@Autowired
	private ProducerDAO producerDAO;

	@Autowired
	private ProductValidator productValidator;

	// newly added
	@Autowired
	private AccountDAO accountDAO;

	// newly added
	@Autowired
	private AccountInfoValidator accountInfoValidator;

	@Autowired
	private ProducerValidator producerValidator; 
	
	@RequestMapping({ "/403" })
	public String accessDenied() {
		return "403";
	}
	
	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	public String login(Model model) {
		return "login";
	}

	@RequestMapping(value = { "/accountInfo" }, method = RequestMethod.GET)
	public String accountInfo(Model model) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println("Username: " + userDetails.getUsername());
		System.out.println("Password: " + userDetails.getPassword());
		System.out.println("Enable: " + userDetails.isEnabled());

		model.addAttribute("userDetails", userDetails);
		return "accountInfo";
	}
	

	@RequestMapping({"/manageProductList"})
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
		return "manageProductList";
	}
	
	@RequestMapping(value = {"/manageProductDetail"})
	public String productDetail(HttpServletRequest request, HttpServletResponse response, Model model,
			@RequestParam("code") String code) throws IOException {
		Product product = null;
		if(code != null) {
			product = productDAO.getProductByCode(code);
		}
		if(product == null) {
			return "redirect:/manageProductList";
		}
		model.addAttribute("productInfo", product);
		return "manageProductDetail";
	}

	@RequestMapping({"/manageProductList/producttype"})
	public String getAllProductInfosByType(Model model, @RequestParam(value = "type", defaultValue = "") String type,
			@RequestParam(value = "page", defaultValue = "1") int page) {
		final int maxResult = 15;
		final int maxNavigationPage = 10;
		PaginationResult<ProductInfo> productInfos = productDAO.getAllProductInfoByType(page, maxResult, maxNavigationPage, type);
		
		model.addAttribute("paginationProductInfos", productInfos);
		return "manageProductList";
	}
	
	@RequestMapping(value = { "/orderList" }, method = RequestMethod.GET)
	public String orderList(Model model, @RequestParam(value = "page", defaultValue = "1") String pageStr) {
		int page = 1;
		try {
			page = Integer.parseInt(pageStr);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		final int MAX_RESULT = 10;
		final int MAX_NAVIGATION_PAGE = 10;
		PaginationResult<OrderInfo> paginationOrderInfos = orderDAO.getAllOrderInfos(page, MAX_RESULT,
				MAX_NAVIGATION_PAGE);
		model.addAttribute("paginationOrderInfos", paginationOrderInfos);
		return "orderList";
	}

	@RequestMapping(value = { "/order" }, method = RequestMethod.GET)
	public String orderView(Model model, @RequestParam("orderId") String orderId) {
		OrderInfo orderInfo = null;
		if (orderId != null) {
			orderInfo = orderDAO.getOrderInfoById(orderId);
		}
		if (orderInfo == null) {
			return "redirect:/orderList";
		}

		List<OrderDetailInfo> orderDetailInfos = orderDAO.getAllDetailInfos(orderId);
		orderInfo.setOrderDetailInfos(orderDetailInfos);
		model.addAttribute("orderInfo", orderInfo);
		return "order";
	}

	@RequestMapping(value = { "/product" }, method = RequestMethod.GET)
	public String product(Model model, @RequestParam(value = "code", defaultValue = "") String code, 
			@RequestParam(value = "type", defaultValue = "") String typeid,
			@RequestParam(value = "producer", defaultValue = "") String producerid) {
		ProductInfo productInfo = null;
		//TypeInfo typeInfo = null;
		if (code != null && code.length() > 0) {
			productInfo = productDAO.getProductInfoByCode(code);
			
		}
		if (productInfo == null) {
			productInfo = new ProductInfo();
			productInfo.setNewProduct(true);
		}
		List<Type> types = typeDAO.getAllType(typeid);
		productInfo.setTypes(types);
		List<Producer> producers = producerDAO.getAllProducer(producerid);
		productInfo.setProducers(producers);
		model.addAttribute("productForm", productInfo);
		return "product";
	}

	@RequestMapping(value = { "/product" }, method = RequestMethod.POST)
	public String productSave(Model model, @ModelAttribute("productForm") @Validated ProductInfo productInfo,
			BindingResult result) {
		productValidator.validate(productInfo, result);
		if (result.hasErrors()) {
			return "product";
		}
		try {
			productDAO.saveProductInfo(productInfo);
		} catch (Exception e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "product";
		}
		return "redirect:/manageProductList";
	}

	@RequestMapping(value = { "/accountList" }, method = RequestMethod.GET)
	public String accountList(Model model, @RequestParam(value = "page", defaultValue = "1") String pageStr) {
		int page = 1;
		try {
			page = Integer.parseInt(pageStr);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		final int MAX_RESULT = 5;
		final int MAX_NAVIGATION_PAGE = 10;
		PaginationResult<AccountInfo> paginationAccountInfos = accountDAO.getAllAccountInfos(page, MAX_RESULT,
				MAX_NAVIGATION_PAGE);
		model.addAttribute("paginationAccountInfos", paginationAccountInfos);
		return "accountList";
	}

	@RequestMapping(value = { "/account" }, method = RequestMethod.GET)
	public String account(Model model, @RequestParam(value = "username", defaultValue = "") String username) {
		Account account = null;
		if (username != null) {
			account = accountDAO.getAccountByUserName(username);
		}
		if (account == null) {
			account = new Account();
		}
		model.addAttribute("accountForm", account);
		return "account";
	}

	@RequestMapping(value = { "/account" }, method = RequestMethod.POST)
	public String accountSave(Model model, @ModelAttribute("accountForm") @Validated AccountInfo accountInfo,
			BindingResult result) {
		accountInfoValidator.validate(accountInfo, result);
		if (result.hasErrors()) {
			return "account";
		}
		try {
			accountDAO.saveAccountInfo(accountInfo);
		} catch (Exception e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "account";
		}
		return "redirect:/accountList";
	}
	
	@RequestMapping(value = {"/editAccount"}, method = RequestMethod.GET)
	public String editaccount (Model model, @RequestParam(value = "username", defaultValue = "") String username) {
		Account account = null;
		if (username != null) {
			account = accountDAO.getAccountByUserName(username);
		}
		if (account == null) {
			account = new Account();
		}
		model.addAttribute("editaccountForm", account);
		return "editaccount";
	}
	
	@RequestMapping(value = {"/editAccount"}, method = RequestMethod.POST)
	public String editaccountSave(Model model, @ModelAttribute("editaccountForm") @Validated AccountInfo accountInfo,
			BindingResult result) {
		if (result.hasErrors()) {
			return "editaccount";
		}
		try {
			accountDAO.saveAccountInfo(accountInfo);
		} catch (Exception e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "editaccount";
		}
		return "redirect:/accountList";
	}

	@RequestMapping({ "/removeAccount" })
	public String removeAccountHandler(HttpServletRequest request, Model model,
			@RequestParam(value = "username", defaultValue = "") String username) {
		Account account = null;

		if (username != null) {
			account = accountDAO.getAccountByUserName(username);
		}

		if (account != null) {
			accountDAO.removeAccountByUsername(username);
		}
		return "redirect:/accountList";
	}

	@RequestMapping({ "/removeProduct" })
	public String removeProductHandler(HttpServletRequest request, Model model,
			@RequestParam(value = "code", defaultValue = "") String code) {
		Product product = null;

		if (code != null) {
			product = productDAO.getProductByCode(code);
		}

		if (product != null) {
			productDAO.removeProductByCode(code);
		}
		return "redirect:/manageProductList";
	}
	
	@RequestMapping({"/changeOrder"})
	public String cancelOrder(HttpServletRequest request, Model model,
			@RequestParam(value = "status", defaultValue = "") String status,  
			@RequestParam(value = "orderId", defaultValue = "") String orderId) {
		Order order = null;
		
		if(status.equals("SHIPPED")) {
			return "redirect:/orderList";
		}
		if(status.equals("CANCELLED")) {
			return "redirect:/orderList";
		}
		if(status.equals("SHIPPING")) {
			order = orderDAO.getOrderById(orderId);
			orderDAO.updateOrderStatus2(orderId);
		}
		String username = order.getUserAccount().getUsername();
		return "redirect:/orderList?username=" + username;//chỗ này chưa gọi đc do thiếu @
		
	}
	
	/*
	@RequestMapping({"/cancelOrderAdmin"})
	public String cancelOrderAdmin(HttpServletRequest request, Model model,
			@RequestParam(value = "status", defaultValue = "") String status,  
			@RequestParam(value = "orderId", defaultValue = "") String orderId) {
		Order order = null;
	
		if(status.equals("SHIPPED")) {
			return "redirect:/orderList";
		}
		if(status.equals("CANCELLED")) {
			return "redirect:/orderList";
		}
		if(status.equals("SHIPPING")) {
			order = orderDAO.getOrderById(orderId);
			orderDAO.updateOrderStatus(orderId);
		}
		String username = order.getUserAccount().getUsername();
		return "redirect:/myOrderList?username=" + username;//chỗ này chưa gọi đc do thiếu @
		
	}*/
	
	@RequestMapping(value = "/type", method = RequestMethod.GET)
	public String inputType(Model model, @RequestParam(value = "id", defaultValue = "") String id) {
		Type type = null;
		if(id != null) {
			type = typeDAO.getAllTypeById(id);
		}
		if(type == null) {
			type = new Type();
		}
		model.addAttribute("typeForm", type);
		return "type";
		
	}
	
	@RequestMapping(value = "/type", method = RequestMethod.POST)
	public String inputTypeSave(Model model, @ModelAttribute("typeForm") TypeInfo typeInfo, 
			BindingResult result) {
		
		if(result.hasErrors()) {
			return "type";
		}try {
			typeDAO.saveTypeInfo(typeInfo);
		}catch(Exception e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "type";
		}
		return "redirect:/typeList";
		
	}
	
	
	@RequestMapping(value = {"/editType"}, method = RequestMethod.GET)
	public String edittype (Model model, @RequestParam(value = "id", defaultValue = "") String id) {
		Type type = null;
		if (id != null) {
			type = typeDAO.getAllTypeById(id);
		}
		if (type == null) {
			type = new Type();
		}
		model.addAttribute("editTypeForm", type);
		return "editType";
	}
	
	@RequestMapping(value = {"/editType"}, method = RequestMethod.POST)
	public String edittypeSave(Model model, @ModelAttribute("editTypeForm") @Validated TypeInfo typeInfo,
			BindingResult result) {
		if (result.hasErrors()) {
			return "editType";
		}
		try {
			typeDAO.saveTypeInfo(typeInfo);
		} catch (Exception e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "editType";
		}
		return "redirect:/typeList";
	}
	

	@RequestMapping(value = { "/typeList" }, method = RequestMethod.GET)
	public String typeList(Model model, @RequestParam(value = "page", defaultValue = "1") String pageStr) {
		int page = 1;
		try {
			page = Integer.parseInt(pageStr);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		final int MAX_RESULT = 10;
		final int MAX_NAVIGATION_PAGE = 10;
		PaginationResult<TypeInfo> paginationTypeInfos = typeDAO.getAllTypeInfos(page, MAX_RESULT,
				MAX_NAVIGATION_PAGE);
		model.addAttribute("paginationTypeInfos", paginationTypeInfos);
		return "typeList";
	}
	
	@RequestMapping(value = "/producer", method = RequestMethod.POST)
	public String inputProducerSave(Model model, @ModelAttribute("producerForm") ProducerInfo producerInfo, 
			BindingResult result) {
		producerValidator.validate(producerInfo, result);
		if(result.hasErrors()) {
			return "producer";
		}try {
			producerDAO.saveProducerInfo(producerInfo);
		}catch(Exception e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "producer";
		}
		return "redirect:/producerList";
		
	}
	@RequestMapping(value = "/producer", method = RequestMethod.GET)
	public String inputProducer(Model model, @RequestParam(value = "producerid", defaultValue = "") String producerid) {
		Producer producer = null;
		if(producerid != null) {
			producer = producerDAO.getProducerById2(producerid);
		}
		if(producerid.isEmpty()) {
			producer = new Producer();
		}
		model.addAttribute("producerForm", producer);
		return "producer";
		
	}
	
	@RequestMapping(value = { "/producerList" }, method = RequestMethod.GET)
	public String producerList(Model model, @RequestParam(value = "page", defaultValue = "1") String pageStr,
			@RequestParam(value = "producername", defaultValue = "") String producername,
			@RequestParam(value = "country", defaultValue = "") String country) {
		int page = 1;
		try {
			page = Integer.parseInt(pageStr);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		final int MAX_RESULT = 10;
		final int MAX_NAVIGATION_PAGE = 10;
		PaginationResult<ProducerInfo> paginationProducerInfos = producerDAO.getAllProducerInfos(page, MAX_RESULT,
				MAX_NAVIGATION_PAGE, producername, country);
		model.addAttribute("paginationProducerInfos", paginationProducerInfos);
		return "producerList";
	}
	
	
}
