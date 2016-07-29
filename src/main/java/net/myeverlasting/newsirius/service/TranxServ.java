package net.myeverlasting.newsirius.service;

import java.util.List;

import net.myeverlasting.newsirius.entity.Transaction;

public interface TranxServ {
	public void addTranx(Transaction tr);
	
	public List<Transaction> allTranx();
	
	public Transaction byTref(String tref);
}
