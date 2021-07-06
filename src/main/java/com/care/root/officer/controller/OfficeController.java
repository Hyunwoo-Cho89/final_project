package com.care.root.officer.controller;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.care.root.common.session.MemberSessionName;
import com.care.root.officer.dto.OfficerDTO;
import com.care.root.officer.service.OfficerService;

@Controller
@RequestMapping("officer")
public class OfficeController implements MemberSessionName{

	@Autowired
	OfficerService os;
	
	@RequestMapping("adminMain")
	public String adminMain() {
		return "admin/adminMain";
	}
	
	@RequestMapping("staffInfo")
	public String staffInfo(HttpSession session, Model model) {
		System.out.println(session.getAttribute(LOGIN));
		model.addAttribute("dto", os.staffInfo((String)session.getAttribute(LOGIN)));
		return "admin/staffInfo";
	}
	
	@RequestMapping("studentManagement")
	public String studentManagement(Model model, @RequestParam(value = "num", required=false, defaultValue = "1") int num) {
		ArrayList<OfficerDTO> list = new ArrayList<OfficerDTO>();
		list = os.findAll(model, num);
		model.addAttribute("list", list);
		return "admin/studentManagement";
	}
	
	@RequestMapping("studentInsert")
	public String studentInsert() {
		return "admin/studentInsert";
	}
	
	@PostMapping("insertStu")
	public String insertStu(HttpServletRequest request) {
		int result = os.insertStu(request);
		if(result == 1) {
			return "admin/insertSuccess";
		}
		return "admin/insertFail";
	}
	
	@PostMapping(value="findProfessor", produces = "application/json; charset=utf-8")
	@ResponseBody
	public ArrayList<String> findProfessor(@RequestBody Map <String, Object> map) {
		return os.findProfessor((String)map.get("inputMajor"));
	}
	
	@PostMapping(value="findinfo", produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String,Object> findinfo(@RequestBody Map <String, Object> map, @RequestParam(value = "num", required=false, defaultValue = "1") int num){
		return os.findinfo((String)map.get("major"), num);	
	}
	
	@PostMapping(value="searchStudent", produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String,Object> searchStudent(@RequestBody Map <String, Object> map, @RequestParam(value = "num", required=false, defaultValue = "1") int num){
		System.out.println(map.get("searchSelect"));
		System.out.println(map.get("searchText"));
		System.out.println(map.get("searchMajor"));
		if(map.get("searchMajor").equals("all")) {
			return os.searchAll((String)map.get("searchSelect"), (String)map.get("searchText"), num);
		}
		else {
			return os.searchStudent((String)map.get("searchSelect"), (String)map.get("searchText"), (String)map.get("searchMajor"), num);
		}
	}
	
	@RequestMapping("studentInfo")
	public String studentInfo(HttpServletRequest request, Model model) {
		OfficerDTO dto = new OfficerDTO();
		dto = os.studentInfo(request.getParameter("idNum"));
		model.addAttribute("dto", dto);
		return "admin/studentInfo";
	}
	
	@RequestMapping("grade")
	public String grade(Model model, @RequestParam(value = "num", required=false, defaultValue = "1") int num) {
		os.grade(model, num);
		return "admin/grade";
	}
	
	@PostMapping(value="gradeMajor", produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String,Object> gradeMajor(@RequestBody Map <String, Object> map, @RequestParam(value = "num", required=false, defaultValue = "1") int num){
		System.out.println("컨트롤러");
		return os.gradeMajor((String)map.get("major"), num);	
	}
	
	@RequestMapping("timeTable")
	public String timeTable() {
		return "admin/timeTable";
	}
	
	@PostMapping(value="findSubject", produces = "application/json; charset=utf-8")
	@ResponseBody
	public ArrayList<String> findSubject(@RequestBody Map <String, Object> map){
		return os.findSubject((String)map.get("major"));
	}
	
	@PostMapping(value="findLecture", produces = "application/json; charset=utf-8")
	@ResponseBody
	public ArrayList<String> findLecture(@RequestBody Map <String, Object> map){
		return os.findLecture((String)map.get("major"));
	}
	
	@PostMapping(value="findTeacher", produces = "application/json; charset=utf-8")
	@ResponseBody
	public ArrayList<String> findTeacher(@RequestBody Map <String, Object> map){
		return os.findTeacher((String)map.get("subject"));
	}
	
	@PostMapping("insertTimeTable")
	public String insertTimeTable(HttpServletRequest request) {
		int result = os.insertTimeTable(request);
		if(result == 1) {
			return "admin/insertSuccess";
		}
		return "admin/insertFail";
	}
	
	@PostMapping(value="timeCheck", produces = "application/json; charset=utf-8")
	@ResponseBody
	public ArrayList<String> timeCheck(@RequestBody Map <String, Object> map){
		return os.timeCheck((String)map.get("major"), (String)map.get("week"), (String)map.get("lecture"));
	}
	
	@RequestMapping("tuitionAdmin")
	public String tuitionAdmin() {
		return "admin/tuitionAdmin";
	}
	
	@RequestMapping("adminProTel")
	public String adminProTel(Model model, @RequestParam(value = "num", required=false, defaultValue = "1") int num) {
		ArrayList<OfficerDTO> list = new ArrayList<OfficerDTO>();
		list = os.findAllAdmin(model, num);
		model.addAttribute("list", list);
		return "admin/adminProTel";
	}
	
	@PostMapping(value="findinfoProTel", produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String,Object> findinfoProTel(@RequestBody Map <String, Object> map, @RequestParam(value = "num", required=false, defaultValue = "1") int num){
		return os.findinfoProTel((String)map.get("major"), num);	
	}
	
	@PostMapping(value="searchAdminPro", produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String,Object> searchAdminPro(@RequestBody Map <String, Object> map, @RequestParam(value = "num", required=false, defaultValue = "1") int num){
		System.out.println(map.get("searchSelect"));
		System.out.println(map.get("searchText"));
		System.out.println(map.get("searchMajor"));
		if(map.get("searchMajor").equals("all")) {
			return os.searchAllAdminPro((String)map.get("searchSelect"), (String)map.get("searchText"), num);
		}
		else {
			return os.searchAdminPro((String)map.get("searchSelect"), (String)map.get("searchText"), (String)map.get("searchMajor"), num);
		}
	}
	
	@RequestMapping("adminProInfo")
	public String adminProInfo(HttpServletRequest request, Model model) {
		OfficerDTO dto = new OfficerDTO();
		dto = os.adminProInfo(request.getParameter("idNum"));
		model.addAttribute("dto", dto);
		return "admin/adminProInfo";
	}
	
	@PostMapping(value="searchGrade", produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String,Object> searchGrade(@RequestBody Map <String, Object> map, @RequestParam(value = "num", required=false, defaultValue = "1") int num){
		System.out.println(map.get("searchSelect"));
		System.out.println(map.get("searchText"));
		System.out.println(map.get("searchMajor"));
		if(map.get("searchMajor").equals("all")) {
			return os.searchAllGrade((String)map.get("searchSelect"), (String)map.get("searchText"), num);
		}
		else {
			return os.searchMajorGrade((String)map.get("searchSelect"), (String)map.get("searchText"), (String)map.get("searchMajor"), num);
		}
	}
	
	
	@RequestMapping("finalProcess")
	public String finalProcess() {
		if(os.finalProcess() == 1) {
			return "admin/finalProcessSuccess";
		}
		else {
			return "admin/finalProcessFail";
		}
	}
	
	@RequestMapping("tuition")
	public String tuition(HttpServletRequest request) {
		System.out.println(request.getParameter("startDate").getClass());
		System.out.println(os.tuition(request));
		if(os.tuition(request) != 0) {
			return "admin/tuitionSuccess";
		}
		else {
			return "admin/tuitionFail";
		}
	}
	
	@RequestMapping("setGrade")
	public String setGrade(Model model) {
		if(os.setGrade(model) != 0) {
			return "admin/setGradeSuccess";
		}
		else {
			return "admin/setGradeFail";
		}
	}
}
