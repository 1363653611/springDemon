package com.zbcn.module.batch.function;

import org.springframework.batch.item.ItemProcessor;

import com.zbcn.module.batch.pojo.Bill;
import com.zbcn.module.batch.pojo.User;

public class BillingItemProcessor implements ItemProcessor<User, Bill> {

	@Override
	public Bill process(User item) throws Exception {
		Bill b = new Bill();
		b.setUser(item);
		b.setFees(70.00);
		b.setPaidFees(0.0);
		b.setUnpaidFees(70.00);
		b.setPayStatus(0);/*unpaid*/
		return b;
	}

}
