package il.ac.bgu.finalproject.server.Domain.NLPHandlers;

import ch.qos.logback.core.net.SyslogOutputStream;
import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentDetails.ApartmentDetails;

import java.util.*;

public class NLPImp implements NLPInterface {

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


    @Override
    public ApartmentDetails extractApartment(String str) {
        return null;
    }

    public static void main(String[] args) {
        String s = "***הרחוב השקט ביותר בב\"ש***\nשרעבי 21, שכונה ג',קרוב לרכבת צפון, פארק ההייטק, 12 דקות לאוניברסיטה דירת 4 חדרים, 3 סוויטות מפוארות ומרוהטות לחלוטין, ממוזגת, 80 מ\"ר, קומה 3,\nשכ\"ד 1100 ש\"ח לשותף, 3300 ₪ לכל הדירה.\nלפרטים נוספים בפרטי או בוואטסאפ ל- 052-4848-414\n";
        EnvList l = new EnvList(s);
        /*for(Environment env: l) {
            System.out.println(env.getEnvString());
            System.out.println(env.getDelimeter());
        }
        /*Set set = new HashSet();
        String a1 = "hello";
        String a2 = "hello";
        set.add(a1);
        set.add(2);
        set.add(a2);*/
        //AnalyzedDS an= new AnalyzedDS(l.size());
        //an.Insert(Classify.FURNITURE,3,"ספא");
        //an.Insert(Classify.FURNITURE,3,"מזגן");

        //System.out.println(an.toString());
        //an.Remove(Classify.FURNITURE,3,"D");
        //System.out.println(an.toString(Classify.FURNITURE));
        /*Dictionary<Classify,Dictionary<Integer,Set<String>>> g= new Hashtable<Classify,Dictionary<Integer,Set<String>>>();
        g.put(Classify.FURNITURE, new Hashtable<Integer,Set<String>>());
        g.put(Classify.NAME, new Hashtable<Integer,Set<String>>());

        g.get(Classify.FURNITURE).put(1,new HashSet<String>());
        g.get(Classify.FURNITURE).put(2,new HashSet<String>());
        g.get(Classify.NAME).put(2,new HashSet<String>());

        g.get(Classify.FURNITURE).get(1).add("a");
        g.get(Classify.FURNITURE).get(2).add("b");
        g.get(Classify.FURNITURE).get(2).add("c");
        g.get(Classify.NAME).get(2).add("shavit");

        g.get(Classify.FURNITURE).get(1).toString();
        System.out.println(g.toString());*/
        //System.out.println(set.toString());
      // for(int i=0;i<23;i++)
          //  System.out.println(s.charAt(31));//minDistance("יהודה הלוי","הלוי"));
    }
}
