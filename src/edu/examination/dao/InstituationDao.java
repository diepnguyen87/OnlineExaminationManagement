package edu.examination.dao;

import java.util.List;
import edu.examination.entity.InstituationEntity;

public interface InstituationDao {
	
	public int addInstituation(InstituationEntity insti);
	public List<InstituationEntity> getAllInstituations();
	public InstituationEntity getInstituation(int instiID);
	public String getInstituationID(String instiEmail);
}
