package util.fetcher;

import entity.ProxyEntity;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This fetcher's website url is "www.66ip.cn", but whatever I try,
 * It constantly show me the [ERROR 521]:{ HTTP error fetching URL}
 */
@Deprecated
public class Www66IPFetcher extends AbstractFetcher<List<ProxyEntity>> {

    private static final String BASE_URL = "http://www.66ip.cn";

    private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy年MM月dd日HH时 验证");

    public Www66IPFetcher() {
        this(100);
    }

    public Www66IPFetcher(int totalPage) {
        this(totalPage, 1000);
    }

    public Www66IPFetcher(int totalPage, long interval) {
        super(totalPage, interval);
    }

    protected String pageUrl() {
        String url;
        if (pageIndex == 1)
            url = BASE_URL + "/1.html";
        else
            url = BASE_URL + "/" + pageIndex + ".html";

        return url;
    }

    protected List<ProxyEntity> parseHtml(String html) {

        List<ProxyEntity> res = new ArrayList<>();
        Document doc = Jsoup.parse(html);
        Elements tables = doc.select("tbody");
        if(tables == null)
            return res;
        for (Element table : tables) {
            Elements trs = table.select("tr");
            if(trs == null)continue;
            for (int i = 1; i < trs.size(); i++) {
                Element tr = trs.get(i);
                Elements tds = tr.select("td");
                if (tds.size() != 5) continue;

                /**
                 * <td>14.211.126.99</td>
                 * <td>9797</td>
                 * <td>广东省佛山市</td>
                 * <td>高匿代理</td>
                 * <td>2017年06月29日00时 验证</td>
                 */
                ProxyEntity enity = new ProxyEntity();
                enity.setIp(tds.get(0).text().trim());
                enity.setPort(Integer.parseInt(tds.get(1).text()));
                enity.setLocation(tds.get(2).text().trim());
                enity.setAgentType(tds.get(3).text().trim());

                Date date = new Date();
                try {
                    date = SDF.parse(tds.get(4).text().trim());
                } catch (ParseException e) {
                }

                enity.setLastValidateTime(date);

                System.out.println("got an agent: " + enity);

                res.add(enity);
            }
        }
        return res;
    }
}
