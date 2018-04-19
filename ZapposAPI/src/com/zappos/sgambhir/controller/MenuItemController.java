package com.zappos.sgambhir.controller;

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

import com.zappos.sgambhir.dao.MenuItemDao;
import com.zappos.sgambhir.exceptions.ApplicationException;
import com.zappos.sgambhir.model.MenuItem;

/**
 * @author Shriya
 *
 */
@Path("/menuItems")
public class MenuItemController {

	MenuItemDao menuItemDao = new MenuItemDao();

	/*
	 * @GET
	 * 
	 * @Path("/menuItems")
	 * 
	 * @Produces(MediaType.APPLICATION_JSON) public Response getMenuItems()
	 * throws Exception { List<MenuItem> mItems = menuItemDao.getAllMenuItems();
	 * return Response.status(Response.Status.OK).entity(mItems).build(); }
	 */

	/**
	 * This API call returns all the menu item details stored for the passed
	 * mItemId
	 * 
	 * @param mItemId
	 * @return
	 * @throws Exception
	 */
	@GET
	@Path("/{mItemId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMenuItem(@PathParam("mItemId") int mItemId)
			throws Exception {
		MenuItem item = menuItemDao.getMItem(mItemId);

		if (item == null) {
			System.out.println("NOT FOUND");
			throw new ApplicationException("Item does not exist");
		}
		return Response.status(Response.Status.OK).entity(item).build();
	}

	/**
	 * This method adds a menu item
	 * 
	 * @param mItem
	 * @param servletResponse
	 * @return
	 * @throws Exception
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createMenuItem(MenuItem mItem,
			@Context HttpServletResponse servletResponse) throws Exception {
		int result = menuItemDao.addMItem(mItem);
		return Response.status(Response.Status.OK).build();
	}

	/**
	 * This method updates a menu item
	 * 
	 * @param uItem
	 * @param servletResponse
	 * @return
	 * @throws Exception
	 */
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateMenuItem(MenuItem uItem,
			@Context HttpServletResponse servletResponse) throws Exception {
		int result = menuItemDao.updateMItem(uItem);
		return Response.status(Response.Status.OK).build();
	}

	/**
	 * This method deletes a menu item
	 * 
	 * @param mItemId
	 * @return
	 * @throws Exception
	 */
	@DELETE
	@Path("/{mItemId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteMenuItem(@PathParam("mItemId") int mItemId)
			throws Exception {
		int result = menuItemDao.deleteMItem(mItemId);
		return Response.status(Response.Status.OK).build();
	}

	/**
	 * @return
	 */
	@OPTIONS
	@Produces(MediaType.APPLICATION_JSON)
	public String getSupportedOperations() {
		return "<operations>GET, PUT, POST, DELETE</operations>";
	}
}