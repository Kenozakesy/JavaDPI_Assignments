package mix.Aggregator;

import mix.model.Husky;
import mix.model.HuskyTestReply;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Gebruiker on 20-2-2019.
 */
public class Aggregator {

    private AggregationID aggregationID;
    private List<HuskyTestReply> replyList;
    private Husky husky;
    private int messagesSend;

    public Aggregator(Husky husky, int messagesSend)
    {
        aggregationID = new AggregationID();
        replyList = new ArrayList<>();
        this.husky = husky;
        this.messagesSend = messagesSend;
    }

    public AggregationID getAggregationID() {
        return aggregationID;
    }

    public HuskyTestReply addReply(HuskyTestReply reply)
    {
        replyList.add(reply);
        replyList.sort(new Comparator<HuskyTestReply>() {
            @Override
            public int compare(HuskyTestReply o1, HuskyTestReply o2) {
                return Double.compare(o1.getScore().getScore(), o2.getScore().getScore());
            }
        });

        if(replyList.size() == messagesSend)
        {
            return replyList.get(0);
        }
        return null;
    }
}
