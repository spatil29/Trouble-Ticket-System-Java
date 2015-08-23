/*
 * @author Sanjyoti Patil(spatil29@hawk.iit.edu)
 * Date:28 Nov 2014
 * Description:This class is to Delete Ticket permanently from database
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class PurgeTicket {

	public static String purgeTicketNumber;
	public static String CloseTicketNumber;
	public static String updateTicketDes;
	private static Connection connect = null;
	private static Statement statement = null;

	/*
	 * Main Method
	 */
	public static void main(String[] args) throws Exception {

		new PurgeTicket();
	}

	// Constructor
	public PurgeTicket() {
		String message = "Enter a number to choose an option:" + "\n", response;

		message += "\n" + "  1 to Delete Ticket";
		message += "\n" + "  2 to Return Main Menu";
		char answer = 'Y';

		do {
			try {
				response = JOptionPane.showInputDialog(message);
				int choice = Integer.parseInt(response);

				switch (choice) {
				case 1:
					PurgeTicketInfo();
					answer = 'N';
					System.exit(1);
					break;
				case 2:
					AdminMenu menu = new AdminMenu();
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
	 * Information about Purge Ticket
	 */

	public void PurgeTicketInfo() {
		try {
			// Connect to Db
			ConnectToDB();
			purgeTicketNumber = JOptionPane
					.showInputDialog("Enter Ticket number To delete");

			String sql = "SELECT ticketNum FROM sanjyotipati_my_ticket WHERE ticketNum="
					+ purgeTicketNumber;
			ResultSet rs = statement.executeQuery(sql);
			// This will check entered ticket number is exist in DB or not
			if (!rs.isBeforeFirst()) {
				JOptionPane.showMessageDialog(null,
						"No Record has been found.Try Again", "Alert:\n",
						JOptionPane.PLAIN_MESSAGE);
			} else {
				// Show Warning and then delete ticket
				JOptionPane.showMessageDialog(null,
						"Deletes are PERMANENT. Are you sure you want to delete ticket number "
								+ purgeTicketNumber + "?", "Result",
						JOptionPane.PLAIN_MESSAGE);
				String sql1 = "DELETE FROM sanjyotipati_my_ticket WHERE ticketNum ="
						+ purgeTicketNumber;
				statement.executeUpdate(sql1);
				JOptionPane.showMessageDialog(null, "Ticket is Deleted",
						"Result", JOptionPane.PLAIN_MESSAGE);
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
