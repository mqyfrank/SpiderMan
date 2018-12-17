package util.flightFetcher;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import util.Constants;

import java.io.IOException;
import java.util.List;


public abstract class AbstractFlyFetcher<All, Certain> implements FlyFetcher{

    //for HTTP headers
    private static final String[][] HEADERS = new String[][]{
            {"Connection", "keep-alive"},
            {"Cache-Control", "max-age=0"},
            {"Upgrade-Insecure-Requests", "1"},
            {"User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) " +
                    "Chrome/70.0.3538.110 Safari/537.36"},
            {"Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8"},
            {"Accept-Encoding", "gzip, deflate"},
            {"Accept-Language", "zh,zh-TW;q=0.9,en-US;q=0.8,en;q=0.7"},
    };

    /**
     * Get a page's text
     * @param _url page url
     * @return
     */
    public String getPage(String _url){
        String html;
        String allFlightUrl = _url;
        System.out.println("@localhost: fetching url: " + allFlightUrl);
        Connection connection;
        try {
            connection = Jsoup.connect(allFlightUrl).timeout(4000).followRedirects(true);
            for(String[] header : HEADERS){
                connection.header(header[0], header[1]);
            }
            //get html text
            html = connection.execute().parse().html();
        }catch (IOException e){
            System.out.println("@localhost: [fatal error occurs in \"getPage()\": " + e.getMessage());
            return null;
        }
        return html;
    }


    public abstract List<All> fetchAllPage();
    public abstract List<Certain> fetchCertainPage(List<All> allFlight);
}