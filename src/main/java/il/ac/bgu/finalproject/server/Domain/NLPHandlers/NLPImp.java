package il.ac.bgu.finalproject.server.Domain.NLPHandlers;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentDetails.ApartmentDetails;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentDetails.Contact;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentDetails.Locations.ApartmentLocation;

import java.io.*;
import java.util.*;

public class NLPImp implements NLPInterface {

    @Override
    public ApartmentDetails extractApartment(String str) {

        EnvList l = new EnvList(str);
        AnalyzedDS ads= new AnalyzedDS(l);

        ApartmentDetails ap = new ApartmentDetails();
        ApartmentLocation a = new ApartmentLocation();
        Set<Contact> c = new HashSet<>();
        Set<String> ss = ads.GetResultsByClassify(Classify.PHONE);
        for(String stri :ss)
            c.add(new Contact("aa", stri));
        ap.setContacts(c);
        return ap;
    }


    public static void main(String[] args) throws IOException {
        String s = "מחפשים שותף/ שותפה !\n\nרגר 139, קומה 3, 84 מ\"ר, מתפנה חדר ענקי !!!!\nבדירה משופצת , דקה הליכה איטית לשער רגר!\nבדירה שירותים ומקלחת + שירותים נפרדים, דוד שמש + חשמל, מטבח מאובזר ומכונת כביסה. בכל חדר מזגן נפרד.  בחדר שמתפנה נשאר ארון ובסיס למיטה. אופציה לשולחן !\nללא עישון וללא בע\"ח\n\nכניסה מיידית, חוזה עד 31.8, 1050 ש\"ח !!!\nלפרטים ותיאומים מוזמנים לפנות אלי בהודעות !\n";
        NLPImp n = new NLPImp();
        n.extractApartment(s);
    }
}
