package com.ideyatech.allowance.service;

import java.util.List;

import org.opentides.service.BaseCrudService;

import com.ideyatech.allowance.entity.Receipt;

public interface ReceiptService extends BaseCrudService<Receipt> {
	
	List<Receipt> findByTinStartsWith(String tin);

}
