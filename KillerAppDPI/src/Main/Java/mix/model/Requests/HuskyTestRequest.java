package mix.model.Requests;

import mix.model.Husky;

public class HuskyTestRequest {

    private Husky husky;
    private int aggregatorID;

    public HuskyTestRequest(Husky husky, int aggregatorID)
    {
        this.husky = husky;
        this.aggregatorID = aggregatorID;
    }

    public Husky getHusky() {
        return husky;
    }
    public void setHusky(Husky husky) {
        this.husky = husky;
    }
    public int getAggregatorID() {
        return aggregatorID;
    }
    public void setAggregatorID(int aggregatorID) {
        this.aggregatorID = aggregatorID;
    }
}
