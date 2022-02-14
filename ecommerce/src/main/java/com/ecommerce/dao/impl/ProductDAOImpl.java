package com.ecommerce.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.dao.ProductDAO;
import com.ecommerce.dao.TypeDAO;
import com.ecommerce.entity.Product;
import com.ecommerce.entity.Type;
import com.ecommerce.model.PaginationResult;
import com.ecommerce.model.ProductInfo;
import com.ecommerce.model.TypeInfo;


@Repository
@Transactional
public class ProductDAOImpl implements ProductDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private TypeDAO typeDAO;
	
	TypeInfo typeInfo;
	
	@Override
	public PaginationResult<ProductInfo> getAllProductInfos(int page, int maxResult, int maxNavigationPage,
			String likeName) {
		Session session = sessionFactory.getCurrentSession();
		String hql = " SELECT NEW " + ProductInfo.class.getName() + " (PRO.code, PRO.name, PRO.type, PRO.price) FROM Product PRO ";
		if (likeName != null && likeName.length() > 0) {
			hql += " WHERE LOWER(PRO.name) like :LIKENAME ";
		}
		hql += " ORDER BY PRO.createDate DESC ";

		Query<ProductInfo> query = session.createQuery(hql);

		if (likeName != null && likeName.length() > 0) {
			query.setParameter("LIKENAME", "%" + likeName.toLowerCase() + "%");
		}
		List<ProductInfo> productInfos = query.list();
		
		return new PaginationResult<ProductInfo>(query, page, maxResult, maxNavigationPage);
	}

	@Override
	public Product getProductByCode(String code) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "SELECT PRO FROM Product PRO WHERE PRO.code = :CODE";
		Query<Product> query = session.createQuery(hql);
		query.setParameter("CODE", code);
		Product product = (Product) query.uniqueResult();
		return product;
	}

	@Override
	public ProductInfo getProductInfoByCode(String code) {
		Product product = getProductByCode(code);
		if (product == null) {
			return null;
		}
		ProductInfo productInfo = new ProductInfo(product.getCode(), product.getName(), product.getType() ,product.getPrice());
		return productInfo;
	}

	@Override
	public void saveProductInfo(ProductInfo productInfo) {
		Session session = sessionFactory.getCurrentSession();
		String code = productInfo.getCode();
		Product product = null;
		boolean isNew = false;

		if (code != null) {
			product = getProductByCode(code);
		}
		if (product == null) {
			isNew = true;
			product = new Product();
			product.setCreateDate(new Date());
		}
		product.setCode(code);
		product.setName(productInfo.getName());
		product.setType(productInfo.getTypes().get(0));//get(0) -->lấy phần tử đầu tiên
		//productInfo.getTypes() là 1 mảng kiểu Type
		//giờ e muốn lấy ra phần tử Type đầu tiên thì dùng productInfo.getTypes().get(0)
		//thấy số 0 là phần tử đầu tiên ko? dạ có thầy
		//sau khi lấy đc Type thì 3 set vào product.setType là xong
		product.setPrice(productInfo.getPrice());

		if (productInfo.getFileData() != null) {
			byte[] image = productInfo.getFileData().getBytes();
			if (image != null && image.length > 0) {
				product.setImage(image);
			}
		}
		if (isNew) {
			session.persist(product);
		}
		session.flush();
	}

	public boolean removeProductByCode(String code) {
		try {
			Session session = sessionFactory.getCurrentSession();
			String hql = "DELETE FROM Product PRO WHERE PRO.code = :code1";
			Query query = session.createQuery(hql);
			query.setParameter("code1", code);
			query.executeUpdate();
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	

}