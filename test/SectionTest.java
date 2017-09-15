import models.Section;
import org.junit.Before;
import org.junit.Test;
import play.test.Fixtures;
import play.test.UnitTest;

/**
 * Created by aazzou on 10/12/14.
 */
public class SectionTest extends UnitTest {
    @Before
    public void init(){
        Section.deleteAll();
    }

    @Test
    public void createAndRetrieveMemberCategory(){
        Fixtures.loadModels("data/section.yml");
        assertEquals(3, Section.findAll().size());


        // Retrieve and Test
        Section sect1 = Section.find("byName","Section 1").first();
        assertNotNull(sect1);
        assertEquals(sect1.description, "Description of the category 1");



    }
}
