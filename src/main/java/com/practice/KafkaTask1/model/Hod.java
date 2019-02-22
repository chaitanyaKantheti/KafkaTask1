package com.practice.KafkaTask1.model;

public class Hod {
		int hodId;
		String hodName;
		
		public Hod() {
		
		}
		
		public Hod(int hodId, String hodName) {
			super();
			this.hodId = hodId;
			this.hodName = hodName;
		}
		
		public int getHodId() {
			return hodId;
		}
		public void setHodId(int hodId) {
			this.hodId = hodId;
		}
		public String getHodName() {
			return hodName;
		}
		public void setHodName(String hodName) {
			this.hodName = hodName;
		}
		
		
}
