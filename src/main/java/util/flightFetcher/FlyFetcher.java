package util.flightFetcher;

import java.util.List;

public interface FlyFetcher<All, Certain> {
    /**
     * fetch certain flight page
     * like "http://www.variflight.com/flight/fnum/3K692.html?AE71649A58c77="
     */
    //List<Certain> fetchCertainPage(String certainFlight);

    /**
     * fetch the page that contains all flights' simple info
     * like "http://www.variflight.com/sitemap.html?AE71649A58c77="
     */
    //List<All> fetchAllPage();
}
