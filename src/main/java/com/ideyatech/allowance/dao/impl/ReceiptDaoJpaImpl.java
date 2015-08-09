package com.ideyatech.allowance.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.opentides.dao.impl.BaseEntityDaoJpaImpl;
import org.springframework.stereotype.Repository;

import com.ideyatech.allowance.dao.ReceiptDao;
import com.ideyatech.allowance.entity.Receipt;

@Repository("receiptDao")
public class ReceiptDaoJpaImpl extends BaseEntityDaoJpaImpl<Receipt, Long> implements ReceiptDao {
	
	@Override
	public List<Receipt> findByTinStartsWith(String tin) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("tin", tin + "%");
		return findByNamedQuery("jpql.receipt.findByTinStarts", params);
	}

}
