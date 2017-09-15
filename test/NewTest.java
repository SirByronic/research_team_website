import models.New;
import org.junit.Before;
import org.junit.Test;
import play.test.Fixtures;
import play.test.UnitTest;

/**
 * Created by aazzou on 10/12/14.
 */
public class NewTest extends UnitTest {
    @Before
    public void init(){
        New.deleteAll();
    }

    @Test
    public void createAndRetrieveNew(){
        Fixtures.loadModels("data/new.yml");

        // do your tests
    }
}
