package mix.model;

import mix.model.Enums.TrainingStatus;
import mix.model.Replies.HuskyTestReply;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gebruiker on 12-3-2019.
 */
public class Kennel {

    private String name;
    private List<Husky> huskyList;

    public Kennel()
    {
        name = "Hubsky";
        huskyList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }
    public List<Husky> getHuskyList() {
        return huskyList;
    }
    public void addHusky(Husky husky)
    {
        huskyList.add(husky);
    }

    public void removeHusky(Husky husky)
    {
        huskyList.remove(husky);
    }

    public void updateHuskyStatus(HuskyTestReply reply)
    {
        for (Husky h: huskyList)
        {
            if(reply.getHusky().equals(h))
            {
                h.setStatus(TrainingStatus.UpForTraining);
                h.setSchoolAddress(reply.getPrivateTrainAddress());
                reply.setHusky(h);
                break;
            }
        }

    }

    public Husky updateHuskyToTrained(Husky husky)
    {
        for (Husky h: huskyList)
        {
            if(husky.equals(h))
            {
                h = husky;
                return h;
            }
        }
        return null;
    }

}
