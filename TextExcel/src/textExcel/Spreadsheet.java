// Jack Tan 3/7/18 2nd Period
package textExcel;

// Update this file with your own code.

public class Spreadsheet implements Grid {
	private Cell[][] elements;

	public Spreadsheet() { //Creates an array that is 12*20
		elements = new Cell[20][12];
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 12; j++) {
				elements[i][j] = new EmptyCell();
			}
		}
	}
			
			@Override
			public String processCommand(String command) { //processCommand is used to create new kinds of cells and returns a string with that result 
				String[] commands = command.split(" "); // Split to see how many are word are in String command
				if (commands[0].equalsIgnoreCase("clear")) { //Clear the cell
					if(commands.length > 1) {
						SpreadsheetLocation clearCell = new SpreadsheetLocation(commands[1]);
						elements[clearCell.getRow()][clearCell.getCol()] = new EmptyCell();
						return getGridText();
					} else { //clearing the whole Array
						for(int i = 0; i < 20; i++) {
							for (int j = 0; j < 12; j++) {
								elements[i][j] = new EmptyCell();
							}
						}
						return getGridText();
					} 
				}
				else if (commands.length >= 3) { //Checks if it has a cell name 
					String[] name = command.split(" = ", 2); //Fix the name later
					if(name[1].contains("(")) { //Checks for a parentheses first and makes a new FormulaCell
						SpreadsheetLocation assigment = new SpreadsheetLocation(name[0].toUpperCase());
						elements[assigment.getRow()][assigment.getCol()] = new FormulaCell(name[1].substring(2, name[1].length()-2), this); //This part could be wrong it may fix other errors
						return getGridText();
					}else if(name[1].contains("\"")) {
							SpreadsheetLocation assigment = new SpreadsheetLocation(name[0].toUpperCase());
							elements[assigment.getRow()][assigment.getCol()] = new TextCell(name[1].substring(1, name[1].length()-1));
							//This works only for strings for numbers the index must be from 0 to length
							System.out.println(elements[assigment.getRow()][assigment.getCol()].fullCellText());
							return getGridText();
						} else if(name[1].contains("%")) { //One change needed for This in Percent cell when I type in a decimal and percent it forgets the decimal
							SpreadsheetLocation assigment = new SpreadsheetLocation(name[0].toUpperCase()); //This is if the decimal then it returns the correct thing for numbers
							elements[assigment.getRow()][assigment.getCol()] = new PercentCell(name[1].substring(0, name[1].length()));
							return getGridText();
						} else if(name[1].contains(".")){
							SpreadsheetLocation assigment = new SpreadsheetLocation(name[0].toUpperCase()); //This is if the decimal then it returns the correct thing for numbers
							elements[assigment.getRow()][assigment.getCol()] = new ValueCell(name[1].substring(0, name[1].length()));
							System.out.println(name[1]);
							return getGridText();
						} else {
							SpreadsheetLocation assigment = new SpreadsheetLocation(name[0].toUpperCase());
							elements[assigment.getRow()][assigment.getCol()] = new ValueCell(name[1].substring(0, name[1].length()));
							//This works only for strings for numbers the index must be from 0 to length
							System.out.println(elements[assigment.getRow()][assigment.getCol()].fullCellText());
							return getGridText(); // This could need changing it feels wrong
						}
				}
						 else { // Cell Inspection Test 
							 SpreadsheetLocation assigment = new SpreadsheetLocation(commands[0].toUpperCase());
							 return elements[assigment.getRow()][assigment.getCol()].fullCellText(); 
							 
				}
						
			}
			public int getRows() {
				return 20;
			}
			public int getCols() {
				return 12;
			}
			public Cell getCell(Location loc) {
				return elements[loc.getRow()][loc.getCol()];
			}
			public String getGridText() {
				String entireSpreadSheet = "";
				for(int i = 0; i < 21; i++) { //This for loops creates the numbers on the side
								if(i == 0) { 
								entireSpreadSheet += "   |";
							}else if(i <= 9) {
								entireSpreadSheet += ""+i+"  |";
							} else {
								entireSpreadSheet += ""+i+" |";
							}
							for(int j = 0; j < 12; j++) {
								if(i == 0) { //Prints the Letters in the first row
								entireSpreadSheet += (""+ (char) (65 + j))+"         |";
								} else {//Prints the rest of the empty bracket
									entireSpreadSheet += ""+ elements[i-1][j].abbreviatedCellText() +"|";
								}
							}
							entireSpreadSheet = entireSpreadSheet + "\n"; //I need help inorder to print a new line afte ri print one row
					}
							return entireSpreadSheet;
							
						}

			}




			