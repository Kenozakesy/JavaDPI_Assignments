package mix.model.loan;

/**
 *
 * This class stores all information about a
 * request that a client submits to get a loan.
 *
 */
public class LoanRequest {

    private static int staticID = 0;
    private int id;
    private int ssn; // unique client number.
    private int amount; // the ammount to borrow
    private int time; // the time-span of the loan


    public LoanRequest(int ssn, int amount, int time) {
        super();
        if(id == 0)
        {
            id = staticID + 1;
        }
        this.ssn = ssn;
        this.amount = amount;
        this.time = time;
    }

    public int getSsn() {
        return ssn;
    }

    public void setSsn(int ssn) {
        this.ssn = ssn;
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

    @Override
    public String toString() {
        return "ssn=" + String.valueOf(ssn) + " amount=" + String.valueOf(amount) + " time=" + String.valueOf(time);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LoanRequest)) return false;

        LoanRequest that = (LoanRequest) o;

        if (id != that.id) return false;
        if (getSsn() != that.getSsn()) return false;
        if (getAmount() != that.getAmount()) return false;
        return getTime() == that.getTime();
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + getSsn();
        result = 31 * result + getAmount();
        result = 31 * result + getTime();
        return result;
    }
}
