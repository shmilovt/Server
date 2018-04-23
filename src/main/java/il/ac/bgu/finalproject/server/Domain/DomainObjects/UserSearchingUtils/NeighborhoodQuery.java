package il.ac.bgu.finalproject.server.Domain.DomainObjects.UserSearchingUtils;

import il.ac.bgu.finalproject.server.Domain.DomainObjects.ApartmentUtils.Apartment;

public class NeighborhoodQuery extends CategoryQuery {
    private String neighborhood;

    public NeighborhoodQuery(String neighborhood){
        this.neighborhood= neighborhood;
    }

    public boolean mainQuery(Apartment apartment){
        String neighborhood= apartment.getApartmentLocation().getNeighborhood();
        return neighborhood.equals(this.neighborhood);
    }
}
