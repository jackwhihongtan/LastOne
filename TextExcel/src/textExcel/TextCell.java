package textExcel;

public class TextCell implements Cell {
	private String value;

	public TextCell(String cellName) { //Change name 
		value = cellName;
	}

	public String fullCellText() {
		return  "\""+value+"\"";
	}

	public String abbreviatedCellText() {
		int extraSpaces = 0; // To count how many spaces I need to add
		String returnString = value;
		if (value.length() >= 10) {
			return returnString.substring(0, 10);
		} else {
			extraSpaces = 10 - value.length();
			for (int j = 0; j < extraSpaces; j++) {
				returnString += " ";
			}
			return returnString;
		}
	}
}
