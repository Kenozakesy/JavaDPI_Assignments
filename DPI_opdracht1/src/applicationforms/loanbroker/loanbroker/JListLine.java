package applicationforms.loanbroker.loanbroker;

import mix.model.loan.*;
import mix.model.bank.*;

/**
 * This class represents one line in the JList in Loan Broker.
 * This class stores all objects that belong to one LoanRequest:
 *    - LoanRequest,
 *    - BankInterestRequest, and
 *    - BankInterestReply.
 *  Use objects of this class to add them to the JList.
 *    
 * @author 884294
 *
 */
 public class JListLine {
	
	private LoanRequest loanRequest;
	private BankInterestRequest bankRequest;
	private BankInterestReply bankReply;

//	public JListLine(LoanRequest loanRequest) {
//		this.setLoanRequest(loanRequest);
//	}

	public JListLine(LoanRequest loanRequest, BankInterestRequest bankInterestRequest) {
		this.setLoanRequest(loanRequest);
		this.setBankRequest(bankInterestRequest);
	}

	public LoanRequest getLoanRequest() {
		return loanRequest;
	}

	public void setLoanRequest(LoanRequest loanRequest) {
		this.loanRequest = loanRequest;
	}

	public BankInterestRequest getBankRequest() {
		return bankRequest;
	}

	public void setBankRequest(BankInterestRequest bankRequest) {
		this.bankRequest = bankRequest;
	}

	public BankInterestReply getBankReply() {
		return bankReply;
	}

	public void setBankReply(BankInterestReply bankReply) {
		this.bankReply = bankReply;
	}

	@Override
	public String toString() {
		return loanRequest.toString() + " || " + ((bankReply != null) ? bankReply.toString() : "waiting for reply...");
	}

}
