package mix.model.bank;

import java.util.Comparator;

/**
 * This class stores information about the bank reply
 *  to a loan request of the specific client
 * 
 */
public class BankInterestReply implements Comparable {

    private double interest; // the loan interest
    private String bankId; // the nunique quote Id
    
    public BankInterestReply() {
        this.interest = 0;
        this.bankId = "";
    }
    
    public BankInterestReply(double interest, String quoteId) {
        this.interest = interest;
        this.bankId = quoteId;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public String getQuoteId() {
        return bankId;
    }

    public void setQuoteId(String quoteId) {
        this.bankId = quoteId;
    }

    public String toString() {
        return "quote=" + this.bankId + " interest=" + this.interest;
    }

    @Override
    public int compareTo(Object o) {
        BankInterestReply b = (BankInterestReply)o;
        if(b.interest < interest)
        {
            return 1;
        }
        else if(b.interest > interest)
        {
            return -1;
        }
        return 0;
    }
}
