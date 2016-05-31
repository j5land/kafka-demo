package com.test.msg.common.kafka.handle;

import org.apache.log4j.Logger;

import com.test.msg.common.kafka.consumer.handle.MessageHandler;
import com.test.util.JsonHelper;

public abstract class AbstractMessageHandler<T extends Object> implements MessageHandler {
	private static Logger logger=Logger.getLogger(AbstractMessageHandler.class);
	
	@SuppressWarnings("unchecked")
	@Override
	public void handle(String message) {
		try {
			//执行同步
			logger.debug("cinemaMessageHandle message="+message);
			T object=null;
			//json
			if(message.startsWith("{")){
				object=(T) JsonHelper.getObject(message,getObjectClass());
			}else{
				object=(T) message;
			}
			executeSync(object,message);
		} catch (Exception e) {
			logger.error("handle exception=",e);
		}
	}
	
	/**
	 * 进行同步
	 * @param t void
	 *
	*/
	public abstract void executeSync(T t,String message);
	
	/**
	 * 获取对象对应的类
	 * @return Class
	 *
	*/
	public abstract Class<?> getObjectClass();
	
}
