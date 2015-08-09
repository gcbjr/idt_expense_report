package com.ideyatech.allowance.web.controller;

import java.util.List;

import org.opentides.web.controller.BaseCrudController;
import org.opentides.web.json.ResponseView;
import org.opentides.web.json.Views;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ideyatech.allowance.entity.Receipt;
import com.ideyatech.allowance.service.ReceiptService;

@Controller
@RequestMapping("/receipt")
public class ReceiptCrudController extends BaseCrudController<Receipt> {
	
	@RequestMapping("/byTin")
	@ResponseView(Views.SearchView.class)
	public @ResponseBody List<Receipt> findByTinStartsWith(@RequestParam("value") String tin) {
		return getThisService().findByTinStartsWith(tin);
	}
	
	public ReceiptService getThisService() {
		return (ReceiptService)getService();
	}

}
