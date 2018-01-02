package il.ac.bgu.finalproject.server.Domain.NLPHandlers;

import java.util.Dictionary;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

public class AnalyzedDS {
    Dictionary<Classify,Dictionary<Integer,Set<String>>> g;

    public AnalyzedDS(Dictionary<Classify, Dictionary<Integer, Set<String>>> g) {
        this.g = g;
    }

    public AnalyzedDS(int numOfEnv)
    {
        this.g = new Hashtable<Classify,Dictionary<Integer,Set<String>>>();
        g.put(Classify.FURNITURE, new Hashtable<Integer,Set<String>>());
        g.put(Classify.NAME, new Hashtable<Integer,Set<String>>());
        g.put(Classify.NEIGHBORHOOD, new Hashtable<Integer,Set<String>>());
        g.put(Classify.NUMERIC, new Hashtable<Integer,Set<String>>());
        g.put(Classify.PREPOSITION, new Hashtable<Integer,Set<String>>());
        g.put(Classify.STREET, new Hashtable<Integer,Set<String>>());
        for(int i=0;i<numOfEnv;i++)
        {
            g.get(Classify.FURNITURE).put(i,new HashSet<String>());
            g.get(Classify.NAME).put(i,new HashSet<String>());
            g.get(Classify.NEIGHBORHOOD).put(i,new HashSet<String>());
            g.get(Classify.NUMERIC).put(i,new HashSet<String>());
            g.get(Classify.PREPOSITION).put(i,new HashSet<String>());
            g.get(Classify.STREET).put(i,new HashSet<String>());
        }
    }

    public void Insert(Classify classify,int envNum,String value)
    {
        g.get(classify).get(envNum).add(value);
    }

    public void Remove(Classify classify,int envNum,String value)
    {
        g.get(classify).get(envNum).remove(value);
    }

    public String toString(Classify classify , int envNum)
    {
        return "(" + classify.name() + "," + String.valueOf(envNum) + ")"
                + " ---> " + g.get(classify).get(envNum).toString();
    }

    public String toString(Classify classify)
    {
        int size = g.get(classify).size();
        String str="";
        for(int i=0;i<size;i++)
            str=str + toString(classify,i) + "\n";
        return str;
    }

}
