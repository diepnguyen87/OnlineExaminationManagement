package edu.examination.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.examination.config.ConnectionFactory;
import edu.examination.dao.InstituationDao;
import edu.examination.entity.InstituationEntity;

public class InstituationDaoImpl implements InstituationDao{

	private Connection connection = null;
	private PreparedStatement ptmt = null;
	private ResultSet resultSet = null;
	
	public InstituationDaoImpl() {
		
	}
	
	private Connection getConnection() throws SQLException{
		Connection conn;
		conn = ConnectionFactory.getInstance().getConnection();
		return conn;
	}
	
	@Override
	public int addInstituation(InstituationEntity insti) {
		int addedRows=0;
		try{
			String queryString = "Insert into instituation(insti_email_address, insti_password) "
								+ "values(?,?)";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			ptmt.setString(1, insti.getInstiEmailAddress());
			ptmt.setString(2, insti.getInstiPassword());
			addedRows = ptmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try{
				if(ptmt != null){
					ptmt.close();
				}
				if(connection != null){
					connection.close();
				}
			}catch(SQLException e){
				e.printStackTrace();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return addedRows;
	}

	@Override
	public List<InstituationEntity> getAllInstituations() {
		try{
			String queryString = "Select * from instituation";
			connection = getConnection();
			ptmt = connection.prepareStatement(queryString);
			resultSet = ptmt.executeQuery();
			List<InstituationEntity> instituationList = new ArrayList<InstituationEntity>();
			int i = 0;
			while(resultSet.next()){
				InstituationEntity currentInsti = new InstituationEntity();
				currentInsti.setInstiEmailAddress(resultSet.getString("insti_email_address"));
				currentInsti.setInstiPassword(resultSet.getString("insti_password"));
				instituationList.add(currentInsti);
				i++;
			}
			return instituationList;
		}catch(SQLException e){
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try{
				if(resultSet != null){
					resultSet.close();
				}
				if(ptmt != null){
					ptmt.close();
				}
				if(connection != null){
					connection.close();
				}
			}catch(SQLException e){
				e.printStackTrace();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public InstituationEntity getInstituation(int instiID) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
	

}
