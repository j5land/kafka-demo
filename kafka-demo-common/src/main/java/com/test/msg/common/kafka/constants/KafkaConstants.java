package com.test.msg.common.kafka.constants;


/**
 * 
 * 电影票kafka常量
 * @author j5land
 *
 * 2016-4-19 下午3:12:31
 * 
 */
public interface KafkaConstants {
	//场次同步group
	String show_sync_group="showSync_group";
	
	//场次同步topic
	String show_sync_topic="showSync_topic";
	
	//影院同步group
	String cinema_sync_group="cinemaSync_group";
	
	//影院同步topic
	String cinema_sync_topic="cinemaSync_topic";	
}
