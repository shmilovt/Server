package il.ac.bgu.finalproject.server.Domain.DomainObjects.UserSearchingUtils;

import javax.naming.directory.SearchResult;
import java.util.List;

public class SearchResults {
    private List<ResultRecord> resultRecordList;

    public SearchResults() {
    }
    public SearchResults(List<ResultRecord> resultRecordList)
    {
        this.resultRecordList = resultRecordList;
    }
    public List<ResultRecord> getResultRecordList() {
        return resultRecordList;
    }

    public void setResultRecordList(List<ResultRecord> resultRecordList) {
        this.resultRecordList = resultRecordList;
    }
}
