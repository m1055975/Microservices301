package com.mindtree.userservice.dto;


public class AddYoYoBalanceDto extends ResponseMessage{

	private int amountToBuyPoints;
	private String userName;
	private double PointsPurchased;
	private double yoYoBalance;
	
	
	public double getYoYoBalance() {
		return yoYoBalance;
	}
	public void setYoYoBalance(double yoYoBalance) {
		this.yoYoBalance = yoYoBalance;
	}
	public int getAmountToBuyPoints() {
		return amountToBuyPoints;
	}
	public void setAmountToBuyPoints(int amountToBuyPoints) {
		this.amountToBuyPoints = amountToBuyPoints;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public double getPointsPurchased() {
		return PointsPurchased;
	}
	public void setPointsPurchased(double pointsPurchased) {
		PointsPurchased = pointsPurchased;
	}
	@Override
	public String toString() {
		return "BalanceDto [amountToBuyPoints=" + amountToBuyPoints + ", userName=" + userName + ", PointsPurchased="
				+ PointsPurchased + "]";
	}
	
	
	
}
