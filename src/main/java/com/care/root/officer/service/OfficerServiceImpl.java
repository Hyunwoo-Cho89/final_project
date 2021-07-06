package com.care.root.officer.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.care.root.officer.dao.OfficerDAO;
import com.care.root.officer.dto.GradeDTO;
import com.care.root.officer.dto.OfficerDTO;
import com.care.root.officer.dto.RegisterDTO;
import com.care.root.officer.dto.TimeTableDTO;

@Service
public class OfficerServiceImpl implements OfficerService{

	@Autowired
	OfficerDAO mapper;
	
	@Override
	public OfficerDTO staffInfo(String id) {
		OfficerDTO dto = mapper.staffInfo(id);
		dto.setBirthDate(dto.getResidentNum().substring(0,6));
		return dto;
	}

	@Override
	public int insertStu(HttpServletRequest request) {
		OfficerDTO dto = new OfficerDTO();
		
		int year = Calendar.getInstance().get(Calendar.YEAR);
		String id = year + "" + (int)(Math.random() * 9 + 1) + "" + (int)(Math.random() * 9 + 1) + "" + (int)(Math.random() * 9 + 1) + "" + (int)(Math.random() * 9 + 1);

		dto.setIdNum(id);
		dto.setPw(request.getParameter("inputResidentNum").substring(2,6));
		dto.setPosition("학생");
		dto.setName(request.getParameter("inputName"));
		dto.setResidentNum(request.getParameter("inputResidentNum"));
		dto.setCountry(request.getParameter("inputCountry"));
		dto.setGrade(Integer.parseInt(request.getParameter("inputGrade")));
		dto.setClass_(request.getParameter("inputClass"));
		dto.setPhoneNum(request.getParameter("inputPhonenum"));
		dto.setMajor(request.getParameter("inputMajor"));
		dto.setMentor(request.getParameter("inputMentor"));
		try {
			return mapper.insertStu(dto);
		}
		catch (Exception e){
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public ArrayList<String> findProfessor(String major) {
		return mapper.findProfessor(major);
	}

	@Override
	public Map<String, Object> findinfo(String major,int num) {
		int pageLetter = 3;
		int allCount;
		if(major.equals("전체")) {
			allCount = mapper.selectStudentCount(); // 총 글 개수 얻어오기
		}
		else {
			allCount = mapper.selectMajorStudentCount(major); // 총 글 개수 얻어오기
		}
		
		int repeat = allCount / pageLetter;	// 총 페이지 수
		if(allCount % pageLetter != 0) {
			repeat += 1;
		}
		int end = num * pageLetter;
		int start = end + 1 - pageLetter;
		
		if(repeat == 0) {
			repeat = 1;
		}
		
		if(major.equals("전체")) {
			Map <String,Object> map = new HashMap<String, Object>();
			map.put("repeat", repeat);
			map.put("list", mapper.findAll(start, end));
			return map;
		}
		else {
			Map <String,Object> map = new HashMap<String, Object>();
			map.put("repeat", repeat);
			map.put("list", mapper.findinfo(major, start, end));
			return map;
		}
	}
	
	@Override
	public ArrayList<OfficerDTO> findAll(Model model, int num) {
		int pageLetter = 3;
		int allCount = mapper.selectStudentCount(); // 총 글 개수 얻어오기
		
		int repeat = allCount / pageLetter;	// 총 페이지 수
		if(allCount % pageLetter != 0) {
			repeat += 1;
		}
		int end = num * pageLetter;
		int start = end + 1 - pageLetter;
		
		if(repeat == 0) {
			repeat = 1;
		}
		
		model.addAttribute("repeat", repeat);
		model.addAttribute("list", mapper.findAll(start, end));
		return mapper.findAll(start,end);
	}

	@Override
	public Map<String, Object> searchStudent(String searchS, String searchT, String searchM, int num) {
		int pageLetter = 3;
		int allCount = mapper.searchStudentCount(searchS, searchT, searchM); // 총 글 개수 얻어오기
		int repeat = allCount / pageLetter;	// 총 페이지 수
		if(allCount % pageLetter != 0) {
			repeat += 1;
		}
		int end = num * pageLetter;
		int start = end + 1 - pageLetter;
		
		System.out.println(num);
		
		if(repeat == 0) {
			repeat = 1;
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("repeat", repeat);
		map.put("list", mapper.searchStudent(start, end, searchS, searchT, searchM));
		return map;
	}

	@Override
	public Map<String, Object> searchAll(String searchS, String searchT, int num) {
		int pageLetter = 3;
		int allCount = mapper.searchAllCount(searchS, searchT); // 총 글 개수 얻어오기
		int repeat = allCount / pageLetter;	// 총 페이지 수
		if(allCount % pageLetter != 0) {
			repeat += 1;
		}
		int end = num * pageLetter;
		int start = end + 1 - pageLetter;
		
		System.out.println(num);
		
		if(repeat == 0) {
			repeat = 1;
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("repeat", repeat);
		map.put("list", mapper.searchAll(start, end, searchS, searchT));
		return map;
	}

	@Override
	public OfficerDTO studentInfo(String idNum) {
		OfficerDTO dto = new OfficerDTO();
		dto = mapper.studentInfo(idNum);
		dto.setBirthDate(dto.getResidentNum().substring(0,6));
		return dto;
	}

	@Override
	public ArrayList<String> findSubject(String major) {
		ArrayList<String> list = new ArrayList<String>();
		if(mapper.AlreadySubject(major).size() != 0) {
			int i = 0;
			while(i < mapper.findSubject(major).size()) {
				String fs = mapper.findSubject(major).get(i);
				for(int j = 0; j<mapper.AlreadySubject(major).size(); j++) {
					String as = mapper.AlreadySubject(major).get(j);
					if(fs.equals(as) == false) {
						list.add(fs);
					}
				}
			}
		}
		else {
			for(int i = 0; i < mapper.findSubject(major).size(); i++) {
				String fs = mapper.findSubject(major).get(i);
				list.add(fs);
			}
		}
		return list;
	}

	@Override
	public ArrayList<String> findLecture(String major) {
		return mapper.findLecture(major);
	}

	@Override
	public ArrayList<String> findTeacher(String subject) {
		ArrayList<String> list = new ArrayList<String>();
		if (mapper.findTeacher(subject) == null) {
			list.add(" ");
		}
		else {
			list.add(mapper.findTeacher(subject));
		}
		if(mapper.findTeacherIdNum(subject) == null) {
			list.add(" ");
		}
		else {
			list.add(mapper.findTeacherIdNum(subject));
		}
		return list;
	}

	@Override
	public int insertTimeTable(HttpServletRequest request) {
		TimeTableDTO dto = new TimeTableDTO();
		
		dto.setYearSemester(request.getParameter("semester"));
		dto.setMajor(request.getParameter("major"));
		dto.setSubject(request.getParameter("subject"));
		dto.setProfessor(request.getParameter("professorName"));
		dto.setIdNum(request.getParameter("professorIdNum"));
		dto.setLectureRoom(request.getParameter("lecture"));
		dto.setTime(request.getParameter("time"));
		dto.setWeek(request.getParameter("week"));
		
		try {
			return mapper.insertTimeTable(dto);
		}
		catch (Exception e){
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public ArrayList<String> timeCheck(String major, String week, String lecture) {
		ArrayList <String> finalTime = new ArrayList<String>();
		String firstTime = "09:00 ~ 12:00";
		String secondTime = "12:00 ~ 15:00";
		String thirdTime = "15:00 ~ 18:00";
		String fourthTime = "18:00 ~ 21:00";
		if(mapper.findTime(major, week, lecture).size() != 0) {
			for(int i = 0; i<mapper.findTime(major, week, lecture).size(); i++) {
				if(mapper.findTime(major, week, lecture).get(i).equals(firstTime) == false){
					finalTime.add(firstTime);
				}
				if(mapper.findTime(major, week, lecture).get(i).equals(secondTime) == false){
					finalTime.add(secondTime);
				}
				if(mapper.findTime(major, week, lecture).get(i).equals(thirdTime) == false){
					finalTime.add(thirdTime);
				}
				if(mapper.findTime(major, week, lecture).get(i).equals(fourthTime) == false){
					finalTime.add(fourthTime);
				}
			}
		}
		else {
			finalTime.add(firstTime); finalTime.add(secondTime); finalTime.add(thirdTime); finalTime.add(fourthTime);
		}
		return finalTime;
	}
	
	@Override
	public ArrayList<OfficerDTO> findAllAdmin(Model model, int num) {
		int pageLetter = 3;
		int allCount = mapper.selectAdminProCount();
		
		int repeat = allCount / pageLetter;	// 총 페이지 수
		if(allCount % pageLetter != 0) {
			repeat += 1;
		}
		if(repeat == 0) {
			repeat = 1;
		}
		int end = num * pageLetter;
		int start = end + 1 - pageLetter;
		model.addAttribute("repeat", repeat);
		return mapper.findAllAdmin(start,end);
	}

	@Override
	public Map<String, Object> findinfoProTel(String major,int num) {
		int pageLetter = 3;
		int allCount;
		if(major.equals("전체")) {
			allCount = mapper.selectAdminProCount(); // 총 글 개수 얻어오기
		}
		else {
			allCount = mapper.selectMajorAdminProCount(major); // 총 글 개수 얻어오기
		}
		
		int repeat = allCount / pageLetter;	// 총 페이지 수
		if(allCount % pageLetter != 0) {
			repeat += 1;
		}
		int end = num * pageLetter;
		int start = end + 1 - pageLetter;
		
		if(major.equals("전체")) {
			Map <String,Object> map = new HashMap<String, Object>();
			if(repeat == 0) {
				repeat = 1;
			}
			map.put("repeat", repeat);
			map.put("list", mapper.findAllAdmin(start, end));
			return map;
		}
		else {
			Map <String,Object> map = new HashMap<String, Object>();
			if(repeat == 0) {
				repeat = 1;
			}
			map.put("repeat", repeat);
			map.put("list", mapper.findinfoAdminPro(major, start, end));
			return map;
		}
	}
	@Override
	public Map<String, Object> searchAdminPro(String searchS, String searchT, String searchM, int num) {
		int pageLetter = 3;
		int allCount = mapper.searchAdminProCount(searchS, searchT, searchM); // 총 글 개수 얻어오기
		int repeat = allCount / pageLetter;	// 총 페이지 수
		if(allCount % pageLetter != 0) {
			repeat += 1;
		}
		int end = num * pageLetter;
		int start = end + 1 - pageLetter;
		
		System.out.println(num);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("repeat", repeat);
		map.put("list", mapper.searchAdminPro(start, end, searchS, searchT, searchM));
		return map;
	}

	@Override
	public Map<String, Object> searchAllAdminPro(String searchS, String searchT, int num) {
		int pageLetter = 3;
		int allCount = mapper.searchAllAdminProCount(searchS, searchT); // 총 글 개수 얻어오기
		int repeat = allCount / pageLetter;	// 총 페이지 수
		if(allCount % pageLetter != 0) {
			repeat += 1;
		}
		int end = num * pageLetter;
		int start = end + 1 - pageLetter;
		
		System.out.println(num);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("repeat", repeat);
		map.put("list", mapper.searchAllAdminPro(start, end, searchS, searchT));
		return map;
	}
	
	@Override
	public OfficerDTO adminProInfo(String idNum) {
		OfficerDTO dto = new OfficerDTO();
		dto = mapper.adminProInfo(idNum);
		dto.setBirthDate(dto.getResidentNum().substring(0,6));
		return dto;
	}

	@Override
	public ArrayList<GradeDTO> grade(Model model, int num){
		
		int pageLetter = 5;
		int allCount = mapper.selectStudentCount(); // 총 글 개수 얻어오기
		
		int repeat = allCount / pageLetter;	// 총 페이지 수
		if(allCount % pageLetter != 0) {
			repeat += 1;
		}
		int end = num * pageLetter;
		int start = end + 1 - pageLetter;
		
		if(repeat == 0) {
			repeat = 1;	
		} 
		
		Calendar cal = Calendar.getInstance();
		
		int year = cal.get(cal.YEAR);
		int month = cal.get(cal.MONTH)+1;
		String semester = null;
		
		if(month < 9 && month > 0) {
			semester = "1학기";
		}
		else if (month >= 9 && month < 13 ){
			semester = "2학기";
		}
		
		insertCurTotalGrade();
		insertAveGrade();
		
		mapper.insertCurRank();
		
		model.addAttribute("year", year);
		model.addAttribute("semester", semester);
		model.addAttribute("repeat", repeat);	
		model.addAttribute("list", mapper.findRank(start, end));
		model.addAttribute("listSecond", mapper.findRank(start, end));
		return mapper.grade(start,end);
		
	}
	
	public void insertCurTotalGrade() {
		ArrayList<String> list = new ArrayList<String>();
		list = mapper.studentIdNum();
		Integer totalScore;
		
		Calendar cal = Calendar.getInstance();
		
		int year = cal.get(cal.YEAR);
		int month = cal.get(cal.MONTH)+1;
		String dbSemester = null;
		String dbYear = year + "";
		
		if(month < 9 && month > 0) {
			dbSemester = "1";
		}
		else if (month >= 9 && month < 13 ){
			dbSemester = "2";
		}
		
		for(int i = 0; i<list.size(); i++) {
			totalScore = mapper.totalCal(list.get(i), dbSemester, dbYear);
			if(totalScore == null) {
				totalScore = 0;
				mapper.insertCurTotalScore(totalScore, list.get(i), dbSemester);
			}
			else {
				mapper.insertCurTotalScore(totalScore, list.get(i), dbSemester);
			}
		}
	}
	
	@Override
	public Map<String, Object> gradeMajor(String major,int num) {
		int pageLetter = 3;
		int allCount;
		if(major.equals("전체")) {
			allCount = mapper.selectStudentCount(); // 총 글 개수 얻어오기
		}
		else {
			allCount = mapper.selectMajorStudentCount(major); // 총 글 개수 얻어오기
		}
		
		int repeat = allCount / pageLetter;	// 총 페이지 수
		if(allCount % pageLetter != 0) {
			repeat += 1;
		}
		int end = num * pageLetter;
		int start = end + 1 - pageLetter;
		
		Calendar cal = Calendar.getInstance();
		
		int year = cal.get(cal.YEAR);
		int month = cal.get(cal.MONTH)+1;
		String semester = null;
		
		if(month < 9 && month > 0) {
			semester = "1학기";
		}
		else if (month >= 9 && month < 13 ){
			semester = "2학기";
		}
		System.out.println("전체 실행");
		if(major.equals("전체")) {
			Map <String,Object> map = new HashMap<String, Object>();
			if(repeat == 0) {
				repeat = 1;
			}
			map.put("repeat", repeat);
			map.put("list", mapper.findRank(start, end));
			map.put("year", year);
			map.put("semester", semester);
			return map;
		}
		else {
			Map <String,Object> map = new HashMap<String, Object>();
			if(repeat == 0) {
				repeat = 1;
			}
			map.put("repeat", repeat);
			map.put("list", mapper.findRankMajor(major, start, end));
			map.put("year", year);
			map.put("semester", semester);
			return map;
		}
	}
	
	@Override
	public Map<String, Object> searchMajorGrade(String searchS, String searchT, String searchM, int num) {
		int pageLetter = 3;
		int allCount = mapper.searchMajorGradeCount(searchS, searchT, searchM); // 총 글 개수 얻어오기
		int repeat = allCount / pageLetter;	// 총 페이지 수
		if(allCount % pageLetter != 0) {
			repeat += 1;
		}
		int end = num * pageLetter;
		int start = end + 1 - pageLetter;
		
		Calendar cal = Calendar.getInstance();
		
		int year = cal.get(cal.YEAR);
		int month = cal.get(cal.MONTH)+1;
		String semester = null;
		
		if(month < 9 && month > 0) {
			semester = "1학기";
		}
		else if (month >= 9 && month < 13 ){
			semester = "2학기";
		}
		
		System.out.println(num);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("repeat", repeat);
		map.put("list", mapper.searchMajorGrade(start, end, searchS, searchT, searchM));
		map.put("year", year);
		map.put("semester", semester);
		return map;
	}

	@Override
	public Map<String, Object> searchAllGrade(String searchS, String searchT, int num) {
		int pageLetter = 3;
		int allCount = mapper.searchAllGradeCount(searchS, searchT); // 총 글 개수 얻어오기
		int repeat = allCount / pageLetter;	// 총 페이지 수
		if(allCount % pageLetter != 0) {
			repeat += 1;
		}
		int end = num * pageLetter;
		int start = end + 1 - pageLetter;
		
		Calendar cal = Calendar.getInstance();
		
		int year = cal.get(cal.YEAR);
		int month = cal.get(cal.MONTH)+1;
		String semester = null;
		
		if(month < 9 && month > 0) {
			semester = "1학기";
		}
		else if (month >= 9 && month < 13 ){
			semester = "2학기";
		}
		
		System.out.println(num);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("repeat", repeat);
		map.put("list", mapper.searchAllGrade(start, end, searchS, searchT));
		map.put("year", year);
		map.put("semester", semester);
		return map;
	}
	
	
	@Override
	public int finalProcess() {
		RegisterDTO dto = new RegisterDTO();
		ArrayList<OfficerDTO> set = new ArrayList<OfficerDTO>();

		set = mapper.getAllMember();
		
		Calendar cal = Calendar.getInstance();
		
		String year = cal.get(cal.YEAR)+"";
		int cnt = 0;
		
		for(int i = 0; i<set.size(); i++) {
			if(set.get(i).getCurRank() > 0 && set.get(i).getCurRank() < 4) {
				dto.setScholarship(300);
			}
			else {
				dto.setScholarship(0); 
			}
			dto.setMajor(set.get(i).getMajor());
			dto.setName(set.get(i).getName());
			dto.setRank(set.get(i).getCurRank());
			dto.setTotalScore(set.get(i).getCurTotalGrade());
			dto.setIdNum(set.get(i).getIdNum());
			dto.setYear(year);
			if(set.get(i).getGrade() == 1 && set.get(i).getSemester().equals("1")) {
				dto.setGradeSemester(1);
				dto.setMjSemester(101);
			}
			else if(set.get(i).getGrade() == 1 && set.get(i).getSemester().equals("2")) {
				dto.setGradeSemester(2);
				dto.setMjSemester(102);
			}
			else if(set.get(i).getGrade() == 2 && set.get(i).getSemester().equals("1")) {
				dto.setGradeSemester(3);
				dto.setMjSemester(201);
			}
			else {
				dto.setGradeSemester(4);
				dto.setMjSemester(202);
			}
			dto.setApplicationCred(mapper.getApplicationCred(set.get(i).getIdNum(), year, set.get(i).getSemester()));
			dto.setReceivedCred(mapper.getApplicationCred(set.get(i).getIdNum(), year, set.get(i).getSemester()));
			mapper.finalProcess(dto);
			cnt++;
		}
		if(cnt == set.size()) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public int tuition(HttpServletRequest request) {
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		
		return mapper.tuition(startDate, endDate);
	}
	
	@Override
	public int setGrade(Model model){
		String getInputPeriod = mapper.getInputPeriod().get(0);
		String getChkPeriod = mapper.getChkPeriod().get(0);
		
		if (getInputPeriod.equals("N") && getChkPeriod.equals("N")) {
			mapper.setInputPeriod("Y");
			mapper.setChkPeriod("Y");
			model.addAttribute("result", "Y");
			return mapper.setInputPeriod("Y");
		}
		else if (getInputPeriod.equals("Y") && getChkPeriod.equals("Y")){
			mapper.setInputPeriod("N");
			mapper.setChkPeriod("N");
			model.addAttribute("result", "N");
			return mapper.setInputPeriod("N");
		}
		else {
			return 0;
		}
	}
	
	public void insertAveGrade() {
		ArrayList<String> list = new ArrayList<String>();
		list = mapper.studentIdNum();
		String aveGrade;
		
		Calendar cal = Calendar.getInstance();
		
		int year = cal.get(cal.YEAR);
		int month = cal.get(cal.MONTH)+1;
		String dbSemester = null;
		String dbYear = year + "";
		
		if(month < 9 && month > 0) {
			dbSemester = "1";
		}
		else if (month >= 9 && month < 13 ){
			dbSemester = "2";
		}
		
		for(int i = 0; i<list.size(); i++) {
			aveGrade = mapper.aveGradeCal(list.get(i), dbSemester, dbYear);
			if(aveGrade == null) {
				aveGrade = "0";
				mapper.insertAveGrade(aveGrade, list.get(i), dbSemester);
			}
			else {
				mapper.insertAveGrade(aveGrade, list.get(i), dbSemester);
			}
		}
	}
}
