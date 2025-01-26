package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;
import model.entities.Seller;

public class DepartmentDaoJDBC implements DepartmentDao{
	
	private Connection conn = null;
	
	public DepartmentDaoJDBC(Connection conn ) {
		this.conn = conn;
		
	}

	@Override
	public void insert(Department obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(""
					+ "INSERT INTO department (Name) "
					+ "VALUES (? )", Statement.RETURN_GENERATED_KEYS);
			st.setString(1, obj.getName());
			
			int rowsAffected = st.executeUpdate();
			
			if(rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				
				if (rs.next()) {
					int id= rs.getInt(1);
					obj.setId(id);
					
				}
				DB.closeResultSet(rs);
			}
			
		} catch (Exception e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public void update(Department obj) {
		
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement(""
					+ "UPDATE department "
					+ "SET Name = ? "
					+ "WHERE Id = ? ");
			st.setString(1, obj.getName());
			st.setInt(2, obj.getId());
			
			st.executeUpdate();
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}

	
	
	//Verificar - Department tem fk de Seller
	@Override
	public void deleById(Integer id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(""
					+ "UPDATE seller SET DepartmentId = NULL WHERE DepartmentId = ?"
					+ "DELETE FROM department WHERE Id = ? "
					+ "");
			st.setInt(1, id);
			st.setInt(2, id);
			st.executeUpdate();
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		
	}

	@Override
	public Department findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement(""
					+ "SELECT * FROM department WHERE Id = ? ");
			st.setInt(1, id);
			rs = st.executeQuery();
			
			while(rs.next()) {
				Department dep = instantiateDepartment(rs);
				
				return dep;
			}
			return null;
			
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Department> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {	
			st = conn.prepareStatement("SELECT * FROM department ORDER BY Id");
			rs = st.executeQuery();	
			List<Department> list = new ArrayList<Department>();
			while(rs.next()) {
				Department dep = instantiateDepartment(rs);
				list.add(dep);
			}
			return list;	
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
		
		
	}


	private Department instantiateDepartment(ResultSet rs) throws SQLException {
		Department dep = new Department();
		dep.setId(rs.getInt("Id"));
		dep.setName(rs.getString("Name"));
		return dep;
	}

}
