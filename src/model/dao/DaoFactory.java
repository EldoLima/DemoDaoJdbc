package model.dao;

import model.dao.impl.SellerDaoJDBC;

public class DaoFactory {
	
	/*SellerDao é a interface, mas retorna SellerDaoJDBC,
	que contém, a implementação de Seller
	*/
	public static SellerDao createSellerDao() {
		return new SellerDaoJDBC();
	}

}
