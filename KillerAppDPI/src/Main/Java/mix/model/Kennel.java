package mix.model;

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



}
