package model.entities;

import java.util.Date;  
import java.io.Serializable;
import java.text.SimpleDateFormat;

public final class Movie implements Serializable{
	//MOVIE ENTITIE CLASS
	
	private static final long serialVersionUID = 1L; //SERIALIZABLE
	
	//ATTRIBUTES
	private Integer id;
	private String name, genre;
	private Date releaseDate;
	private Double budget, boxOffice;
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	//EMPTY CONSTRUCTOR
	public Movie() {
	}
    
	//CONSTRUCTOR
	public Movie(Integer id, String name, String genre, Date releaseDate, Double budget, Double boxOffice) {
		this.id = id;
		this.name = name;
		this.genre = genre;
		this.releaseDate = releaseDate;
		this.budget = budget;
		this.boxOffice = boxOffice;
	}

	//GETTERS AND SETTERS
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public Double getBudget() {
		return budget;
	}

	public void setBudget(Double budget) {
		this.budget = budget;
	}

	public Double getBoxOffice() {
		return boxOffice;
	}

	public void setBoxOffice(Double boxOffice) {
		this.boxOffice = boxOffice;
	}

	//HASH CODE
	@Override
	public final int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	//EQUALS
	@Override
	public final boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movie other = (Movie) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	//TO STRING METHOD
	public final String toString () {
		StringBuilder sb = new StringBuilder();
		sb.append("Movie ");
		sb.append("[");
		sb.append("Id = " + id + ", ");
		sb.append("Name = " + name + ", ");
		sb.append("Release date = " + sdf.format(releaseDate) + ", ");
		sb.append("Budget = " + budget + ", ");
		sb.append("Box Office = " + boxOffice);
		return sb.toString();
	}
}