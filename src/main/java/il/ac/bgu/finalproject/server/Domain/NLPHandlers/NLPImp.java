package il.ac.bgu.finalproject.server.Domain.NLPHandlers;


import com.restfb.Connection;
import com.restfb.ConnectionIterator;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.Post;

import java.nio.file.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.*;
import java.util.stream.*;

//import java.util.Logger;
import java.io.IOException;
//package com.vogella.eclipse.ide.first;
import java.io.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.stream.Stream;
//import java.util.logging;

import java.lang.Object;
import java.util.Scanner;
import java.util.regex.*;
import java.util.ArrayList;
import java.util.List;

public class NLPImp  implements NLPInterface{

    public static List<String> extractPhoneNumber(String str) {
        List<String> phoneArray= new ArrayList<String>();
        Pattern p = Pattern.compile("(0[2346789]\\s*-?\\s*\\d{7}|0\\d{2}\\s*-?\\s*\\d{3}\\s*-?\\s*\\d{4})");
        Matcher m = p.matcher(str);
        int count=0;
        String temp;
        while(m.find()) {
            count++;
            temp=str.substring(m.start(),m.end());
            phoneArray.add(temp.replaceAll("\\D",""));
            System.out.println("Phone Number: "+count+")  "+temp.replaceAll("\\D",""));
        }
        return phoneArray;
    }

    public static int extractAddress(String str, List<String> streets) {
        //Pattern p = Pattern.compile("(ב?רחוב||כתובת):?\\s?.*");
        //Pattern p = Pattern.compile("( ב(\\S*\\s\\S*)||(ב?רחוב||ב?כתובת)(\\S*\\s))");
        Pattern p2 = Pattern.compile("(מצדה)");
        Pattern p1 = Pattern.compile("(\\s\\S*)");
        Pattern p = Pattern.compile("(ב?רחוב||ב?כתובת)" + p2.pattern());
        //Pattern p = Pattern.compile("(ב?רחוב||ב?כתובת)");
        Matcher m = p.matcher(str);
        boolean b = m.matches();
        if (b) {
            System.out.println("RECOGNIZED:");
            System.out.println(m.group());
            //System.out.println(m.group(2));
            //if (streets.contains(m.group(2)))
            return m.end();
        }
        return 0;
    }

    public static String extractStreetName(String str, List<String> streets) {
        Pattern p = Pattern.compile("(\\S+)(\\s)(\\S+)(\\s)(\\S+)([^\\d\\s]*)");
        Matcher m = p.matcher(str);
        boolean b = m.lookingAt();
        if (b && (streets.contains(m.group()) || streets.contains("" + m.group(1) + m.group(2) + m.group(5) + m.group(4) + m.group(3)) ||
                streets.contains("" + m.group(3) + m.group(2) + m.group(5) + m.group(4) + m.group(1)) ||
                streets.contains("" + m.group(3) + m.group(2) + m.group(1) + m.group(4) + m.group(5)) ||
                streets.contains("" + m.group(5) + m.group(2) + m.group(1) + m.group(4) + m.group(3)) ||
                streets.contains("" + m.group(5) + m.group(2) + m.group(3) + m.group(4) + m.group(1)))) {
            String temp = "" + m.group(1) + m.group(2) + m.group(3) + m.group(4) + m.group(5);
            return temp;
        }
        else if (b && (streets.contains("" + m.group(1) + m.group(2) + m.group(5)) ||
                streets.contains("" + m.group(5) + m.group(2) + m.group(1)))) {
            String temp = "" + m.group(1) + m.group(2) + m.group(5);
            return temp;
        }
        else if (b && (streets.contains("" + m.group(5) + m.group(2) + m.group(3)) ||
                streets.contains("" + m.group(3) + m.group(2) + m.group(5)))) {
            String temp = "" + m.group(3) + m.group(2) + m.group(5);
            return temp;
        }
        else if (b && (streets.contains("" + m.group(1) + m.group(2) + m.group(3)) ||
                streets.contains("" + m.group(3) + m.group(2) + m.group(1)))) {
            String temp = "" + m.group(1) + m.group(2) + m.group(3);
            return temp;
        }
        p = Pattern.compile("(\\S+)(\\s)(\\S+)([^\\d\\s]*)");
        m = p.matcher(str);
        b = m.lookingAt();
        if (b && (streets.contains(m.group()) || streets.contains("" + m.group(3) + m.group(2) + m.group(1)))) {
            return m.group(1) + m.group(2) + m.group(3);
        }
        p = Pattern.compile("(\\S+)([^\\d\\s]*)");
        m = p.matcher(str);
        b = m.lookingAt();
        if (b && streets.contains(m.group(1))) {
            return m.group(1);
        }
        return "";
    }


    public static String extractStreetNameNumber(String str, List<String> streets) {
        Pattern p = Pattern.compile("(\\S+)(\\s)(\\S+)(\\s)(\\S+)(\\s?\\d{1,3})([^\\d\\s]*)"); //([^\d\s]*)
        Matcher m = p.matcher(str);
        boolean b = m.lookingAt();
        String temp;
        if (b && (streets.contains(m.group()) || streets.contains("" + m.group(1) + m.group(2) + m.group(5) + m.group(4) + m.group(3)) ||
                streets.contains("" + m.group(3) + m.group(2) + m.group(5) + m.group(4) + m.group(1)) ||
                streets.contains("" + m.group(3) + m.group(2) + m.group(1) + m.group(4) + m.group(5)) ||
                streets.contains("" + m.group(5) + m.group(2) + m.group(1) + m.group(4) + m.group(3)) ||
                streets.contains("" + m.group(5) + m.group(2) + m.group(3) + m.group(4) + m.group(1)))) {
            temp = "" + m.group(1) + m.group(2)+ m.group(3) + m.group(4) + m.group(5)+ m.group(6);
            return temp;
        } else if (b && (streets.contains("" + m.group(1) + m.group(2) + m.group(5)) ||
                streets.contains("" + m.group(5) + m.group(2) + m.group(1)))) {
            temp = "" + m.group(1) + m.group(2) + m.group(5)+ m.group(6);
            return temp;
        } else if (b && (streets.contains("" + m.group(5) + m.group(2) + m.group(3)) ||
                streets.contains("" + m.group(3) + m.group(2) + m.group(5)))) {
            temp = "" + m.group(3) + m.group(2) + m.group(5)+ m.group(6);
            return temp;
        } else if (b && (streets.contains("" + m.group(1) + m.group(2) + m.group(3)) ||
                streets.contains("" + m.group(3) + m.group(2) + m.group(1)))) {
            temp = "" + m.group(1) + m.group(2) + m.group(3)+ m.group(6);
            return temp;
        }
        p = Pattern.compile("(\\S+)(\\s)(\\S+)(\\s?\\d{1,3})([^\\d\\s]*)");
        m = p.matcher(str);
        b = m.lookingAt();
        if (b && (streets.contains(m.group()) || streets.contains("" + m.group(3) + m.group(2) + m.group(1)))) {
            temp = "" + m.group(1) + m.group(2) + m.group(3)+ m.group(4);
            return temp;
        }
        p = Pattern.compile("(\\S+)(\\s?\\d{1,3})([^\\d\\s]*)");
        m = p.matcher(str);
        b = m.lookingAt();
        if (b && streets.contains(m.group(1))) {
            return m.group(1)+m.group(2);
        }
        return "";
    }


    public static int extractStreetCombine2(String str, List<String> streets) {
        //Pattern p4 = Pattern.compile("(ב?רחוב||כתובת):?\\s?.*");
        //Pattern p5 = Pattern.compile("( ב(\\S*\\s\\S*)||(ב?רחוב||ב?כתובת)(\\S*\\s))");
        Pattern p1 = Pattern.compile("(ב?רחוב ||ב?כתובת )");

        //Pattern p3 = Pattern.compile("(ב?רחוב ||ב?כתובת )||ב"+p2.pattern());
        //Pattern p6 = Pattern.compile("(ב?רחוב||ב?כתובת)");


        Pattern p = Pattern.compile(p1.pattern() + "\\S*\\s\\S*\\s\\S*||\\S*\\s\\S*||\\S*");
        Matcher m = p.matcher(str);
        boolean b = m.matches();
        if (b) {
            if (streets.contains(m.group())) {
                System.out.println(m.group());
                return m.end();
            }
        }
        return 0;
    }

    public static int extractNRooms(String str) {
        Pattern p = Pattern.compile("([הב]?דיר[תה])? (\\d||שלושה?||שני?||\\d.\\d) חדרים");
        //Pattern p = Pattern.compile("([הב]?דיר[תה])? (\\d||שלושה?||שני?||\\d.\\d) חדרים");
        //Pattern p = Pattern.compile("([הב]?דיר[תה])? (\\d||שלושה?||שני?||\\d.\\d)[( חדר)(חדרי?ם?)]");
        Matcher m = p.matcher(str);
        boolean b = m.matches();
        if (b) {
            System.out.println("Rooms in the apartment: "+m.group());
            return m.end();
        }
        return 0;
    }

    public static int extractPrices(String str) {
        //System.out.println("the whole sentence: "+ str);
        Pattern p = Pattern.compile("(\\S*)(\\s)(\\D*)(\\d?)(,?)(\\d{3})(\\D*)(\\s)(\\S*)(\\s)");
        Matcher m = p.matcher(str);
        boolean b = m.matches();
        if (b) {
            int temp = 0;
            if (m.group(4).isEmpty()) {
                temp = Integer.parseInt(m.group(6));
            } else {
                temp = 1000 * (Integer.parseInt(m.group(4)));
                temp = temp + Integer.parseInt(m.group(6));
            }
            if (m.group().contains("שותף") || m.group().contains("לחדר") || m.group().contains("קטן") || m.group().contains("גדול")) {
                System.out.println("We Found: Roomate Will Pay: " + temp);
            } else if (m.group().contains("מחיר") || m.group().contains("שכירות") || m.group().contains("שכ\"ד") || m.group().contains("שכד")
                    || m.group().contains("₪") || m.group().contains("שח") || m.group().contains("ש\"ח") || m.group().contains("שקל")
                    || m.group().contains("בלבד") || m.group().contains("חודש") || m.group().contains("ש''ח") || m.group().contains("אחד")
                    || m.group().contains("שכר") || m.group().contains("כולל") || m.group().contains("דירה") || m.group().contains("ש'ח")
                    || m.group().contains("עד")) {
                if (m.group().contains("כל") || temp > 1700) {
                    System.out.println("We Suggest: For all the apartment: " + temp);
                } else if ((temp > 550) && (temp < 1700)) {
                    System.out.println("We Suggest: Roomate Will Pay: " + temp);
                }
            } else {
                //System.out.println("---WE DONT:---        "+m.group());
                return 0;
            }
            //if ((temp>550)&&(1800>temp))
            //System.out.println(m.group());
            String until = "" + m.group(1) + m.group(2) + m.group(3) + m.group(4) + m.group(5) + m.group(6);
            return until.length();
        }
        return 0;
    }

    public static int extractSH(String str) {
        //System.out.println("the whole sentence: "+ str);
        //Pattern p = Pattern.compile("\\S+\\s\\S*0\\d\\s*-?\\s*\\d{7}\\S*\\s\\S+||\\S+\\s\\S*0\\d{2}\\s*-?\\s*\\d{3}\\s*-?\\s*\\d{4}\\S*\\s\\S+");
        Pattern p = Pattern.compile("[^\\d\\s]*0[2346789]\\s*-?\\s*\\d{7}[^\\d\\s]*||[^\\d\\s]*0\\d{2}\\s*-?\\s*\\d{3}\\s*-?\\s*\\d{4}[^\\d\\s]*");
        //Pattern p = Pattern.compile("\\S*\\s\\S*\\s\\S*רו?הי?ט\\S*\\s\\S*\\s");
        Matcher m = p.matcher(str);
        boolean b = m.matches();
        if (b) {
            //System.out.println(m.group());
            System.out.println("Phone Number: "+m.group().replaceAll("[\\D]", ""));
            return m.group().replaceAll("[\\D]", "").length();
        }
        return 0;
    }

    public static int extractSHOnlyTheWord(String str) {
        //System.out.println("the whole sentence: "+ str);
        Pattern p = Pattern.compile("\\S*חד\\S*");
        Matcher m = p.matcher(str);
        boolean b = m.matches();
        if (b) {
            System.out.println(m.group());
            return m.end();
        }
        return 0;
    }

    public static int extractNeighborhood(String str) {
        Pattern p = Pattern.compile("ש?[בה]?שכונ[הת] (יא||הרכס||רבין||נוו?ה[ -]נוי||נחל[ -]בקע||רמות||[אבגדהוט]'?)][,.]?");
        Matcher m = p.matcher(str);
        boolean b = m.matches();
        if (b) {
            System.out.println("The Neighborhood is: "+m.group());
            return m.end();
        }
        return 0;
    }



    public static void tokentoken(String str) {
        Pattern p1 = Pattern.compile("(נפטצי)");
        Pattern p = Pattern.compile("(נופר)\\s?"+p1.pattern());//
        Matcher m = p.matcher(str);
        boolean b = m.matches();
        if (b){
            System.out.println(m.group());
            System.out.println(m.group(1));
            System.out.println(m.group(2));
        }
    }

    public static List<String> noDupsList(List<String> list){
        Set<String> hs = new HashSet<>();
        hs.addAll(list);
        list.clear();
        list.addAll(hs);
        return list;
    }
    public static List<String> loadStreets(){
        String fileName = "po.txt";
        List<String> streets = new ArrayList<String>();
        //List<String> streetslist= loadStreets();
        String line = null;
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            int s;
            int counter=0;

            while((line = bufferedReader.readLine()) != null) {
                //System.out.println(line);
                streets.add(line);
            }

            // Always close files.
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
    public static void readText(){
        // The name of the file to open.
        String fileName = "posts.txt";
        List<String> streets= loadStreets();
        // This will reference one line at a time
        String line = null;
        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            int s;
            int counter=0;
            List<String> shcone = new ArrayList<String>();
            while((line = bufferedReader.readLine()) != null) {
                //in the future, we won't write "line = bufferedReader.readLine()"
                //we will use bufferedReader.toString() or something like that
                while (line.length()>0) {
                    /* MAKING LIST OF THE WORD APPEAREANCE ACCORDING TO THE PATTERN
                    s=extractSHOnlyTheWord(line);
                    if (s == 0) {
                        line = line.substring(1);
                    }
                    else {
                        counter++;
                        shcone.add(line.substring(0,s));
                        line = line.substring(s);
                    */


                    //System.out.println(line);
                    //s=extractPhoneNumber(line);  //63824
                    //if (s==0) {
                    s = extractNRooms(line);  //528
                    //s=extractAddress(line,streets); //==>extractStreetNameNumber
                    //s=extractSHOnlyTheWord(line);    //help func for analyzing
                    //s=extractNeighborhood(line);   //912
                    //s= extractStreetName(line,streets);
                    //s= extractStreetNameNumber(line,streets); //8141
                    //s=extractPrices(line);
                    //s = extractSH(line);     //help func for analyzing
                    //s=extractPhoneNumber(line);
                    if (s == 0) {
                        line = line.substring(1);
                    }
                    else {
                        counter++;
                        shcone.add(line.substring(0,s));
                        line = line.substring(s);
                    }
                    //}
                    //else
                    //    line = line.substring(s);
                }
            }
            System.out.println("counter= "+ counter);
            shcone= noDupsList(shcone);
            for (int i=0;i<shcone.size();i++)
                System.out.println(shcone.get(i));
            // Always close files.
            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");
        }
        catch(IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }
    }


    public static void workOnAPost(String postText, List<String> streets) {
        if (postText != null) {
            postText=postText.replaceAll("\\n"," ");
            postText=postText.replaceAll("[,;'\"]","");
            String s;
            int num;

            String line= postText;
            /*if (extractStreetNameNumberNM(line,streets))
                extractStreetNameNM(line,streets);
            extractPrices(line);
            extractNeighborhood(line);
            extractNRooms(line);
            */
            ///*
            extractPhoneNumber(line);
            while (line.length() > 0) {
                if ((s=extractStreetNameNumber(line, streets)) == "") {
                    if ((s=extractStreetName(line, streets))=="") {
                        line = line.substring(1);
                    }else {
                        System.out.println("extractStreetName: "+s);
                        line = line.substring(s.length());
                    }
                }else {
                    System.out.println("extractStreetName+Number: "+s);
                    line = line.substring(s.length());
                }
            }
        }
    }


    @Override
    public void extractApartment(String str) {
        System.out.println("NLP Results:");
        workOnAPost(str ,loadStreets());
    }

    public static class FacebookHandler {
        public FacebookHandler() {
        }

        public static String get2WeeksAgoDate() {
            Date date = new Date();
            int noOfDays = -14;
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(6, noOfDays);
            date = calendar.getTime();
            System.out.println(date.getTime() / 1000L);
            String year = String.valueOf(calendar.get(1));
            String month = String.valueOf(calendar.get(2) + 1);
            String day = String.valueOf(calendar.get(5));
            return year + "-" + month + "-" + day;
        }
    }

    public static void printPosts(){
        String accessToken = "EAAESqCOZCzy4BANaOKhN4VeZAtYr0Ja9rAZCPEKVgsl8VFuyW0PY1yNZC5YMhiqPMRYN0VlV4WaZCIxsJz7GvrBHbbpZCIzeVDbXZBdCr2IhlMspJdAjGCZAqUnTRklPcIsDy2hD4ZBjfgL6DM5CBYTeYJS1bwC6FdOEZD";
        FacebookClient fbClient = new DefaultFacebookClient(accessToken);
        String groupId = "279135451973";
        Connection<Post> postFeed = fbClient.fetchConnection(groupId + "/feed", Post.class, new Parameter[0]);
        ConnectionIterator var5 = postFeed.iterator();

        //Handler handler = new FileHandler("test.log", LOG_SIZE, LOG_ROTATION_COUNT);
        //Logger.getLogger("").addHandler(handler);
        //Files.write(Paths.get("fileName"), StandardOpenOption.CREATE);
        while(var5.hasNext()) {
            List<Post> postPage = (List)var5.next();
            Iterator var7 = postPage.iterator();
            while(var7.hasNext()) {
                System.out.println("********************** HERE COMES A POST **********************");
                Post aPost = (Post)var7.next();
                System.out.println(aPost.getMessage());
                workOnAPost(aPost.getMessage(),loadStreets());
            }
        }
    }


    public static void main(String[] args) {
        //printPosts();
        readText();
        /*
        List<String> streets= loadStreets();
        Scanner input;
        String st;
        for(int i=0; i<10; i++) {
            System.out.println("input:");
            input= new Scanner(System.in);
            String curr= input.nextLine();
            if (streets.contains(curr)==true)
                System.out.println(curr);
        }
*/
 /*
        Scanner input;
        String st;
        for(int i=0; i<10; i++) {
            input= new Scanner(System.in);
            st=input.nextLine();
            //extractAddress(st);
            //extractPhoneNumber(st);
            //extractNRooms(st);
            tokentoken(st);
        }
*/

        //workOnAPost Function
        /*
        Scanner input;
        String st;
        for(int i=0; i<10; i++) {
            input= new Scanner(System.in);
            st=input.nextLine();
            System.out.println(st);
            workOnAPost(st,loadStreets());
            //extractAddress(st);
            //extractPhoneNumber(st);
            //extractNRooms(st);
            //tokentoken(st);
        }
        */
        System.out.println("Hello World!");
        //System.out.println("משהו בעברית");
    }
}
