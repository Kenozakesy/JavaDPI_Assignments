package mix.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Gebruiker on 20-3-2019.
 */
public class HuskySchool {

    private String name;
    private List<Husky> huskyList;

    public HuskySchool()
    {
        huskyList = new ArrayList<>();
        name = "Schuulsky";
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public List<Husky> getHuskyList() {
        return huskyList;
    }
    public void setHuskyList(List<Husky> huskyList) {
        this.huskyList = huskyList;
    }

    public HuskyScore calculatePotential()
    {
        Random ran = new Random();
        int random = 1 + ran.nextInt(100 - 1 + 1);
        HuskyScore score = new HuskyScore((int)random);
        return score;
    }

    public void addHusky(Husky husky)
    {
        huskyList.add(husky);
    }

    public void removeHusky(Husky husky)
    {
        huskyList.remove(husky);
    }
}
