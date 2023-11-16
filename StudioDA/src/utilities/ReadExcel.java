package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import connectJDBC.JDBCUtil;

public class ReadExcel {
	public static final String excelPath = "E:\\Java\\UDPM\\ThuongHieu.xlsx";
	
	public static void downDB() throws IOException, SQLException {
	int batchSize = 20;
	
		 long start = System.currentTimeMillis();
		 
		 FileInputStream inputStream = new FileInputStream(excelPath);
		 
         Workbook workbook = new XSSFWorkbook(inputStream);
         
         Sheet firstSheet = workbook.getSheetAt(0);
         Iterator<Row> rowIterator = firstSheet.iterator();
         
         Connection conn = JDBCUtil.getConnection();
         conn.setAutoCommit(false);
         
         String sql = "INSERT INTO THUONGHIEU VALUES (?, ?)";
         PreparedStatement statement = conn.prepareStatement(sql);    
         
         int count = 0;
         
         rowIterator.next();
         
         while (rowIterator.hasNext()) {
             Row nextRow = rowIterator.next();
             Iterator<Cell> cellIterator = nextRow.cellIterator();

             while (cellIterator.hasNext()) {
                 Cell nextCell = cellIterator.next();

                 int columnIndex = nextCell.getColumnIndex();

                 switch (columnIndex) {
                 case 0:
                     String id = nextCell.getStringCellValue();
                     statement.setString(1, id);
                     break;
                 case 1:
                	 String name = nextCell.getStringCellValue();
                     statement.setString(2, name);
                 }

             }
              
             statement.addBatch();
              
             if (++count % batchSize == 0) {
                 statement.executeBatch();
             }              

         }

         workbook.close();
          
         // execute the remaining queries
         statement.executeBatch();

         conn.commit();
         conn.close();
          
         long end = System.currentTimeMillis();
         System.out.printf("Import done in %d ms\n", (end - start));
         
         
	}
}
