package com.zappos.sgambhir.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.zappos.sgambhir.dao.MenuDao;
import com.zappos.sgambhir.dao.RestaurantDao;
import com.zappos.sgambhir.exceptions.ApplicationException;
import com.zappos.sgambhir.model.Menu;
import com.zappos.sgambhir.model.Restaurant;

/**
 * @author Shriya
 * 
 */
@Path("/restaurant")
public class RestaurantController {

	RestaurantDao restaurantDao = new RestaurantDao();
	MenuDao menuDao = new MenuDao();

	/**
	 * Call to get all restaurants
	 * 
	 * @return
	 * @throws Exception
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRestaurants() throws Exception {
		List<Restaurant> restnts = restaurantDao.getAllRestaurants();
		if (restnts == null || restnts.size() == 0) {
			throw new ApplicationException("Please check. No restaurants exist");
		}
		return Response.status(Response.Status.OK).entity(restnts).build();
	}

	/**
	 * Call to get all details of a particular restaurant
	 * 
	 * @param restaurantId
	 * @return
	 * @throws Exception
	 */
	@GET
	@Path("/{restaurantId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRestaurant(@PathParam("restaurantId") int restaurantId)
			throws Exception {
		Restaurant result = restaurantDao.getRestaurant(restaurantId);
		if (result == null) {
			throw new ApplicationException("Restaurant does not exist");
		}
		return Response.status(Response.Status.OK).entity(result).build();
	}

	/**
	 * Call to get menu list for a particular restaurant
	 * 
	 * @param restaurantId
	 * @return
	 * @throws Exception
	 */
	@GET
	@Path("/{restaurantId}/menu")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRestMenu(@PathParam("restaurantId") int restaurantId)
			throws Exception {
		List<Menu> resultMenu = menuDao.getAllMenusForRestaurant(restaurantId);
		if (resultMenu == null || resultMenu.size() == 0) {
			throw new ApplicationException("Restaurant does not exist");
		}

		return Response.status(Response.Status.OK).entity(resultMenu).build();
	}

	/**
	 * Adds a restaurant
	 * 
	 * @param newrest
	 * @param servletResponse
	 * @return
	 * @throws Exception
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createRetaurant(Restaurant newrest,
			@Context HttpServletResponse servletResponse) throws Exception {
		int result = restaurantDao.addRestaurant(newrest);
		return Response.status(Response.Status.OK).build();
	}

	/**
	 * Updates a restaurant
	 * 
	 * @param uRest
	 * @param servletResponse
	 * @return
	 * @throws Exception
	 */
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateRestaurant(Restaurant uRest,
			@Context HttpServletResponse servletResponse) throws Exception {
		int result = restaurantDao.updateRestaurant(uRest);
		return Response.status(Response.Status.OK).build();
	}

	/**
	 * Deletes a Restaurant
	 * 
	 * @param restaurantId
	 * @return
	 * @throws Exception
	 */
	@DELETE
	@Path("/{restaurantId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteMenuItem(@PathParam("restaurantId") int restaurantId)
			throws Exception {
		int result = restaurantDao.deleteRestaurant(restaurantId);
		return Response.status(Response.Status.OK).build();
	}

	@OPTIONS
	@Produces(MediaType.APPLICATION_JSON)
	public String getSupportedOperations() {
		return "<operations>GET, PUT, POST, DELETE</operations>";
	}
}
