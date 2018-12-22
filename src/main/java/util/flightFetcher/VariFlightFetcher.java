package util.flightFetcher;

import bean.DetailFlightBean;
import bean.SimpleFlightBean;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import util.Constants;
import util.HtmlParserUtil;
import util.SQLServerUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class VariFlightFetcher extends AbstractFlyFetcher<SimpleFlightBean, DetailFlightBean, List<DetailFlightBean>> {

    /**
     * Fetch all flights' simple information from main web page
     * @return List<SimpleFlightBean> 036925
     */
    @Override
    public List<SimpleFlightBean> fetchAllPage() {
        //base url
        String url = Constants.flight_number_info;
        //String html = getPage(url);
        String html;
        while ((html = getPage(url)).equals("error"))
            ;
        /**
         * <div class="list">
         *     <strong style="font-size:14px">2018-12-17航班列表</strong>&nbsp;&nbsp;&nbsp;&nbsp;
         *     <a href="/sitemap/flight?AE71649A58c77=">国内航段列表</a>
         *     <br>
         *     <br>
         *     <a href="/flight/fnum/036925.html?AE71649A58c77=">036925</a>
         *     <a href="/flight/fnum/036926.html?AE71649A58c77=">036926</a>
         *     <a href="/flight/fnum/3K691.html?AE71649A58c77=">3K691</a>
         *     <a href="/flight/fnum/3K692.html?AE71649A58c77=">3K692</a>
         *     <a href="/flight/fnum/3K695.html?AE71649A58c77=">3K695</a>
         *     <a href="/flight/fnum/3K696.html?AE71649A58c77=">3K696</a>
         *     <a href="/flight/fnum/3K697.html?AE71649A58c77=">3K697</a>
         *     <a href="/flight/fnum/3K698.html?AE71649A58c77=">3K698</a>
         *     <a href="/flight/fnum/3K721.html?AE71649A58c77=">3K721</a>
         * </div>
         */
        List<SimpleFlightBean> list = new ArrayList<>();
        Document doc = Jsoup.parse(html);
        Elements elements = doc.select("[class=list]");
        if(elements == null)
            return list;

        for(Element node : elements){
            Elements links = node.select("a");
            if(links == null) //current node don not have <href>
                continue;
            for(int i = 1; i < links.size(); i++){
                //because the first href isn't a correct flight href
                Element flightNode = links.get(i);
                String linkAttr = flightNode.attr("href");
                String linkText = flightNode.text();
                list.add(new SimpleFlightBean(linkText, linkAttr));
            }
        }
        return list;
    }

    /**
     * Using certain flight's name to fetch certain information,
     * for each page, changing system proxy once, all proxys
     * were store in database"VariFlight" , in table "CrawledIp"
     * @param allFlight
     * @return
     */
    @Override
    public HashMap<String, List<DetailFlightBean>> fetchCertainPage(List<SimpleFlightBean> allFlight) {
        HtmlParserUtil htmlParserUtil = new HtmlParserUtil();
        HashMap<String, List<DetailFlightBean>> detailFlightBeans = new HashMap<>();
        SQLServerUtil sqlServerUtil = new SQLServerUtil();

        for(SimpleFlightBean bean : allFlight) {
            //obtain base url, like "http://www.variflight.com/flight/fnum/AA8893.html?AE71649A58c77="
            String certainUrl = Constants.FLIGHT_BASE + bean.getLink();
            String html;
            while((html = this.getPage(certainUrl)).equals("error")); //get whole page

            List<DetailFlightBean> detailFlightBeanList = htmlParserUtil.parseHtmlOfCertainFlight(html);
            try {
                sqlServerUtil.insertDetailFlightBean(detailFlightBeanList);
            }catch (Exception e){
                System.out.println("[SQL Server]: " + e.getMessage());
            }
            detailFlightBeans.put(bean.getFlight(), detailFlightBeanList);
        }
        return detailFlightBeans;
    }
}
