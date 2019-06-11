package com.demo.service.Impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;

import com.demo.service.DashboardService;

@Service("DashboardService")
public class DashboardServiceImpl implements DashboardService{
	
	@PersistenceContext
    protected EntityManager entityManager;
	
	public List<Object[]> finddis(Integer caremaId, String queryTime) {
		// TODO Auto-generated method stub
		return entityManager.createNativeQuery("SELECT t.*, p.NAME, p.sex,fp.PIC_PATH, p.PERSON_IMG, ru.match_percent, ci.TAKE_TIME, fc.DETAIL_ADDRESS,p.PIC_LIB_ID FROM fla_recognition_img t, fla_reco_img_result ru, fla_person_info p, fla_camera_img ci,FLA_PICLIB fp,FLA_CAMERA fc WHERE t.id = ru.reco_img_id AND t.source_img = ci.id AND ru.match_person = p.id(+) AND (ru.match_percent IS NULL OR ru.match_percent = (SELECT MAX(match_percent) FROM fla_reco_img_result WHERE reco_img_id = t.id)) and p.PIC_LIB_ID=fp.id AND ci.CAMERA_ID in (-1,"+ caremaId + ") AND ci.TAKE_TIME > to_date('"+ queryTime +"', 'yyyy-mm-dd HH24:mi:ss')  and ci.CAMERA_ID=fc.id   ORDER BY ci.TAKE_TIME ").getResultList();
	}
	
	public List<Object> findPICLIBPath(Integer caremaId) {
		// TODO Auto-generated method stub
		return entityManager.createNativeQuery("SELECT fp.PIC_PATH FROM fla_recognition_img t, fla_reco_img_result ru, fla_person_info p, fla_camera_img ci,FLA_PICLIB fp,FLA_CAMERA fc WHERE t.id = ru.reco_img_id AND t.source_img = ci.id AND ru.match_person = p.id(+) AND (ru.match_percent IS NULL OR ru.match_percent = (SELECT MAX(match_percent) FROM fla_reco_img_result WHERE reco_img_id = t.id)) and p.PIC_LIB_ID=fp.id AND ci.CAMERA_ID in (-1,"+ caremaId + ") and ci.CAMERA_ID=fc.id GROUP BY PIC_PATH").getResultList();
	}

	@Override
	public List<Object> findimgpath(Integer caremaId) {
		// TODO Auto-generated method stub
		return entityManager.createNativeQuery("SELECT t.RECOGNITION_IMG_PATH FROM fla_recognition_img t, fla_reco_img_result ru, fla_person_info p, fla_camera_img ci,FLA_PICLIB fp,FLA_CAMERA fc WHERE t.id = ru.reco_img_id AND t.source_img = ci.id AND ru.match_person = p.id(+) AND (ru.match_percent IS NULL OR ru.match_percent = (SELECT MAX(match_percent) FROM fla_reco_img_result WHERE reco_img_id = t.id)) and p.PIC_LIB_ID=fp.id AND ci.CAMERA_ID in (-1,"+ caremaId + ") and ci.CAMERA_ID=fc.id GROUP BY RECOGNITION_IMG_PATH").getResultList();
	}

}
