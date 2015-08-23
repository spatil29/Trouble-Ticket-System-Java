/*
 * @author Sanjyoti Patil(spatil29@hawk.iit.edu)
 * Date:28 Nov 2014
 * Description:This class is to Update Ticket in database
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class UpdateTicket {

	public static String updateTicketNumber;
	public static String updateTicketDes;
	private static Connection connect = null;
	private static Statement statement = null;

	/*
	 * Main method
	 */
	public static void main(String[] args) throws Exception {

		new UpdateTicket();
	}

	// Constructor
	public UpdateTicket() {

		String message = "Enter a number to choose an option:" + "\n", response;

		message += "\n" + "  1 to Update Ticket";
		message += "\n" + "  2 to Return Main Menu";
		char answer = 'Y';

		do {
			try {
				response = JOptionPane.showInputDialog(message);
				int choice = Integer.parseInt(response);

				switch (choice) {
				case 1:
					UpdateTicketDescription();
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
	 * Method to update ticket description
	 */
	public void UpdateTicketDescription() {

		try {
			// Connect to Db
			ConnectToDB();
			updateTicketNumber = JOptionPane
					.showInputDialog("Enter Ticket number of Ticket update");
			// This will check entered ticket number is exist in DB or not
			String sql = "SELECT ticketNum FROM sanjyotipati_my_ticket WHERE ticketNum="
					+ updateTicketNumber;
			ResultSet rs = statement.executeQuery(sql);
			if (!rs.isBeforeFirst()) {
				JOptionPane.showMessageDialog(null,
						"No Record has been found.Try Again", "Alert:\n",
						JOptionPane.PLAIN_MESSAGE);
			} else {
				// update the ticket
				updateTicketDes = JOptionPane
						.showInputDialog("Enter Description to Update");
				String sql1 = "UPDATE sanjyotipati_my_ticket SET ticketDesc= '"
						+ updateTicketDes
						+ "',updateStatus='Updated' WHERE ticketNum="
						+ updateTicketNumber;
				statement.executeUpdate(sql1);

				JOptionPane.showMessageDialog(null, "Ticket has been updated",
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
