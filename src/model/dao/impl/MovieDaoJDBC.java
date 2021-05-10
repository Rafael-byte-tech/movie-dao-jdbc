package model.dao.impl;

import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
				Movie obj = instantiateMovie(rs); //OBJ INSTANTIATION
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
	
	@Override
	public Movie findByName(String name) {
		//FINDS MOVIE BY NAME
		PreparedStatement st = null; //SQL STATEMENT
		ResultSet rs = null;         //DISPLAY DATA
		
		//TRY BLOCK
		try {
			st = conn.prepareStatement("SELECT * FROM movie "
					+ "WHERE movie.Name = ?");
					
			st.setString(1, name);
			rs = st.executeQuery();
			
			//IF BLOCK
			if(rs.next()) {
				Movie obj = instantiateMovie(rs); //OBJ INSTANTIATION
				return obj;
			}
			
			//ELSE BLOCK
			else {
				return null;
			}
		}
		
		//CATCH BLOCK
		catch (SQLException e) {
			throw new DbException(e.getMessage());
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
		
		PreparedStatement st = null; //SQL STATEMENT
		ResultSet rs = null;         //RESULT SET
		
		List<Movie> list = new ArrayList<Movie>(); //LIST OF MOVIES
		
		//TRY BLOCK
		try {
			st = conn.prepareStatement("SELECT * FROM movie"); 
			rs = st.executeQuery();
			
			//WHILE BLOCK
			while(rs.next()) {
				Movie obj = instantiateMovie(rs); //OBJ INSTANTIATION
				list.add(obj);
			}
			
			return list;
		}
		
		//CATCH BLOCK
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		
		//FINALLY BLOCK 
		finally {
			DB.closeStatement(st); //CLOSE STATEMENT 
			DB.closeResultSet(rs); //CLOSE RESULT SET
		}
	}

	@Override
	public void add(Movie obj) {
		//ADD MOVIE TO THE DB
		
		PreparedStatement st = null; //SQL STATEMENT
		ResultSet rs = null;         //DISPLAY DATA
		
		//TRY BLOCK
		try {
			st = conn.prepareStatement("INSERT INTO movie (Name, Genre, ReleaseDate, Budget, BoxOffice) VALUES "
					+"(?, ?, ?, ?, ?)", 
					Statement.RETURN_GENERATED_KEYS);
			st.setString(1, obj.getName());
			st.setString(2, obj.getGenre());
			st.setDate(3, new java.sql.Date(obj.getReleaseDate().getTime()));
			st.setDouble(4, obj.getBudget());
			st.setDouble(5, obj.getBoxOffice());
			
			Integer rowsAffected = st.executeUpdate();
			
			//IF BLOCK
			if(rowsAffected > 0) {
				rs = st.getGeneratedKeys();
				//IF BLOCK
				if (rs.next()) {           
					Integer id = rs.getInt(1);
					obj.setId(id);
				}
				DB.closeResultSet(rs); //CLOSE RESULT SET
			}
			
			//ELSE BLOCK
			else {
				throw new DbException("Error. No rows Affected"); //DB EXCEPTION
			}
		}
		
		//CATCH BLOCK
		catch(SQLException e) {
			throw new DbException(e.getMessage()); //SQL EXCEPTION
		}
		
		//FINALLY BLOCK
		finally {
			DB.closeStatement(st); //CLOSE STATEMENT
		}
	}

	@Override
	public void update(Movie obj) {
		//UPDATE MOVIE DATA 
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement(null);
		}
		
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		
		finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void deleteById(Integer id) {
		// DELETE MOVIE BY ID
		PreparedStatement st = null; //SQL STATEMENT
		
		//TRY BLOCK
		try {
			st = conn.prepareStatement("DELETE FROM movie WHERE Id = ?"); 
			
			st.setInt(1, id);
			
			st.executeUpdate();
		}
		
        //CATCH BLOCK		
		catch (SQLException e){
			throw new DbException(e.getMessage());
		}
		
		//FINALLY BLOCK
		finally {
			DB.closeStatement(st); //CLOSE STATEMENT
		}	
	}
}
