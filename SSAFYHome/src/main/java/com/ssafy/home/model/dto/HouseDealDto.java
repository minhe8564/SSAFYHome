package com.ssafy.home.model.dto;

import java.math.BigDecimal;

public class HouseDealDto {
    private int no;
    private String aptSeq;
    private String aptDong;
    private String floor;
    private int dealYear;
    private int dealMonth;
    private int dealDay;
    private BigDecimal exclusiveUseArea;
    private String dealAmount;
    
	@Override
	public String toString() {
		return "HouseDealDto [no=" + no + ", aptSeq=" + aptSeq + ", aptDong=" + aptDong + ", floor=" + floor
				+ ", dealYear=" + dealYear + ", dealMonth=" + dealMonth + ", dealDay=" + dealDay + ", exclusiveUseArea="
				+ exclusiveUseArea + ", dealAmount=" + dealAmount + "]";
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getAptSeq() {
		return aptSeq;
	}

	public void setAptSeq(String aptSeq) {
		this.aptSeq = aptSeq;
	}

	public String getAptDong() {
		return aptDong;
	}

	public void setAptDong(String aptDong) {
		this.aptDong = aptDong;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public int getDealYear() {
		return dealYear;
	}

	public void setDealYear(int dealYear) {
		this.dealYear = dealYear;
	}

	public int getDealMonth() {
		return dealMonth;
	}

	public void setDealMonth(int dealMonth) {
		this.dealMonth = dealMonth;
	}

	public int getDealDay() {
		return dealDay;
	}

	public void setDealDay(int dealDay) {
		this.dealDay = dealDay;
	}

	public BigDecimal getExclusiveUseArea() {
		return exclusiveUseArea;
	}

	public void setExclusiveUseArea(BigDecimal exclusiveUseArea) {
		this.exclusiveUseArea = exclusiveUseArea;
	}

	public String getDealAmount() {
		return dealAmount;
	}

	public void setDealAmount(String dealAmount) {
		this.dealAmount = dealAmount;
	}

	public HouseDealDto(int no, String aptSeq, String aptDong, String floor, int dealYear, int dealMonth, int dealDay,
			BigDecimal exclusiveUseArea, String dealAmount) {
		this.no = no;
		this.aptSeq = aptSeq;
		this.aptDong = aptDong;
		this.floor = floor;
		this.dealYear = dealYear;
		this.dealMonth = dealMonth;
		this.dealDay = dealDay;
		this.exclusiveUseArea = exclusiveUseArea;
		this.dealAmount = dealAmount;
	}

	public HouseDealDto() {

	}    
}
