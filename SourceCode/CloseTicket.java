/*
 * @author Sanjyoti Patil(spatil29@hawk.iit.edu)
 * Date:28 Nov 2014
 * Description:This class is to Close Ticket in database
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

public class CloseTicket {

	public static String CloseTicketNumber;
	private static Connection connect = null;
	private static Statement statement = null;
	String datetimeclose;

	/*
	 * Main method
	 */
	public static void main(String[] args) throws Exception {

		new CloseTicket();
	}

	// Constructor
	public CloseTicket() {

		String message = "Enter a number to choose an option:" + "\n", response;

		message += "\n" + "  1 to Close Ticket";
		message += "\n" + "  2 to Return Main Menu";
		char answer = 'Y';

		do {
			try {
				response = JOptionPane.showInputDialog(message);
				int choice = Integer.parseInt(response);

				switch (choice) {
				case 1:
					CloseTicket();
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
	 * Method to close ticket
	 */
	public void CloseTicket() {

		try {
			// Connect to Db
			ConnectToDB();
			// get current date time with Date()
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			datetimeclose = dateFormat.format(date);
			CloseTicketNumber = JOptionPane
					.showInputDialog("Enter Ticket number to close the ticket");
			// This will check entered ticket number is exist in DB or not
			String sql = "SELECT ticketNum FROM sanjyotipati_my_ticket WHERE ticketNum="
					+ CloseTicketNumber;
			ResultSet rs = statement.executeQuery(sql);
			if (!rs.isBeforeFirst()) {
				JOptionPane.showMessageDialog(null,
						"No Record has been found.Try Again", "Alert:\n",
						JOptionPane.PLAIN_MESSAGE);
			} else {
				// Close the ticket
				String sql1 = "UPDATE sanjyotipati_my_ticket SET status ='Closed', closetime= '"
						+ datetimeclose
						+ "' WHERE ticketNum ="
						+ CloseTicketNumber;
				statement.executeUpdate(sql1);
				JOptionPane.showMessageDialog(null, "Ticket has been Closed.",
						"Result", JOptionPane.PLAIN_MESSAGE);
			}

		} catch (SQLException e) {
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
