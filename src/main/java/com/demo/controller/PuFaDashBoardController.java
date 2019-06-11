package com.demo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.demo.service.DashboardService;
import com.demo.spring_boot.utils.DateUtils;


@RestController
@RequestMapping("/pufaDashboard")
@Component("taskJob")
public class PuFaDashBoardController {
	// 日志记录
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private DashboardService dashboardService;
	private static final String JPG = "image/jpeg;charset=GB2312";

	/**
	 * 浦东新区租赁住房房源供应量实时监测大屏（按板块）
	 * 
	 **/
	@RequestMapping(value = "getCameraData")
	public JSONObject getTangchengData(@RequestBody JSONObject requestObj, HttpServletResponse response) {
		JSONObject result = new JSONObject();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String qtime = sdf.format(new Date());
		if(requestObj.getString("queryDate") != null) {
			qtime = requestObj.getString("queryDate");
		}
		List<Object[]> list = dashboardService.finddis(Integer.parseInt(requestObj.getString("caremaId")),qtime);
		JSONObject jsonObj = new JSONObject();
		JSONArray namearr = new JSONArray();// 姓名
		JSONArray agearr = new JSONArray();// 性别
		JSONArray sexarr = new JSONArray();// 性别
		JSONArray degreearr = new JSONArray();// 相似度
		JSONArray newimgarr = new JSONArray();//
		JSONArray oldimgarr = new JSONArray();//
		JSONArray taketimearr = new JSONArray();//
		JSONArray personarr = new JSONArray();//
		Integer num = list.size();
		if (list != null && list.size() > 0) {
			String camname = null;
			Date refreshTime =  null;
			
			for (Object[] obj : list) {

				String img_path = obj[2].toString();
				String img_name = obj[3].toString();
				String path = img_name;
				// upload(response, path);
				
				String name = "";
				if (obj[7] != null) {
					name = obj[7].toString();
				} else {
					name = "陌生人";
				}
				String age = "";
				if (obj[5] != null) {
					age = obj[5].toString();
				}
				String sex = "";
				if (obj[6] != null) {
					sex = obj[6].toString();
					if ("0".equals(sex)) {
						sex = "男";
					} else {
						sex = "女";
					}
				} else {
					sex = "未知";
					age = "";
				}
				String oldimg_path = obj[9].toString();
				String oldimg_name = obj[10].toString();
				String oldpath = oldimg_name;
				camname = obj[13].toString();
				String taketime = obj[12].toString();
				Double degree = Double.parseDouble(obj[11].toString());
				String piclib =obj[14].toString();
				
				try {
					Date takeImgDate = DateUtils.getStringToDateyyyyMMddHHmmss(taketime);
					String time = requestObj.getString("queryDate");
					refreshTime = DateUtils.getStringToDateyyyyMMddHHmmss(time);
					if (takeImgDate.after(refreshTime)) {
						refreshTime = takeImgDate;
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				personarr.add(Integer.parseInt(piclib));
				agearr.add(age);
				namearr.add(name);
				sexarr.add(sex);
				degreearr.add(degree);
				newimgarr.add(path);
				oldimgarr.add(oldpath);
				taketimearr.add(taketime);

			}
			jsonObj.put("person", personarr);
			jsonObj.put("camname", camname);
			jsonObj.put("refreshTime", refreshTime);
			jsonObj.put("name", namearr);
			jsonObj.put("sex", sexarr);
			jsonObj.put("age", agearr);
			jsonObj.put("degree", degreearr);
			jsonObj.put("newimg", newimgarr);
			jsonObj.put("oldimg", oldimgarr);
			jsonObj.put("taketime", taketimearr);
			result.put("refreshTime", refreshTime);
			
		}else {
			result.put("refreshTime", qtime);
		}
		result.put("num", num);
		result.put("jsonObj", jsonObj);
		return result;

	}

	@RequestMapping("picture")
	public void upload(String filename, Integer caremaId,HttpServletRequest request, HttpServletResponse response) {
		List<Object> piclibpath = dashboardService.findPICLIBPath(caremaId);
		OutputStream os = null;
		FileInputStream fis = null;
		try {
			os = response.getOutputStream();
			String path = null;
			for (Object obj : piclibpath) {
				File file = new File(obj + File.separator + filename);
				if (file.exists()) {
					path = obj + File.separator + filename;
					break;
				} else {
					continue;
				}
			}

			fis = new FileInputStream(path);
			byte[] buffer = new byte[1024];
			while (fis.read(buffer) != -1) {
				os.write(buffer);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (os != null) {
				try {
					os.flush();
					os.close();
					fis.close();
				} catch (Exception e) {

				}
			}
		}
	}
	

	@RequestMapping("picture1")
	public void upload1(String filename, Integer caremaId, HttpServletRequest request, HttpServletResponse response) {
		
		List<Object> pathList = dashboardService.findimgpath(caremaId);
		OutputStream os = null;
		FileInputStream fis = null;
		try {
			os = response.getOutputStream();
			String path = null;
			/*
			 * List<Object> alist = new ArrayList<Object>();; alist.add("E:/img3");
			 * alist.add("E:/img2"); alist.add("E:/img");
			 */
			for (Object obj : pathList) {
				File file = new File(obj + File.separator + filename);
				if (file.exists()) {
					path = obj + File.separator + filename;
					break;
				} else {
					continue;
				}
			}

			/*
			 * File file = new File("E:/img2" + File.separator + filename);
			 * 
			 * if(file.exists()) { path = "E:/img2" + File.separator + filename;
			 * System.out.println(path); }else { file = new File("E:\\img" + File.separator
			 * + filename); if(file.exists()) { path = "E:/img" + File.separator + filename;
			 * System.out.println(path); } }
			 */

			fis = new FileInputStream(path);
			byte[] buffer = new byte[1024];
			while (fis.read(buffer) != -1) {
				os.write(buffer);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (os != null) {
				try {
					os.flush();
					os.close();
					fis.close();
				} catch (Exception e) {

				}
			}
		}
	}
	
	@RequestMapping(value = "getCameraData1/{caremaId}/{queryDate}")
	public JSONObject getTangData(@PathVariable String caremaId, @PathVariable String queryDate, HttpServletResponse response) {
		JSONObject result = new JSONObject();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String qtime = sdf.format(new Date());
		if(queryDate != null) {
			qtime = queryDate;
		}
		List<Object[]> list = dashboardService.finddis(Integer.parseInt(caremaId),qtime);
		JSONObject jsonObj = new JSONObject();
		JSONArray namearr = new JSONArray();// 姓名
		JSONArray agearr = new JSONArray();// 性别
		JSONArray sexarr = new JSONArray();// 性别
		JSONArray degreearr = new JSONArray();// 相似度
		JSONArray newimgarr = new JSONArray();//
		JSONArray oldimgarr = new JSONArray();//
		JSONArray taketimearr = new JSONArray();//
		JSONArray personarr = new JSONArray();//
		Integer num = list.size();
		if (list != null && list.size() > 0) {
			String camname = null;
			Date refreshTime =  null;
			for (Object[] obj : list) {

				String img_path = obj[2].toString();
				String img_name = obj[3].toString();
				String path = img_name;
				// upload(response, path);
				
				String name = "";
				if (obj[7] != null) {
					name = obj[7].toString();
				} else {
					name = "陌生人";
				}
				String age = "";
				if (obj[5] != null) {
					age = obj[5].toString();
				}
				String sex = "";
				if (obj[6] != null) {
					sex = obj[6].toString();
					if ("0".equals(sex)) {
						sex = "男";
					} else {
						sex = "女";
					}
				} else {
					sex = "未知";
					age = "";
				}
				String oldimg_path = obj[9].toString();
				String oldimg_name = obj[10].toString();
				String oldpath = oldimg_name;
				camname = obj[13].toString();
				String taketime = obj[12].toString();
				Double degree = Double.parseDouble(obj[11].toString());
				String piclib =obj[14].toString();
				
				try {
					Date takeImgDate = DateUtils.getStringToDateyyyyMMddHHmmss(taketime);
					String time = queryDate;
					refreshTime = DateUtils.getStringToDateyyyyMMddHHmmss(time);
					if (takeImgDate.after(refreshTime)) {
						refreshTime = takeImgDate;
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				personarr.add(Integer.parseInt(piclib));
				agearr.add(age);
				namearr.add(name);
				sexarr.add(sex);
				degreearr.add(degree);
				newimgarr.add(path);
				oldimgarr.add(oldpath);
				taketimearr.add(taketime);

			}
			jsonObj.put("person", personarr);
			jsonObj.put("camname", camname);
			jsonObj.put("refreshTime", sdf.format(refreshTime));
			jsonObj.put("name", namearr);
			jsonObj.put("sex", sexarr);
			jsonObj.put("age", agearr);
			jsonObj.put("degree", degreearr);
			jsonObj.put("newimg", newimgarr);
			jsonObj.put("oldimg", oldimgarr);
			jsonObj.put("taketime", taketimearr);
			result.put("refreshTime", sdf.format(refreshTime));
		}else {
			result.put("refreshTime", qtime);
		}
		result.put("num", num);
		result.put("jsonObj", jsonObj);
		return result;

	}

}
