package com.appleframework.auto.service.fence.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.appleframework.auto.bean.fence.Fence;
import com.appleframework.auto.service.fence.FenceInfoService;
import com.appleframework.auto.service.fence.KDTreeService;
import com.appleframework.exception.ServiceException;
import com.hazelcast.core.HazelcastInstance;

@Service("fenceInfoService")
public class FenceInfoServiceImpl implements FenceInfoService {

	protected final static Logger logger = Logger.getLogger(FenceInfoServiceImpl.class);
	
	private final static String KEY_FENCE = "KEY_FENCE";

	@Resource
	private HazelcastInstance hazelcastInstance;
	
	@Resource
	private KDTreeService kdtreeService;

	@Override
	public void create(Fence fence) throws ServiceException {
		Map<String, Fence> map = this.get();
		String id = fence.getId();
		Fence oldFence = map.get(id);
		if(null == oldFence) {
			kdtreeService.create(fence);
		}
		else {
			kdtreeService.update(oldFence, fence);
		}
		map.put(id, fence);
	}
	
	@Override
	public void update(Fence fence) throws ServiceException {
		this.create(fence);
	}
		
	
	@Override
	public Map<String, Fence> get() throws ServiceException {
		return hazelcastInstance.getMap(KEY_FENCE);
	}

}