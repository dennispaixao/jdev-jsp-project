package connection;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.management.RuntimeErrorException;

/*
 * responsável por fazer conexão com o banco de dados
 */
public class SingleConnection {
	private static String banco ="jdbc:postgresql://localhost:5432/jspproject?autoReconnect=true";
	
	private static String password ="postgres";
	
	private static String user ="postgres";
	
	public static Connection connection = null;
	
	static {
		connect();
	}
	
	public SingleConnection() {
		connect();
	}
	
	private static void connect() {
		try {
			if(connection == null) {
			Class.forName("org.postgresql.Driver");
			connection= DriverManager.getConnection(banco,user,password);
			connection.setAutoCommit(false);
			System.out.println("conectou");
			}
		}catch(Exception e) {
			throw new RuntimeException( "erro ao conectar com o banco");
		}
	}
	
	public static Connection getConnection() {
		return connection;
	}

}
