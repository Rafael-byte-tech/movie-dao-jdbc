package application;

import java.util.Date;

import model.entities.Movie;

public class Program {

	public static void main(String[] args) {
		
		Movie movie = new Movie(3 ,"Hallowen", "Horror", new Date(), 3.0, 4.0);
		System.out.println(movie);
		
	}

}
