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
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao{
	private Connection conn=null;
	
	public SellerDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Seller findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement(
					"SELECT seller.*, department.Name as DepName "
					+"FROM seller INNER JOIN department "
					+"ON seller.DepartmentId = department.Id "
					+ "WHERE seller.Id = ?");
			
			st.setInt(1, id);
			
			//resultSet -> resultado em forma de tabela, mas estou programando em OB
			rs = st.executeQuery();
			//abaixo faço a converção
			//a linha abaixo seta os valores obtidos do banco de dados
			if(rs.next()) {
				//setando atributos do Department
				Department dep = instantiateDepartment(rs);
				
				//objeto Seller
				Seller obj = instantiateSeller(rs, dep);
				return obj;
			}
			//quando não há resultado para a busca do Id
			return null;
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	
	}

	private Seller instantiateSeller(ResultSet rs, Department dep) throws SQLException {
		Seller obj = new Seller();
		obj.setId(rs.getInt("Id"));
		obj.setName(rs.getString("Name"));
		obj.setEmail(rs.getString("Email"));
		obj.setBaseSalary(rs.getDouble("BaseSalary"));
		obj.setBrithDate(rs.getDate("BirthDate"));
		obj.setDepartment(dep);
		return obj;
	}

	private Department instantiateDepartment(ResultSet rs) throws SQLException {
		 Department dep = new Department();
			dep.setId(rs.getInt("DepartmentId"));
			dep.setName(rs.getString("DepName"));
		return dep;
	}

	@Override
	public List<Seller> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(""
					+ " SELECT seller.*,department.Name as DepName"
					+ " FROM seller INNER JOIN department"
					+ " ON seller.DepartmentId = department.Id"
					+ " ORDER BY Name");
			
		
			rs = st.executeQuery();
			
			List<Seller> list = new ArrayList<Seller>();
			Map<Integer, Department> map = new HashMap<Integer, Department>();
			
			while(rs.next()) {
				
				//verificar se o dep já existe
				Department dep = map.get(rs.getInt("DepartmentId"));
				
				
				//se não exist, faço sua instância
				if(dep == null) {
					 dep = instantiateDepartment(rs);
					 map.put(rs.getInt("DepartmentId"), dep);
				}
				Seller obj =  instantiateSeller(rs, dep);
				list.add(obj);
				
			}
			return list;
			
			
		} catch (Exception e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
		
	}

	@Override
	public List<Seller> findByDepartment(Department department) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(""
					+ " SELECT seller.*,department.Name as DepName"
					+ " FROM seller INNER JOIN department"
					+ " ON seller.DepartmentId = department.Id"
					+ " WHERE DepartmentId = ?"
					+ " ORDER BY Name");
			
			st.setInt(1, department.getId());
			rs = st.executeQuery();
			
			List<Seller> list = new ArrayList<Seller>();
			Map<Integer, Department> map = new HashMap<Integer, Department>();
			
			while(rs.next()) {
				
				//verificar se o dep já existe
				Department dep = map.get(rs.getInt("DepartmentId"));
				
				
				//se não exist, faço sua instância
				if(dep == null) {
					 dep = instantiateDepartment(rs);
					 map.put(rs.getInt("DepartmentId"), dep);
				}
				Seller obj =  instantiateSeller(rs, dep);
				list.add(obj);
				
			}
			return list;
			
			
		} catch (Exception e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
	
	

}
