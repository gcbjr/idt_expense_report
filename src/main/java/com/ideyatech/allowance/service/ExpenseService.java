package com.ideyatech.allowance.service;

import java.io.OutputStream;
import java.util.Date;

import org.opentides.service.BaseCrudService;

import com.ideyatech.allowance.entity.Expense;

public interface ExpenseService extends BaseCrudService<Expense> {
	
	void generateExpenseReport(OutputStream out, Date monthYear);
	
}
