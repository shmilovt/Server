package il.ac.bgu.finalproject.server.Domain.NLPHandlers;
import java.util.LinkedList;

public class EnvList extends LinkedList<Environment> {

    public EnvList(String post)
    {
        String[] splitedPost = post.split("[\\n,.]");
        int offset=0;
        int index=0;
        for(String str: splitedPost)
        {
            if(str.compareTo("")!=0) {
                this.add(new Environment(str, offset,index));
                offset = offset + str.split("\\s").length;
                index++;
            }
        }
    }

    public Environment ExpandToRight(Environment env, int skipAmount)
    {
        if(env.getIndex()-skipAmount>=0) {
            String str = "";
            for (int i = skipAmount; i >= 0; i--)
                str = str + this.get(env.getIndex() - i).getEnvString();
            return new Environment(str, this.get(env.getIndex() - skipAmount).getOffset(), -1);
        }
        return null;
    }

    public Environment ExpandToLeft(Environment env, int skipAmount)
    {
        if(env.getIndex()+skipAmount<=this.size()) {
            String str = "";
            for (int i = 0; i <= skipAmount; i++)
                str = str + this.get(env.getIndex() + i).getEnvString();
            return new Environment(str, env.getOffset(), -1);
        }
        return null;
    }


}
