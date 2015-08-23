/*
 * @author Sanjyoti Patil (spatil29@hawk.iit.edu)
 * Date:28 Nov 2014
 * Description:To show different menu options to Admin
 * his/her choice.
 */

import javax.swing.JOptionPane;

/*
 * This method show the menu box
 */
public class AdminMenu {
	public AdminMenu() {
		String message = "Welcome to the IT help desk trouble ticket system."
				+ "\n", response;

		message += "\n" + "Enter a number to choose an option";
		message += "\n" + "  1 to View Ticket";
		message += "\n" + "  2 to Close Ticket";
		message += "\n" + "  3 to Purge Ticket";
		message += "\n" + "  4 to Exit" + "\n" + " ";

		char answer = 'Y';

		do {

			try {

				response = JOptionPane.showInputDialog(message);
				// Check for non - numeric character and display error message
				if (response.matches("[a-zA-Z]+")) {

					JOptionPane.showMessageDialog(null,
							"Please enter numeric value only", "Result",
							JOptionPane.PLAIN_MESSAGE);

				}

				int choice = Integer.parseInt(response);

				switch (choice) {
				case 1:
					ViewTicket vt = new ViewTicket();
					answer = 'N';
					System.exit(1);
					break;
				case 2:
					CloseTicket ut = new CloseTicket();
					answer = 'N';
					System.exit(1);
					break;
				case 3:
					PurgeTicket pt = new PurgeTicket();
					answer = 'N';
					System.exit(1);
					break;
				case 4:
					answer = 'N';
					System.exit(1);
					break;

				default: {
					answer = 'Y';
					choice = 0;
					JOptionPane
							.showMessageDialog(null, "enter a number: 1 - 4");
				}
				}// end switch
			}// end try
			catch (Exception e) {
				System.out.println(e);
			}
		} while (answer == 'Y' || answer == 'y');

	}

	/*
	 * Main method
	 */
	public static void main(String[] args) {
		new AdminMenu();
	}// end main

}// end class
