package mix.model.Replies;

import mix.model.Husky;
import mix.model.HuskyScore;

import java.util.Comparator;

/**
 * Created by Gebruiker on 20-3-2019.
 */
public class HuskyTestReply{

    private Husky husky;
    private HuskyScore score;
    private String privateTrainAddress;
    private int aggregatorID;

    public HuskyTestReply(Husky husky, HuskyScore score, String privateTrainAddress, int aggregatorID)
    {
        this.husky = husky;
        this.score = score;
        this.aggregatorID = aggregatorID;
        this.privateTrainAddress = privateTrainAddress;
    }

    public Husky getHusky() {
        return husky;
    }
    public void setHusky(Husky husky) {
        this.husky = husky;
    }
    public HuskyScore getScore() {
        return score;
    }
    public void setScore(HuskyScore score) {
        this.score = score;
    }
    public String getPrivateTrainAddress() {
        return privateTrainAddress;
    }
    public void setPrivateTrainAddress(String privateTrainAddress) {
        this.privateTrainAddress = privateTrainAddress;
    }
    public int getAggregatorID() {
        return aggregatorID;
    }
}
