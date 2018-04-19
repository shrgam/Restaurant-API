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
import com.zappos.sgambhir.exceptions.ApplicationException;
import com.zappos.sgambhir.dao.MenuItemDao;
import com.zappos.sgambhir.dao.MenuDao;
import com.zappos.sgambhir.model.Menu;
import com.zappos.sgambhir.model.MenuItem;

@Path("/menus")
public class MenuController {

	MenuDao menuDao = new MenuDao();
	MenuItemDao menuItemDao = new MenuItemDao();

	@GET
	@Path("/{menuId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMenu(@PathParam("menuId") int menuId) throws Exception {
		Menu result = menuDao.getMenu(menuId);
		if (result == null) {
						throw new ApplicationException("Item does not exist");
					}		
			return Response.status(Response.Status.OK).entity(result).build();
	}

	@GET
	@Path("/{menuId}/menuItems")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMenuItems(@PathParam("menuId") int menuId)
			throws Exception {
		List<MenuItem> result = menuItemDao.getAllMenuItemsForMenu(menuId);
		if (result == null) {
			throw new ApplicationException("Item does not exist");
		}	
		return Response.status(Response.Status.OK).entity(result).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createMenu(Menu menus,
			@Context HttpServletResponse servletResponse) throws Exception {
		int result = menuDao.addMenu(menus);
		return Response.status(Response.Status.OK).build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateMenu(Menu umenu,
			@Context HttpServletResponse servletResponse) throws Exception {
		int result = menuDao.updateMenu(umenu);
		return Response.status(Response.Status.OK).build();
	}

	@DELETE
	@Path("/{menuId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteMenu(@PathParam("menuId") int menuId)
			throws Exception {
		int result = menuDao.deleteMenu(menuId);
		return Response.status(Response.Status.OK).build();
	}

	@OPTIONS
	@Produces(MediaType.APPLICATION_JSON)
	public String getSupportedOperations() {
		return "<operations>GET, PUT, POST, DELETE</operations>";
	}
}