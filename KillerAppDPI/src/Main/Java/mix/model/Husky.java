package mix.model;

import com.sun.glass.ui.Size;
import mix.model.Enums.TrainingStatus;

import java.util.Date;

/**
 * Created by Gebruiker on 11-3-2019.
 */
public class Husky {

    private String name;
    private Date birthDate;
    private TrainingStatus status;

    private double height;
    private double width;
    private double length;

    public Husky(double height, double width, double length, String name, Date birthDate)
    {
        this.height = height;
        this.width = width;
        this.length = length;
        this.name = name;
        this.birthDate = birthDate;
        this.status = TrainingStatus.NotTrained;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Date getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
    public double getHeight() {
        return height;
    }
    public void setHeight(double height) {
        this.height = height;
    }
    public double getWidth() {
        return width;
    }
    public void setWidth(double width) {
        this.width = width;
    }
    public double getLength() {
        return length;
    }
    public void setLength(double length) {
        this.length = length;
    }
    public TrainingStatus getStatus() {
        return status;
    }
    public void setStatus(TrainingStatus status) {
        status = status;
    }

    private String Bark()
    {
        return "Woof";
    }

    @Override
    public String toString() {
        return "Husky{" +
                "name='" + name + '\'' +
                ", birthDate=" + birthDate +
                ", height=" + height +
                ", width=" + width +
                ", length=" + length +
                ", Status=" + status +
                '}';
    }

}
