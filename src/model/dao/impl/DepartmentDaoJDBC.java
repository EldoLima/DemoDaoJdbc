package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Department obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Department findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			
			//verificar sql cod
			st = conn.prepareStatement(""
					+ " ");
			
			st.setInt(1, id);
			
			rs = st.executeQuery();
			
			
			
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		
		return null;
	}

	@Override
	public List<Department> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			
			st = conn.prepareStatement("SELECT * FROM department ORDER BY Name");
			
			
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

	private Seller instantiateSeller(ResultSet rs, Department dep) throws SQLException {
		Seller sell = new Seller();
		sell.setId(rs.getInt("Id"));
		sell.setName(rs.getString("Name"));
		sell.setEmail(rs.getString("Email"));
		sell.setBirthDate(rs.getDate("BirthDate"));
		sell.setBaseSalary(rs.getDouble("BaseSalary"));
		sell.setDepartment(dep);
		return sell;
	}

	private Department instantiateDepartment(ResultSet rs) throws SQLException {
		Department dep = new Department();
		dep.setId(rs.getInt("Id"));
		dep.setName(rs.getString("Name"));
		return dep;
	}

}
