package com.common.mybatis.typehandler;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

/**
 * Map Blob TypeHandler数据库字段类型转换
 * @author liuyt
 * @date Aug 16, 2012 10:59:51 AM
 * @filename MapBlobTypeHandler.java
 */

public class MapBlobTypeHandler implements TypeHandler<Map> {

	public Map getResult(ResultSet rs, String name) throws SQLException {
		// TODO Auto-generated method stub
		Map map=null;
		Blob data=rs.getBlob(name);;
		if (data!=null){
			try {
				ObjectInputStream oi=new ObjectInputStream(data.getBinaryStream());
				map=(Map) oi.readObject();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return map;
	}

	
	public Map getResult(ResultSet rs, int index) throws SQLException {
		// TODO Auto-generated method stub
		Map map=null;
		Blob data=rs.getBlob(index);
		if (data!=null){
			try {
				ObjectInputStream oi=new ObjectInputStream(data.getBinaryStream());
				map=(Map) oi.readObject();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return map;
	}

	
	public Map getResult(CallableStatement cs, int index) throws SQLException {
		// TODO Auto-generated method stub
		
		Map map=null;
		Blob data=cs.getBlob(index);
		if (data!=null){
			try {
				ObjectInputStream oi=new ObjectInputStream(data.getBinaryStream());
				map=(Map) oi.readObject();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return map;
	}



	
	public void setParameter(PreparedStatement ps, int i, Map para,
			JdbcType arg3) throws SQLException {
		ps.setObject(i, para);
		
	}

}
