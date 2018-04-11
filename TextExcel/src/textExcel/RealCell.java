package textExcel;

public abstract class RealCell implements Cell {
	private String userinput;
	public RealCell(String numberValue) { //Constructor that will change into int or double Double.parseDouble(userinput);
		userinput = numberValue;
	}
	public String abbreviatedCellText() {
		return userinput;
	}
	public String fullCellText() { //Prints whatever is passed in will need o be override in percent cell
		return userinput;
	}
	public abstract double getDoubleValue(); //Made it abstract method
}
