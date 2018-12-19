package cn.erp.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.erp.dao.GoodsDao;
import cn.erp.domain.Goods;
import cn.erp.utils.C3P0Util;


public class GoodsDaoImpl implements GoodsDao {

	@Override
	public List<Goods> findAll(Integer page,Integer rows,Integer type_id,String codeOrName) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String sql ="";
		List<Goods> list=new ArrayList<>();
		if(type_id==null&&("".equals(codeOrName)||codeOrName==null)){
			sql = "select * from t_goods limit ?,?";
			list =  qr.query(sql,new BeanListHandler<Goods>(Goods.class),(page-1)*rows,rows);
		}
		else if(type_id==null&&(!"".equals(codeOrName)||codeOrName!=null)){
			sql = "select * from t_goods where code like ? or name like ? limit ?,?";
			list =  qr.query(sql,new BeanListHandler<Goods>(Goods.class),"%"+codeOrName+"%","%"+codeOrName+"%",(page-1)*rows,rows);
		}else if(type_id!=null&&("".equals(codeOrName)||codeOrName==null)){
			sql = "select * from t_goods where type_id=? limit ?,?";
			list =  qr.query(sql,new BeanListHandler<Goods>(Goods.class),type_id,(page-1)*rows,rows);
		}else if(type_id!=null&&(!"".equals(codeOrName)||codeOrName!=null)){
			sql = "select * from t_goods where type_id=? and code like ? or name like ? limit ?,?";
			list =  qr.query(sql,new BeanListHandler<Goods>(Goods.class),type_id,"%"+codeOrName+"%","%"+codeOrName+"%",(page-1)*rows,rows);
		}
		return list;
	}
	
	@Override
	public List<Goods> findAll() throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		String sql = "select * from t_goods";
		List<Goods> list =  qr.query(sql,new BeanListHandler<Goods>(Goods.class));
		return list;
	}

	@Override
	public int count() throws SQLException {
		return findAll().size();
	}

}
