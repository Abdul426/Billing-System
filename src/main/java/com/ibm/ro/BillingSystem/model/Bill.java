package com.ibm.ro.BillingSystem.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Bills", schema = "BILLING")
public class Bill {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	private Float amount;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Bill [Id=" + Id + ", amount=" + amount + "]";
	}

}
