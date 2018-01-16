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
    private static List<String> neighborhoodList ;
    private static List<String> wordStreetList ;
    private static List<String> locationsList;
    public List<String> loadFile(String fileName){
        String pathPref = "src\\main\\java\\il\\ac\\bgu\\finalproject\\server\\Domain\\NLPHandlers\\Dictionaries\\";
        String  path= pathPref + fileName;
        List<String> streets = new ArrayList<String>();
        String line = null;
        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while((line = bufferedReader.readLine()) != null)
                streets.add(line);
            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");
        }
        catch(IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");
        }
        return streets;
    }

    public Analyzer(AnalyzedDS aDS) {
        this.aDS = aDS;
        gardenList = loadFile("garden.txt");
        rommateList = loadFile("rommate.txt");
        blackList = loadFile("blackList.txt");
        wordLocationList = loadFile("wordLocation.txt");
        firstNamesList = loadFile("firstNames.txt");
        wordPriceList = loadFile("price.txt");
        wordSizeList = loadFile("size.txt");
        wordNegativeList = loadFile("negative.txt");
        streetList = loadFile("streets1.txt");
        floorList = loadFile("floor.txt");
        neighborhoodList = loadFile("neighborhood.txt");
        wordStreetList = loadFile("streetWord.txt");
        locationsList = loadFile("locations.txt");
        // we will load the Dictionaries
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
            for (String s : splited) {
                s = s.replaceAll(notToInclude,"");
                if(!s.equals("") && firstNameDictionary.contains(s.substring(1)) && s.substring(0,1).equals("ל"))
                    aDS.Insert(Classify.NAME, i, s.substring(1));
                else if (!s.equals("") && (firstNameDictionary.contains(s) || firstNameDictionary.contains(s + "ל")))
                    aDS.Insert(Classify.NAME, i, s);
                else{}
            }
        }
    }

    private static String cleanLocationPrefix(String str)
    {
        String s="";
        if(str.length()>1) {
            if ((("ב" + str.substring(1)).equals(str) && !( "ב" + str.substring(1)).equals("בו")) || ("מ" + str.substring(1)).equals(str) || (("ל" + str.substring(1)).equals(str) && !("ל" + str.substring(1)).equals("לא") && !("ל" + str.substring(1)).equals("לד")))
                s = str.substring(1);
            else if ((("*ב" + str.substring(2)).equals(str) && !( "*ב" + str.substring(2)).equals("בו")) || ("*מ" + str.substring(2)).equals(str) || (("*ל" + str.substring(2)).equals(str) && !("*ל" + str.substring(2)).equals("*לא") && !("*ל" + str.substring(2)).equals("*לד")))
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


    private static boolean isWordExist(String envWord,String dicWord)
    {
        if(cleanLocationPrefix(envWord).equals(dicWord) || envWord.equals(dicWord)
                || cleanLocationPrefix("*" + envWord).equals(dicWord) || ("*" + envWord).equals(dicWord))
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
                    if (isWordExist(strSplited[i], splitedStreet[0])) {
                        if(classify.equals(Classify.NEIGHBORHOOD))
                            if(i-1>=0 && !strSplited[i-1].equals("קומה") && !strSplited[i-1].equals("בקומה"))
                                streetLst.add(fullName(street, streets));
                            else
                                break;
                        else
                            streetLst.add(fullName(street, streets));
                        if(strSplited.length>i+1 && isNumeric(strSplited[i+1]) && Integer.parseInt(strSplited[i+1])<500)
                            aDS.Insert(Classify.APARTMENT_NUMBER,index,strSplited[i+1]);
                    break;
                    }
                } else if (length == 2  && strSplited.length - i > 1) {
                    if ((isWordExist(strSplited[i], splitedStreet[0]) && isWordExist(strSplited[i + 1], splitedStreet[1]))
                            || (isWordExist(strSplited[i], splitedStreet[1]) && isWordExist(strSplited[i + 1], splitedStreet[0]))
                            ) {
                        streetLst.add(fullName(street, streets));
                        if(strSplited.length>i+2 && isNumeric(strSplited[i+2]) && Integer.parseInt(strSplited[i+2])<500)
                            aDS.Insert(Classify.APARTMENT_NUMBER,index,strSplited[i+2]);
                        i=i+2;
                        break;
                    }
                } else if (length == 3 && strSplited.length - i > 2) {
                    if (isWordExist(strSplited[i], splitedStreet[0]) && isWordExist(strSplited[i + 1], splitedStreet[1]) && isWordExist(strSplited[i + 2], splitedStreet[2]))
                    {
                        streetLst.add(fullName(street, streets));
                        if(strSplited.length>i+3 && isNumeric(strSplited[i+3]) && Integer.parseInt(strSplited[i+3])<500 )
                            aDS.Insert(Classify.APARTMENT_NUMBER,index,strSplited[i+3]);
                        i=i+3;
                        break;
                    }
                }
        }
        }
        return streetLst;
    }


    public void extractAddress(Classify classify, List<String> dictionary, String notToInclude)
    {
        int size = aDS.getEnvLst().size();
        for (int i = 0; i < size; i++) {
            String str = aDS.getEnvLst().get(i).getEnvString();
            str = str.replaceAll("[(]"," ").replaceAll(notToInclude,"");
            List<String> streetList = extractAddressField(str.replaceAll("[(-]"," "),dictionary,i,classify);
                if(!streetList.isEmpty())
                    for(String street:streetList)//System.out.println(streetList);
                        aDS.Insert(classify,i,street);
        }
    }

    public void extractWord(Classify classify, List<String> dictionary, String notToInclude)
    {
        int size = aDS.getEnvLst().size();
        for (int i = 0; i < size; i++) {
            String str = aDS.getEnvLst().get(i).getEnvString();
            String[] splited = str.split(" ");
            for (String s : splited) {
                s = s.replaceAll(notToInclude,"");//.replaceAll("״","");
                if(!s.isEmpty()) {
                    if (dictionary.contains(s) || (dictionary.contains(s.substring(1)) && ("כ" + s.substring(1)).equals(s) && classify.equals(Classify.WORD_SIZE)))
                        aDS.Insert(classify, i, s);
                }
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
                aDS.Insert(Classify.PHONE,i,temp.replaceAll("\\D",""));
            }
        }
    }


    public void extractGapNumber(Classify classify,int min, int max) {
        int size = aDS.getEnvLst().size();
        for (int i = 0; i < size; i++) {
            String str = aDS.getEnvLst().get(i).getEnvString();
            for (String s : str.split(" ")) {
                if(classify.equals(Classify.SIZE_APARTMENT))
                {
                    if (!s.contains("/"))
                        s = s.replaceAll("\\D", "");
                }
                else
                    s = s.replaceAll("\\D", "");
                if (NumberUtils.isCreatable(s)) {
                    int x = Integer.parseInt(s);
                    if (x >= min && x <= max)
                        aDS.Insert(classify, i, s);
                }
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

    public void analyze()
    {
        String notToIncludeRegex = "([!,~@#$%-:״^&*\\)]|\\d)";
        String notToIncludeStreetRegex = "[*!@#'$%^&)]";

        extractWord(Classify.GARDEN,gardenList,notToIncludeRegex);
        extractPhoneNumber();
        extractWord(Classify.ROMMATE,rommateList,notToIncludeRegex);
        extractWord(Classify.BLACKLIST,blackList,notToIncludeRegex);
        extractAddress(Classify.WORD_LOCATION,wordLocationList,notToIncludeRegex);
        extractFirstName(firstNamesList,notToIncludeRegex);
        extractWord(Classify.WORD_PRICE,wordPriceList,notToIncludeRegex);
        extractGapNumber(Classify.PRICE,500,5000);
        extractWord(Classify.WORD_SIZE,wordSizeList,notToIncludeRegex);
        extractGapNumber(Classify.SIZE_APARTMENT,30,270);
        extractGapNumber(Classify.SIZE_GARDEN,10,270);
        extractWord(Classify.NEGATIVE,wordNegativeList,notToIncludeRegex);
        extractAddress(Classify.STREET,streetList,notToIncludeStreetRegex);
        extractWord(Classify.FLOOR,floorList,notToIncludeStreetRegex);
        extractAddress(Classify.NEIGHBORHOOD,neighborhoodList,notToIncludeStreetRegex);
        extractAddress(Classify.WORD_STREET,wordStreetList,notToIncludeRegex);
        extractAddress(Classify.LOCATION,locationsList,notToIncludeStreetRegex);
        //extractGapNumber(Classify.APARTMENT_NUMBER,1,500);
    }
}