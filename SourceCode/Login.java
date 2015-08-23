/*
 * @author Sanjyoti Patil (spatil29@hawk.iit.edu)
 * Date of creation:28 Nov 2014
 * Description:Login page for IIT help desk Trouble Ticket System.
 * This will register User first and then allowed to system.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Login {
	private static Connection connect = null;
	private static Statement statement = null;

	public static String usernameInput;
	public static String passwordInput;
	public static String adminInput;
	public static String adminpasswordInput;

	/*
	 * Main Method
	 */
	public static void main(String[] args) throws Exception {

		LoginToIThelpDesk();
		

	}

	private static void LoginToIThelpDesk() {
		// TODO Auto-generated method stub

		String message = "Welcome to IT help desk Trouble Ticket System "
				+ "\n", response;

		message += "\n" + "  1 to login as Admin";
		message += "\n" + "  2 to login as User";
		char answer = 'Y';

		do {
			try {
				response = JOptionPane.showInputDialog(message);
				int choice = Integer.parseInt(response);

				switch (choice) {
				case 1:
					AdminLogin();
					answer = 'N';
					System.exit(1);
					break;
				case 2:
					UserLogin();
					answer = 'N';
					System.exit(1);
					break;
				}

			} catch (Exception e) {
				System.out.println(e);
			}

		} while (answer == 'Y' || answer == 'y');

	}

	private static void AdminLogin() {

		JOptionPane.showMessageDialog(null, "Please Login to proceed",
				"Result", JOptionPane.PLAIN_MESSAGE);

		try {
			adminInput = JOptionPane.showInputDialog("Enter your username");
			passwordInput = JOptionPane.showInputDialog("Enter your Password");

			if (adminInput.equalsIgnoreCase("admin")
					&& passwordInput.equalsIgnoreCase("password")) {
				JOptionPane.showMessageDialog(null, "Welcome Admin", "Result",
						JOptionPane.PLAIN_MESSAGE);
				AdminMenu menu = new AdminMenu();
			} else {
				JOptionPane.showMessageDialog(null,
						"Access Denied.Please Try again", "Result",
						JOptionPane.PLAIN_MESSAGE);

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void UserLogin() {

		JOptionPane.showMessageDialog(null,
				" Welcome User..! Register yourself to procced.", "Result",
				JOptionPane.PLAIN_MESSAGE);
		try {
			usernameInput = JOptionPane.showInputDialog("Enter your username");
			passwordInput = JOptionPane.showInputDialog("Enter your Password");

			if (usernameInput.isEmpty() || passwordInput.isEmpty()) {

				JOptionPane.showMessageDialog(null,
						"Username/Password can not be blank.", "Alert!",
						JOptionPane.PLAIN_MESSAGE);
				System.exit(1);
			}

			createDataBaseForUser(usernameInput, passwordInput);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void createDataBaseForUser(String usernametoChk,
			String passwordtoChk) throws Exception {
		
		try {

			ConnectToDB();

			// create table-sanjyotipati_my_ticket
			String sql = "CREATE TABLE IF NOT EXISTS  sanjyotipati_user_login "
					+ "(id INTEGER not NULL AUTO_INCREMENT, "
					+ " username VARCHAR(30), " + " password VARCHAR(100),"
					+ " PRIMARY KEY (id), UNIQUE KEY(username,password))";
			
			statement.executeUpdate(sql);
			// end create table

			System.out.println("Inserting records into the table...");
			String sql1 = "INSERT IGNORE INTO sanjyotipati_user_login(username, password) "
					+ "VALUES ('"
					+ usernametoChk
					+ "','"
					+ passwordtoChk
					+ "')";
			System.out.println("Inserted  records into the table...");
			String sql2 = " Select * from  sanjyotipati_user_login where username='"
					+ usernametoChk + "'";
			String message = "Welcome " + usernametoChk;
			ResultSet rs = statement.executeQuery(sql2);

			if (rs.isBeforeFirst()) {
				JOptionPane.showMessageDialog(null,
						"You are already registered.", "Result",
						JOptionPane.PLAIN_MESSAGE);
			} else {

				statement.executeUpdate(sql1);
				JOptionPane.showMessageDialog(null,
						"You have successfully registered.", "Result",
						JOptionPane.PLAIN_MESSAGE);
			}
			JOptionPane.showMessageDialog(null, message, "Result",
					JOptionPane.PLAIN_MESSAGE);
			Menu ticketMenu = new Menu();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	/*
	 * Common method to connect to database
	 */
	public static Statement ConnectToDB() {
		try {
			// Get DB driver and connection
			Class.forName("com.mysql.jdbc.Driver");
			// connect to the server database
			connect = DriverManager
					.getConnection("jdbc:mysql://www.papademas.net/tickets?"
							+ "user=root&password=jamesp");
			statement = connect.createStatement();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return statement;

	}

}// end main