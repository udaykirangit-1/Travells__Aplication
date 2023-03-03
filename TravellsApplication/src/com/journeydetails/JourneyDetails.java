package com.journeydetails;

public class JourneyDetails {
	private String sourceDestination;
	private String date;
	private int noOfPassengers;
	private double bill;
	private String bookingStatus;

public String getSourceDestination() {
		return sourceDestination;
	}
	public void setSourceDestination(String sourceDestination) {
		this.sourceDestination = sourceDestination;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getNoOfPassengers() {
		return noOfPassengers;
	}
	public void setNoOfPassengers(int noOfPassengers) {
		this.noOfPassengers = noOfPassengers;
	}
	public double getBill() {
		return bill;
	}
	public void setBill(double bill) {
		this.bill = bill;
	}
	public JourneyDetails(String sourceDestination, String date, int noOfPassengers, double bill) {
		super();
		this.sourceDestination = sourceDestination;
		this.date = date;
		this.noOfPassengers = noOfPassengers;
		this.bill = bill;
	}
public JourneyDetails() {
}

public String getBookingStatus() {
	return bookingStatus;
}
@Override
public String toString() {
	return "JourneyDetails [sourceDestination=" + sourceDestination + ", date=" + date + ", noOfPassengers="
			+ noOfPassengers + ", bill=" + bill + ", bookingStatus=" + bookingStatus + "]";
}
public void setBookingStatus(String bookingStatus) {
	this.bookingStatus = bookingStatus;
}


}
