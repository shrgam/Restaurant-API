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
import com.zappos.sgambhir.model.Restaurant;

/**
 * @author Shriya
 *
 */
public class RestaurantDao {
	/**
	 * This method returns all restaurants
	 * @return
	 * @throws Exception
	 */
	public List<Restaurant> getAllRestaurants() throws Exception {
		// Initializing a list to store all restaurants
		List<Restaurant> restaurantList = null;
		Connection conn = null;
		try {
			// get Db details
			DBManager db = new DBManager();
			conn = db.getConnection();
			if (conn == null) {
				throw new ServerException("Connection could not be made");
			}

			String query = "select * from restaurant"; // call from table
			// restaurant
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);

			// Creating a list to store all restaurants

			restaurantList = new ArrayList<Restaurant>();
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int rating = rs.getInt("rating");
				float avg_price = rs.getFloat("avg_price");
				boolean online_delivery = rs.getBoolean("online_delivery");
				String number = rs.getString("number");
				Restaurant item = new Restaurant(id, name, rating, avg_price,
						online_delivery, number);
				restaurantList.add(item);
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

		return restaurantList;
	}

	/**
	 * This method returns the restaurant
	 * for given id
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Restaurant getRestaurant(int id) throws Exception {
		Restaurant rest = null;
		Connection conn = null;
		try {
			DBManager db = new DBManager();
			conn = db.getConnection();

			if (conn == null) {
				throw new ServerException("Connection could not be made");
			}

			String query = "SELECT name,rating, avg_price, online_delivery, number FROM test.restaurant where id ="
					+ id;

			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {
				String name = rs.getString("name");
				int rating = rs.getInt("rating");
				float avg_price = rs.getFloat("avg_price");
				boolean online_delivery = rs.getBoolean("online_delivery");
				String number = rs.getString("number");

				rest = new Restaurant(id, name, rating, avg_price,
						online_delivery, number);

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


		return rest;
	}

	/**
	 * This method adds a restaurant
	 * @param newRest
	 * @return
	 * @throws Exception
	 */
	public int addRestaurant(Restaurant newRest) throws Exception {
		boolean isRestaurantExists = getRestaurant(newRest.getId()) != null;

		if (!isRestaurantExists) {
			Connection conn = null;

			try {
				DBManager db = new DBManager();
				conn = db.getConnection();

				if (conn == null) {
					throw new ServerException("Connection could not be made");
				}
				String query = "insert into restaurant (id,name,rating, avg_price, online_delivery, number) values(?,?,?,?,?,?)";

				PreparedStatement preparedStmt = conn.prepareStatement(query);

				preparedStmt.setInt(1, newRest.getId());
				preparedStmt.setString(2, newRest.getName());
				preparedStmt.setInt(3, newRest.getRating());
				preparedStmt.setFloat(4, newRest.getPrice());
				preparedStmt.setBoolean(5, newRest.getonlineDelivery());
				preparedStmt.setString(6, newRest.getPhonenumbers());
				preparedStmt.executeUpdate();

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
			throw new ApplicationException(
					"Restaurant already exists, use update instead");
		}
	}

	/**
	 * This method updates a restaurant
	 * @param uRest
	 * @return
	 * @throws Exception
	 */
	public int updateRestaurant(Restaurant uRest) throws Exception {

		boolean isRestaurantExists = getRestaurant(uRest.getId()) != null;

		if (isRestaurantExists) {
			Connection conn = null;

			try {
				DBManager db = new DBManager();
				conn = db.getConnection();

				if (conn == null) {
					throw new ServerException("Connection could not be made");
				}

				String query = "update restaurant set name = ?, rating = ?, avg_price = ?, online_delivery = ?, number= ? where id = ?";

				PreparedStatement preparedStmt = conn.prepareStatement(query);

				preparedStmt.setString(1, uRest.getName());
				preparedStmt.setInt(2, uRest.getRating());
				preparedStmt.setFloat(3, uRest.getPrice());
				preparedStmt.setBoolean(4, uRest.getonlineDelivery());
				preparedStmt.setString(5, uRest.getPhonenumbers());
				preparedStmt.setInt(6, uRest.getId());
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
			throw new ApplicationException("Restaurant does not exist");
		}
	}

	/**
	 * This method deletes a restaurant
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int deleteRestaurant(int id) throws Exception {
		boolean isRestaurantExists = getRestaurant(id) != null;
		Connection conn = null;
		if (isRestaurantExists) {

			try {
				DBManager db = new DBManager();
				conn = db.getConnection();

				if (conn == null) {
					throw new ServerException("Connection could not be made");
				}

				String query = "delete from restaurant where id = ?";

				PreparedStatement preparedStmt = conn.prepareStatement(query);

				preparedStmt.setInt(1, id);
				preparedStmt.execute();

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
			throw new ApplicationException("Restaurant does not exist");
		}
	}

}