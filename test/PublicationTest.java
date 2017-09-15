import models.Publication;
import org.junit.Before;
import org.junit.Test;
import play.test.Fixtures;
import play.test.UnitTest;

/**
 * Created by aazzou on 10/12/14.
 */
public class PublicationTest extends UnitTest {

    @Before
    public void init(){
        Publication.deleteAll();
    }

    @Test
    public void createAndRetrievePublication(){
        Fixtures.loadModels("data/publication.yml");

        // do your tests
    }
}
