package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args){
		
		/*
		 * SellerDao é a interface. DaoFactory possui um método stático
		 * que cria um SellerDaoJDBC, que possui a imprementação de FindById, ou seja,
		 * estou acessando camadas, delegando funções. Quando uso o createSellerDao,
		 * preciso retornar um SellerDaoJDBC
		 */
		SellerDao sellerDao = DaoFactory.createSellerDao();
		
		
		System.out.println("=== TESTE 1: Seller findById");
		
		//seller vai receber o resultado de findById e retornar um seller
		Seller seller = sellerDao.findById(3);
		System.out.println(seller);
		
		
		System.out.println("\n=== TESTE 2: Seller findByIdDepartment");
		Department department = new Department(2, null);
		
		List<Seller> list = sellerDao.findByDepartment(department);
		for (Seller obj : list) {
			System.out.println(obj);
		}


	}

}
