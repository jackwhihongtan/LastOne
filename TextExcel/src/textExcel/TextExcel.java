// Jack Tan 3/7/18 2nd Period
package textExcel;

import java.io.FileNotFoundException;
import java.util.Scanner;

// Update this file with your own code.

public class TextExcel
{

	public static void main(String[] args) // Add your command loop here
	{
		Scanner sc = new Scanner(System.in); //to get user info
	    boolean stillRunning = true;
	    Spreadsheet newSpreadsheet = new Spreadsheet();
	    	while(stillRunning == true) {
	    		System.out.println("What is your command?");
	    		String command = sc.nextLine();
	    		System.out.println(newSpreadsheet.processCommand(command));
	    		System.out.println("Are you done?");
	    		String input = sc.nextLine().toLowerCase(); //Test to see if it works, Takes a input and changes it all to lowercase
	    			if(input == "quit") {
	    				stillRunning = false;
	    			}
	    		}
	}
}
