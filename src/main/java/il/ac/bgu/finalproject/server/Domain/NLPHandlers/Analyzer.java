package il.ac.bgu.finalproject.server.Domain.NLPHandlers;

import org.apache.commons.lang3.math.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class  Analyzer {

    private static AnalyzedDS aDS;
    private static List<String> gardenList ;
    private static List<String> rommateList ;
    private static List<String> blackList ;
    private static List<String> wordLocationList ;
    private static List<String> firstNamesList ;
    private static List<String> wordPriceList ;
    private static List<String> wordSizeList ;
    private static List<String> wordNegativeList ;
    private static List<String> streetList ;
    private static List<String> floorList ;
    private static List<String> floorQuantityList ;
    private static List<String> neighborhoodList ;
    private static List<String> wordStreetList ;
    private static List<String> locationsList;
    private static List<String> protectedSpace;
    private static List<String> warehouseList;
    private static List<String> animalNameList;
    private static List<String> animalExistList;
    private static List<String> balconyList;
    private static List<String> decisivenessList;
    private static List<String> requirementList;
    private static List<String> furnitureList;
    private static List<String> almostDescList;
    private static List<String> rommateQuantityList;
    private static List<String> roomList;
    private static List<String> roommateExistList;
    private static List<String> roomS_desList;
    private static boolean isLoaded = false;


    public static void reload()
    {
        isLoaded = false;
    }

    public Analyzer(AnalyzedDS aDS,DataBaseNlp db) {
        this.aDS = aDS;
        if(!isLoaded) {
            db.connect();
            gardenList = db.getValuesOneCol("garden");
            rommateList = db.getValuesOneCol("roommate");
            blackList = db.getValuesOneCol("blackList");
            wordLocationList = db.getValuesOneCol("wordLocation");
            firstNamesList = db.getValuesOneCol("firstNames");
            wordPriceList = db.getValuesOneCol("price");
            wordSizeList = db.getValuesOneCol("size");
            wordNegativeList = db.getValuesOneCol("negative");
            streetList = db.getValuesOneCol("streets");
            floorList = db.getValuesOneCol("floor");
            neighborhoodList = db.getValuesOneCol("neighborhood");
            wordStreetList = db.getValuesOneCol("streetWord");
            locationsList = db.getValuesOneCol("locations");
            protectedSpace = db.getValuesOneCol("protectedSpace");
            warehouseList = db.getValuesOneCol("warehouse");
            animalExistList = db.getValuesOneCol("animalExist");
            animalNameList = db.getValuesOneCol("animalName");
            balconyList = db.getValuesOneCol("balcony");
            decisivenessList = db.getValuesOneCol("decisiveness");
            requirementList = db.getValuesOneCol("requirement");
            furnitureList = db.getValuesOneCol("furniture");
            almostDescList = db.getValuesOneCol("almostDescription");
            rommateQuantityList = db.getValuesOneCol("roommate_quantity");
            roomList = db.getValuesOneCol("roomDes");
            roommateExistList = db.getValuesOneCol("roommateExist");
            roomS_desList = db.getValuesOneCol("roomS_des");
            floorQuantityList = db.getValuesOneCol("floorQuantity");
            isLoaded=true;
            db.disConnect();
            // we will load the Dictionaries
        }
    }

    /***
     * @param word1
     * @param word2
     * @return minimum number of operations required to transform one string into the other.
     */
    private static int minDistance(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();

        // len1+1, len2+1, because finally return dp[len1][len2]
        int[][] dp = new int[len1 + 1][len2 + 1];

        for (int i = 0; i <= len1; i++) {
            dp[i][0] = i;
        }

        for (int j = 0; j <= len2; j++) {
            dp[0][j] = j;
        }

        //iterate though, and check last char
        for (int i = 0; i < len1; i++) {
            char c1 = word1.charAt(i);
            for (int j = 0; j < len2; j++) {
                char c2 = word2.charAt(j);

                //if last two chars equal
                if (c1 == c2) {
                    //update dp value for +1 length
                    dp[i + 1][j + 1] = dp[i][j];
                } else {
                    int replace = dp[i][j] + 1;
                    int insert = dp[i][j + 1] + 1;
                    int delete = dp[i + 1][j] + 1;

                    int min = replace > insert ? insert : replace;
                    min = delete > min ? min : delete;
                    dp[i + 1][j + 1] = min;
                }
            }
        }

        return dp[len1][len2];
    }


    public void extractFirstName(List<String> firstNameDictionary, String notToInclude)
    {
        int size = aDS.getEnvLst().size();
        for (int i = 0; i < size; i++) {
            String str = aDS.getEnvLst().get(i).getEnvString();
            String[] splited = str.split(" ");
            int j=0;
            for (String s : splited) {
                s = s.replaceAll(notToInclude,"");
                if(!s.equals("") && firstNameDictionary.contains(s.substring(1)) && s.substring(0,1).equals("ל"))
                    aDS.Insert(Classify.NAME, i, new Word(s,s.substring(1),j));
                else if (!s.equals("") && (firstNameDictionary.contains(s))) //HELP!!! || firstNameDictionary.contains(s + "ל")))
                    aDS.Insert(Classify.NAME, i, new Word(s,s,j));
                else{}
            j++;}
        }
    }

    private static String cleanLocationPrefix(String str,Classify classify)
    {
        String s="";
        if(str.length()>1) {
            if ((("ב" + str.substring(1)).equals(str) && !( "ב" + str.substring(1)).equals("בו")) || (("מ" + str.substring(1)).equals(str) && !classify.equals(Classify.STREET)) || (("ל" + str.substring(1)).equals(str) && !("ל" + str.substring(1)).equals("לא") && !("ל" + str.substring(1)).equals("לד") && !classify.equals(Classify.STREET)))
                s = str.substring(1);
            else if ((("*ב" + str.substring(2)).equals(str) && !( "*ב" + str.substring(2)).equals("בו")) || (("*מ" + str.substring(2)).equals(str) && !classify.equals(Classify.STREET)) || (("*ל" + str.substring(2)).equals(str)  && !classify.equals(Classify.STREET) && !("*ל" + str.substring(2)).equals("*לא") && !("*ל" + str.substring(2)).equals("*לד")))
                s = "*" + str.substring(2);
            else
                s = str;
        }
        return s;
    }

    public static String fullName(String street, List<String> streets) {
        if(!street.contains("*"))
            return  street;
        else {
            int index = streets.indexOf(street);
            String streetName = streets.get(index);
            while(streetName.contains("*")) {
                index--;
                streetName = streets.get(index);
            }
                return streets.get(index);
        }
    }


    private static boolean isWordExist(String envWord,String dicWord,Classify classify)
    {
        if(cleanLocationPrefix(envWord,classify).equals(dicWord) || envWord.equals(dicWord)
                || cleanLocationPrefix("*" + envWord,classify).equals(dicWord) || ("*" + envWord).equals(dicWord))
            return true;
        return  false;
    }

    public static boolean isNumeric(String s) {
        return s != null && s.matches("[-+]?\\d*\\.?\\d+");
    }

    public static List<String> extractAddressField(String str, List<String> streets, int index, Classify classify) {
        List<String> streetLst = new LinkedList<String>();
        boolean found = false;
        String[] strSplited = str.split(" ");
        for (int i = 0; i < strSplited.length; i++){
            for(String street :streets)
            {
                String[] splitedStreet = street.split(" ");
                int length = splitedStreet.length;
                if (length == 1) {
                    if (isWordExist(strSplited[i], splitedStreet[0],classify)) {
                        if(classify.equals(Classify.NEIGHBORHOOD))
                            if(i-1>=0 && !strSplited[i-1].equals("קומה") && !strSplited[i-1].equals("בקומה"))
                                streetLst.add(street);
                            else
                                break;
                        else
                            streetLst.add(street);
                        if(strSplited.length>i+1 && isNumeric(strSplited[i+1]) && Integer.parseInt(strSplited[i+1])<500)
                            aDS.Insert(Classify.APARTMENT_NUMBER,index,new Word(strSplited[i+1],strSplited[i+1],i+1));
                        break;
                    }
                } else if (length == 2  && strSplited.length - i > 1) {
                    if ((isWordExist(strSplited[i], splitedStreet[0],classify) && isWordExist(strSplited[i + 1], splitedStreet[1],classify))
                            || (isWordExist(strSplited[i], splitedStreet[1],classify) && isWordExist(strSplited[i + 1], splitedStreet[0],classify))
                            ) {
                        streetLst.add(street);
                        if(strSplited.length>i+2 && isNumeric(strSplited[i+2]) && Integer.parseInt(strSplited[i+2])<500)
                            aDS.Insert(Classify.APARTMENT_NUMBER,index,new Word(strSplited[i+2],strSplited[i+2],i+2));
                        i=i+2;
                        break;
                    }
                } else if (length == 3 && strSplited.length - i > 2) {
                    if (isWordExist(strSplited[i], splitedStreet[0],classify) && isWordExist(strSplited[i + 1], splitedStreet[1],classify) && isWordExist(strSplited[i + 2], splitedStreet[2],classify))
                    {
                        streetLst.add(street);
                        if(strSplited.length>i+3 && isNumeric(strSplited[i+3]) && Integer.parseInt(strSplited[i+3])<500 )
                            aDS.Insert(Classify.APARTMENT_NUMBER,index,new Word(strSplited[i+3],strSplited[i+3],i+3));
                        i=i+3;
                        break;
                    }
                }
            }
        }
        return streetLst;
    }

    private int isContain(String[]array,String toCheck)
    {
        int index = -1;
        for (int i=0;i<array.length;i++) {
            if (array[i].equals(toCheck)) {
                index = i;
                break;
            }
        }
        return index;
    }

    public void extractFloorQuantity(Classify classify, List<String> dictionary, String notToInclude) {

        int size = aDS.getEnvLst().size();
        int count=0;
        for (int i = 0; i < size; i++) {
            count=0;
            String str = aDS.getEnvLst().get(i).getEnvString();
            str=str.replaceAll("[\\\\/]"," / ");
            String[] strSplited = str.split(" ");
            for (int j = 0; j < strSplited.length; j++) {
                if(strSplited[j].contains("/"))
                    count=count-2;
                for (String dicWord : dictionary) {
                    if (isWordExist(strSplited[j], dicWord,classify))
                        aDS.Insert(classify, i, new Word(strSplited[j], fullName(dicWord, dictionary), count));
                }
                count++;
            }
        }
    }


    public void extractAddress(Classify classify, List<String> dictionary, String notToInclude)
    {
        int size = aDS.getEnvLst().size();
        for (int i = 0; i < size; i++) {
            String str = aDS.getEnvLst().get(i).getEnvString();
            str = str.replaceAll("[(]"," ").replaceAll(notToInclude,"");
            List<String> streetList = new LinkedList<>();
          //  if(classify.equals(Classify.FLOOR_QUANTITY))
          //      streetList = extractAddressField(str.replaceAll("[(/-\\\\]"," "),dictionary,i,classify);
          //  else
                streetList = extractAddressField(str.replaceAll("[(-]"," "),dictionary,i,classify);
            if(!streetList.isEmpty())
                for(String street:streetList)//System.out.println(streetList);
                {
                    String s = fullName(street, dictionary);      //fullName(street, dictionary)
                    if(street.charAt(0) == '*')
                        street=street.substring(1);
                    aDS.Insert(classify, i, new Word(street, s, isContain(aDS.getEnvLst().get(i).getEnvString().split(" "), street)));
                }
        }
    }

    private String cleanEmojis(String str)
    {
        return str.replaceAll("\uD83D\uDC8E","");
    }

    public void extractWord(Classify classify, List<String> dictionary, String notToInclude)
    {
        int size = aDS.getEnvLst().size();
        for (int i = 0; i < size; i++) {
            String str = aDS.getEnvLst().get(i).getEnvString();
            str=cleanEmojis(str.replaceAll(notToInclude,"").replaceAll("[+-]"," "));
            String[] splited = str.split(" ");
            int j=0;
            for (String s : splited) {
               // s = s.replaceAll(notToInclude,"");//.replaceAll("״","");
                if(!s.isEmpty()) {
                    if (dictionary.contains(s))
                        aDS.Insert(classify, i, new Word(s,s,j));
                    else if((dictionary.contains(s.substring(1)) && classify.equals(Classify.ROMMATE_EXIST)) ||
                            (dictionary.contains(s.substring(1)) && (classify.equals(Classify.BALCONY) || classify.equals(Classify.REQUIREMENT))) ||
                            (dictionary.contains(s.substring(1)) && ("כ" + s.substring(1)).equals(s) && classify.equals(Classify.WORD_SIZE)))
                            aDS.Insert(classify, i, new Word(s,s.substring(1),j));
                }
                j++;
            }
        }
    }

    public void  extractPhoneNumber() {
        Pattern p = Pattern.compile("(0\\d{2}\\s*-?\\s*\\d{1}\\s*-?\\s*\\d{2}\\s*-?\\s*\\d{1}\\s*-?\\s*\\d{3}|0[2346789]\\s*-?\\s*\\d{7}|\\d{7}|077\\s*-?\\s*\\d{7})");
        int size = aDS.getEnvLst().size();
        for(int i=0;i<size;i++)
        {
            String str=aDS.getEnvLst().get(i).getEnvString();
            Matcher m = p.matcher(str);
            String temp;
            while(m.find()) {
                temp=str.substring(m.start(),m.end());
                aDS.Insert(Classify.PHONE,i,new Word(temp,temp.replaceAll("\\D",""),isContain(str.split(" "),temp)));
            }
        }
    }

    /*
    public void extractProtectedSpace() {
        int size = aDS.getEnvLst().size();
        for (int i = 0; i < size; i++) {
            String str = aDS.getEnvLst().get(i).getEnvString();


        }
    }*/

    public void extractGapNumber(Classify classify,int min, int max) {
        int size = aDS.getEnvLst().size();
        for (int i = 0; i < size; i++) {
            String str = aDS.getEnvLst().get(i).getEnvString();
            int j=0;
            for (String s : str.split(" ")) {
                String tmp=s;
                if(classify.equals(Classify.SIZE_APARTMENT))
                {
                    if (!tmp.contains("/"))
                        tmp = tmp.replaceAll("\\D", "");
                }
                else
                    tmp = tmp.replaceAll("\\D", "");
                if (NumberUtils.isCreatable(tmp)) {
                    int x = Integer.parseInt(tmp);
                    if (x >= min && x <= max)
                        aDS.Insert(classify, i, new Word(s,tmp,j));
                }
                j++;
            }
        }
    }

    public int NumberOfGapAccourence(int min,int max)
    {
        int count=0;
        int size = aDS.getEnvLst().size();
        for (int i = 0; i < size; i++) {
            String str = aDS.getEnvLst().get(i).getEnvString();
            for (String s : str.split(" ")) {
                if(!s.contains("/") && !s.contains("."))
                    s = s.replaceAll("\\D","");
                if (NumberUtils.isCreatable(s)) {
                    int x = Integer.parseInt(s);
                    if (x >= min && x <= max)
                        count++;
                }
            }
        }
        return count;
    }

    //    ****************************************************************
    //    ****************************************************************                i used this helper fun also in NLPImp
    //    ****************************************************************
    private static boolean rootAndWord (String root, String word){
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

    private void extractFurnitureType() {
        int size = aDS.getEnvLst().size();
        for (int i = 0; i < size; i++) {
            String str = aDS.getEnvLst().get(i).getEnvString();
            String[] splitedStr = str.split(" ");
            for (int j = 0; j < splitedStr.length; j++)
                if (rootAndWord("אבזר", splitedStr[j]) || rootAndWord("רהט", splitedStr[j]))
                    aDS.Insert(Classify.FURNITURE_EXIST, i, new Word(splitedStr[j],splitedStr[j].replaceAll(":",""),j));
        }
    }

    private void extractRommate() {
        int size = aDS.getEnvLst().size();
        for (int i = 0; i < size; i++) {
            String str = aDS.getEnvLst().get(i).getEnvString();
            String[] splitedStr = str.split(" ");
            for (int j = 0; j < splitedStr.length; j++)
                if (rootAndWord("שתפ", splitedStr[j]) || rootAndWord("שתף", splitedStr[j]) )
                    aDS.Insert(Classify.ROMMATE, i, new Word(splitedStr[j],splitedStr[j],j));
        }
    }


    private void extractFloor(){
        int size = aDS.getEnvLst().size();
        for (int i = 0; i < size; i++) {
            String str = aDS.getEnvLst().get(i).getEnvString();
            String[] splitedStr = str.split(" ");
            for (int j = 0; j < splitedStr.length; j++)
                if (!splitedStr[j].equals("") &&
                        (splitedStr[j].equals("קומה") || splitedStr[j].substring(1).equals("קומות") || (splitedStr[j].equals("קרקע") ||
                                (splitedStr[j].length()>0 &&  (splitedStr[j].substring(1).equals("קומה") || splitedStr[j].substring(1).equals("קרקע") || splitedStr[j].equals("קומות"))))))
                    aDS.Insert(Classify.FLOOR, i, new Word(splitedStr[j],splitedStr[j],j));
        }
    }

    public void analyze()
    {
        String notToIncludeRegex = "([!,~@#$%:״^&*\\)]|\\d)";
        String notToIncludeStreetRegex = "[*!@#'$%^&)]";
        extractFurnitureType();
        extractWord(Classify.DECISIVENESS,decisivenessList,notToIncludeRegex);
        extractWord(Classify.DECISIVENESS,decisivenessList,notToIncludeRegex);
        extractWord(Classify.REQUIREMENT,requirementList,notToIncludeRegex);
        extractWord(Classify.FURNITURE,furnitureList,notToIncludeRegex);
        extractWord(Classify.ALMOST_DESC,almostDescList,notToIncludeRegex);

        extractRommate();
        extractAddress(Classify.ROMMATE_QUANTITY,rommateQuantityList,notToIncludeStreetRegex);
        extractWord(Classify.ROOM_DES,roomList,notToIncludeRegex);
        extractWord(Classify.ROOMS_DES,roomS_desList,notToIncludeRegex);
        extractWord(Classify.ROMMATE,rommateList,notToIncludeRegex);
        extractWord(Classify.ROMMATE_EXIST,roommateExistList,notToIncludeRegex);


        extractFloor();
        extractFloorQuantity(Classify.FLOOR_QUANTITY,floorQuantityList,notToIncludeStreetRegex);


        extractWord(Classify.ANIMELNAME,animalNameList,notToIncludeRegex);
        extractWord(Classify.ANIMEL_EXIST,animalExistList,notToIncludeRegex);
        extractWord(Classify.ANIMEL_EXIST,animalExistList,notToIncludeRegex);
        extractWord(Classify.BALCONY,balconyList,notToIncludeRegex);
        extractWord(Classify.WAREHOUSE,warehouseList,notToIncludeRegex);
        extractWord(Classify.GARDEN,gardenList,notToIncludeRegex);
        extractWord(Classify.PROTECTED_SPACE,protectedSpace,notToIncludeRegex);
        extractPhoneNumber();
        extractWord(Classify.BLACKLIST,blackList,notToIncludeRegex);
        extractAddress(Classify.WORD_LOCATION,wordLocationList,notToIncludeRegex);
        extractFirstName(firstNamesList,notToIncludeRegex);
        extractWord(Classify.WORD_PRICE,wordPriceList,notToIncludeRegex);
        extractGapNumber(Classify.PRICE,500,5000);
        //extractGapNumber(Classify.FLOOR,-1,25);
        extractWord(Classify.WORD_SIZE,wordSizeList,notToIncludeRegex);
        extractGapNumber(Classify.SIZE_APARTMENT,30,270);
        extractGapNumber(Classify.SIZE_GARDEN,10,270);
        extractWord(Classify.NEGATIVE,wordNegativeList,notToIncludeRegex);
        extractAddress(Classify.STREET,streetList,notToIncludeStreetRegex);
        extractAddress(Classify.NEIGHBORHOOD,neighborhoodList,notToIncludeStreetRegex);
        extractAddress(Classify.WORD_STREET,wordStreetList,notToIncludeRegex);
        extractAddress(Classify.LOCATION,locationsList,notToIncludeStreetRegex);
        //extractGapNumber(Classify.APARTMENT_NUMBER,1,500);
    }
}