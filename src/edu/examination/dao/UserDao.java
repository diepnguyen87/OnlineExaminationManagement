package edu.examination.dao;

import java.util.List;
import edu.examination.entity.UserEntity;

public interface UserDao {

	public List<UserEntity> getAllUsers();
	public UserEntity getUser(int userID);
	public void addUser(UserEntity newUser);
}
