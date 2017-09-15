import models.Member;
import models.MemberCategory;
import org.junit.Before;
import org.junit.Test;
import play.test.Fixtures;
import play.test.UnitTest;

/**
 * Created by aazzou on 10/12/14.
 */
public class MemberCategoryTest extends UnitTest {
    @Before
    public void init(){
        MemberCategory.deleteAll();
    }

    @Test
    public void createAndRetrieveMemberCategory(){
        Fixtures.loadModels("data/member_category.yml");
        assertEquals(2, MemberCategory.findAll().size());


        // Retrieve and Test
        MemberCategory doctorCat = MemberCategory.find("byName","Ph. Doctors").first();
        assertNotNull(doctorCat);
        assertEquals(doctorCat.description, "Category consisting of team members who are Ph. Doctors");

        MemberCategory studentCat = MemberCategory.find("byName","Ph.D Students").first();
        assertNotNull(studentCat);
        assertEquals(studentCat.description, "Category consisting of team members who are Ph.D Students");


    }
}
