import models.CarouselImg;
import models.Event;
import org.junit.Before;
import org.junit.Test;
import play.test.Fixtures;
import play.test.UnitTest;

/**
 * Created by aazzou on 10/12/14.
 */

public class CarouselImgTest extends UnitTest {
    @Before
    public void init(){
        CarouselImg.deleteAll();
    }

    @Test
    public void createAndRetrieveImg(){
        Fixtures.loadModels("data/carousel_img.yml");


        // do your tests
    }
}
