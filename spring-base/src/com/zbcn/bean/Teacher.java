package com.zbcn.bean;

import java.util.List;
import java.util.Map;

public class Teacher {

	 private Integer id;
	 private String name;
	 private List<String> dream;
	 private Map<String, Integer> score;
	 private boolean graduation;
	 
	 public Teacher() {}
	public Teacher(Integer id, String name, List<String> dream, Map<String, Integer> score, boolean graduation) {
		super();
		this.id = id;
		this.name = name;
		this.dream = dream;
		this.score = score;
		this.graduation = graduation;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getDream() {
		return dream;
	}
	public void setDream(List<String> dream) {
		this.dream = dream;
	}
	public Map<String, Integer> getScore() {
		return score;
	}
	public void setScore(Map<String, Integer> score) {
		this.score = score;
	}
	public boolean isGraduation() {
		return graduation;
	}
	public void setGraduation(boolean graduation) {
		this.graduation = graduation;
	}
	
	
	 
	 
}
