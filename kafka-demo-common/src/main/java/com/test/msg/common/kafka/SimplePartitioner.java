package com.test.msg.common.kafka;

import kafka.producer.Partitioner;
import kafka.utils.VerifiableProperties;

public class SimplePartitioner implements Partitioner {

	public SimplePartitioner (VerifiableProperties props) {
	}

	@Override
	public int partition(Object key, int numPartitions) {
//		System.out.println("-----SimplePartitioner_key---"+key);
//		System.out.println("-----SimplePartitioner_numPartitions---"+numPartitions);
		return (key.hashCode() & 0x0FFFFFFF) % numPartitions;
	}

}
