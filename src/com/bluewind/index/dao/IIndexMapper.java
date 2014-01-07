package com.bluewind.index.dao;

import java.util.List;
import java.util.Map;

/**
 * index 数据接口
 * @author chenz
 *
 */
public interface IIndexMapper {

	public List<Map> queryAddress(Map map);
	
	public List<Map> queryAddressForPage(Map map);
}
