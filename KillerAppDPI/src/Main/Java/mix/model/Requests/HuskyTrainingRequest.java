package mix.model.Requests;

import mix.model.Husky;

public class HuskyTrainingRequest {

    private Husky husky;
    private String schoolAddress;

    public HuskyTrainingRequest(Husky husky, String schoolAddress)
    {
        this.husky = husky;
        this.schoolAddress = schoolAddress;
    }

    public Husky getHusky() {
        return husky;
    }
    public void setHusky(Husky husky) {
        this.husky = husky;
    }
    public String getSchoolAddress() {
        return schoolAddress;
    }
    public void setSchoolAddress(String schoolAddress) {
        this.schoolAddress = schoolAddress;
    }
}
