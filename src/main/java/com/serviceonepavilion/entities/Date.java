package com.serviceonepavilion.entities;

import java.time.LocalDate;

public class Date {
	private LocalDate date;
	
	public static LocalDate getLocalDate() {
	    return LocalDate.now();
	}

	public LocalDate getDate() {
		return LocalDate.now();
	}

	public void setDate(LocalDate date) {
		this.date = Date.getLocalDate();
	}

	@Override
	public String toString() {
		return "Date [date=" + date + "]";
	}
	
	
	
}
