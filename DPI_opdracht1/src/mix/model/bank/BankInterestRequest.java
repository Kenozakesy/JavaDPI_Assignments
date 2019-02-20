package mix.model.bank;

import applicationforms.loanbroker.loanbroker.Aggregator.AggregationID;

/**
 *
 * This class stores all information about an request from a bank to offer
 * a loan to a specific client.
 */
public class BankInterestRequest {

    private int amount; // the requested loan amount
    private int time; // the requested loan period
    private AggregationID aggregationID;

    public BankInterestRequest() {
        super();
        this.amount = 0;
        this.time = 0;
    }

    public BankInterestRequest(int amount, int time) {
        super();
        this.amount = amount;
        this.time = time;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public AggregationID getAggregationID() {
        return aggregationID;
    }
    public void setAggregationID(AggregationID aggregationID) {
        this.aggregationID = aggregationID;
    }

    @Override
    public String toString() {
        return " amount=" + amount + " time=" + time;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BankInterestRequest)) return false;

        BankInterestRequest that = (BankInterestRequest) o;

        if (getAmount() != that.getAmount()) return false;
        return getTime() == that.getTime();
    }

    @Override
    public int hashCode() {
        int result = getAmount();
        result = 31 * result + getTime();
        return result;
    }


}
