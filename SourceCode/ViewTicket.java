/*
 * @author Sanjyoti Patil(spatil29@hawk.iit.edu)
 * Date:28 Nov 2014
 * Description:This class is to view Ticket in database
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class ViewTicket {

	public static String viewTicketNumber;
	public static Connection connect = null;
	public static Statement statement = null;

	/*
	 * Main method
	 */
	public static void main(String[] args) throws Exception {

		new ViewTicket();
	}

	// Constructor
	public ViewTicket() {

		String message = "Enter a number to choose an option:" + "\n", response;

		message += "\n" + "  1 to View all Tickets";
		message += "\n" + "  2 to View specific Ticket";
		message += "\n" + "  3 to Return main menu";
		char answer = 'Y';

		do {
			try {
				response = JOptionPane.showInputDialog(message);
				int choice = Integer.parseInt(response);

				switch (choice) {
				case 1:
					ViewAllTicket();
					answer = 'N';
					System.exit(1);
					break;
				case 2:
					ViewParticularTicket();
					answer = 'N';
					System.exit(1);
					break;
				case 3:
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
	 * Method to view particular ticket
	 */
	public void ViewParticularTicket() {
		try {
			// Connect to Db
			ConnectToDB();

			viewTicketNumber = JOptionPane
					.showInputDialog("Enter Ticket number to view");

			// This will check entered ticket number is exist in DB or not
			String sql = "SELECT ticketNum,ticketDesc,status,currentDateTime,closetime,updateStatus FROM sanjyotipati_my_ticket WHERE ticketNum="
					+ viewTicketNumber;
			ResultSet rs = statement.executeQuery(sql);
			if (!rs.isBeforeFirst()) {
				JOptionPane.showMessageDialog(null,
						"No Record has been found.Try Again",
						"Ticket Information:\n", JOptionPane.PLAIN_MESSAGE);
			}
			// Show the Ticket
			while (rs.next()) {

				String result = "Ticket Number:  " + rs.getString(1) + "\n"
						+ "Ticket Description:  " + rs.getString(2) + "\n"
						+ "Status:  " + rs.getString(3) + "\n"
						+ "Date and Time of Creation:  " + rs.getString(4)
						+ "\n" + "Date and Time of Closer:  " + rs.getString(5)
						+ "\n" + "Ticket Updated Status:  " + rs.getString(6)
						+ "\n";
				JOptionPane.showMessageDialog(null, result,
						"Ticket Information:\n", JOptionPane.PLAIN_MESSAGE);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * Method to view all tickets
	 */
	public void ViewAllTicket() throws ClassNotFoundException {

		try {
			// Connect to Db
			ConnectToDB();
			String sql = "SELECT ticketNum,ticketDesc,status,currentDateTime,closetime,updateStatus FROM sanjyotipati_my_ticket";
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				// Show all tickets
				// Retrieve by column name
				String result = "Ticket Number:  " + rs.getString("ticketNum")
						+ "\n" + "Ticket Description:  "
						+ rs.getString("ticketDesc") + "\n" + "Status:  "
						+ rs.getString("status") + "\n"
						+ "Date and Time of Creation:  "
						+ rs.getString("currentDateTime") + "\n"
						+ "Date and Time of Closer:  "
						+ rs.getString("closetime") + "\n"
						+ "Ticket Updated Status:  "
						+ rs.getString("updateStatus") + "\n";
				JOptionPane.showMessageDialog(null, result, " All Tickets:\n",
						JOptionPane.PLAIN_MESSAGE);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
