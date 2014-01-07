package com.common.mybatis.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;


/**
 * 描述
 * 
 * @作者 liuyt
 * @创建日期 2012-9-14
 * @版本 V 1.0
 */
public class IntBooleanTypeHandler implements TypeHandler {

	/* (non-Javadoc)
	 * @see org.apache.ibatis.type.TypeHandler#setParameter(java.sql.PreparedStatement, int, java.lang.Object, org.apache.ibatis.type.JdbcType)
	 */
	@Override
	public void setParameter(PreparedStatement ps, int i, Object parameter,
			JdbcType jdbcType) throws SQLException {
		// TODO Auto-generated method stub
		int value=Integer.parseInt((String) parameter);
		if(value==0)
			ps.setBoolean(i, false);
		else
			ps.setBoolean(i, true);
	}

	/* (non-Javadoc)
	 * @see org.apache.ibatis.type.TypeHandler#getResult(java.sql.ResultSet, java.lang.String)
	 */
	@Override
	public Object getResult(ResultSet rs, String columnName)
			throws SQLException {
		// TODO Auto-generated method stub
		
		int value=rs.getInt(columnName);
		if(value==1)
			return true;
		else 
			return false;
	}

	/* (non-Javadoc)
	 * @see org.apache.ibatis.type.TypeHandler#getResult(java.sql.ResultSet, int)
	 */
	@Override
	public Object getResult(ResultSet rs, int columnIndex) throws SQLException {
		// TODO Auto-generated method stub
		int value=rs.getInt(columnIndex);
		if(value==0)
			return false;
		else
			return true;
	}

	/* (non-Javadoc)
	 * @see org.apache.ibatis.type.TypeHandler#getResult(java.sql.CallableStatement, int)
	 */
	@Override
	public Object getResult(CallableStatement cs, int columnIndex)
			throws SQLException {
		// TODO Auto-generated method stub
		int value=cs.getInt(columnIndex);
		if(value==0)
			return false;
		else
			return true;
	}

	

}
