// Jack Tan 3/7/18 2nd Period
package textExcel;

//Update this file with your own code.

public class SpreadsheetLocation implements Location
{
	private int row;
	private int column;
	@Override
    public int getRow()
    {
        // TODO Auto-generated method stub
        return row;
    }

    @Override
    public int getCol()
    {
        // TODO Auto-generated mehod stub
        return column;
    }
    
    public SpreadsheetLocation(String cellName) //set rows and columns first cellName.charAt(0)
    {
    	column = Character.toUpperCase(cellName.charAt(0)) - 'A';
    	row = Integer.valueOf(cellName.substring(1)) - 1;
       
    	// TODO: Fill this out with your own code
    }

}
