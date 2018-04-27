package il.ac.bgu.finalproject.server.Domain.NLPHandlers;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EnvList extends LinkedList<Environment> {

    private String cleanPricesComma(String s)
    {
        Pattern p = Pattern.compile("(\\d)+(,)(\\d)+");
        Matcher m = p.matcher(s);
        String temp;
        while(m.find()) {
            temp = s.substring(m.start(), m.end());
            s = s.replaceAll(temp, temp.replaceAll(",",""));
        }
        return s;
    }

    // if the post doesnt end with delimeter it adds one.
    private String checkIfEndWithDelimeter(String s)
    {
        String end = s.substring(s.length());
        if(end.equals(",") || end.equals(".") || end.equals("\n"))
            return s;
        else
            return s + ".";
    }

    private String convertNumberDotToComma(String s)
    {
        int index = s.indexOf('.');
        while (index >= 0) {
          /*  int l = s.length();
            int x = index-1;
            int y=index+1;
            boolean t = Character.isDigit(s.charAt(index-1));
            boolean z = Character.isDigit(s.charAt(index+1));*/
            if((index-1>=0 && Character.isDigit(s.charAt(index-1))) && (index+1<s.length() && Character.isDigit(s.charAt(index+1))))
                s = s.substring(0,index) + "|" + s.substring(index+1);
            index = s.indexOf(s, index + 1);
        }
        return s;
    }

    private boolean isContain(char[] charArray,char ch){
        boolean contains = false;
        for (char c : charArray)
            if (c == ch)
                return true;
        return false;
        }

    private String makeSpaceBetweencharAndDigit(String s)
    {
        char[] sym={',',':','.','!',' ','-','/','\\'};
        for(int i=0;i<s.length();i++)
        {

            if(Character.isDigit(s.charAt(i)))
            {
//                char x= s.charAt(i+1);
  //              boolean b = Character.isDigit(x);
                if(i+1<s.length() && !Character.isDigit(s.charAt(i+1)) && !isContain(sym,s.charAt(i+1)))
                    s=s.substring(0,i+1) + " " + s.substring(i+1);
            /*    if(i-1>0 && !Character.isDigit(s.charAt(i-1)) && !Arrays.asList(sym).contains(s.charAt(i-1))) {
                    s = s.substring(0, i) + " " + s.substring(i);
                }*/
            }
        }
        return s;
    }

    public EnvList(Environment env) {
    this.add(env);
    }
    public EnvList(String post)
    {
        post = makeSpaceBetweencharAndDigit(post);
        post = checkIfEndWithDelimeter(post);
        post = cleanPricesComma(post);
        post = convertNumberDotToComma(post);
        String[] splitedPost = post.split("[\\n,.]");
        int offset=0;
        int index=0;
        char a;
        for(String str: splitedPost)
        {
            if(str.compareTo("")!=0)
            {
                if(str.length()<post.length()) {
                    this.add(new Environment(str, offset, index, post.charAt(offset + str.length())));
                    offset = offset + str.length();
                    if (post.length() > offset + 1)
                        offset++;
                    index++;
                }
                else
                    this.add(new Environment(str, offset, index,'.'));
            }
            else
                offset++;
        }
    }

    public Environment ExpandToRight(Environment env, int skipAmount)
    {
        if(env.getIndex()-skipAmount>=0) {
            String str = "";
            for (int i = skipAmount; i >= 0; i--)
                str = str + this.get(env.getIndex() - i).getEnvString();
            return new Environment(str, this.get(env.getIndex() - skipAmount).getOffset(), -1,env.getDelimeter());
        }
        return null;
    }

    public Environment ExpandToLeft(Environment env, int skipAmount)
    {
        if(env.getIndex()+skipAmount<=this.size()) {
            String str = "";
            for (int i = 0; i <= skipAmount; i++)
                str = str + this.get(env.getIndex() + i).getEnvString();
            return new Environment(str, env.getOffset(), -1,this.get(env.getIndex() + skipAmount).getDelimeter());
        }
        return null;
    }
}