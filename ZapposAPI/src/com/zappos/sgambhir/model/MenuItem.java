package com.zappos.sgambhir.model;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Shriya
 *
 */
@XmlRootElement(name = "menuItem")
public class MenuItem implements Serializable {

	/**
	 * Attributes for entity Menu items
	 */
	private int mItemid;
	private String mItemName;
	private Double mItemPrice;
	private int menuId;

	public MenuItem() {
	}

	/**
	 * @param id
	 * @param name
	 * @param price
	 * @param menuId
	 */
	public MenuItem(int id, String name, Double price, int menuId) {
		this.mItemid = id;
		this.mItemName = name;
		this.mItemPrice = price;
		this.menuId = menuId;
	}

	/**
	 * @return
	 */
	public int getId() {
		return mItemid;
	}

	/**
	 * @param id
	 */
	@XmlElement
	public void setId(int id) {
		this.mItemid = id;
	}

	/**
	 * @return
	 */
	public String getName() {
		return mItemName;
	}

	/**
	 * @param name
	 */
	@XmlElement
	public void setName(String name) {
		this.mItemName = name;
	}

	/**
	 * @return
	 */
	public Double getPrice() {
		return mItemPrice;
	}

	/**
	 * @param price
	 */
	@XmlElement
	public void setPrice(Double price) {
		this.mItemPrice = price;
	}

	/**
	 * @return
	 */
	public int getMenuId() {
		return menuId;
	}

	/**
	 * @param id
	 */
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