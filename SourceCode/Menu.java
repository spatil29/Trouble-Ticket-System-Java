/*
 * @author Sanjyoti Patil (spatil29@hawk.iit.edu)
 * Date:28 Nov 2014
 * Description:To show different menu options to user and accordingly user will select 
 * his/her choice.
 */

import javax.swing.JOptionPane;

/*
 * This method show the menu box
 */
public class Menu {
	public Menu() {
		String message = "Welcome to the IT help desk trouble ticket system."
				+ "\n", response;

		message += "\n" + "Enter a number to choose an option";
		message += "\n" + "  1 to Start Ticket";
		message += "\n" + "  2 to View Ticket";
		message += "\n" + "  3 to Update Ticket";
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
					StartTicket st = new StartTicket();
					answer = 'N';
					System.exit(1);
					break;
				case 2:
					ViewTicket vt = new ViewTicket();
					answer = 'N';
					System.exit(1);
					break;
				case 3:
					UpdateTicket ut = new UpdateTicket();
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
		new Menu();
	}// end main

}// end class
