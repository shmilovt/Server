package il.ac.bgu.finalproject.server.Domain.NLPHandlers;

public class Word {
    private String sourceValue;
    private String dictionaryValue;
    private int index;

    public Word(String val,String dicVal,int i)
    {
        sourceValue=val;
        dictionaryValue=dicVal;
        index=i;
    }

    public String getSourceValue() {
        return sourceValue;
    }

    public void setSourceValue(String sourceValue) {
        this.sourceValue = sourceValue;
    }

    public String getDictionaryValue() {
        return dictionaryValue;
    }

    public void setDictionaryValue(String dictionaryValue) {
        this.dictionaryValue = dictionaryValue;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
