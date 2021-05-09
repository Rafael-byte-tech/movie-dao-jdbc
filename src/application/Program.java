package application;

import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.MovieDao;

public class Program {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		MovieDao movieDao = DaoFactory.createMovieDao();
		
		Integer id;
		
		System.out.println("=====Find Movie by Id=====");
		System.out.print("Enter movie Id: ");
		id = sc.nextInt();
		System.out.println(movieDao.findById(id));

	}

}
