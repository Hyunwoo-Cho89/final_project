package com.care.root.officer.service;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.care.root.officer.dto.GradeDTO;
import com.care.root.officer.dto.OfficerDTO;

public interface OfficerService {
	public OfficerDTO staffInfo(String id);
	public int insertStu(HttpServletRequest request);
	public ArrayList<String> findProfessor(String major);
	public Map<String,Object> findinfo(String major, int num);
	public ArrayList<OfficerDTO> findAll(Model model, int num);
	public Map<String,Object> searchAll(String searchS, String searchT, int num);
	public Map<String,Object> searchStudent(String searchS, String searchT, String searchM, int num);
	public OfficerDTO studentInfo(String idNum);
	public ArrayList<String> findSubject(String major);
	public ArrayList<String> findLecture(String major);
	public ArrayList<String> findTeacher(String subject);
	public int insertTimeTable(HttpServletRequest request);
	public ArrayList<String> timeCheck(String major, String week, String lecture);
	public ArrayList<OfficerDTO> findAllAdmin(Model model, int num);
	public Map<String,Object> findinfoProTel(String major, int num);
	public Map<String,Object> searchAllAdminPro(String searchS, String searchT, int num);
	public Map<String,Object> searchAdminPro(String searchS, String searchT, String searchM, int num);
	public OfficerDTO adminProInfo(String idNum);
	public ArrayList<GradeDTO> grade(Model model, int num);
	public Map<String,Object> gradeMajor(String major, int num);
	public Map<String,Object> searchAllGrade(String searchS, String searchT, int num);
	public Map<String,Object> searchMajorGrade(String searchS, String searchT, String searchM, int num);
	public int finalProcess();
	public int tuition(HttpServletRequest request);
	public int setGrade(Model model);
}
