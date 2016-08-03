package com.library.web.dao;

import java.util.List;

import com.library.web.bean.Book;

public interface BookDao {
	public boolean addbook(Book book);
	public List<Book> findAllbook();
	public List<Book> findOnebook(String bookname);
	public boolean changebook(Book book);
	public boolean deletebook(int bookid);
	public Book findForbookid(int bookid);
	public Book findForbookisbn(String bookisbn);
}
