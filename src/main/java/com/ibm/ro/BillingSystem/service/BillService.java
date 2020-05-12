package com.ibm.ro.BillingSystem.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.ro.BillingSystem.model.Bill;
import com.ibm.ro.BillingSystem.repo.BillRepo;

@Service
public class BillService {

	@Autowired
	BillRepo billRepo;

	public Bill getBill(Long id) {
		Optional<Bill> optional = billRepo.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	public Bill create(Bill bill) {
		Bill save = billRepo.save(bill);
		return save;
	}

}
