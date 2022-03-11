package policlinica;

import java.awt.Font;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Conexiune {
	
	// Clasa care se ocupa cu conexiunea aplicatiei de baza de date
	
	private Connection activeConnection;
	
	public Conexiune() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			try {
				String userURL = "jdbc:mysql://localhost/", userDB = null, userName = null, userPass = null;
				File userInfo = new File("settings.txt");
				Scanner userReader = new Scanner(userInfo);
				userDB   = userReader.nextLine();
				userName = userReader.nextLine();
				userPass = userReader.nextLine();
				userReader.close();
				activeConnection = DriverManager.getConnection(userURL + userDB, userName, userPass);
			} catch (Exception e) {
				JLabel sqlError = new JLabel("An error occured during SQL Database connecting.");
				sqlError.setFont(new Font("Tahoma", Font.PLAIN, 20));
				JOptionPane.showMessageDialog(null, sqlError, "Eroare SQL", JOptionPane.ERROR_MESSAGE);
				activeConnection = null;
			}
		} catch (Exception e) {
			JLabel jdbcError = new JLabel("An error occured during JDBC Driver connecting.");
			jdbcError.setFont(new Font("Tahoma", Font.PLAIN, 20));
			JOptionPane.showMessageDialog(null, jdbcError, "Eroare JDBC", JOptionPane.ERROR_MESSAGE);
			activeConnection = null;
		}
	}
	
	public Connection getConnection() {
		return this.activeConnection;
	}

}
