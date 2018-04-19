package com.zappos.sgambhir.tests;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.zappos.sgambhir.model.Restaurant;

/**
 * @author Shriya
 *
 */
/**
 * @author User
 *
 */
/**
 * @author User
 *
 */
public class RestaurantTests {

	private Client client;
	private String REST_SERVICE_URL = "http://localhost:8080/RestaurantAPI/rest/restaurant";
	private static TestController tc = new TestController();

	private void init() {
		this.client = ClientBuilder.newClient();
	}

	/**
	 * @param args
	 */
	
	
	public static void main(String[] args) {
		RestaurantTests rTest = new RestaurantTests();

		/*initialize the tester*/
		rTest.init();

		/* test getAllRestaurants*/
		rTest.testGetAllRestaurants("Get All restaurants");

		/* test getRestaurant */
		rTest.testGetRestaurant("Get Restaurant", 1);
		rTest.testGetRestaurant_RestaurantDoesNotExist("Get restaurantDoesNotExists", 11);
		
		/* test getAllMenusForRestaurant*/
		rTest.testGetAllMenusForRestaurant("Get allMenusForRestaurant",2);
		rTest.testGetAllMenusForRestaurant_RestaurantDoesNotExists("Get restaurantDoesNotExists 1", 11);
		
		/* test updateRestaurant*/
		rTest.testUpdateRestaurant("Put Restaurant", 1 , "FukuCafe" , 3 , 15, false , "520-218-8980");
		rTest.testUpdateRestaurant_RestaurantDoesNotExist("Put restaurantNotExists" , 11 , "FukuCafe" , 3 , 15, false , "520-218-8980");
		
		/* test addMenuItem */
		rTest.testAddRestaurant("Post Restaurant", 3 , "FukuBurger" , 4 , 50 , false , "888-320-7770");
		rTest.testAddRestaurant("Post Restaurant 1", 4 , "Raku" , 4 , 60 , false , "870-32-7270");
		rTest.testAddRestaurant_RestaurantAlreadyExists("Post restaurantAlreadyExists", 4 , "Eiffel Tower restaurant" , 4 , 50 , false , "888-320-7770");
		
		/* test deleteRestaurant */
		rTest.testDeleteRestaurant("Delete Restaurant", 3 );
		rTest.testDeleteRestaurant("Delete Restaurant 1", 4 );
		rTest.testDeleteRestaurant_RestaurantDoesNotExists("Post restaurantNotExists", 4);
		
		tc.printResults();
	}

	
	/**
	 * @param testCaseName
	 */
	private void testGetAllRestaurants(String testCaseName) {
		Response resp = client.target(REST_SERVICE_URL)
				.request(MediaType.APPLICATION_JSON).get();

		tc.positiveCase(testCaseName, resp);

	}

	/**
	 * @param testCaseName
	 * @param id
	 */
	private void testGetRestaurant(String testCaseName, int id) {
		Response resp = client.target(REST_SERVICE_URL).path("/{restaurantId}")
				.resolveTemplate("restaurantId", id)
				.request(MediaType.APPLICATION_JSON).get();
		tc.positiveCase(testCaseName, resp);
	}
	
	/**
	 * @param testCaseName
	 * @param id
	 */
	private void testGetRestaurant_RestaurantDoesNotExist(String testCaseName, int id) {
		Response resp = client.target(REST_SERVICE_URL).path("/{restaurantId}")
				.resolveTemplate("restaurantId", id)
				.request(MediaType.APPLICATION_JSON).get();
		tc.negativeCase(testCaseName, resp);
	}

	
	/**
	 * @param testCaseName
	 * @param restId
	 */
	private void testGetAllMenusForRestaurant(String testCaseName, int restId) {

		Response resp = client.target(REST_SERVICE_URL).path("/{restaurantId}/menu")
				.resolveTemplate("restaurantId", restId)
				.request(MediaType.APPLICATION_JSON).get();

		tc.positiveCase(testCaseName, resp); 
	}
	
	/**
	 * @param testCaseName
	 * @param restId
	 */
	private void testGetAllMenusForRestaurant_RestaurantDoesNotExists(String testCaseName, int restId) {

		Response resp = client.target(REST_SERVICE_URL).path("/{restaurantId}/menu")
				.resolveTemplate("restaurantId", restId)
				.request(MediaType.APPLICATION_JSON).get();

		tc.negativeCase(testCaseName, resp); 
	}
	
	
	/**
	 * @param testCaseName
	 * @param id
	 * @param name
	 * @param rating
	 * @param avg_price
	 * @param online_delivery
	 * @param number
	 */
	private void testUpdateRestaurant(String testCaseName, int id, String name, int rating,float avg_price , boolean online_delivery, String number) {
		Restaurant newItem = new Restaurant(id, name, rating,avg_price,online_delivery,number);

		Response resp = client.target(REST_SERVICE_URL)
				.request(MediaType.APPLICATION_JSON)
				.put(Entity.entity(newItem, MediaType.APPLICATION_JSON));
		tc.positiveCase(testCaseName, resp);

	}
	
	/**
	 * @param testCaseName
	 * @param id
	 * @param name
	 * @param rating
	 * @param avg_price
	 * @param online_delivery
	 * @param number
	 */
	private void testUpdateRestaurant_RestaurantDoesNotExist(String testCaseName, int id, String name, int rating,float avg_price , boolean online_delivery, String number) {
		Restaurant newItem = new Restaurant(id, name, rating,avg_price,online_delivery,number);

		Response resp = client.target(REST_SERVICE_URL)
				.request(MediaType.APPLICATION_JSON)
				.put(Entity.entity(newItem, MediaType.APPLICATION_JSON));
		tc.negativeCase(testCaseName, resp);

	}

	
	/**
	 * @param testCaseName
	 * @param id
	 * @param name
	 * @param rating
	 * @param avg_price
	 * @param online_delivery
	 * @param number
	 */
	private void testAddRestaurant(String testCaseName,int id, String name, int rating,float avg_price , boolean online_delivery, String number) {
		Restaurant newItem = new Restaurant(id, name, rating,avg_price,online_delivery,number);
		
		Response resp = client.target(REST_SERVICE_URL)
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.entity(newItem, MediaType.APPLICATION_JSON));
		tc.positiveCase(testCaseName, resp);
		}
	
	/**
	 * @param testCaseName
	 * @param id
	 * @param name
	 * @param rating
	 * @param avg_price
	 * @param online_delivery
	 * @param number
	 */
	private void testAddRestaurant_RestaurantAlreadyExists(String testCaseName,int id, String name, int rating,float avg_price , boolean online_delivery, String number) {
		Restaurant newItem = new Restaurant(id, name, rating,avg_price,online_delivery,number);
		
		Response resp = client.target(REST_SERVICE_URL)
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.entity(newItem, MediaType.APPLICATION_JSON));
		tc.negativeCase(testCaseName, resp);
		}


	/**
	 * @param testCaseName
	 * @param id
	 */
	private void testDeleteRestaurant(String testCaseName, int id) {
		Response resp = client.target(REST_SERVICE_URL).path("/{restaurantId}")
				.resolveTemplate("restaurantId", id)
				.request(MediaType.APPLICATION_JSON).delete();
		tc.positiveCase(testCaseName, resp);
		
	}
	
	/**
	 * @param testCaseName
	 * @param id
	 */
	private void testDeleteRestaurant_RestaurantDoesNotExists(String testCaseName, int id) {
		Response resp = client.target(REST_SERVICE_URL).path("/{restaurantId}")
				.resolveTemplate("restaurantId", id)
				.request(MediaType.APPLICATION_JSON).delete();
		tc.negativeCase(testCaseName, resp);
		
	}
}