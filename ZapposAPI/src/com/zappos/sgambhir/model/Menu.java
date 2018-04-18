package com.zappos.sgambhir.model;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "menu")
public class Menu implements Serializable {

	// private static final long serialVersionUID = 1L;
	
	//Attributes for entity Menu 
	private int menuId;
	private String menuType;
	private int restId;

	public Menu() {
	}

	public Menu(int id, String name, int restId) {
		this.menuId = id;
		this.menuType = name;
		this.restId = restId;

	}

	public int getId() {
		return menuId;
	}

	@XmlElement
	public void setId(int id) {
		this.menuId = id;
	}

	public String getName() {
		return menuType;
	}

	@XmlElement
	public void setName(String name) {
		this.menuType = name;
	}

	public int getrestId() {
		return restId;
	}

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
			if (menuId == item.getId() 
					&& menuType.equals(item.getName())
					&& restId == item.getrestId()) {
				return true;
			}
		}
		return false;
	}
}