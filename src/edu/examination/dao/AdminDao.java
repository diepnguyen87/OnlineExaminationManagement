package edu.examination.dao;

import java.util.List;
import edu.examination.entity.AdminEntity;

public interface AdminDao {

	public List<AdminEntity> getAllAdmins();
	public AdminEntity getUser(int adminID);
}
