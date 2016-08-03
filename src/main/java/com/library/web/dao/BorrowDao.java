package com.library.web.dao;

import java.util.List;

import com.library.web.bean.Borrow;

public interface BorrowDao {
	public List<Borrow> findAll();
	public List<Borrow> finnUserborrow(int userid);
	public boolean delete(int borrowid);
	public boolean add(Borrow borrow);
	public boolean change(Borrow borrow);
}
