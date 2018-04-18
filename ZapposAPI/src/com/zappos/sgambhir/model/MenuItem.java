package com.zappos.sgambhir.model;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "menuItem")
public class MenuItem implements Serializable {
	
	//Attributes for entity Menu items
	private int mItemid;
	private String mItemName;
	private Double mItemPrice;
	private int menuId;

	public MenuItem() {
	}

	public MenuItem(int id, String name, Double price, int menuId) {
		this.mItemid = id;
		this.mItemName = name;
		this.mItemPrice = price;
		this.menuId = menuId;
	}

	public int getId() {
		return mItemid;
	}

	@XmlElement
	public void setId(int id) {
		this.mItemid = id;
	}

	public String getName() {
		return mItemName;
	}

	@XmlElement
	public void setName(String name) {
		this.mItemName = name;
	}

	public Double getPrice() {
		return mItemPrice;
	}

	@XmlElement
	public void setPrice(Double price) {
		this.mItemPrice = price;
	}

	public int getMenuId() {
		return menuId;
	}

	@XmlElement
	public void setMenuId(int id) {
		this.menuId = id;
	}

	@Override
	public boolean equals(Object object) {
		if (object == null) {
			return false;
		} else if (!(object instanceof MenuItem)) {
			return false;
		} else {
			MenuItem item = (MenuItem) object;
			if (mItemid == item.getId() && mItemName.equals(item.getName())
					&& mItemPrice.equals(item.getPrice())
					&& menuId == item.getMenuId()) {
				return true;
			}
		}
		return false;
	}
}