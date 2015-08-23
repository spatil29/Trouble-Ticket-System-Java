/*
 * @author Sanjyoti Patil(spatil29@hawk.iit.edu)
 * Date:28 Nov 2014
 * Description:This class is to Create Ticket in database. This will establish the connection with database 
 * This will create table in the given database "tickets"
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.swing.JOptionPane;

public class StartTicket {

	public static String ticket;
	public static int ticketNumber;
	String datetime;
	private static Connection connect = null;
	private static Statement statement = null;

	/*
	 * Main method
	 */
	public static void main(String[] args) throws Exception {
		new StartTicket();

	}

	// Constructor
	public StartTicket() {

		String message = "Enter a number to choose an option:" + "\n", response;

		message += "\n" + "  1 to Create New Tickets";
		message += "\n" + "  2 to Return main menu";
		char answer = 'Y';

		do {
			try {
				response = JOptionPane.showInputDialog(message);
				int choice = Integer.parseInt(response);

				switch (choice) {
				case 1:
					StartNewTicket();
					answer = 'N';
					System.exit(1);
					break;
				case 2:
					Menu menu = new Menu();
					answer = 'N';
					System.exit(1);
					break;
				}

			} catch (Exception e) {
				System.out.println(e);
			}

		} while (answer == 'Y' || answer == 'y');
	}

	/*
	 * Method to Create table and Ticket
	 */

	public void StartNewTicket() {
		try {
			createDataBase();

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int repeat = 1;

		do {

			ticket = JOptionPane
					.showInputDialog("Enter brief description of your problem");
			try {
				TicketInfo(ticketNumber, ticket);
			} catch (Exception e) {

				e.printStackTrace();
			}

		} while (repeat == 0);

		System.exit(1);
	}

	/*
	 * Method to insert information into database
	 */

	public void TicketInfo(int ticketNumber, String ticket) throws Exception {

		try {
			// get current date time with Date()
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			datetime = dateFormat.format(date);

			// This will load the MySQL driver, each DB has its own driver
			ConnectToDB();
			Random rand = new Random();
			ticketNumber = rand.nextInt(10001) + 1000;
			System.out.println("Inserting records into the table...");
			String sql = "INSERT INTO sanjyotipati_my_ticket(ticketNum, ticketDesc,status,currentDateTime,closetime,updateStatus) "
					+ "VALUES ('"
					+ ticketNumber
					+ "','"
					+ ticket
					+ "','"
					+ "Open"
					+ "','"
					+ datetime
					+ "','"
					+ "NA"
					+ "','"
					+ "NA"
					+ "')";

			statement.executeUpdate(sql);
			System.out.println("Inserted records into the table...");
			JOptionPane.showMessageDialog(null, "New Ticket has been created",
					"Result", JOptionPane.PLAIN_MESSAGE);
		} catch (NumberFormatException e) {
			System.out.println(e.getMessage());
		}

	}

	/*
	 * Create table into tickets database
	 */

	public static void createDataBase() throws Exception {
		try {
			// This will load the MySQL driver, each DB has its own driver
			ConnectToDB();

			// create table-sanjyotipati_my_ticket
			String sql = "CREATE TABLE sanjyotipati_my_ticket "
					+ "(id INTEGER not NULL AUTO_INCREMENT, "
					+ " ticketNum int, " + " ticketDesc VARCHAR(100),"
					+ " status VARCHAR(20), "
					+ " currentDateTime VARCHAR(20), "
					+ " closetime VARCHAR(20), "
					+ " updateStatus VARCHAR(10),  " + " PRIMARY KEY (id))";

			statement.executeUpdate(sql);
			// end create table

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
}// end main class
