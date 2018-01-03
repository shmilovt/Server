package il.ac.bgu.finalproject.server.Domain.NLPHandlers;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentDetails.ApartmentDetails;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentDetails.Contact;
import java.io.*;
import java.util.*;

public class NLPImp implements NLPInterface {

    @Override
    public ApartmentDetails extractApartment(String str) {

        EnvList l = new EnvList(str);
        AnalyzedDS ads= new AnalyzedDS(l);
        ApartmentDetails ap = new ApartmentDetails();
        Set<Contact> c = new HashSet<>();
        Set<String> ss = ads.GetResultsByClassify(Classify.NUMERIC);
        for(String stri :ss)
            c.add(new Contact("aa", stri));
        ap.setContacts(c);
        return ap;
    }


    public static void main(String[] args) throws IOException {
        String s = "***הרחוב השקט ביותר בב\"ש***\nשרעבי 21, שכונה ג',קרוב לרכבת צפון, פארק ההייטק, 12 דקות לאוניברסיטה דירת 4 חדרים, 3 סוויטות מפוארות ומרוהטות לחלוטין, ממוזגת, 80 מ\"ר, קומה 3,\nשכ\"ד 1100 ש\"ח לשותף, 3300 ₪ לכל הדירה.\nלפרטים נוספים בפרטי או בוואטסאפ ל- 052-4848414\n";
        NLPImp n = new NLPImp();
        n.extractApartment(s);
    }
}
