package frameworkActions;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.IOException;

public class FileActions {
    private Logger log = LogManager.getLogger(FileActions.class.getName());

    private FileInputStream fileSource = null;
    private XSSFWorkbook workBook = null;

    public int SheetNumber = 0;
    public int pagesNumber = 0;

    public FileActions(String fileRoute) throws IOException {
       this.fileSource = new FileInputStream(fileRoute);
       this.workBook = new XSSFWorkbook(this.fileSource);
       this.pagesNumber = this.workBook.getNumberOfSheets();
       this.log.info("Flow 3: Data File created successfully");
    }

    public int getNumberOfSheets(){
        return this.pagesNumber;
    }

    private XSSFSheet getSheet(){
        return this.workBook.getSheetAt(this.SheetNumber);
    }

    public String getSheeName(){
        return getSheet().getSheetName();
    }

    public int getNumberOfRows(){
        return this.getSheet().getLastRowNum();
    }

    public String getValueOfCell(int row, int cell){
        String cellValue = "";

        try {
            cellValue = getSheet().getRow(row).getCell(cell).toString();
            log.info("Flow 3: It's got the value " +cellValue+ " from row " + row + " on cell " + cell + " from the sheet " + getSheeName());
        }
        catch (Exception e){
            log.fatal("Error in the Flow 3: the system can\'t get the value from row " + row + "on cell " + cell);
            log.fatal("Exception detail " + e.getMessage());
        }

        return cellValue;
    }

    public void closeFile() throws IOException {
        this.fileSource.close();
        this.workBook.close();
    }
}
