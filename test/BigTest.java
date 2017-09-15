import org.junit.Test;
import play.test.UnitTest;

/**
 * Created by aazzou on 10/17/14.
 */
public class BigTest extends UnitTest {
    @Test
    public void process(){
        (new MemberCategoryTest()).createAndRetrieveMemberCategory();
        (new MemberTest()).createAndRetrieveMember();
        (new ResearchAreaTest()).createAndRetrieveResearchArea();
        (new ProjectTest()).createAndRetrieveProject();
        (new PublicationTest()).createAndRetrievePublication();
        (new SectionTest()).createAndRetrieveMemberCategory();
        (new NewTest()).createAndRetrieveNew();
        (new EventTest()).createAndRetrieveEvent();
        (new CarouselImgTest()).createAndRetrieveImg();
        (new AdministratorTest()).createAndRetrieveAdministrator();
     }
}
