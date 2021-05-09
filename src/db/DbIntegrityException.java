package db;

public class DbIntegrityException extends RuntimeException{
	//DATA BASE INTEGRITY EXCEPTION

	private static final long serialVersionUID = 1L;
	
	public DbIntegrityException (String msg) {
		super(msg);
	}
}
