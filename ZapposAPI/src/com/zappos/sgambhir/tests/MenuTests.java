package com.zappos.sgambhir.tests;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.zappos.sgambhir.model.Menu;


public class MenuTests {

	private Client client;
	private String REST_SERVICE_URL = "http://localhost:8086/ZapposAPI/rest/menus";
	private static TestController tc = new TestController();

	private void init() {
		this.client = ClientBuilder.newClient();
	}

	public static void main(String[] args) {
		MenuTests mTest = new MenuTests();

		// initialize the tester
		mTest.init();

		// test getMenu
		mTest.testGetMenu("Get menu", 12);
		mTest.testGetMenu_MenuDoesNotExist("Get menuNotExists",34);
		
		//test getMenuItemsForMenu
		mTest.testGetMenuItemForMenu("Get menuItemForMenu", 12);
		mTest.testGetMenuItemForMenu_MenuDoesNotExist("Get menuItemForMenu", 34);
		
		// test updateMenu
		mTest.testUpdateMenu("Put menu", 44, "Breakfast", 2);
		mTest.testUpdateMenu_MenuDoesNotExist("Put menuNotExists", 34 , "Breakfast",3);

		// test addMenu
		mTest.testAddMenu("Post menuItem", 15, "Desserts", 2);
		mTest.testAddMenu("Post menuItem 1", 16, "Drinks", 2);
		mTest.testAddMenu_MenuAlreadyExists("Post menuItemExists", 15, "Snacks", 2);
		

		// test deleteMenu
		mTest.testDeleteMenu("Delete menu", 15);
		mTest.testDeleteMenu("Delete menu 1", 16);
		mTest.testDeleteMenu_MenuDoesNotExists("Delete menuNotExists", 15);
		
		tc.printResults();
	}

	

	private void testGetMenu(String testCaseName, int id) {
		
		Response resp = client.target(REST_SERVICE_URL).path("/{menuId}")
				.resolveTemplate("{menuId}", id)
				.request(MediaType.APPLICATION_JSON).get();
		tc.positiveCase(testCaseName, resp);
	}
	
	private void testGetMenu_MenuDoesNotExist(String testCaseName, int id) {
		
		Response resp = client.target(REST_SERVICE_URL).path("/{menuId}")
				.resolveTemplate("{menuId}", id)
				.request(MediaType.APPLICATION_JSON).get();
		tc.negativeCase(testCaseName, resp);
	}
	
	private void testGetMenuItemForMenu(String testCaseName, int menuId) {

		Response resp = client.target(REST_SERVICE_URL).path("/{menuId}/menuItems")
				.resolveTemplate("restaurantId", menuId)
				.request(MediaType.APPLICATION_JSON).get();

		tc.positiveCase(testCaseName, resp);
	}
	
	private void testGetMenuItemForMenu_MenuDoesNotExist(String testCaseName, int menuId) {

		Response resp = client.target(REST_SERVICE_URL).path("/{menuId}/menuItems")
				.resolveTemplate("restaurantId", menuId)
				.request(MediaType.APPLICATION_JSON).get();

		tc.negativeCase(testCaseName, resp);
	}
	
	private void testUpdateMenu(String testCaseName, int id, String name,int restId) {
		Menu newMenu = new Menu(id, name, restId);

		Response resp = client.target(REST_SERVICE_URL)
				.request(MediaType.APPLICATION_JSON)
				.put(Entity.entity(newMenu, MediaType.APPLICATION_JSON));

		tc.positiveCase(testCaseName, resp);
	}

	private void testUpdateMenu_MenuDoesNotExist(String testCaseName, int id, String name,int restId) {
		Menu newMenu = new Menu(id, name, restId);

		Response resp = client.target(REST_SERVICE_URL)
				.request(MediaType.APPLICATION_JSON)
				.put(Entity.entity(newMenu, MediaType.APPLICATION_JSON));

		tc.negativeCase(testCaseName, resp);
	}
	

	private void testAddMenu(String testCaseName, int id, String name,int restId) {
		Menu newMenu = new Menu(id, name, restId);

		Response resp = client.target(REST_SERVICE_URL)
				.request(MediaType.APPLICATION_JSON)
				.put(Entity.entity(newMenu, MediaType.APPLICATION_JSON));

		tc.positiveCase(testCaseName, resp);
		
	}

	private void testAddMenu_MenuAlreadyExists(String testCaseName, int id, String name,int restId) {
		Menu newMenu = new Menu(id, name, restId);

		Response resp = client.target(REST_SERVICE_URL)
				.request(MediaType.APPLICATION_JSON)
				.put(Entity.entity(newMenu, MediaType.APPLICATION_JSON));

		tc.negativeCase(testCaseName, resp);
		
	}
	
	private void testDeleteMenu(String testCaseName, int id) {
		Response resp = client.target(REST_SERVICE_URL).path("/{menuId}")
				.resolveTemplate("menuId", id)
				.request(MediaType.APPLICATION_JSON).delete();

		tc.positiveCase(testCaseName, resp);
	}
	
	private void testDeleteMenu_MenuDoesNotExists(String testCaseName, int id) {
		Response resp = client.target(REST_SERVICE_URL).path("/{menuId}")
				.resolveTemplate("menuId", id)
				.request(MediaType.APPLICATION_JSON).delete();

		tc.negativeCase(testCaseName, resp);
	}
	

	
}
