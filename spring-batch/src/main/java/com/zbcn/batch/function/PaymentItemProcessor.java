/**
 * 
 */
package com.zbcn.batch.function;

import com.zbcn.batch.exception.MoneyNotEnoughException;
import com.zbcn.batch.pojo.Bill;
import com.zbcn.batch.pojo.PayRecord;
import org.springframework.batch.item.ItemProcessor;

/**
 * @author kunrey
 * 
 */
public class PaymentItemProcessor implements ItemProcessor<Bill, PayRecord> {

	@Override
	public PayRecord process(Bill item) throws Exception {
		if (item.getUser().getBalance() <= 0) {
			return null;
		}
		if (item.getUser().getBalance() >= item.getUnpaidFees()) {
			// create payrecord
			PayRecord pr = new PayRecord();
			pr.setBill(item);
			pr.setPaidFees(item.getUnpaidFees());
			// update balance
			item.getUser().setBalance(item.getUser().getBalance() - item.getUnpaidFees());
			// update bill
			item.setPaidFees(item.getUnpaidFees());
			item.setUnpaidFees(0.0);
			item.setPayStatus(1);/* paid */
			return pr;
		} else {
			throw new MoneyNotEnoughException();
		}
	}
}
