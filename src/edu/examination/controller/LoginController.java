package edu.examination.controller;

import java.util.List;

import edu.examination.dao.AdminDao;
import edu.examination.dao.InstituationDao;
import edu.examination.dao.UserDao;
import edu.examination.dao.impl.AdminDaoImp;
import edu.examination.dao.impl.InstituationDaoImpl;
import edu.examination.dao.impl.UserDaoImpl;
import edu.examination.entity.AdminEntity;
import edu.examination.entity.InstituationEntity;
import edu.examination.entity.UserEntity;

public class LoginController {

	private InstituationDao instituation;
	private UserDao user;
	private AdminDao admin;
	protected String role;
	
	public boolean login(String email, String password){
		boolean succesfulLogin = false;
		if(role.equals("Admin")){
			admin = new AdminDaoImp();
			succesfulLogin = loginAdmin(email, password);
		}else if(role.equals("Instituation")){
			instituation = new InstituationDaoImpl();
			succesfulLogin = loginInstituation(email, password);
		}else if(role.equals("User")){
			user = new UserDaoImpl();
			succesfulLogin = loginUser(email, password);
		}
		return succesfulLogin;
	}
	
	public boolean loginAdmin(String email, String password) {
		List<AdminEntity> adminList = admin.getAllAdmins();
		for (AdminEntity currentAdmin : adminList) {
			String currentAdminEmail = currentAdmin.getAdminEmailAddress();
			if (currentAdminEmail.equals(email)) {
				String currentAdminPassword = currentAdmin.getAdminPassword();
				if (currentAdminPassword.equals(password)) {
					return true;
				} 
			}
		}
		return false;
	}
	
	public boolean loginInstituation(String email, String password) {
		List<InstituationEntity> instituationList = instituation.getAllInstituations();
		for (InstituationEntity currentInstitute : instituationList) {
			String currentInstiEmail = currentInstitute.getInstiEmailAddress();
			if (currentInstiEmail.equals(email)) {
				String currentInstiPassword = currentInstitute.getInstiPassword();
				if (currentInstiPassword.equals(password)) {
					return true;
				}
			}
		}
		return false;
	}
	
	
	public boolean loginUser(String email, String password) {
		List<UserEntity> userList = user.getAllUsers();
		for (UserEntity currentUser : userList) {
			String currentUserEmail = currentUser.getUserEmailAddress();
			if (currentUserEmail.equals(email)) {
				String currentInstiPassword = currentUser.getUserPassword();
				if (currentInstiPassword.equals(password)) {
					return true;
				}
			}
		}
		return false;
	}
	
	
}
