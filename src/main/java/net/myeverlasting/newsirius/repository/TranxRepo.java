package net.myeverlasting.newsirius.repository;

import net.myeverlasting.newsirius.entity.Transaction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface TranxRepo extends JpaRepository<Transaction, Long> {
	
	Transaction findByTxnref(String txnref);
	

}
