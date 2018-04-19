package com.zappos.sgambhir.model;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Shriya
 *
 */

@XmlRootElement(name = "retaurant")
public class Restaurant implements Serializable {

	// Declaring attributes for entity Restaurant
	private int restntId;
	private String restntName;
	private int rating;
	private float averageCostForTwo;
	private boolean hasOnlineDelivery;
	private String phoneNumbers;

	public Restaurant() {
	}

	// Constructor for object type Restaurant

	public Restaurant(int id, String name, int rating, float avgPrice,
			boolean onlineDelivery, String number) {
		this.restntId = id;
		this.restntName = name;
		this.rating = rating;
		this.averageCostForTwo = avgPrice;
		this.hasOnlineDelivery = onlineDelivery;
		this.phoneNumbers = number;
	}

	public int getId() {
		return restntId;
	}

	@XmlElement
	public void setId(int id) {
		this.restntId = id;
	}

	public String getName() {
		return restntName;
	}

	@XmlElement
	public void setName(String name) {
		this.restntName = name;
	}

	public int getRating() {
		return rating;
	}

	@XmlElement
	public void setRating(int rating) {
		this.rating = rating;
	}

	public float getPrice() {
		return averageCostForTwo;
	}

	@XmlElement
	public void setPrice(float avgPrice) {
		this.averageCostForTwo = avgPrice;
	}

	public boolean getonlineDelivery() {
		return hasOnlineDelivery;
	}

	@XmlElement
	public void setonlineDelivery(boolean onlineDelivery) {
		this.hasOnlineDelivery = onlineDelivery;
	}

	public String getPhonenumbers() {
		return phoneNumbers;
	}

	@XmlElement
	public void setgetPhonenumbers(String number) {
		this.phoneNumbers = number;
	}

	@Override
	public boolean equals(Object object) {
		if (object == null) {
			return false;
		} else if (!(object instanceof Restaurant)) {
			return false;
		} else {
			Restaurant item = (Restaurant) object;
			if (restntId == item.getId() && restntName.equals(item.getName())
					&& rating == item.getRating()
					&& averageCostForTwo == item.getPrice()
					&& hasOnlineDelivery == item.getonlineDelivery()
					&& phoneNumbers.equals(item.getPhonenumbers())

			) {
				return true;
			}
		}
		return false;
	}
}