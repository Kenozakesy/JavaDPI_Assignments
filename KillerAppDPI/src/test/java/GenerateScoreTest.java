import mix.model.Husky;
import mix.model.HuskySchool;
import mix.model.HuskyScore;
import org.junit.jupiter.api.Test;

import java.util.Date;

/**
 * Created by Gebruiker on 20-3-2019.
 */
public class GenerateScoreTest {

    @Test
    public void scoreTest()
    {
        HuskySchool school = new HuskySchool();
        HuskyScore score = school.calculatePotential();
        //new Husky(56,66,78, "husk", new Date(4,4,4))
        System.out.println(score.getScore());
    }
}
