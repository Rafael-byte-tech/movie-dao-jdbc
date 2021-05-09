package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.MovieDao;
import model.entities.Movie;

public class MovieDaoJDBC implements MovieDao {
	//THIS CLASS IMPLEMENTS THE MOVIE DAO INTERFACE
	
	private Connection conn; //CONNECTS TO DB
	
	public MovieDaoJDBC(Connection conn) { //DEPENDENCY
		this.conn = conn;
	}

	//METHODS
	@Override
	public Movie findById(Integer id) { 
		//FINDS MOVIE BY ID	
		
		PreparedStatement st = null; //SQL STATEMENT
		ResultSet rs = null;         //DISPLAY DB DATA
		
		//TRY BLOCK
		try {
			st = conn.prepareStatement(
					"SELECT * FROM movie "
					+ "WHERE movie.Id = ?");
			
			st.setInt(1, id);
			rs = st.executeQuery();
			
			//IF BLOCK
			if (rs.next()) {
				Movie obj = instantiateMovie(rs);
				return obj;
			}
			
			//ELSE BLOCK
			else {
				return null;
			}
		}
		
		//CATCH BLOCK
		catch(SQLException e) {
			throw new DbException(e.getMessage()); //SQL EXCEPTION
		}
		
		//FINALLY BLOCK
		finally {
			DB.closeStatement(st); //CLOSE STATEMENT
			DB.closeResultSet(rs); //CLOSE RESULT SET
		}
	}
	
	private Movie instantiateMovie(ResultSet rs) throws SQLException{ 
		//INSTANTIATE A MOVIE FROM THE DB. USED IN THE FIND METHOD
		
		Movie obj = new Movie();
		obj.setId(rs.getInt("Id"));
		obj.setName(rs.getString("Name"));
		obj.setGenre(rs.getString("Genre"));
		obj.setReleaseDate(rs.getDate("ReleaseDate"));
		obj.setBudget(rs.getDouble("Budget"));
		obj.setBoxOffice(rs.getDouble("BoxOffice"));
		return obj;
	}

	@Override
	public List<Movie> findAll() {
		//FIND ALL MOVIES 
		return null;
	}

	@Override
	public void add(Movie obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Movie obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

}
