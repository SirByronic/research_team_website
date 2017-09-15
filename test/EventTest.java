import models.Event;
import org.junit.Before;
import org.junit.Test;
import play.test.Fixtures;
import play.test.UnitTest;

/**
 * Created by aazzou on 10/12/14.
 */

public class EventTest extends UnitTest {
    @Before
    public void init(){
        Event.deleteAll();
    }

    @Test
    public void createAndRetrieveEvent(){
        Fixtures.loadModels("data/event.yml");


        // do your tests
    }
}
