package com.practice.KafkaTask1.model;

public class Student {
		int sID;
		String sName;
		private Branch branch;
		//private Hod hod;
		
		public Student() {
			
		}
		public Student(int sID, String sName, int bId,int hodId) {
			super();
			this.sID = sID;
			this.sName = sName;
			this.branch = new Branch(bId,"",hodId);
		}
		public int getsID() {
			return sID;
		}
		public void setsID(int sID) {
			this.sID = sID;
		}
	
		public String getsName() {
			return sName;
		}
		
		public void setsName(String sName) {
			this.sName = sName;
		}
		
	
		
		
		public Branch getBranch() {
			return branch;
		}
		public void setBranch(Branch branch) {
			this.branch = branch;
		}
		
}
