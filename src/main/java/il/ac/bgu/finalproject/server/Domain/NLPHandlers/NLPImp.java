package il.ac.bgu.finalproject.server.Domain.NLPHandlers;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentDetails.ApartmentDetails;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentDetails.Contact;
import java.io.*;
import java.util.*;

public class NLPImp implements NLPInterface {


    private static int distance(String str, String a, String b) {
        int aIndex = -1;
        int bIndex = -1;
        int minDistance = Integer.MAX_VALUE;
        String[] aS = str.toLowerCase().split("[ \t]+");
        int i = 0;
        for (String t : aS) {
            if (t.equals(a)) {
                aIndex = i;
            }
            if (t.equals(b)) {
                bIndex = i;
            }
            if (aIndex != -1 && bIndex != -1) {
                minDistance = minDistance > Math.abs(bIndex - aIndex) ? bIndex - aIndex : minDistance;
            }
            i++;
        }
        if (aIndex == -1 || bIndex == -1)
            return -1;
        else
            return minDistance;
    }

    private List<Integer> minusList(List<Integer> a,List<Integer> b)
    {
        List<Integer> tmp = new LinkedList<Integer>();
        for(int index: a)
            tmp.add(index);
        tmp.removeAll(b);
        return tmp;
    }


    private List<Integer> intersectList(List<Integer> a,List<Integer> b)
    {
        List<Integer> tmp = new LinkedList<Integer>();
        for(int index: a)
            tmp.add(index);
        tmp.retainAll(b);
        return tmp;
    }


    private Set<Contact> phoneDecision(AnalyzedDS ads)
    {
        Set<Contact> contactList = new HashSet<Contact>();
        List<Integer> phoneList = ads.GetEnvsIndex(Classify.PHONE);
        List<Integer> nameList = ads.GetEnvsIndex(Classify.NAME);
        List<Integer> phoneWithoutName = minusList(ads.GetEnvsIndex(Classify.PHONE),nameList);
        List<Integer> nameAndPhone = intersectList(nameList,phoneList);

        if (!nameAndPhone.isEmpty()) // Envs with names and telephones
        {
            for (int i : nameAndPhone)
            {
                List<String> phones = new ArrayList<String>(ads.GetResultsByClassifyAndIndex(Classify.PHONE, i));
                List<String> names = new ArrayList<String>(ads.GetResultsByClassifyAndIndex(Classify.NAME, i));
                if (names.size() <= phones.size())
                {
                    for (int j = 0; j < names.size(); j++)
                        contactList.add(new Contact(names.get(j), phones.get(j)));
                    for (int j = names.size(); j < phones.size(); j++)
                        contactList.add(new Contact(names.get(j), phones.get(j)));
                }
                else
                    for (int j = 0; j < phones.size(); j++)
                        contactList.add(new Contact(names.get(j), phones.get(j)));
            }
        }
        else { // TO DO FOR THE OTHER SIDE
            //List<Integer> namesIndex = ads.GetEnvsIndex(Classify.NAME);
            List<Integer> phoneRightToName = new LinkedList<Integer>();
            List<Integer> phoneLeftToName = new LinkedList<Integer>();

            for (int i : nameList) {
                if (phoneList.contains(i - 1))
                    phoneRightToName.add(i - 1);
                if(phoneList.contains(i + 1))
                    phoneLeftToName.add(i + 1);
            }
            //phoneList.retainAll(phoneRightToName);
            if (phoneRightToName.size() > 0) {
               // phoneWithoutName = minusList(phoneWithoutName,phoneRightToName);
                for (int i : phoneRightToName) {
                    List<String> prevNames = new ArrayList<String>(ads.GetResultsByClassifyAndIndex(Classify.NAME, i + 1));
                    List<String> phones = new ArrayList<String>(ads.GetResultsByClassifyAndIndex(Classify.PHONE, i));
                    String[] st = ads.getEnvLst().get(i + 1).getEnvString().split(" ");
                    if (prevNames.contains(st[st.length - 1])) {
                        contactList.add(new Contact(st[st.length - 1], phones.get(0)));
                        phoneWithoutName.remove(phoneWithoutName.indexOf(i));
                    }
                }
            }
            if (phoneLeftToName.size() > 0) {
               // phoneWithoutName = minusList(phoneWithoutName,phoneLeftToName);
                for (int i : phoneLeftToName) {
                    List<String> prevNames = new ArrayList<String>(ads.GetResultsByClassifyAndIndex(Classify.NAME, i - 1));
                    List<String> phones = new ArrayList<String>(ads.GetResultsByClassifyAndIndex(Classify.PHONE, i));
                    String[] st = ads.getEnvLst().get(i - 1).getEnvString().split(" ");
                    if (prevNames.contains(st[st.length - 1])) {
                        contactList.add(new Contact(st[st.length - 1], phones.get(0)));
                        phoneWithoutName.remove(phoneWithoutName.indexOf(i));;
                    }
                }
            }
        }
        //Envs with only phones
        for (int i : phoneWithoutName)
        {
            Set<String> phones = ads.GetResultsByClassifyAndIndex(Classify.PHONE, i);
            for (String p : phones)
                contactList.add(new Contact("", p));
        }
        return contactList;
    }

    // assume that price and wordPrice must exist. rommate help to devide if it exist in more than one env
    private int priceDecision(AnalyzedDS ads) {
        int price = -1;
        List<Integer> priceList = ads.GetEnvsIndex(Classify.PRICE);
        List<Integer> priceWordList = ads.GetEnvsIndex(Classify.WORD_PRICE);
        List<Integer> rommateList = ads.GetEnvsIndex(Classify.ROMMATE);
        List<Integer> suspicious = new LinkedList<Integer>();
        suspicious = intersectList(priceList,priceWordList);

        if (suspicious.size() == 0) // assume that price and wordPrice must exist.
            return price;
        else if (suspicious.size() == 1) {
            Set<String> res = ads.GetResultsByClassifyAndIndex(Classify.PRICE, suspicious.get(0));
            if (res.size() == 1)
                price = Integer.parseInt(res.iterator().next());
            else //exist 2 prices or more in the same env
            {
                Iterator<String> iterator = res.iterator();
                Set<String> rommateWords = ads.GetResultsByClassifyAndIndex(Classify.ROMMATE, suspicious.get(0));
                Set<String> priceWords = ads.GetResultsByClassifyAndIndex(Classify.WORD_PRICE, suspicious.get(0));
                rommateWords.retainAll(priceWords);

                if (rommateList.contains(suspicious.get(0)) && !rommateWords.isEmpty())//nearest price to rommate word
                {
                    String rommateWord = rommateWords.iterator().next();
                    String str1 = iterator.next();
                    price = Integer.parseInt(str1);
                    int gap = Math.abs(distance(ads.getEnvLst().get(suspicious.get(0)).getEnvString(), rommateWord, str1));
                    while (iterator.hasNext()) {
                        String str2 = iterator.next();
                        int tmp = Integer.parseInt(str2);
                        int distance = Math.abs(distance(ads.getEnvLst().get(suspicious.get(0)).getEnvString(), rommateWord, str2));
                        if (Math.abs(distance) < gap) {
                            gap = Math.abs(distance);
                            price = Integer.parseInt(str2);
                        }
                    }
                    return price;
                } else//first that end with zero
                {
                    int tmp;
                    while (iterator.hasNext()) {
                        tmp = Integer.parseInt(iterator.next());
                        if (tmp % 10 == 0)
                            return tmp;
                    }
                }
            }
        } else  // more than one env with prices
        {
            suspicious.retainAll(rommateList);
            if (suspicious.size() == 1)
                return Integer.parseInt(ads.GetResultsByClassifyAndIndex(Classify.PRICE, suspicious.get(0)).iterator().next());
            else // more than one env with rommanteWord priceWord and price
                System.out.println("Rare price decision path");
        }
        return price;
    }

    private Dictionary<Integer,Integer> gardenDecision(AnalyzedDS ads)
    {
        int exist=0;
        Dictionary<Integer,Integer> gardenRes = new Hashtable<Integer, Integer>();
        List<Integer> gardenList = ads.GetEnvsIndex(Classify.GARDEN);
        List<Integer> negativeList = ads.GetEnvsIndex(Classify.NEGATIVE);
        List<Integer> gardenNotNegativeList = minusList(gardenList,negativeList);
        if(gardenNotNegativeList.size()==0)
            return gardenRes;
        exist = 1;
        List<Integer> wordSizeList = ads.GetEnvsIndex(Classify.WORD_SIZE);
        List<Integer> sizeList = ads.GetEnvsIndex(Classify.SIZE_GARDEN);
        List<Integer> intersectSizeWordSizeList = intersectList(wordSizeList,sizeList);
        intersectSizeWordSizeList.retainAll(gardenNotNegativeList);
        if(intersectSizeWordSizeList.size()==0) {
            gardenRes.put(exist, -1);
            return gardenRes;
        }
        if(ads.getNumberOfGapAccourences(10,270)>1) {
            if (intersectSizeWordSizeList.size() == 1) {
                List<String> tmp = new ArrayList<String>(ads.GetResultsByClassifyAndIndex(Classify.SIZE_GARDEN, intersectSizeWordSizeList.get(0)));
                List<Integer> sizeLst = new LinkedList<Integer>();
                for (String s : tmp)
                    sizeLst.add(Integer.valueOf(s));
                int min = sizeLst.get(0);
                for (int i = 1; i < sizeLst.size(); i++)
                    if (min > sizeLst.get(i))
                        min = sizeLst.get(i);
                gardenRes.put(exist, min);
            } else {
                System.out.println("Very Rare Path Situation");
            }
        }
        if(gardenRes.isEmpty())
            gardenRes.put(1,-1);
        return gardenRes;
    }

    private int sizeDecision(AnalyzedDS ads) {
        int size = 0;
        List<Integer> sizeList = ads.GetEnvsIndex(Classify.SIZE_APARTMENT);
        List<Integer> sizeWordList = ads.GetEnvsIndex(Classify.WORD_SIZE);
        // List<Integer> gardenList = ads.GetEnvsIndex(Classify.GARDEN);
        // sizeList.retainAll(gardenList);
        List<Integer> sizeAndWordSize = intersectList(sizeList,sizeWordList);
        int occourence = ads.getNumberOfGapAccourences(40,270);
        if(sizeAndWordSize.size()==0)
            return -1;
        if(sizeAndWordSize.size()==1)
        {
            List<String> sizeL = new ArrayList<String>(ads.GetResultsByClassifyAndIndex(Classify.SIZE_APARTMENT, sizeAndWordSize.get(0)));
            if (occourence == 1)
                return Integer.parseInt(sizeL.get(0));
            else
            {
                int max=Integer.parseInt(sizeL.get(0));
                for(int i=1;i<sizeL.size();i++)
                {
                    int num = Integer.parseInt(sizeL.get(i));
                    if(num > max)
                        max = num;
                }
                return max;
            }
        }
        else {
            List<Integer> gardenList = ads.GetEnvsIndex(Classify.GARDEN);
            List<Integer> sizeNotGarden = minusList(sizeAndWordSize, gardenList);
            if (sizeNotGarden.size() == 0)
                return -1;
            if (sizeNotGarden.size() == 1) {
                List<String> sizeL = new ArrayList<String>(ads.GetResultsByClassifyAndIndex(Classify.SIZE_APARTMENT, sizeNotGarden.get(0)));
                if (occourence == 1)
                    return Integer.parseInt(sizeL.get(0));
                else {
                    int max = Integer.parseInt(sizeL.get(0));
                    for (int i = 1; i < sizeL.size(); i++) {
                        int num = Integer.parseInt(sizeL.get(i));
                        if (num > max)
                            max = num;
                    }
                    return max;
                }
            } else {
                System.out.println("Size Rare Path");
                return -1;
            }
        }
    }

        @Override
    public ApartmentDetails extractApartment(String str) {

        EnvList l = new EnvList(str);
        AnalyzedDS ads= new AnalyzedDS(l);

        ApartmentDetails ap = new ApartmentDetails();
        Dictionary<Integer,Integer> gardenDic = gardenDecision(ads);
        System.out.println("************************");
        if(gardenDic.isEmpty())
            System.out.println("GARDEN NOT EXIST");
        else
            System.out.println("SIZE  " + gardenDic.get(1));
            //int size = gardenDic.get(1);
        System.out.println("************************");

        ap.setSize(sizeDecision(ads));
        ap.setContacts(phoneDecision(ads));
        ap.setCost(priceDecision(ads));

        return ap;
    }


    public static void main(String[] args) throws IOException {
        //String s = "מחפשים שותף/ שותפה !\n\nרגר 139, קומה 3, 84 מ\"ר, מתפנה חדר ענקי !!!!\nבדירה משופצת , דקה הליכה איטית לשער רגר!\nבדירה שירותים ומקלחת + שירותים נפרדים, דוד שמש + חשמל, מטבח מאובזר ומכונת כביסה. בכל חדר מזגן נפרד.  בחדר שמתפנה נשאר ארון ובסיס למיטה. אופציה לשולחן !\nללא עישון וללא בע\"ח\n\nכניסה מיידית, חוזה עד 31.8, 1050 ש\"ח !!!\nלפרטים ותיאומים מוזמנים לפנות אלי בהודעות !\n";
        //String s = "***החדר הכי גדול ושווה מתפנה***\nמתפנה בעקבות הפסקת לימודים (לא שלי)\n350 מטר משער רגר. 6 דקות משער 90. רגר 130 כניסה ' דירה 5.\nדירה גדולה ומרווחת,צנרת חדשה, דוד חדש, מזגנים בכל החדרים כולל בסלון וטלוויזיה ענקית, מטבח גדול ומרווח, סלון גדול לארח חברים עם בריזה מטורפת, שירותים נפרדים ומקלחת נפרדת עם מרפסת סגורה ובה מכונת כביסה.\nבחדר נשאר מיטה, ארון, כיסא ושולחן רק להביא מזוודה (:\nוכל התענוג הזה רק ב1100 שקלים!! ללא שקרים רק בואו ותראו!!\nבדירה נשארים שני שותפים מקסימים.\nכניסה מיידית!! אבל גמישים.\nלפנות רק אם זה רלוונטי אליכם כדי שנחסוך זמן יקר גם לשותפים וגם לכם (:\nשאלות נוספות יכולים לפנות אליי בפרטי או בטלפון 0524744888 וכמובן לתאם ולבוא לראותת (:\n";


        String s = "להשכרה קוטג' 3 חדרים בשכונת נחל עשן\n\nממוזגת\nמשופצת\nכניסה מיידית\n65 מ\"ר\nגינה 230 מ\"ר\n\nריהוט: מקרר.\n\nמחיר: רק ב3,500.\n\nטלפון: 052-477-8940\n";
        NLPImp n = new NLPImp();
        n.extractApartment(s);
    }
}
