package com.ideyatech.allowance.dao;

import java.util.List;

import org.opentides.dao.BaseEntityDao;

import com.ideyatech.allowance.entity.Receipt;

public interface ReceiptDao extends BaseEntityDao<Receipt, Long> {
	
	List<Receipt> findByTinStartsWith(String tin);

}
