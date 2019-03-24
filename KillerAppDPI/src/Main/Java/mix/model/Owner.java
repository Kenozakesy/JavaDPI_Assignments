package mix.model;

import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import mix.model.Enums.TrainingStatus;


import javax.security.auth.callback.Callback;
import java.util.*;

import static java.lang.StrictMath.E;

/**
 * Created by Gebruiker on 11-3-2019.
 */
public class Owner {

    private String firstName;
    private String lastName;
    private Date birthdate;

    private List<Husky> huskyList;

    public Owner(String firstname, String lastName, Date birthDate)
    {
        this.firstName = firstname;
        this.lastName = lastName;
        this.birthdate = birthDate;

        //this normally comes from a database


        huskyList = new ArrayList<>();
        huskyList.add(new Husky(55,20, 120, "Husko", new Date(2017,8,23)));
        huskyList.add(new Husky(51,18, 113, "Husshi", new Date(2018,2,24)));
        huskyList.add(new Husky(58,21, 125, "Sushi", new Date(2017,6,20)));
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public Date getBirthdate() {
        return birthdate;
    }
    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public List<Husky> getHuskyList() {
        return huskyList;
    }

    public void updateHuskyStatus(Husky husky)
    {
        for (Husky h: huskyList)
        {
            if(husky.equals(h))
            {
                h.setStatus(TrainingStatus.Trained);
                break;
            }
        }
    }
}
