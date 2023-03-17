package cnx;

import java.sql.*;

public class Connex{
	public static Connection OracleConnect(){
		Connection c = null;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			String user = "Ticket";
			String pwd = "ticket";
			c = DriverManager.getConnection(url,user,pwd);
		}catch(Exception e){
			System.out.println("Erreur de connexion");
			e.printStackTrace();
		}
		if(c != null){
			//System.out.println("Connexion etablie");
		}

		return c;
	}

	public static Connection PsqlConnect(){
		Connection c = null;
		try
		{
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/systinfo","postgres","root");
		}
		catch(Exception e){
			System.out.println("Erreur de connexion");
			e.printStackTrace();
		}
		return c;
	}

	public static void main(String[] args) {
		Connection cnx = Connex.PsqlConnect();
		System.out.println(">>>> "+cnx);
	}
}