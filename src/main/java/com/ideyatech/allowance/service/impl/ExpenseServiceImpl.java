package com.ideyatech.allowance.service.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.opentides.dao.BaseEntityDao;
import org.opentides.service.impl.BaseCrudServiceImpl;
import org.springframework.stereotype.Service;

import com.ideyatech.allowance.dao.ExpenseDao;
import com.ideyatech.allowance.entity.Expense;
import com.ideyatech.allowance.service.ExpenseService;

@Service("expenseService")
public class ExpenseServiceImpl extends BaseCrudServiceImpl<Expense> implements ExpenseService {

	@Override
	public void generateExpenseReport(OutputStream out, Date monthYear) {
		List<Expense> expenses = getDao().findByMonthAndYear(monthYear);
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Sheet 1");
		Row headerRow = sheet.createRow(0);
		writeHeader(headerRow);
		if(expenses != null && !expenses.isEmpty()) {
			CellStyle cellStyle = workbook.createCellStyle();
			CreationHelper createHelper = workbook.getCreationHelper();
			cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("d-MMM-yy"));
			int count = 1;
			for(Expense expense : expenses) {
				Row row = sheet.createRow(count++);
				//Date of purchase...
				Cell cell1 = row.createCell(0);
				cell1.setCellStyle(cellStyle);
				
				cell1.setCellValue(expense.getDate());
				//Tin
				Cell cell2 = row.createCell(1);
				cell2.setCellValue(expense.getReceipt().getTin());
				//Name
				Cell cell3 = row.createCell(2);
				cell3.setCellValue(expense.getReceipt().getName());
				//Address 1
				Cell cell4 = row.createCell(3);
				cell4.setCellValue(expense.getReceipt().getAddress1());
				//Name
				Cell cell5 = row.createCell(4);
				cell5.setCellValue(expense.getReceipt().getAddress2());
				//Total
				Cell cell6 = row.createCell(5);
				cell6.setCellValue(expense.getAmount());
				//Less Amount...
				Cell cell7 = row.createCell(6);
				if(expense.getLessAmount() != null) {
					cell7.setCellValue(expense.getLessAmount());
				}
				Cell cell8 = row.createCell(7); //Meals
				Cell cell9 = row.createCell(8); //Representation
				Cell cell10 = row.createCell(9); //Gas & Oil
				Cell cell11 = row.createCell(10); //Transpo
				if(Expense.Type.MEALS.equals(expense.getType())) {
					cell8.setCellValue(expense.getAmount());
				} else if(Expense.Type.REPRESENTATION.equals(expense.getType())) {
					cell9.setCellValue(expense.getAmount());
				} else if(Expense.Type.GAS_OIL.equals(expense.getType())) {
					cell10.setCellValue(expense.getAmount());
				} else if(Expense.Type.TRANSPO.equals(expense.getType())) {
					cell11.setCellValue(expense.getAmount());
				}
						
			}
		}
		try {
			workbook.write(out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void writeHeader(Row row) {
		String [] header = {"Date of Purchase", "TIN", "Name", "Address 1", "Address 2", "Total", 
				"Less Amount", "Meals", "Representation", "Gas & Oil", "Transpo"};
		int count = 0;
		for(String s : header) {
			Cell cell = row.createCell(count++);
			cell.setCellValue(s);
		}
	}
	
	@Override
	public ExpenseDao getDao() {
		return (ExpenseDao)super.getDao();
	}

}
