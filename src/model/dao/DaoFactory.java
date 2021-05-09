package model.dao;

import db.DB;
import model.dao.MovieDao;
import model.dao.impl.MovieDaoJDBC;

public class DaoFactory {
	//STATIC METHOD TO INSTANTIATE A DAO
	
	public static MovieDao createMovieDao() {
		return new MovieDaoJDBC(DB.getConnection());
	}
	
}
