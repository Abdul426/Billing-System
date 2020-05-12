package com.ibm.ro.BillingSystem.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ibm.ro.BillingSystem.model.Bill;

@Repository
public interface BillRepo extends JpaRepository<Bill, Long> {

}
