package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.MovieDao;
import model.entities.Movie;

public class Program {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		Movie movie;
		
		MovieDao movieDao = DaoFactory.createMovieDao();
		
		Integer id;
		String name, genre;
		Date releaseDate;
		Double budget, boxOffice;
		
		try {
			/*
			System.out.println("=====Find movie by Id=====");
			System.out.print("Enter movie Id: ");
			id = sc.nextInt();
			System.out.println(movieDao.findById(id));
			
			System.out.println("=====Find movie by name");
			System.out.println("Enter movie name: ");
			sc.nextLine();
			name = sc.nextLine();
			System.out.println(movieDao.findByName(name));
			
			System.out.println("=====Find all movies");
			movieDao.findAll().forEach(System.out::println);
			*/
			System.out.println("=====Add movie into the database");
			System.out.print("Name: ");
			name = sc.nextLine();
			System.out.print("Genre: ");
			genre = sc.next();
			System.out.print("Release Date: ");
			releaseDate = sdf.parse(sc.next());
			System.out.print("Budget: ");
			budget = sc.nextDouble();
			System.out.print("Box Office: ");
			boxOffice = sc.nextDouble();
			
			movie = new Movie(null ,name, genre, releaseDate, budget, boxOffice);
			
			movieDao.add(movie);
			
			System.out.println("Inserted. New movie Id = " + movie.getId());
		}
		
		catch (InputMismatchException e) {
			System.out.println(e.getMessage());
		}
		
		catch(ParseException e) {
			System.out.println(e.getMessage());
		}
		
		sc.close();
	}

}
