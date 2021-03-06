package cn.erp.dao.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import cn.erp.dao.UserDao;
import cn.erp.domain.User;
import cn.erp.utils.C3P0Util;

public class UserDaoImpl implements UserDao{

	@Override
	public User findUserById(Integer id) throws SQLException {
		String sql = "select * from t_user where id=?";
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		return qr.query(sql,new BeanHandler<User>(User.class),id);
	}

}
