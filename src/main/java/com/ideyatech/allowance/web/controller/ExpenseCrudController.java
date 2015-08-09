package com.ideyatech.allowance.web.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.opentides.web.controller.BaseCrudController;
import org.opentides.web.editor.BaseEntityEditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ideyatech.allowance.entity.Expense;
import com.ideyatech.allowance.entity.Receipt;
import com.ideyatech.allowance.service.ExpenseService;
import com.ideyatech.allowance.service.ReceiptService;

@Controller
@RequestMapping("/expense")
public class ExpenseCrudController extends BaseCrudController<Expense> {
	
	@Autowired
	private ReceiptService receiptService;
	
	@ModelAttribute("expenseTypes")
	public List<Expense.Type> getExpenseTypes() {
		return Arrays.asList(Expense.Type.values());
	}
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) throws Exception {
		binder.registerCustomEditor(Receipt.class, new BaseEntityEditor(receiptService));
	}
	
	@Override
	protected void preCreate(Expense command, BindingResult bindingResult,
			Model uiModel, HttpServletRequest request,
			HttpServletResponse response) {
		if(command.getReceipt() == null || command.getReceipt().isNew()) {
			Receipt temp = command.getTempReceipt();
			Receipt receipt = new Receipt();
			receipt.setTin(temp.getTin());
			receipt.setName(temp.getName());
			receipt.setAddress1(temp.getAddress1());
			receipt.setAddress2(temp.getAddress2());
			receiptService.save(receipt);
			command.setReceipt(receipt);
		}
	}
	
	@RequestMapping(value = "/generate")
	public void generateExpenseReport(@RequestParam Date monthYear,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", 
				"attachment; filename=ExpenseReport" + new Date() + ".xls");
		ServletOutputStream out = null;
		try {
			out = response.getOutputStream();
			getThisService().generateExpenseReport(out, monthYear);
			out.flush();
		} finally {
			if(out != null)
				out.close();
		}
	}
	
	public ExpenseService getThisService() {
		return (ExpenseService)getService();
	}

}
