package com.test.msg.common.kafka.handle;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;



/**
 * 
 *
 * 影院信息监听,监听到信息变化修改本地内存
 *
 * 2016-4-18 下午2:03:34
 * 
 */
@Service
public class CinemaMessageHandle extends AbstractMessageHandler<String>{
	
	private static Logger logger=Logger.getLogger(CinemaMessageHandle.class);
	
	@Override
	public void executeSync(String string,String message) {
		//回调中更新信息-update
		logger.info("string="+string);
	}
	
	@Override
	public Class<?> getObjectClass() {
		return String.class;
	}
}
