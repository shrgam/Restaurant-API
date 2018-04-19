package com.zappos.sgambhir.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
import com.zappos.sgambhir.exceptions.ApplicationException;
import com.zappos.sgambhir.exceptions.ServerException;
import com.zappos.sgambhir.dbManager.DBManager;
import com.zappos.sgambhir.model.Menu;

/**
 * @author Shriya
 *
 */
public class MenuDao {
	/**
	 * This method returns all menus 
	 * corresponding to a restaurant id
	 * @param restaurantId
	 * @return
	 * @throws Exception
	 */
	public List<Menu> getAllMenusForRestaurant(int restaurantId)
			throws Exception {
		List<Menu> menuList = null;
		Connection conn = null;
		try {
			DBManager db = new DBManager();
			conn = db.getConnection();

			if (conn == null) {
				throw new ServerException("Connection could not be made");
			}

			String query = "select * from menus where restid = " + restaurantId;

			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);

			menuList = new ArrayList<Menu>();
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int restid = rs.getInt("restid");

				Menu item = new Menu(id, name, restid);
				menuList.add(item);
			}

		} catch (Exception e) {
			throw new ServerException(e.getMessage());
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				throw new ServerException(se.getMessage());
			}
		}

		return menuList;
	}

	/**
	 * This method returns the menu 
	 * for given menu id
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Menu getMenu(int id) throws Exception {
		Menu menu = null;
		Connection conn = null;

		try {
			DBManager db = new DBManager();
			conn = db.getConnection();

			if (conn == null) {
				throw new ServerException("Connection could not be made");
			}

			String query = "SELECT * FROM menus where id =" + id;

			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {
				String name = rs.getString("name");
				int restid = rs.getInt("restid");
				menu = new Menu(id, name, restid);
			}

		} catch (Exception e) {
			throw new ServerException(e.getMessage());
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				throw new ServerException(se.getMessage());
			}
		}
		return menu;
	}

	/**
	 * This method adds a menu 
	 * @param newRest
	 * @return
	 * @throws Exception
	 */
	public int addMenu(Menu newRest) throws Exception {
		boolean isMenuExists = getMenu(newRest.getId()) != null;

		if (!isMenuExists) {
			Connection conn = null;
			try {
				DBManager db = new DBManager();
				conn = db.getConnection();

				if (conn == null) {
					throw new ServerException("Connection could not be made");

				}

				// TODO : Auto increment
				String query = "insert into menus (id, name,restid) values(?,?,?)";

				PreparedStatement preparedStmt = conn.prepareStatement(query);

				preparedStmt.setInt(1, newRest.getId());
				preparedStmt.setString(2, newRest.getName());
				preparedStmt.setInt(3, newRest.getrestId());
				preparedStmt.executeUpdate();

				return 1;
			}

			catch (Exception e) {
				throw new ServerException(e.getMessage());
			} finally {
				try {
					if (conn != null)
						conn.close();
				} catch (SQLException se) {
					throw new ServerException(se.getMessage());
				}
			}
		} else {
			throw new ApplicationException(
					"Menu already exists, use update instead");
		}
	}

	/**
	 * @param uMenu
	 * @return
	 * @throws Exception
	 */
	public int updateMenu(Menu uMenu) throws Exception {

		boolean isMenuExists = getMenu(uMenu.getId()) != null;

		if (isMenuExists) {
			Connection conn = null;
			try {
				DBManager db = new DBManager();
				conn = db.getConnection();

				if (conn == null) {
					throw new ServerException("Connection could not be made");
				}

				String query = "update menus set name = ?, restid = ? where id = ?";

				PreparedStatement preparedStmt = conn.prepareStatement(query);

				preparedStmt.setString(1, uMenu.getName());
				preparedStmt.setInt(2, uMenu.getrestId());
				preparedStmt.setInt(3, uMenu.getId());
				preparedStmt.executeUpdate();

				return 1;
			}

			catch (Exception e) {
				throw new ServerException(e.getMessage());
			} finally {
				try {
					if (conn != null)
						conn.close();
				} catch (SQLException se) {
					throw new ServerException(se.getMessage());
				}

			}
		} else {
			throw new ApplicationException("Menu does not exist");
		}
	}

	/**
	 * This method deletes a menu
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int deleteMenu(int id) throws Exception {
		boolean isMenuExists = getMenu(id) != null;
		Connection conn = null;
		if (isMenuExists) {

			try {
				DBManager db = new DBManager();
				conn = db.getConnection();

				if (conn == null) {
					throw new ServerException("Connection could not be made");

				}

				String query = "delete from menus where id = ?";

				PreparedStatement preparedStmt = conn.prepareStatement(query);

				preparedStmt.setInt(1, id);
				preparedStmt.execute();

				return 1;

			} catch (Exception e) {
				throw new ServerException(e.getMessage());
			} finally {
				try {
					if (conn != null)
						conn.close();
				} catch (SQLException se) {
					throw new ServerException(se.getMessage());
				}
			}
		} else {
			throw new ApplicationException("Menu does not exist");
		}
	}

}