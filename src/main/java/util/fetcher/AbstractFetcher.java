package util.fetcher;


import org.jsoup.Connection;
import org.jsoup.Jsoup;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public abstract class AbstractFetcher<T> implements Fetcher {
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

    //current page index, counting from 1
    protected int pageIndex = 1;

    //total page number, for maximum page crawling
    private int totalPage = 10;

    //interval
    private long interval = 1000;

    public AbstractFetcher(int totalPage, long interval){
        this.totalPage = totalPage;
        this.interval = interval;
    }

    //whether this website has next page
    public boolean hasNextPage() {
        return pageIndex <= totalPage;
    }

    public String nextPage() {
        String html = "";
        String url = pageUrl();
        pageIndex++;
        System.out.println("fetching page: " + url);
        Connection connection;
        try {
            connection = Jsoup.connect(url).timeout(4000).followRedirects(true);
            for (String[] head : HEADERS) {
                connection.header(head[0], head[1]);
            }
            html = connection.execute().parse().html();//execute
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("fetch page error: " + e.getMessage());
        }

        return html;
    }

    public List<T> fetchAll() {
        List<T> res = new ArrayList<>();
        while (hasNextPage()) {
            String html = nextPage();
            res.add(parseHtml(html));
            try {
                Thread.sleep(interval);
            }catch (InterruptedException e){}
        }

        return res;
    }

    public void fetchAll(Consumer<T> comsumer) {
        while (hasNextPage()) {
            String html = nextPage();
            comsumer.consume(parseHtml(html));
            try {
                Thread.sleep(interval);
            }catch (InterruptedException e){}
        }
    }


    public int getPageIndex() {return pageIndex;}
    public int getTotalPage() {return totalPage;}
    public long getInterval() {return interval;}

    protected abstract String pageUrl();
    protected abstract T parseHtml(String html);

    public interface Consumer<T>{
        void consume(T t);
    }
}
