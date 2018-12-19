package cn.erp.service.impl;

import java.sql.SQLException;
import java.util.List;

import cn.erp.dao.CustomerDao;
import cn.erp.dao.SaleListDao;
import cn.erp.dao.UserDao;
import cn.erp.dao.impl.CustomerDaoImpl;
import cn.erp.dao.impl.SaleListDaoImpl;
import cn.erp.dao.impl.UserDaoImpl;
import cn.erp.domain.Customer;
import cn.erp.domain.SaleList;
import cn.erp.domain.User;
import cn.erp.service.SaleListService;

public class SaleListServiceImpl implements SaleListService{

	private SaleListDao saleListDao = new SaleListDaoImpl();
	private CustomerDao customerDao = new CustomerDaoImpl();
	private UserDao userDao = new UserDaoImpl();

	@Override
	public int addSaleList(SaleList saleList) throws SQLException {
		return saleListDao.insertSaleList(saleList);
	}

	@Override
	public List<SaleList> selectByCondition(String sale_number,Integer customer_id,Integer state,String bSaleDate,String eSaleDate) throws SQLException {
		List<SaleList> list = saleListDao.selectByCondition(sale_number,customer_id,state,bSaleDate,eSaleDate);
		for (SaleList saleList : list) {
			Customer customer = customerDao.findById(saleList.getCustomer_id());
			saleList.setCustomer(customer);
			User user = userDao.findUserById(saleList.getUser_id());
			saleList.setUser(user);
		}
		return list;
	}
	

}
