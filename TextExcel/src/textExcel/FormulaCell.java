package textExcel;

public class FormulaCell extends RealCell{
	private String userInput;  //It is an array but still has the parentheses
	private Spreadsheet sheet;
	
	public FormulaCell(String newValue, Spreadsheet sheet) { //Just an outline
		super(newValue);
		userInput = newValue;
		this.sheet = sheet;
	}
	public String abbreviatedCellText() { //May need to change so it print out something same with Full Cell Text
		double formatString = getDoubleValue();
		System.out.println(formatString);
		int extraSpaces = 0; // To count how many spaces I need to add
		String returnString = ""+formatString; 
		if (returnString.length() >= 10) {
			return returnString.substring(0, 10);
		} else {
			extraSpaces = 10 - returnString.length();
			for (int j = 0; j < extraSpaces; j++) {
				returnString += " ";
			}
			return returnString;
		}
	}
	
	public String fullCellText() { //Prints whatever is passed in will need o be override in percent cell
		return "( "+userInput+" )";
	}
	public double getDoubleValue() { //First split array then check operand
		double totalValue = 0;
		String[] insideParentheses = userInput.split(" ");
		if(insideParentheses.length == 1) {
			return Double.parseDouble(insideParentheses[0]);
		} 
		
		if(insideParentheses[0].equalsIgnoreCase("SUM")) {
			String[] deleteDash = userInput.substring(4).split("-"); //Got rid of the dash and the Sum
			for(char x = Character.toUpperCase(deleteDash[0].charAt(0)); x <= Character.toUpperCase(deleteDash[1].charAt(0)); x++ ) { //x = letters and y = numbers
					for(int y = Integer.parseInt(deleteDash[0].substring(1)); y <= Integer.parseInt(deleteDash[1].substring(1)) ; y++) { //use substring and parseint to change into number 
						Location loc = new SpreadsheetLocation(""+x+y);
						RealCell cell = (RealCell) sheet.getCell(loc);
						totalValue += cell.getDoubleValue();
					}
				}
			return totalValue;
		}
	
			if(insideParentheses[0].equalsIgnoreCase("AVG")) {
				String[] noDash = userInput.substring(4).split("-"); //Got rid of the dash and the Sum
				int numberOfValues = 0;
				for(char x = Character.toUpperCase(noDash[0].charAt(0)); x <= Character.toUpperCase(noDash[1].charAt(0)); x++ ) { //x = letters and y = numbers
						for(int y = Integer.parseInt(noDash[0].substring(1)); y <= Integer.parseInt(noDash[1].substring(1)) ; y++) { //use substring and parseint to change into number 
							Location loc = new SpreadsheetLocation(""+x+y);
							RealCell cell = (RealCell) sheet.getCell(loc);
							totalValue += cell.getDoubleValue();
							numberOfValues += 1;
						}	
					}
				return totalValue/numberOfValues;
			}
			
			if(insideParentheses[0].charAt(0) >= 65) {
				Location assignment = new SpreadsheetLocation(insideParentheses[0].toUpperCase());
				RealCell getValue = (RealCell) sheet.getCell(assignment);
				insideParentheses[0] = ""+getValue.getDoubleValue();
			}
		
		

		for(int i = 1; i < insideParentheses.length; i += 2) { //It checks the operon first
			if(insideParentheses[i+1].charAt(0) >= 65) { //If there is a cell name this will be the action
				Location assignment = new SpreadsheetLocation(insideParentheses[i+1].toUpperCase());
				RealCell getValue = (RealCell) sheet.getCell(assignment);
				insideParentheses[i+1] = ""+getValue.getDoubleValue();
			}
			if(insideParentheses[i].equals("+")) {
				insideParentheses[i+1] = Double.parseDouble(insideParentheses[i-1]) + Double.parseDouble(insideParentheses[i+1])+"";
				System.out.println(insideParentheses[i+1]);
				totalValue = Double.parseDouble(insideParentheses[i+1]);
			} else if(insideParentheses[i].equals("-")) {
				insideParentheses[i+1] = Double.parseDouble(insideParentheses[i-1]) - Double.parseDouble(insideParentheses[i+1])+""; //You need to find out hwo to concatenate
				System.out.println(insideParentheses[i+1]);
				totalValue = Double.parseDouble(insideParentheses[i+1]);
			} else if(insideParentheses[i].equals("/")) {
				insideParentheses[i+1] = Double.parseDouble(insideParentheses[i-1]) / Double.parseDouble(insideParentheses[i+1])+"";
				System.out.println(insideParentheses[i+1]);
				totalValue = Double.parseDouble(insideParentheses[i+1]);
			} else { //This one is multiplying
				insideParentheses[i+1] = Double.parseDouble(insideParentheses[i-1]) * Double.parseDouble(insideParentheses[i+1])+"";
				System.out.println(insideParentheses[i+1]);
				totalValue = Double.parseDouble(insideParentheses[i+1]);
			} 
		}
		return totalValue;
	}
}
