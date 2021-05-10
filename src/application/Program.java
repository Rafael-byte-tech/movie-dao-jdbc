package application;

import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.MovieDao;

public class Program {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		MovieDao movieDao = DaoFactory.createMovieDao();
		
		Integer id;
		String name;
		
		System.out.println("=====Find movie by Id=====");
		System.out.print("Enter movie Id: ");
		id = sc.nextInt();
		System.out.println(movieDao.findById(id));
		
		System.out.println("=====Find movie by name");
		System.out.println("Enter movie name: ");
		sc.nextLine();
		name = sc.nextLine();
		System.out.println(movieDao.findByName(name));
		
		
		

	}

}
