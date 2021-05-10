package model.dao;

import java.util.List;

import model.entities.Movie;

public interface MovieDao {
	//MOVIE DAO INTERFACE
	
	public Movie findById(Integer id);
	public Movie findByName(String Name);
	public List<Movie> findAll();
	public void add(Movie obj);
	public void update(Movie obj);
	public void deleteById(Integer id);
}
