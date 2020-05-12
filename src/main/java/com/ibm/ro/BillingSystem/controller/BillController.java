package com.ibm.ro.BillingSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.ro.BillingSystem.model.Bill;
import com.ibm.ro.BillingSystem.service.BillService;

@RestController
@RequestMapping("/bills")
public class BillController {

	@Autowired
	BillService billService;

	@GetMapping("/{id}")
	public Bill getBill(@PathVariable("id") Long id) {
		Bill bill = billService.getBill(id);
		return bill;
	}

	@PostMapping
	public Bill createBill(@RequestBody Bill bill) {
		Bill bill2 = billService.create(bill);
		return bill2;
	}
}
