import models.Member;
import models.MemberCategory;
import org.junit.Before;
import org.junit.Test;
import play.test.Fixtures;
import play.test.UnitTest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aazzou on 10/12/14.
 */
public class MemberTest  extends UnitTest {
    @Before
    public void init(){
        Member.deleteAll();
    }

    @Test
    public void createAndRetrieveMember(){
        Fixtures.loadModels("data/member.yml");
        // Retrieve and Test Member
        retrieveSaid();

        retrieveBehja();

        retrieveZemmouri();


        // Retrieve Student members
        MemberCategory sCat = MemberCategory.find("byName","Ph.D Students").first();
        List<Member> students = Member.find("byCategory",sCat).fetch();

        assertNotNull(students);
        assertEquals(1,students.size());
        Member aazzou = students.get(0);
        assertNotNull(aazzou);
        assertEquals(aazzou.fullName,"Said AAZZOU");

        // Retrieve Doctor members
        MemberCategory dCat = MemberCategory.find("byName","Ph. Doctors").first();
        List<Member> doctors = Member.find("byCategory",dCat).fetch();

        assertNotNull(doctors);
        assertEquals(2,doctors.size());
        Member doctor = doctors.get(0);
        assertNotNull(doctor);
        assertEquals(doctor.fullName,"Hicham BEHJA");
    }

    private void retrieveZemmouri() {
        Member zemmouri = Member.find("byFullName","El Moukhtar ZEMMOURI").first();
        assertNotNull(zemmouri);
        assertEquals(zemmouri.institution,"ENSAM - Meknes");
        assertEquals(zemmouri.email,"zemmouri@gmail.com");
    }

    private void retrieveBehja() {
        Member behja = Member.find("byFullName","Hicham BEHJA").first();
        assertNotNull(behja);
        assertEquals(behja.institution,"ENSEM - Casablanca");
        assertEquals(behja.email,"behja@gmail.com");
    }

    private void retrieveSaid() {
        Member said = Member.find("byFullName","Said AAZZOU").first();
        assertNotNull(said);
        assertEquals(said.institution,"ENSEM - Casablanca");
        assertEquals(said.email,"said.aazzou@gmail.com");
    }
}
