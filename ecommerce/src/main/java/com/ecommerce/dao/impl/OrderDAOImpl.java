package com.ecommerce.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.dao.OrderDAO;
import com.ecommerce.dao.ProductDAO;
import com.ecommerce.entity.Order;
import com.ecommerce.entity.OrderDetail;
import com.ecommerce.entity.Product;
import com.ecommerce.model.CartInfo;
import com.ecommerce.model.CartLineInfo;
import com.ecommerce.model.CustomerInfo;
import com.ecommerce.model.GooglePojo;
import com.ecommerce.model.OrderDetailInfo;
import com.ecommerce.model.OrderInfo;
import com.ecommerce.model.PaginationResult;

@Repository
@Transactional
public class OrderDAOImpl implements OrderDAO {

	@Autowired
	private ProductDAO productDAO;
	
	GooglePojo googlePojo;

	@Autowired
	private SessionFactory sessionFactory;

	private int getMaxOrderNum() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "SELECT MAX(ORD.orderNum) FROM Order ORD";
		Query<Integer> query = session.createQuery(hql);
		Integer value = (Integer) query.uniqueResult();
		if (value == null) {
			return 0;
		}
		return value;
	}

	@Override
	public void saveOrder(CartInfo cartInfo) {
		Session session = sessionFactory.getCurrentSession();
		int orderNum = getMaxOrderNum() + 1;

		Order order = new Order();
		order.setId(UUID.randomUUID().toString());
		order.setOrderNum(orderNum);
		order.setOrderDate(new Date());
		order.setAmount(cartInfo.getAmountTotal());

		CustomerInfo customerInfo = cartInfo.getCustomerInfo();
		order.setCustomerName(customerInfo.getName());
		order.setCustomerEmail(googlePojo.getEmail());
		order.setCustomerPhone(customerInfo.getPhone());
		order.setCustomerAddress(customerInfo.getAddress());
		session.persist(order);

		List<CartLineInfo> cartLineInfos = cartInfo.getCartLineInfos();
		for (CartLineInfo cartLineInfo : cartLineInfos) {
			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setId(UUID.randomUUID().toString());
			orderDetail.setOrder(order);
			orderDetail.setAmount(cartLineInfo.getAmount());
			orderDetail.setPrice(cartLineInfo.getProductInfo().getPrice());
			orderDetail.setQuantity(cartLineInfo.getQuantity());

			String code = cartLineInfo.getProductInfo().getCode();
			Product product = productDAO.getProductByCode(code);
			orderDetail.setProduct(product);

			session.persist(orderDetail);
		}
		cartInfo.setOrderNum(orderNum);
	}

	@Override
	public PaginationResult<OrderInfo> getAllOrderInfos(int page, int maxResult, int maxNavigationPage) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "SELECT NEW " + OrderInfo.class.getName()
				+ " (ORD.id, ORD.orderDate, ORD.orderNum, ORD.amount, ORD.customerName, ORD.customerAddress, "
				+ "ORD.customerEmail, ORD.customerPhone) FROM Order ORD ORDER BY ORD.orderNum DESC";
		Query<OrderInfo> query = session.createQuery(hql);
		return new PaginationResult<OrderInfo>(query, page, maxResult, maxNavigationPage);
	}

	@Override
	public OrderInfo getOrderInfoById(String orderId) {
		Order order = getOrderById(orderId);
		if (order == null) {
			return null;
		}

		OrderInfo orderInfo = new OrderInfo(order.getId(), order.getOrderDate(), getMaxOrderNum(), order.getAmount(),
				order.getCustomerName(), order.getCustomerAddress(), order.getCustomerEmail(),
				order.getCustomerPhone());
		return orderInfo;
	}

	@Override
	public List<OrderDetailInfo> getAllDetailInfos(String orderId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "SELECT NEW " + OrderDetailInfo.class.getName() + " (ORD.id, ORD.product.code, ORD.product.name, "
				+ "ORD.quantity, ORD.price, ORD.amount) FROM OrderDetail ORD WHERE ORD.order.id = :ORDERID";
		Query<OrderDetailInfo> query = session.createQuery(hql);
		query.setParameter("ORDERID", orderId);
		List<OrderDetailInfo> orderDetailInfos = query.list();

		return orderDetailInfos;
	}

	@Override
	public Order getOrderById(String orderId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "SELECT ORD FROM Order ORD WHERE ORD.id = :ORDERID";
		Query<Order> query = session.createQuery(hql);
		query.setParameter("ORDERID", orderId);
		Order order = (Order) query.uniqueResult();

		return order;
	}

}
