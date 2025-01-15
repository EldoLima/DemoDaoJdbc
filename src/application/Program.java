package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args){
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		//Date dateB = sdf.parse("27/06/1998");

		Department obj = new Department(1, "Books");
		System.out.println(obj);
		
		//Seller seller1 = new Seller(21, "Eldo", "eldo@gmail.com",dateB ,300.0, obj);
		Seller seller2 = new Seller(21, "Eldo", "eldo@gmail.com",new Date() ,300.0, obj);
		System.out.println(seller2);
	}

}
