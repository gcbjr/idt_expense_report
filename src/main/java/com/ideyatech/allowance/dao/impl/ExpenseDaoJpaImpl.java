package com.ideyatech.allowance.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.opentides.dao.impl.BaseEntityDaoJpaImpl;
import org.springframework.stereotype.Repository;

import com.ideyatech.allowance.dao.ExpenseDao;
import com.ideyatech.allowance.entity.Expense;

@Repository("expenseDao")
public class ExpenseDaoJpaImpl extends BaseEntityDaoJpaImpl<Expense, Long> implements ExpenseDao {

	@Override
	public List<Expense> findByMonthAndYear(Date date) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("date", date);
		return findByNamedQuery("jpql.expense.findByMonthAndYear", params);
	}

}
