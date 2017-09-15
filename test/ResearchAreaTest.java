import models.ResearchArea;
import org.junit.Before;
import org.junit.Test;
import play.test.Fixtures;
import play.test.UnitTest;

/**
 * Created by aazzou on 10/12/14.
 */
public class ResearchAreaTest extends UnitTest {
    @Before
    public void init(){
        ResearchArea.deleteAll();
    }

    @Test
    public void createAndRetrieveResearchArea(){
        Fixtures.loadModels("data/research_area.yml");

        // check the number of research area in the yml file
        assertEquals(3,ResearchArea.findAll().size());
        // puts whatever you wanna test about your research area model
    }
}
