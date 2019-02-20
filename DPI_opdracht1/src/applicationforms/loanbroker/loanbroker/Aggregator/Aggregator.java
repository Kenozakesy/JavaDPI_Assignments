package applicationforms.loanbroker.loanbroker.Aggregator;

import mix.model.bank.BankInterestReply;
import mix.model.bank.BankInterestRequest;

import java.util.*;

/**
 * Created by Gebruiker on 20-2-2019.
 */
public class Aggregator {

    private AggregationID aggregationID;
    private List<BankInterestReply> replyList;
    private BankInterestRequest request;
    private int messagesSend;

    public Aggregator(BankInterestRequest request, int messagesSend)
    {
        aggregationID = new AggregationID();
        replyList = new ArrayList<>();
        this.request = request;
        this.messagesSend = messagesSend;
    }

    public AggregationID getAggregationID() {
        return aggregationID;
    }
    public BankInterestRequest getRequest() {
        return request;
    }
    public void setRequest(BankInterestRequest request) {
        this.request = request;
    }
    public int getMessagesSend() {
        return messagesSend;
    }
    public List<BankInterestReply> getReplyList() {
        return replyList;
    }

    public BankInterestReply addReply(BankInterestReply reply)
    {
        replyList.add(reply);
        replyList.sort(new Comparator<BankInterestReply>() {
            @Override
            public int compare(BankInterestReply o1, BankInterestReply o2) {
                return Double.compare(o1.getInterest(), o2.getInterest());
            }
        });
        if(replyList.size() == messagesSend)
        {
            return replyList.get(0);
        }
        return null;
    }
}
