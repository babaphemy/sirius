package net.myeverlasting.newsirius.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.myeverlasting.newsirius.entity.Transaction;
import net.myeverlasting.newsirius.repository.TranxRepo;
@Service
public class TranxservImpl implements TranxServ {
	@Autowired
	private TranxRepo tranxRepo;

	@Override
	public void addTranx(Transaction tr) {
		// TODO Auto-generated method stub
		tranxRepo.save(tr);

	}

	@Override
	public List<Transaction> allTranx() {
		// TODO Auto-generated method stub
		return tranxRepo.findAll();
	}

	@Override
	public Transaction byTref(String tref) {
		// TODO Auto-generated method stub
		return tranxRepo.findByTxnref(tref);
	}

}
