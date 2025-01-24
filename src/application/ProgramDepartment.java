package application;

import java.util.ArrayList;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.dao.SellerDao;
import model.entities.Department;

public class ProgramDepartment {
	public static void main(String[] args) {
		
		
		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
		List<Department> list =  new ArrayList<Department>();
		
		System.out.println("==== TESTE 1: findAll ====");
		list = departmentDao.findAll();
		for (Department obj : list) {
			System.out.println(obj);
		}
		
		
	}

}
