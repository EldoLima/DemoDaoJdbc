package application;

import java.util.Date;
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
		
		
		System.out.println("\n=== TESTE 3: Seller findAll");
		list = sellerDao.findAll();
		for (Seller obj : list) {
			System.out.println(obj);
		}

		
		System.out.println("\n=== TESTE 4: Seller insert");
		
		Seller newSeller = new Seller(null, "Greg","greg@gmail.com", new Date(), 4000.0, department	);
		sellerDao.insert(newSeller);

		System.out.println("Inserted seller! New id = " + newSeller.getId());
		

		
		System.out.println("\n=== TESTE 5: Seller update");
		seller = sellerDao.findById(1);
		
		seller.setName("Eldo");
		sellerDao.insert(seller);
		
		System.out.println(seller);
		
	}

}
