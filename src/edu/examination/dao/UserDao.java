package edu.examination.dao;

import java.util.List;
import edu.examination.entity.UserEntity;

public interface UserDao {

	public List<UserEntity> getAllUsers();
	public int addUser(UserEntity newUser);
	public UserEntity getUserByUserEmail(String userEmail);
}
