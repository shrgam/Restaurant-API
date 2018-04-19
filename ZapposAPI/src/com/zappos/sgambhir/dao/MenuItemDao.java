package com.zappos.sgambhir.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.zappos.sgambhir.dbManager.DBManager;
import com.zappos.sgambhir.exceptions.ApplicationException;
import com.zappos.sgambhir.exceptions.ServerException;
import com.zappos.sgambhir.model.MenuItem;

public class MenuItemDao {
	public List<MenuItem> getAllMenuItemsForMenu(int mId) throws Exception {
		List<MenuItem> mItemList = null;

		Connection conn = null;
		try {
			DBManager db = new DBManager();
			conn = db.getConnection();

			if (conn == null) {
				throw new ServerException("Connection could not be made");
			}

			String query = "select * from mItem where menuId = " + mId;
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);

			mItemList = new ArrayList<MenuItem>();
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				Double price = (double) rs.getFloat("price");
				int menuId = rs.getInt("menuId");

				MenuItem item = new MenuItem(id, name, price, menuId);
				mItemList.add(item);
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

		return mItemList;
	}

	public MenuItem getMItem(int id) throws Exception {
		MenuItem item = null;

		Connection conn = null;
		try {
			DBManager db = new DBManager();
			conn = db.getConnection();

			if (conn == null) {
				throw new ServerException("Connection could not be made");
			}

			String query = "SELECT * FROM mItem where id =" + id;

			System.out.println("query: " + query);
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {
				String name = rs.getString("name");
				Double price = (double) rs.getFloat("price");
				int menuId = rs.getInt("menuId");
				item = new MenuItem(id, name, price, menuId);
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


		return item;
	}

	public int addMItem(MenuItem newItem) throws Exception {
		boolean ismItemExists = getMItem(newItem.getId()) != null;

		System.out.println("value found in db: " + ismItemExists);

		if (!ismItemExists) {
			Connection conn = null;

			try {
				DBManager db = new DBManager();
				conn = db.getConnection();

				if (conn == null) {
					throw new ServerException("Connection could not be made");
				}

				// TODO : Auto increment
				String query = "insert into mitem (id, name, price,menuId) values(?,?,?,?)";

				PreparedStatement preparedStmt = conn.prepareStatement(query);

				preparedStmt.setInt(1, newItem.getId());
				preparedStmt.setString(2, newItem.getName());
				preparedStmt.setFloat(3, newItem.getPrice().floatValue());
				preparedStmt.setInt(4, newItem.getMenuId());
				preparedStmt.executeUpdate();

				System.out.println("Insert succeeded");
				conn.close();

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
					"Element already exists, use update instead");
		}
	}

	public int updateMItem(MenuItem uItem) throws Exception {

		boolean ismItemExists = getMItem(uItem.getId()) != null;

		if (ismItemExists) {

			Connection conn = null;

			try {
				DBManager db = new DBManager();
				conn = db.getConnection();

				if (conn == null) {
					throw new ServerException("Connection could not be made");
				}

				String query = "update mitem set name = ?, price = ? , menuId = ? where id = ?";

				PreparedStatement preparedStmt = conn.prepareStatement(query);

				preparedStmt.setString(1, uItem.getName());
				preparedStmt.setFloat(2, uItem.getPrice().floatValue());
				preparedStmt.setInt(3, uItem.getMenuId());
				preparedStmt.setInt(4, uItem.getId());
				preparedStmt.executeUpdate();

				System.out.println("Update succeeded");
				conn.close();

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
			throw new ApplicationException("Element does not exist");
		}
	}

	public int deleteMItem(int id) throws Exception {
		boolean ismItemExists = getMItem(id) != null;

		Connection conn = null;
		if (ismItemExists) {

			try {
				DBManager db = new DBManager();
				conn = db.getConnection();

				if (conn == null) {
					throw new ServerException("Connection could not be made");
				}

				String query = "delete from mitem where id = ?";

				PreparedStatement preparedStmt = conn.prepareStatement(query);

				preparedStmt.setInt(1, id);
				preparedStmt.execute();

				System.out.println("Delete succeeded");
				conn.close();

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
			throw new ApplicationException("Element does not exist");
		}
	}
}