package com.zappos.sgambhir.tests;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.zappos.sgambhir.model.MenuItem;

/**
 * @author Shriya
 *
 */
public class MenuItemTests {

	private Client client;
	private String REST_SERVICE_URL = "http://localhost:8080/RestaurantAPI/rest/menuItems";
	private static TestController tc = new TestController();

	private void init() {
		this.client = ClientBuilder.newClient();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MenuItemTests miTest = new MenuItemTests();

		// initialize the tester
		miTest.init();

		// Get Menuitem
		miTest.testGetMenuItem("Get menuItem", 1);
		miTest.testGetMenuItem_ItemDoesNotExist("Get menuItemNotExists",11);

		// Update MenuItem
		miTest.testUpdateMenuItem("Put menuItem", 1, "Sandwich", 10.00 , 12);
		miTest.testUpdateMenuItem_ItemDoesNotExist("Put menuItemNotExists", 11, "Sandwich", 10.00 , 12);

		//Add Menuitem
		miTest.testAddMenuItem("Post menuItem", 12, "Noodles", 2.5 ,12);
		miTest.testAddMenuItem("Post menuItem 1", 11, "Burger", 2.5,12);
		miTest.testAddMenuItem_ItemAlreadyExists("Post menuItemExists", 11, "Burger", 3.5,12);
		miTest.testAddMenuItem_MenuDoesNotExist("Put menuItem menuDoesExist", 111, "Sandwich", 10.00 , 102);

		//Delete MenuItem
		miTest.testDeleteMenuItem("Delete menuItem", 12);
		miTest.testDeleteMenuItem("Delete menuItem 1", 11);
		miTest.testDeleteMenuItem_ItemDoesNotExists("Delete menuItemNotExists", 12);
		
		//Get All Menuitem
		//miTest.testGetAllMenuItems("Get All menuItem");
		
		tc.printResults();
	}

	
	/**
	 * Test: Get list of all MenuItems
	 * @param testCaseName
	 */
	private void testGetAllMenuItems(String testCaseName) {
		Response resp = client.target(REST_SERVICE_URL)
				.request(MediaType.APPLICATION_JSON).get();
		
		tc.positiveCase(testCaseName, resp);
	}


	/**
	 * @param testCaseName
	 * @param id
	 */
	private void testGetMenuItem(String testCaseName, int id) {
		Response resp = client.target(REST_SERVICE_URL).path("/{mItemId}")
				.resolveTemplate("mItemId", id)
				.request(MediaType.APPLICATION_JSON).get();

		tc.positiveCase(testCaseName, resp);
	}


	/**
	 * @param testCaseName
	 * @param id
	 */
	private void testGetMenuItem_ItemDoesNotExist(String testCaseName, int id) {
		Response resp = client.target(REST_SERVICE_URL).path("/{mItemId}")
				.resolveTemplate("mItemId", id)
				.request(MediaType.APPLICATION_JSON).get();

		tc.negativeCase(testCaseName, resp);
	}

	/**
	 * @param testCaseName
	 * @param id
	 * @param name
	 * @param price
	 * @param menuId
	 */
	private void testUpdateMenuItem(String testCaseName, int id, String name, Double price , int menuId) {
		MenuItem newItem = new MenuItem(id, name, price , menuId);

		Response resp = client.target(REST_SERVICE_URL)
				.request(MediaType.APPLICATION_JSON)
				.put(Entity.entity(newItem, MediaType.APPLICATION_JSON));

		tc.positiveCase(testCaseName, resp);
	}

	/**
	 * @param testCaseName
	 * @param id
	 * @param name
	 * @param price
	 * @param menuId
	 */
	private void testUpdateMenuItem_ItemDoesNotExist(String testCaseName, int id, String name, Double price , int menuId) {
		MenuItem newItem = new MenuItem(id, name, price, menuId);

		Response resp = client.target(REST_SERVICE_URL)
				.request(MediaType.APPLICATION_JSON)
				.put(Entity.entity(newItem, MediaType.APPLICATION_JSON));

		tc.negativeCase(testCaseName, resp);
	}

	/**
	 * @param testCaseName
	 * @param id
	 * @param name
	 * @param price
	 * @param menuId
	 */
	private void testAddMenuItem(String testCaseName, int id, String name, Double price, int menuId) {
		MenuItem newItem = new MenuItem(id, name, price, menuId);

		Response resp = client.target(REST_SERVICE_URL)
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.entity(newItem, MediaType.APPLICATION_JSON));

		tc.positiveCase(testCaseName, resp);
	}

	/**
	 * @param testCaseName
	 * @param id
	 * @param name
	 * @param price
	 * @param menuId
	 */
	private void testAddMenuItem_ItemAlreadyExists(String testCaseName, int id, String name, Double price,int menuId) {
		MenuItem newItem = new MenuItem(id, name, price, menuId);

		Response resp = client.target(REST_SERVICE_URL)
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.entity(newItem, MediaType.APPLICATION_JSON));

		tc.negativeCase(testCaseName, resp);
	}
	
	/**
	 * @param testCaseName
	 * @param id
	 * @param name
	 * @param price
	 * @param menuId
	 */
	private void testAddMenuItem_MenuDoesNotExist(String testCaseName, int id, String name, Double price,int menuId) {
		MenuItem newItem = new MenuItem(id, name, price, menuId);

		Response resp = client.target(REST_SERVICE_URL)
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.entity(newItem, MediaType.APPLICATION_JSON));

		tc.internalError(testCaseName, resp);
	}
	

	/**
	 * @param testCaseName
	 * @param id
	 */
	private void testDeleteMenuItem(String testCaseName, int id) {
		Response resp = client.target(REST_SERVICE_URL).path("/{mItemId}")
				.resolveTemplate("mItemId", id)
				.request(MediaType.APPLICATION_JSON).delete();

		tc.positiveCase(testCaseName, resp);
	}

	/**
	 * @param testCaseName
	 * @param id
	 */
	private void testDeleteMenuItem_ItemDoesNotExists(String testCaseName, int id) {
		Response resp = client.target(REST_SERVICE_URL).path("/{mItemId}")
				.resolveTemplate("mItemId", id)
				.request(MediaType.APPLICATION_JSON).delete();

		tc.negativeCase(testCaseName, resp);
	}
}