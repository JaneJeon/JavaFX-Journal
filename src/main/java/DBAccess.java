// this class is singleton to ensure that only once instance opens the database
// TODO: figure out how I'm going to pass commands to the database - raw SQL, JDBC, or Hibernate?
public class DBAccess {
	private static DBAccess instance = new DBAccess();
	
	private DBAccess() {
		// open connection to database
	}
	
	public static DBAccess getConnection() {
		return instance;
	}
	
	public static void close() {
		// close connection to database
	}
}