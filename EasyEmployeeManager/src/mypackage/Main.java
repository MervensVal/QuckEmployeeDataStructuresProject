package mypackage;

import java.io.*;
import java.util.*;

public class Main {

	static String employeeDbFile = "EmployeeDB.txt";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Welcome to our non DB Employee management program!");

		System.out.println("Choose an option from the list below");

		System.out.println("*******************************************");
		System.out.println("(0)	Quit");
		System.out.println("(1)	Display All Employee Information");
		System.out.println("(2)	Insert a New Employee");
		System.out.println("(3)	Update Employee Information");
		System.out.println("(4)	View Employee Information by ID (Hash Table & Linked List Implementation)");
		System.out.println("********************************************");

		chooseUserChoices();

	}

	// --------------------------------------------------------------------------------------------------------
	public static boolean chooseUserChoices(/* Employee[] countyArray */) {
		Main mainObj = new Main();
		Scanner input = new Scanner(System.in);
		System.out.println("Please make a selection");
		String userDecision = input.next();
		System.out.println();

		switch (userDecision) {
		case "0":
			System.out.println("Program ended. Thank you");
			return true;
		case "1":
			mainObj.displayEmployees();
			System.out.println("All employee information displayed");
			break;
		case "2":
			mainObj.insertData();
			System.out.println("New employees inserted");
			break;
		case "3":
			mainObj.update();
			System.out.println("Employee information has been updated");
			break;
		case "4":
			mainObj.findEmployeeLinearSearchID();
			System.out.println("Selected employee information displayed");
			break;
		default:
			System.out.println("Incorrect information entered");
		}
		return false;
	}

	// ----------------------------------------------------------------------------------------------------------------------------------------
	public void displayEmployees() {
		System.out.println();
		System.out.printf("%-25s%-25s%-25s%-25s%-25s%-25s%-25s", "ID ", " First Name  ", " Last Name ",
				" Email Address ", " Position ", " Location", "Current Employee");
		System.out.println();
		try {
			BufferedReader br = new BufferedReader(new FileReader(employeeDbFile));
			String s = "";
			while ((s = br.readLine()) != null) {
				String data[] = new String[7];
				data = s.split(",");
				for (int i = 0; i < 7; i++) {
					System.out.printf("%-25s", data[i]);
				}
				System.out.println();
			}
		} catch (Exception e) {

		}

	}

	// ----------------------------------------------------------------------------------------------------------------------------------------

	/*
	 * This method recieves the users input/new employee information and uses that
	 * information to create the new employee and insert the info in the text file
	 */
	public void insertData() {
		System.out.println();

		// [Scanner with System.in] is used to get user data
		Scanner sc = new Scanner(System.in);
//		System.out.println("Enter Employee Id: ");
//		String epmployeeID = sc.nextLine();

		System.out.println("Enter Employee First Name: ");
		String epmployeeFN = sc.nextLine();

		System.out.println("Enter Employee Last Name: ");
		String epmployeeLN = sc.nextLine();

		System.out.println("Enter email: ");
		String email = sc.nextLine();

		System.out.println("Enter Employee Position: ");
		String epmployeePosition = sc.nextLine();

		System.out.println("Enter Location: ");
		String epmployeeLocation = sc.nextLine();

		System.out.println("Current Employee? (Y/N): ");
		String employementStatus = sc.nextLine();

		System.out.println();
		try {
			// [File]An abstract representation of file and directory pathnames.
			File fileObj = new File(employeeDbFile);

			// [PrintWriter] Prints formatted representations of objects to a text-output
			// stream.
			// [FileOutputStream]Creates a file output stream to write to the file
			// represented bythe specified
			// File object. If the second argument is true, then bytes will be written to
			// the end of the filerather than the beginning
			PrintWriter pw = new PrintWriter(new FileOutputStream(fileObj, true));

			int epmployeeID = amountEmployeesStored();

			// [append method] Appends the specified character sequence to this writer.
			pw.append(epmployeeID + "," + epmployeeFN + "," + epmployeeLN + "," + email + "," + epmployeePosition + ","
					+ epmployeeLocation + "," + employementStatus + "\n");
			pw.close();
		} catch (Exception e) {

		}
	}

	// ----------------------------------------------------------------------------------------------------------------------------------------

	public void update() {
		// Part 1)
		System.out.println();
		System.out.println("Enter the ID of the employee you want to update: ");
		Scanner sc = new Scanner(System.in);
		int idToEmployeeToChange = sc.nextInt();
		String ColumnsToChange[] = { "First Name:  ", "Last Name: ", "Email", "Position: ", "Location: ",
				"Employment Status: " };
		char yesNo[] = new char[6];
		String UpdatedInfo[] = new String[6];

		System.out.println("Select the columns you want to update. Type (Y:Yes | N:No)");
		for (int i = 0; i < 6; i++) {
			// asks for which columns to change
			System.out.println(ColumnsToChange[i] + "");
			// accepts the users answer
			char temp = sc.next().charAt(0);
			// adds the answer to this array
			yesNo[i] = temp;
		}

		// Part 2)
		System.out.println();
		System.out.println("----------------------------------------");
		System.out.println("Add new values for the user");
		for (int i = 0; i < 6; i++) {
			System.out.println(ColumnsToChange[i]);
			if (yesNo[i] == 'Y' || yesNo[i] == 'y') {
				// add user input to array to update values
				Scanner sc1 = new Scanner(System.in);
				String tempVal = sc1.nextLine();
				UpdatedInfo[i] = tempVal;
				System.out.println();
			} else {
				System.out.println("Will not change");
			}
		}
		System.out.println();
		// Part 3)
		StringBuffer sb = new StringBuffer();
		try {
			BufferedReader br = new BufferedReader(new FileReader(employeeDbFile));
			String s = "";
			while ((s = br.readLine()) != null) // read line of text
			{
				String data[] = new String[6];
				data = s.split(","); // split text based off of comma
				if (idToEmployeeToChange == Integer.parseInt(data[0])) // if id matches
				{
					String row = data[0] + ",";
					for (int i = 0; i < 6; i++) {
						if (yesNo[i] == 'Y' || yesNo[i] == 'y') {
							row = row + UpdatedInfo[i] + ",";
						} else {
							row = row + data[i + 1] + ",";
						}
					}
					sb.append(row);
					sb.append("\n");
				} else {
					sb.append(s);
					sb.append("\n");
				}
			}

			// Part 4)
			File fileObj = new File(employeeDbFile);
			PrintWriter pw = new PrintWriter(new FileOutputStream(fileObj, false));
			pw.print(sb.toString());
			pw.close();
		} catch (Exception e) {

		}

	}
	
	//-------------------------------------------------------------------------------------
	/*This method will be used to find the amount of employees in a file*/
	public int amountEmployeesStored() 
	{
		int amountEmployees = 1;
		try {
		BufferedReader br = new BufferedReader(new FileReader(employeeDbFile));
		String s = "";
		while (br.readLine() != null) // read line of text
		{
			amountEmployees++;
		}
	}catch(Exception e)
		{
		
		}
		return amountEmployees;
	}
	
	//-------------------------------------------------------------------------------------
	/*This method will be used to find a specific Employee based on id*/
	public void findEmployeeLinearSearchID() 
	{
		Scanner input = new Scanner(System.in);
		System.out.println("Enter the Employee Id: "); 
		int idToSearch = input.nextInt();
		try {
			BufferedReader br = new BufferedReader(new FileReader(employeeDbFile));
			String s = "";
			while((s = br.readLine()) != null) 
			{
				String data[] = new String[6]; // create array with capacity of 6 string items to hold entire line of code
				data = s.split(","); // split text based on comma
				if(idToSearch == Integer.parseInt(data[0])) {
					System.out.println("ID: " + data[0]);
					System.out.println("First Name: "+ data[1]);
					System.out.println("Last Name: " + data[2]);
					System.out.println("Last Name: "+ data[3]);
					System.out.println("Email Address: "+ data[4]);
					System.out.println("Position: "+ data[5]);
					System.out.println("Location: "+ data[6]);
					System.out.println("Current Employee: "+ data[7]);
				}
			}
		}
		catch(Exception e) 
		{
			
		}
	}
	
}



















