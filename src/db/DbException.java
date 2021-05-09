package db;

public class DbException extends RuntimeException {
	//DATA BASE EXCEPTIONS

	private static final long serialVersionUID = 1L;
	
	public DbException(String msg) {
		super(msg);
	}

}
