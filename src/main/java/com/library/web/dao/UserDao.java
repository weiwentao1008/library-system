package com.library.web.dao;

import java.util.List;

import com.library.web.bean.User;

public interface UserDao {
	public User findById(int userid);
	public List<User> findAll();
	public boolean changeUser(User user);
	public boolean addUser(User user);
	public boolean deleteUser(int userid);
}
