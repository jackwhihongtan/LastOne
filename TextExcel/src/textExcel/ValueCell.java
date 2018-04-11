package textExcel;

public class ValueCell extends RealCell { // Current Sattuus when I input some thing long it pritns everything but the
											// first number
	private String userInput; // This will be used for the two methods thatr eturn strings
	private double value;

	public ValueCell(String userInput) {
		super(userInput);
		this.userInput = userInput;
		value = Double.parseDouble(userInput);
	}

	public double getDoubleValue() { // it returns 12 like 12.0
		return value;
	}

	public String abbreviatedCellText() {
		int extraSpaces = 0; // To count how many spaces I need to add
		String returnString = ""+value; 
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

	public String fullCellText() { // I am printing the whole String that was passed in
		return  userInput;
	}
	
}
