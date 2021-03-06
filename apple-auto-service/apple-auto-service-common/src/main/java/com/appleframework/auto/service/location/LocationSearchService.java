package com.appleframework.auto.service.location;

import java.util.List;

import com.appleframework.auto.bean.location.Location;
import com.appleframework.exception.ServiceException;

/**
 * 轨迹搜索
 *
 */
public interface LocationSearchService {
	
	public List<Location> search(String account, long startTime, long endTime, int mapType) throws ServiceException;
	
}
