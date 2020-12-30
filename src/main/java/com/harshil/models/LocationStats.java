package com.harshil.models;

public class LocationStats {

	
	private String state;
	private String country;
	private int latestTotalCases;
	private int diffFromPrevDay;
	private int latestDeathCases;
	private int latestRecoverCases;
	private int diffdeathFromPrevDay;
	private int diffrecoverPrevDay;
	
	public int getDiffrecoverPrevDay() {
		return diffrecoverPrevDay;
	}
	public void setDiffrecoverPrevDay(int diffrecoverPrevDay) {
		this.diffrecoverPrevDay = diffrecoverPrevDay;
	}
	public int getDiffdeathFromPrevDay() {
		return diffdeathFromPrevDay;
	}
	public void setDiffdeathFromPrevDay(int diffdeathFromPrevDay) {
		this.diffdeathFromPrevDay = diffdeathFromPrevDay;
	}
	public int getLatestRecoverCases() {
		return latestRecoverCases;
	}
	public void setLatestRecoverCases(int latestRecoverCases) {
		this.latestRecoverCases = latestRecoverCases;
	}
	public int getLatestDeathCases() {
		return latestDeathCases;
	}
	public void setLatestDeathCases(int latestDeathCases) {
		this.latestDeathCases = latestDeathCases;
	}
	public int getDiffFromPrevDay() {
		return diffFromPrevDay;
	}
	public void setDiffFromPrevDay(int diffFromPrevDay) {
		this.diffFromPrevDay = diffFromPrevDay;
	}
	@Override
	public String toString() {
		return "LocationStats [state=" + state + ", country=" + country + ", latestTotalCases=" + latestTotalCases
				+ "]";
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		if(state.equals(""))
		this.state="N/A";
		else
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public int getLatestTotalCases() {
		return latestTotalCases;
	}
	public void setLatestTotalCases(int latestTotalCases) {
		this.latestTotalCases = latestTotalCases;
	}
}
