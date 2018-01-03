package il.ac.bgu.finalproject.server.Domain.NLPHandlers;

import java.util.*;

public class AnalyzedDS {
    private Dictionary<Classify,Dictionary<Integer,Set<String>>> g;
    private EnvList envLst;

    public AnalyzedDS(Dictionary<Classify, Dictionary<Integer, Set<String>>> g , EnvList env) {
        this.g = g;
        this.envLst=env;
    }

    public AnalyzedDS(EnvList envL)
    {
        this.envLst=envL;
        int size = envL.size();
        this.g = new Hashtable<Classify,Dictionary<Integer,Set<String>>>();
        Classify[] classifyCategories = Classify.values();
        for(Classify c: classifyCategories)
            g.put(c, new Hashtable<Integer,Set<String>>());
        for(Classify c: classifyCategories)
            for(int i=0;i<size;i++)
                g.get(c).put(i,new HashSet<String>());
        Analyzer analyzer = new Analyzer(this);
        analyzer.analyze();
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

    // get classify and return all the envs(probably will be ints) that included.
    public List<Integer> GetEnvsIndex(Classify classify)
    {
        List<Integer> indexLst = new LinkedList<Integer>();
        int c = g.get(classify).size();
        for(int i=0;i<c;i++)
            if(!g.get(classify).get(i).isEmpty())
                indexLst.add(i);
        return indexLst;
    }

    public Set<String> GetResultsByClassify(Classify classify)
    {
        Set<String> aSet = new TreeSet<String>();
        int c = g.get(classify).size();
        for(int i=0;i<c;i++)
            if(!g.get(classify).get(i).isEmpty())
                aSet.addAll(g.get(classify).get(i));
        return aSet;
    }

    public EnvList getEnvLst() {
        return envLst;
    }

    public void setEnvLst(EnvList envLst) {
        this.envLst = envLst;
    }

}