package mix.model.Replies;

import mix.model.Husky;
import mix.model.HuskyScore;

/**
 * Created by Gebruiker on 20-3-2019.
 */
public class HuskyTrainReply {

    private Husky husky;

    public HuskyTrainReply(Husky husky)
    {
        this.husky = husky;
    }

    public Husky getHusky() {
        return husky;
    }
    public void setHusky(Husky husky) {
        this.husky = husky;
    }

}
