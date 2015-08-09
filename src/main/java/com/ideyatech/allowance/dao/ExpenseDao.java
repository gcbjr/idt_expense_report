package com.ideyatech.allowance.dao;

import java.util.Date;
import java.util.List;

import org.opentides.dao.BaseEntityDao;

import com.ideyatech.allowance.entity.Expense;

public interface ExpenseDao extends BaseEntityDao<Expense, Long> {

	List<Expense> findByMonthAndYear(Date date);
	
}
