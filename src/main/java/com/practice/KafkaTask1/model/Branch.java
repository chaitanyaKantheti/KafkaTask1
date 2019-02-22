package com.practice.KafkaTask1.model;

public class Branch {
	int bId;
	String bName;
	
	private Hod hod;

	public Branch(int bId, String bName,int hodId) {
		super();
		this.bId = bId;
		this.bName = bName;
		this.hod = new Hod(hodId,"");
	}

	public Branch() {
		// TODO Auto-generated constructor stub
	}

	public int getbId() {
		return bId;
	}

	public void setbId(int bId) {
		this.bId = bId;
	}

	public String getbName() {
		return bName;
	}

	public void setbName(String bName) {
		this.bName = bName;
	}

	public Hod getHod() {
		return hod;
	}

	public void setHod(Hod hod) {
		this.hod = hod;
	}
	
}
