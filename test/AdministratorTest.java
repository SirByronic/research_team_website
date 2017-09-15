import models.Administrator;
import models.Event;
import org.junit.Before;
import org.junit.Test;
import play.test.Fixtures;
import play.test.UnitTest;

/**
 * Created by aazzou on 10/12/14.
 */

public class AdministratorTest extends UnitTest {
    @Before
    public void init(){
        Administrator.deleteAll();
    }

    @Test
    public void createAndRetrieveAdministrator(){
        Fixtures.loadModels("data/administrator.yml");


        // do your tests
    }
}
