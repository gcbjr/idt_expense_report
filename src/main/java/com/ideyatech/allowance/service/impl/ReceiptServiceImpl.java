package com.ideyatech.allowance.service.impl;

import java.util.List;

import org.opentides.service.impl.BaseCrudServiceImpl;
import org.springframework.stereotype.Service;

import com.ideyatech.allowance.dao.ReceiptDao;
import com.ideyatech.allowance.entity.Receipt;
import com.ideyatech.allowance.service.ReceiptService;

@Service("receiptService")
public class ReceiptServiceImpl extends BaseCrudServiceImpl<Receipt> implements ReceiptService{

	@Override
	public List<Receipt> findByTinStartsWith(String tin) {
		return getDao().findByTinStartsWith(tin);
	}
	
	@Override
	public ReceiptDao getDao() {
		return (ReceiptDao)super.getDao();
	}

}
