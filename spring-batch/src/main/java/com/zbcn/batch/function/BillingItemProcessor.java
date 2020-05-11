package com.zbcn.batch.function;

import com.zbcn.batch.pojo.Bill;
import com.zbcn.batch.pojo.User;
import org.springframework.batch.item.ItemProcessor;

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
