package il.ac.bgu.finalproject.server.Domain.NLPHandlers;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.*;
import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class NLPImp implements NLPInterface {

    Map<String, String> street_neighborhood_dic;

    private void load_street_neighborhood_dic()
    {
        String csvFile = "src\\main\\java\\il\\ac\\bgu\\finalproject\\server\\Domain\\NLPHandlers\\Dictionaries\\neigh_street.csv";
        String line = "";
        String cvsSplitBy = ",";
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            br.readLine();
            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] row = line.split(cvsSplitBy);
                street_neighborhood_dic.put(row[0],row[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public NLPImp(){
        street_neighborhood_dic = new HashMap<String, String>();
        load_street_neighborhood_dic();
    }

    private static boolean rootAndWord (String root, String word){
       // root=root.replaceAll("[*)(!@#$%^&_/-/+=~`]","");
        String reg="ו?(הו?|מו?|א|י|תו?|נ)?"+root.charAt(0);
        String temp=root;
        for (int i=1; i<root.length();i++){
            reg=reg+"[וי]?";
            reg=reg+root.charAt(i);
        }
        reg=reg+"(ה|ות?|ים?|ת[יםן]?|נ[וה])?";
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(word);
        String temp2;
        while(m.find()) {
            temp2=word.substring(m.start(),m.end());
            return true;
        }
        return false;
    }

    private static int distance(String str, String a, String b) {
        str = str.replaceAll("[*!,]"," ");
        int aIndex = -1;
        int bIndex = -1;
        int minDistance = Integer.MAX_VALUE;
        String[] aS = str.toLowerCase().split("[ \t]+");
        int i = 0;
        for (String t : aS) {
            if (t.equals(a) || (t.length()>1 && t.substring(1).equals(a))) {
                aIndex = i;
            }
            if (t.equals(b) || (t.length()>1 && t.substring(1).equals(b))) {
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

    private List<Integer> stringCollectionToIntegerCollection(List<String> toConvert)
    {
        return toConvert.stream()
                .map(s -> Integer.parseInt(s))
                .collect(Collectors.toList());
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
            List<String> phones = ads.GetResultsByClassifyAndIndex(Classify.PHONE, i);
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
        List<Integer> roomsList = ads.GetEnvsIndex(Classify.ROOM_DES);
        rommateList.addAll(roomsList);
        List<Integer> suspicious = new LinkedList<Integer>();
        suspicious = intersectList(priceList,priceWordList);

        if (suspicious.size() == 0) // assume that price and wordPrice must exist.
        {
            if(priceList.isEmpty())
                return -1;
            List<String> res = ads.GetResultsByClassifyAndIndex(Classify.PRICE, priceList.get(0));
            for(int i=1;i<priceList.size();i++)
                res.addAll(ads.GetResultsByClassifyAndIndex(Classify.PRICE, priceList.get(i)));
            return Collections.max(stringCollectionToIntegerCollection(res));

        }
        else if (suspicious.size() == 1) {
            List<String> res = ads.GetResultsByClassifyAndIndex(Classify.PRICE, suspicious.get(0));
            if (res.size() == 1)
                price = Integer.parseInt(res.iterator().next());
            else //exist 2 prices or more in the same env
            {
                Iterator<String> iterator = res.iterator();
                List<String> rommateWords = ads.GetResultsByClassifyAndIndex(Classify.ROMMATE, suspicious.get(0));
                List<String> priceWords = ads.GetResultsByClassifyAndIndex(Classify.WORD_PRICE, suspicious.get(0));
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
            else  if (suspicious.size() > 1)// more than one env with rommanteWord priceWord and price or sus is empty
            {
                List<String> l = new LinkedList<>();
                List<Integer> l1 = new LinkedList<>();

                for(int i=0;i<priceList.size();i++)
                    l.addAll(ads.GetResultsByClassifyAndIndex(Classify.PRICE, priceList.get(i)));
                int min = Integer.parseInt(l.get(0));
                for(String str:l)
                    if(min > Integer.parseInt(str))
                        min = Integer.parseInt(str);
                return min;
            }
            else
            {
                suspicious = intersectList(priceList,priceWordList);
                String toCheck;

                for(int i=0;i<suspicious.size();i++)
                {
                    //String toCheck;
                    toCheck = ads.getEnvLst().get(suspicious.get(i)).getEnvString();
                    String[] toCheckArray = toCheck.replaceAll("-","").split(" ");
                    for(int j=0;j<toCheckArray.length;j++)
                        if(rootAndWord("שכר",toCheckArray[j]) || rootAndWord("חדר",toCheckArray[j]) || rootAndWord("שתף",toCheckArray[j]))
                            return Integer.parseInt(ads.GetResultsByClassifyAndIndex(Classify.PRICE, suspicious.get(i)).get(0));
                }
                List<String> priceSus = ads.GetResultsByClassifyAndIndex(Classify.PRICE, suspicious.get(0));
                priceSus.addAll(ads.GetResultsByClassifyAndIndex(Classify.PRICE, suspicious.get(1)));
                price = Collections.min(stringCollectionToIntegerCollection(priceSus));
            }
        }
        return price;
    }

    private List<Integer> gardenDecision(AnalyzedDS ads)
    {
        int exist=-1;
        List<Integer> gardenRes = new LinkedList<Integer>();
        List<Integer> gardenList = ads.GetEnvsIndex(Classify.GARDEN);
        List<Integer> negativeList = ads.GetEnvsIndex(Classify.NEGATIVE);
        List<Integer> gardenNotNegativeList = minusList(gardenList,negativeList);
        if(gardenNotNegativeList.size()==0 && gardenList.size()==0)
        {
            gardenRes.add(-1);
            gardenRes.add(-1);
            return gardenRes;
        }
        else if(gardenNotNegativeList.size()==0 && gardenList.size()>0)
        {
             List<Integer> ans =  intersectList(gardenList,negativeList);
             for(int i=0;i<ans.size();i++) {
                 List<String> gardenWords = ads.GetResultsByClassifyAndIndex(Classify.GARDEN, ans.get(i));
                 List<String> negativeWords = ads.GetResultsByClassifyAndIndex(Classify.NEGATIVE, ans.get(i));
                 String str = ads.getEnvLst().get(ans.get(i)).getEnvString();
                 for(int j=0;j<gardenWords.size();j++) {
                     for(int k=0;k<negativeWords.size();k++)
                     if (distance(str, gardenWords.get(j), negativeWords.get(k)) < 2) {
                         gardenRes.add(-1);
                         gardenRes.add(-1);
                         return gardenRes;
                     }
                 }
             }
        }
        exist = 1;
        List<Integer> wordSizeList = ads.GetEnvsIndex(Classify.WORD_SIZE);
        List<Integer> sizeList = ads.GetEnvsIndex(Classify.SIZE_GARDEN);
        List<Integer> intersectSizeWordSizeList = intersectList(wordSizeList,sizeList);
        intersectSizeWordSizeList.retainAll(gardenNotNegativeList);
        if(intersectSizeWordSizeList.size()==0) {
            gardenRes.add(1);
            gardenRes.add(-1);
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
                gardenRes.add(1);
                gardenRes.add(min);
            } else {
                System.out.println("Very Rare Path Situation");
            }
        }
        if(gardenRes.isEmpty())
            gardenRes.add(1);
            gardenRes.add(-1);
        return gardenRes;
    }

    private int sizeDecision(AnalyzedDS ads) {
        int size = 0;
        List<Integer> sizeList = ads.GetEnvsIndex(Classify.SIZE_APARTMENT);
        List<Integer> sizeWordList = ads.GetEnvsIndex(Classify.WORD_SIZE);
        List<Integer> streetWordList = ads.GetEnvsIndex(Classify.STREET);
        List<Integer> toRemoveFromSizeLst = intersectList(streetWordList,sizeList);
        for(int i:toRemoveFromSizeLst)
        {
            String str = ads.getEnvLst().get(i).getEnvString();
            Pattern p = Pattern.compile("([3456789])(\\d+)(\\s)(\\S+)(\\s)(מ)");
            Pattern p2 = Pattern.compile("([12])(\\d)(\\d+)(\\s)(\\S+)(\\s)(מ)");
            Matcher m = p.matcher(str);
            Matcher m2 = p2.matcher(str);
            if (m.find() || m2.find()) {
                toRemoveFromSizeLst.remove(toRemoveFromSizeLst.indexOf(i));
                break;
            }
        }
        toRemoveFromSizeLst = minusList(intersectList(streetWordList,sizeList),toRemoveFromSizeLst);
        sizeList = minusList(sizeList, toRemoveFromSizeLst);
        List<Integer> sizeAndWordSize = intersectList(sizeList,sizeWordList);
        int occourence = ads.getNumberOfGapAccourences(30,270);
        if(sizeAndWordSize.size()==0)
            return -1;
        if(sizeAndWordSize.size()==1)
        {
            List<String> sizeL = new ArrayList<String>(ads.GetResultsByClassifyAndIndex(Classify.SIZE_APARTMENT, sizeAndWordSize.get(0)));
            if (occourence == 1)
            {
                List<String> gardenL = new ArrayList<String>(ads.GetResultsByClassifyAndIndex(Classify.GARDEN, sizeAndWordSize.get(0)));
                if (gardenL.isEmpty())
                    return Integer.parseInt(sizeL.get(0));
                int dis = distance(ads.getEnvLst().get(sizeAndWordSize.get(0)).getEnvString(), gardenL.get(0), sizeL.get(0));
                if (dis < -1)
                    return Integer.parseInt(sizeL.get(0));
                return -1;
            }

            else
            {
                if(occourence==2) {
                    List<String> wordSize=ads.GetResultsByClassifyAndIndex(Classify.WORD_SIZE,sizeAndWordSize.get(0));
                    if(sizeL.size()==2) {
                        int dis1 = Math.abs(distance(ads.getEnvLst().get(sizeAndWordSize.get(0)).getEnvString(), sizeL.get(0), wordSize.get(0)));
                        int dis2 = Math.abs(distance(ads.getEnvLst().get(sizeAndWordSize.get(0)).getEnvString(), sizeL.get(1), wordSize.get(0)));
                        if (dis1 < dis2)
                            return Integer.parseInt(sizeL.get(0));
                        else
                            return Integer.parseInt(sizeL.get(1));
                    }
                    return Integer.parseInt(sizeL.get(0));
                }
                else {
                    int max = Integer.parseInt(sizeL.get(0));
                    for (int i = 1; i < sizeL.size(); i++) {
                        int num = Integer.parseInt(sizeL.get(i));
                        if (num > max)
                            max = num;
                    }
                    return max;
                }
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

    private String neighborhoodDecision(AnalyzedDS ads)
    {
        List<Integer> neighborhoodList = ads.GetEnvsIndex(Classify.NEIGHBORHOOD);
        List<Integer> phoneList = ads.GetEnvsIndex(Classify.PHONE);

        neighborhoodList = minusList(neighborhoodList,phoneList);
        if(!neighborhoodList.isEmpty())
            return ads.GetResultsByClassifyAndIndex(Classify.NEIGHBORHOOD,neighborhoodList.get(0)).iterator().next();
        return "";
    }


    private String streetDecision(AnalyzedDS ads)
    {
        List<Integer> badList = ads.GetEnvsIndex(Classify.PHONE);
        badList.addAll(ads.GetEnvsIndex(Classify.LOCATION));
        List<Integer> blackList = ads.GetEnvsIndex(Classify.BLACKLIST);
        List<Integer> streetList = ads.GetEnvsIndex(Classify.STREET);
        List<Integer> streetWord = ads.GetEnvsIndex(Classify.WORD_STREET);
        List<Integer> streetAndStreetWord = intersectList(streetList,streetWord);
        List<Integer> apartmentNumber = ads.GetEnvsIndex(Classify.APARTMENT_NUMBER);
        List<Integer> streetAndApNumber = intersectList(apartmentNumber,streetList);
        streetAndStreetWord = minusList(minusList(streetAndStreetWord,badList),blackList);
        streetList = intersectList(streetList,streetWord);
        if(!streetAndApNumber.isEmpty())
            return ads.GetResultsByClassifyAndIndex(Classify.STREET,streetAndApNumber.get(0)).iterator().next();
        if(!streetAndStreetWord.isEmpty()) {
            List<Integer> locationWord = ads.GetEnvsIndex(Classify.WORD_LOCATION);
            List<Integer> locationWordAndStreet = intersectList(locationWord,streetAndStreetWord);
            if(!locationWordAndStreet.isEmpty()) {
                List<String> streets = ads.GetResultsByClassifyAndIndex(Classify.STREET, locationWordAndStreet.get(0));
                List<String> location = ads.GetResultsByClassifyAndIndex(Classify.WORD_LOCATION, locationWordAndStreet.get(0));
                if(streets.size()==1)
                    return location.get(0) + " " + streets.get(0);
                else if(streets.size() == 2)
                    return streets.get(0) + " " + location.get(0) + " " + streets.get(1);
                else
                    return streets.get(1) + " " + location.get(0) + " " + streets.get(2);
            }
            return ads.GetResultsByClassifyAndIndex(Classify.STREET, streetAndStreetWord.get(0)).iterator().next();
        }
        if(!streetList.isEmpty()) {
            List<String> streetSet = ads.GetResultsByClassifyAndIndex(Classify.STREET, streetList.get(0));
            List<String> locationSet = ads.GetResultsByClassifyAndIndex(Classify.LOCATION, streetList.get(0));
            if (!locationSet.isEmpty())
                if (locationSet.contains("מרכז אורן") || locationSet.contains("אוניברסיטת בן גוריון"))
                    if (streetSet.contains("אורן")) {
                        streetSet.remove("אורן");
                        ads.Remove(Classify.STREET,streetList.get(0),"אורן");
                    }
                        else {
                        streetSet.remove("שדרות בן גרויון");
                        ads.Remove(Classify.STREET,streetList.get(0),"שדרות בן גוריון");

                    }
                        if(!streetSet.isEmpty())
                return streetSet.iterator().next();
        }
        List<Integer> locationWord = ads.GetEnvsIndex(Classify.WORD_LOCATION);
        List<Integer> streetAndWordLocation = intersectList(locationWord,ads.GetEnvsIndex(Classify.STREET));
        if(!streetAndWordLocation.isEmpty())
        {
            List<String> streets = ads.GetResultsByClassifyAndIndex(Classify.STREET, streetAndWordLocation.get(0));
            if(streets.size()>1)
                return ads.GetResultsByClassifyAndIndex(Classify.WORD_LOCATION, streetAndWordLocation.get(0)).iterator().next() + " " + streets.get(0) + " - " + streets.get(1);
        }
        List<Integer> envsWithStreetWithoutWordLocationAndPhone = minusList(minusList(minusList(ads.GetEnvsIndex(Classify.STREET),ads.GetEnvsIndex(Classify.WORD_LOCATION)),ads.GetEnvsIndex(Classify.PHONE)),blackList);
        if(!envsWithStreetWithoutWordLocationAndPhone.isEmpty())
            return ads.GetResultsByClassifyAndIndex(Classify.STREET, envsWithStreetWithoutWordLocationAndPhone.get(0)).iterator().next();

        List<Integer> location = ads.GetEnvsIndex(Classify.LOCATION);
        List<Integer> locationAndWordLocation = intersectList(location,locationWord);
        if(!locationAndWordLocation.isEmpty())
        {
            return ads.GetResultsByClassifyAndIndex(Classify.WORD_LOCATION, locationAndWordLocation.get(0)).iterator().next() + ads.GetResultsByClassifyAndIndex(Classify.LOCATION, locationAndWordLocation.get(0)).iterator().next();
        }
        List<Integer> streetAndNotPhone = minusList(minusList(ads.GetEnvsIndex(Classify.STREET),ads.GetEnvsIndex(Classify.PHONE)),blackList);
        if(!streetAndNotPhone.isEmpty())
            return ads.GetResultsByClassifyAndIndex(Classify.STREET, streetAndNotPhone.get(0)).iterator().next();
        List<Integer> streetsEnvs = ads.GetEnvsIndex(Classify.STREET);
        List<String> streets = ads.GetResultsByClassify(Classify.STREET);
        if(streets.size()==1) {
            String street = streets.get(0);
            if(ads.getEnvLst().get(streetsEnvs.get(0)).getEnvString().contains("ב" + street))
                return street;
        }
        return "";
    }

    private int apartmentNumberDecision(AnalyzedDS ads) {
        List<Integer> apartmentNumberList = ads.GetEnvsIndex(Classify.APARTMENT_NUMBER);
        List<Integer> phoneList = ads.GetEnvsIndex(Classify.PHONE);
        List<Integer> streetList = ads.GetEnvsIndex(Classify.STREET);
        List<Integer> roomList = ads.GetEnvsIndex(Classify.ROOM_DES);
        roomList.addAll(ads.GetEnvsIndex(Classify.ROOMS_DES));
        apartmentNumberList = intersectList(minusList(apartmentNumberList,phoneList),streetList);
        if(!apartmentNumberList.isEmpty())
        {
            List<String> ans = new LinkedList<>();
            for(int i=0;i<apartmentNumberList.size();i++)
            {
                List<Word> apNumList = ads.GetWordResultsByClassifyAndIndex(Classify.APARTMENT_NUMBER,apartmentNumberList.get(i));
                List<Word> roomWordList = ads.GetWordResultsByClassifyAndIndex(Classify.ROOMS_DES,apartmentNumberList.get(i));
                roomWordList.addAll(ads.GetWordResultsByClassifyAndIndex(Classify.ROOM_DES,apartmentNumberList.get(i)));
                if(roomWordList.isEmpty())
                    return Integer.parseInt(apNumList.get(0).getDictionaryValue());
                for(Word apNum:apNumList)
                    for(Word roomW:roomWordList)
                        if(Math.abs(apNum.getIndex()-roomW.getIndex())>2)
                            ans.add(apNum.getDictionaryValue());

            }
            if(!ans.isEmpty())
                return Integer.parseInt(ans.get(0));

        }



            return -1;
    }

    private int protectedSpaceDecision(AnalyzedDS ads)
    {
        List<Integer> protectedSpace = ads.GetEnvsIndex(Classify.PROTECTED_SPACE);
        if(protectedSpace.isEmpty())
            return 0;
        return 1;
    }

    private int warehouseDecision(AnalyzedDS ads)
    {
        List<Integer> suspicious = ads.GetEnvsIndex(Classify.WAREHOUSE);
        if(suspicious.isEmpty())
            return 0;
        return 1;
    }

    private int animalDecision(AnalyzedDS ads)
    {
        List<Integer> animalName = ads.GetEnvsIndex(Classify.ANIMELNAME);
        List<Integer> animalExist = ads.GetEnvsIndex(Classify.ANIMEL_EXIST);
        List<Integer> negative = ads.GetEnvsIndex(Classify.NEGATIVE);

        List<Integer> suspicious = intersectList(animalExist,animalName);
        suspicious = minusList(suspicious,negative);
        if(suspicious.isEmpty())
            return 0;
        return 1;
    }

    private int balconyDecision(AnalyzedDS ads)
    {
        List<Integer> balconyName = ads.GetEnvsIndex(Classify.BALCONY);
        if(balconyName.size()==0)
            return 0;
        for(int i=0;i<balconyName.size();i++)
        {
            String[] toCheck = ads.getEnvLst().get(balconyName.get(i)).getEnvString().split(" ");
            int isNot=0;
            for(String str: toCheck) {
                if (str.equals("שירות"))
                    isNot++;
                if (str.equals("ומרפסת") && isNot>0)
                    isNot--;
            }
            if(isNot==0)
                return 1;
        }
        return 0;
    }

    private int furnitureDecision(AnalyzedDS ads) {
        List<Integer> negativeList = ads.GetEnvsIndex(Classify.NEGATIVE);
        List<Integer> decisiveList = ads.GetEnvsIndex(Classify.DECISIVENESS);
        List<Integer> requirementList = ads.GetEnvsIndex(Classify.REQUIREMENT);
        List<Integer> furnitureList = ads.GetEnvsIndex(Classify.FURNITURE);
        List<Integer> furnitureExistList = ads.GetEnvsIndex(Classify.FURNITURE_EXIST);
        List<Integer> almostDescList = ads.GetEnvsIndex(Classify.ALMOST_DESC);

        if(furnitureExistList.size()>0)
        {
           if(intersectList(furnitureExistList,negativeList).size()>0)
               return 0;
           if(intersectList(furnitureExistList,almostDescList).size()>0)
                return 1;
           if(intersectList(furnitureExistList,decisiveList).size()>0)
               return 2;
           if(intersectList(furnitureExistList,requirementList).size()>0)
               return -1;
           for(int i=0;i<furnitureExistList.size();i++)
               for(String str:ads.GetResultsByClassifyAndIndex(Classify.FURNITURE_EXIST,furnitureExistList.get(i)))
                   if((str.equals("מרוהטת") || str.equals("מרוהטים") || str.equals("מאובזרת") || ((str.charAt(0)== 'ו' || str.charAt(0)== 'ה') && (str.substring(1).equals("מרוהטת") || str.equals("מרוהטים") || str.substring(1).equals("מאובזרת")))))
                       return 2;
            return 1;
        }
        if(furnitureList.size()==0)
            return -1;
        return 1;
    }

    private double numRoomsDecision(AnalyzedDS ads) {
        List<Integer> roomDesList = ads.GetEnvsIndex(Classify.ROOMS_DES);
        List<Integer> rommateQuantityeList = ads.GetEnvsIndex(Classify.ROMMATE_QUANTITY);


        List<Integer> roomDesAndQuantityList = intersectList(roomDesList, rommateQuantityeList);
        if (!roomDesAndQuantityList.isEmpty()) {
            for (int i = 0; i < roomDesAndQuantityList.size(); i++) {
                int envIndex = roomDesAndQuantityList.get(i);
                List<String> quantityWords = ads.GetResultsByClassifyAndIndex(Classify.ROMMATE_QUANTITY, envIndex);
                List<String> roomsDesWords = ads.GetResultsByClassifyAndIndex(Classify.ROOMS_DES, envIndex);
                List<String> roomateExistWords = ads.GetResultsByClassifyAndIndex(Classify.ROMMATE_EXIST, envIndex);
                for (String roomsDes : roomsDesWords)
                    for (String quantityWord : quantityWords)
                        if (Math.abs(distance(ads.getEnvLst().get(envIndex).getEnvString(), roomsDes, quantityWord)) == 1)
                            if (roomateExistWords.isEmpty())
                                return Double.parseDouble(quantityWord.replaceAll("\\|", "."));
                            else {
                                boolean flag = false;
                                for (String rommateExistWord : roomateExistWords)
                                    if (Math.abs(distance(ads.getEnvLst().get(envIndex).getEnvString(), quantityWord, rommateExistWord)) == 1)
                                        flag = true;
                                if (!flag)
                                    return Double.parseDouble(quantityWord.replaceAll("\\|", "."));
                            }
            }
        }
        for (int i = 0; i < ads.getEnvLst().size(); i++) {
            String sentence = ads.getEnvLst().get(i).getEnvString();
            String search = "חדרי שינה";
            if (sentence.toLowerCase().indexOf(search.toLowerCase()) != -1)
                return Double.parseDouble(ads.GetResultsByClassifyAndIndex(Classify.ROMMATE_QUANTITY, i).get(0)) + 1;
        }
        return -1;
    }

    private int rommateDecision(AnalyzedDS ads) {
        for (int i = 0; i < ads.getEnvLst().size(); i++) {
            String sentence = ads.getEnvLst().get(i).getEnvString().replaceAll("[/\\*&!@,#$%^()]", " ");
            boolean flag = rootAndWord("שתפ", sentence);//rootAndWord("סטדנט",sentence) || rootAndWord("שתפ",sentence);


            if (sentence.matches(".*\\bחדרי שינה\\b.*")) {
                List<String> ans = ads.GetResultsByClassifyAndIndex(Classify.ROMMATE_QUANTITY, i);
                Iterator<String> iter = ans.iterator();
                while (iter.hasNext()) {
                    String qun = iter.next();
                    int x = distance(sentence, "שינה", qun);
                    if (!(x < -1 && x >= -3))
                        iter.remove();
                }

                if (!ans.isEmpty())
                    return Collections.min(stringCollectionToIntegerCollection(ans));
            }
        }

        for (int i = 0; i < ads.getEnvLst().size(); i++) {
            String sentence = ads.getEnvLst().get(i).getEnvString().replaceAll("[/\\*&!@,#$%^()]", " ");
            boolean flag = rootAndWord("שתפ", sentence);//rootAndWord("סטדנט",sentence) || rootAndWord("שתפ",sentence);

            String search="מתאימה";
            if (sentence.matches(".*\\bמתאימה\\b.*") || sentence.matches(".*\\bמתאים\\b.*") || rootAndWord("יעד",sentence))
            {
                List <String> ans = ads.GetResultsByClassifyAndIndex(Classify.ROMMATE_QUANTITY, i);
                Iterator<String> iter = ans.iterator();
                while(iter.hasNext())
                {
                    String qun = iter.next();
                    int x = distance(sentence,"מתאימה",qun);
                    int y = distance(sentence,"מיועדת",qun);
                    int z = distance(sentence,"מתאים",qun);

                    if(x<-1 || x>=3)
                        iter.remove();
                    if(z<-1 || z>=3)
                        iter.remove();
                    if(y<-1 || y>=3)
                        iter.remove();
                }
                if(!ans.isEmpty()) {
                    int x = Collections.max(stringCollectionToIntegerCollection(ans));
                    if(ans.contains("0") && x==1)
                        x=0;
                    if(x == 0)
                        if(flag)
                            return 2;
                        else
                            return 0;
                    return x;

                }
                return -1;
            }

            search = "זוג";
            if (sentence.matches(".*\\bלזוג\\b.*") || sentence.matches(".*\\bזוג\\b.*") || sentence.matches(".*\\bלזוגות\\b.*") || sentence.matches(".*\\bזוגות\\b.*")) {
                List<String> a = ads.GetResultsByClassifyAndIndex(Classify.ROMMATE_QUANTITY, i);
                List<String> b= new LinkedList<>();
                for(String w:a)
                    b.add(w);
                b.remove("1");
                if (b.size() == 1)
                    return 0;
                else return -1;
            }
        }
        List<Integer> rommateQuantityeList = ads.GetEnvsIndex(Classify.ROMMATE_QUANTITY);
        List<Integer> rommateList = ads.GetEnvsIndex(Classify.ROMMATE);
        List<Integer> rommateExistList = ads.GetEnvsIndex(Classify.ROMMATE_EXIST);
        List<Integer> roomDesList = ads.GetEnvsIndex(Classify.ROOM_DES);
        List<Integer> rommateQuntityAndrommateExistList = intersectList(rommateQuantityeList,rommateExistList);
        List<Integer> rommateAndQuantityList = intersectList(rommateList,rommateQuantityeList);
        List<Integer> roomDesAndRommateExistList = intersectList(rommateExistList,roomDesList);
        int exist=0;
        int search=0;
        if(!rommateQuntityAndrommateExistList.isEmpty())
        {
            exist = Integer.parseInt(ads.GetResultsByClassifyAndIndex(Classify.ROMMATE_QUANTITY,rommateQuntityAndrommateExistList.get(0)).get(0));
        }
        if(!roomDesAndRommateExistList.isEmpty())
        {
            exist=1;
        }
        if(!rommateAndQuantityList.isEmpty())
        {
            List<Integer> ans=new LinkedList<>();
            for(int i=0;i<rommateAndQuantityList.size();i++)
            {
                ans.retainAll(stringCollectionToIntegerCollection(ads.GetResultsByClassifyAndIndex(Classify.ROMMATE_QUANTITY,rommateAndQuantityList.get(i))));
            }
            if(!ans.isEmpty())
                search=exist+Collections.min(ans);
        }
        if(search!=0 && exist!=0)
            return search+exist;
        return -1;
    }


    private String getNeighborhoodByStreetName(String streetName)
    {
        if(street_neighborhood_dic.containsKey(streetName))
            return street_neighborhood_dic.get(streetName);
        return "";
    }


    private int floorDecision(AnalyzedDS ads)
    {
        List<Integer> floorList = ads.GetEnvsIndex(Classify.FLOOR);
        List<Integer> floorQuantityList = ads.GetEnvsIndex(Classify.FLOOR_QUANTITY);
        List<Integer> ans = intersectList(floorList,floorQuantityList);
        if(!ans.isEmpty())
        {
            for(int i=0;i<ans.size();i++) {
                int envIndex=ans.get(i);
                String sentence = ads.getEnvLst().get(envIndex).getEnvString().replaceAll("[\\\\/]"," ");
                List<Word> fq = ads.GetWordResultsByClassifyAndIndex(Classify.FLOOR_QUANTITY,envIndex);
                List<Word> f = ads.GetWordResultsByClassifyAndIndex(Classify.FLOOR,envIndex);
                List<String> ansRet = new LinkedList<>();
                int counter = 0;
                for(Word fW:f) {
                    for (Word fqW : fq) {
                        int c=fW.getIndex() - fqW.getIndex();
                        if (c == -1)// && Math.abs(fW.getIndex()-fqW.getIndex())!=0)
                        {
                            counter++;
                            ansRet.add(fqW.getDictionaryValue());
                        }
                        else if(c==-2 || c==0)
                            ansRet.add(fqW.getDictionaryValue());
                    }
                    if(!ansRet.isEmpty())
                        return Collections.min(stringCollectionToIntegerCollection(ansRet));
                }

                if(!ansRet.isEmpty()) {
                    if(counter<3)
                        return Collections.min(stringCollectionToIntegerCollection(ansRet));
                    else
                        return Math.min(Integer.parseInt(ansRet.get(0)),Integer.parseInt(ansRet.get(1)));
                }
                    return 0;
            }
        }
            return -2;
    }


        @Override
    public Apartment extractApartment(String str) {

        EnvList l = new EnvList(str);
        AnalyzedDS ads= new AnalyzedDS(l);

        Apartment ap = new Apartment();

        int numMates = rommateDecision(ads);
        double numRooms=numRoomsDecision(ads);
        if(numMates !=-1 && numRooms == -1.0) {
                ap.setNumberOfMates(numMates);
            if(numMates==0)
                ap.setNumberOfRooms(2);
            else
                ap.setNumberOfRooms(numMates+1);
        }
        else if(numMates == -1 && numRooms != -1.0){
            ap.setNumberOfRooms(numRooms);
            int rooms = (int) numRooms;
            if(rooms==-1)
                ap.setNumberOfMates(numMates);//-1
            else {
                if (rooms == 1)
                    ap.setNumberOfMates(1);
                else if (rooms == 2)
                    ap.setNumberOfMates(0);
                else
                    ap.setNumberOfMates(rooms - 1);
            }
        }
        else {
            ap.setNumberOfMates(numMates);//-1
            ap.setNumberOfRooms(numRooms);
        }
        ap.setFurniture(furnitureDecision(ads));
        ap.setBalcony(balconyDecision(ads));
        ap.setAnimal(animalDecision(ads));
        ap.setProtectedSpace(protectedSpaceDecision(ads));
        List<Integer> gardenDic = gardenDecision(ads);
        ap.setGarden(gardenDic.get(0));
        ap.setGardenSize(gardenDic.get(1));
        ap.setWarehouse(warehouseDecision(ads));
        ap.setSize(sizeDecision(ads));
        ap.setContacts(phoneDecision(ads));
        ap.setCost(priceDecision(ads));
        ApartmentLocation apl = new ApartmentLocation();
        Address ad= new Address();
        apl.setFloor(floorDecision(ads));
        ad.setStreet(streetDecision(ads));
        ad.setNumber(apartmentNumberDecision(ads));
        apl.setAddress(ad);
        String neighborhood = neighborhoodDecision(ads);
        if(neighborhood == "" && ad.getStreet() != "")
            neighborhood = getNeighborhoodByStreetName(ad.getStreet());
        apl.setNeighborhood(neighborhood);
        ap.setApartmentLocation(apl);
        return ap;
    }
}