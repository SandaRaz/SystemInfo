package cnx;

import java.net.InetAddress;
import java.net.UnknownHostException;
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
			String password = "";
			try{
				InetAddress adresse = InetAddress.getLocalHost();
				String nompc = adresse.getHostName();
				switch (nompc){
					case "SUPER-STRIKAS":
						password = "mdpprom15";
						break;
					case "sanda":
					case "p15b-160-sanda":
						password = "root";
						break;
					//------ autre nom de pc -----

					//----------------------------
				}
			}catch(UnknownHostException uhe){
				throw new Exception("Impossible de recuperer le nom de l'ordinateur");
			}

			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/systinfo","postgres",password);
		}
		catch(Exception e){
			System.out.println("Erreur de connexion");
			e.printStackTrace();
		}
		return c;
	}

//	public static void main(String[] args) {
//		Connex.PsqlConnect();
//	}
}