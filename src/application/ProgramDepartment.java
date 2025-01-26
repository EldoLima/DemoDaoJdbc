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
		
		System.out.println("==== TESTE 2: findById ====");
		System.out.println(departmentDao.findById(1));
		
		System.out.println("==== TESTE 3: update ====");
		Department dep = departmentDao.findById(1);
		dep.setName("Comida");
		
		departmentDao.update(dep);
		System.out.println("Update!");
		
		System.out.println("==== TESTE 4: insert ====");
		Department newDep = new Department(null, "MOTOS");
		
		departmentDao.insert(newDep);
		list = departmentDao.findAll();
		for (Department obj : list) {
			System.out.println(obj);
		}
		
		
		
		
		
		

		
		
		
		
		

		
		
	}

}
