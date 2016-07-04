package com.klaus.bean;

public class AbilityStander {
	
	private String ability;
	private double score;
	private double mapping;
	
	public AbilityStander(String ability,double score,double mapping){
		
		this.ability=ability;
		this.score=score;
		this.mapping=mapping;
		
	}
	
	public String getAbility() {
		return ability;
	}
	public void setAbility(String ability) {
		this.ability = ability;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public double getMapping() {
		return mapping;
	}
	public void setMapping(double mapping) {
		this.mapping = mapping;
	}
	
	
	
	
}
