import models.Project;
import org.junit.Before;
import org.junit.Test;
import play.test.Fixtures;
import play.test.UnitTest;

/**
 * Created by aazzou on 10/12/14.
 */
public class ProjectTest extends UnitTest {

    @Before
    public void init(){
        Project.deleteAll();
    }

    @Test
    public void createAndRetrieveProject(){
        Fixtures.loadModels("data/project.yml");

        // do your tests
    }
}
