package fetcher;

import bean.DetailFlightBean;
import bean.SimpleFlightBean;
import util.flightFetcher.VariFlightFetcher;

import java.util.HashMap;
import java.util.List;

public class AllFlights {
    public static void main(String[] args){
        VariFlightFetcher variFlightFetcher = new VariFlightFetcher();
        HashMap<String, List<DetailFlightBean>> detailFlightBeans = variFlightFetcher
                .fetchCertainPage(variFlightFetcher.fetchAllPage());

    }
}
