package il.ac.bgu.finalproject.server.Domain.DomainObjects.UserSearchingUtils;

public class NeighborhoodQuery extends CategoryQuery {
    private String neighborhood;

    public NeighborhoodQuery(String neighborhood){
        this.neighborhood= neighborhood;
    }

    @Override
    public boolean mainQuery(ResultRecord apartment){
        String neighborhood= apartment.getNeighborhood();
        return neighborhood.equals(this.neighborhood);
    }
}
