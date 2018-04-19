package com.zappos.sgambhir.model;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Shriya
 *
 */

@XmlRootElement(name = "menu")
public class Menu implements Serializable {

	
	/**
	 * Attributes for entity Menu
	 */
	private int menuId;
	private String menuType;
	private int restId;

	public Menu() {
	}

	/**
	 * @param id
	 * @param name
	 * @param restId
	 */
	public Menu(int id, String name, int restId) {
		this.menuId = id;
		this.menuType = name;
		this.restId = restId;

	}

	/**
	 * @return
	 */
	public int getId() {
		return menuId;
	}

	/**
	 * @param id
	 */
	@XmlElement
	public void setId(int id) {
		this.menuId = id;
	}

	/**
	 * @return
	 */
	public String getName() {
		return menuType;
	}

	/**
	 * @param name
	 */
	@XmlElement
	public void setName(String name) {
		this.menuType = name;
	}

	/**
	 * @return
	 */
	public int getrestId() {
		return restId;
	}

	/**
	 * @param restId
	 */
	@XmlElement
	public void setrestId(int restId) {
		this.restId = restId;
	}

	
	@Override
	public boolean equals(Object object) {
		if (object == null) {
			return false;
		} else if (!(object instanceof Menu)) {
			return false;
		} else {
			Menu item = (Menu) object;
			if (menuId == item.getId() && menuType.equals(item.getName())
					&& restId == item.getrestId()) {
				return true;
			}
		}
		return false;
	}
}