package com.care.root.officer.dto;

public class GradeDTO {
	
	private String idNum;
	private String name;
	private String major;
	private String subjectName;
	private String grade;
	private int score;
	private String profName;
	private int receivedCred;
	private int applicationCred;
	private int gradeSemester;
	private String year;
	private String semester;
	private String numGrade;
	
	public String getSemester() {
		return semester;
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}
	public String getNumGrade() {
		return numGrade;
	}
	public void setNumGrade(String numGrade) {
		this.numGrade = numGrade;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public int getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}
	private int totalScore;
	
	public String getIdNum() {
		return idNum;
	}
	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getProfName() {
		return profName;
	}
	public void setProfName(String profName) {
		this.profName = profName;
	}
	public int getReceivedCred() {
		return receivedCred;
	}
	public void setReceivedCred(int receivedCred) {
		this.receivedCred = receivedCred;
	}
	public int getApplicationCred() {
		return applicationCred;
	}
	public void setApplicationCred(int applicationCred) {
		this.applicationCred = applicationCred;
	}
	public int getGradeSemester() {
		return gradeSemester;
	}
	public void setGradeSemester(int gradeSemester) {
		this.gradeSemester = gradeSemester;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	
	
	
}
