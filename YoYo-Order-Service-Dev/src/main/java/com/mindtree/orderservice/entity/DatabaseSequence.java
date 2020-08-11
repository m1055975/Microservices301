package com.mindtree.orderservice.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "databaseSequences")
public class DatabaseSequence {
	 @Id
	 private String orderId;
	 
	 private long seq;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public long getSeq() {
		return seq;
	}

	public void setSeq(long seq) {
		this.seq = seq;
	}

	public DatabaseSequence() {
		super();
		// TODO Auto-generated constructor stub
	}
	 
	 
}
