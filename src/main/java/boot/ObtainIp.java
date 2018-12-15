package boot;

import util.SQLServerUtil;
import util.fetcher.KuaiDailiFetcher;

public class ObtainIp {
    public static void main(String[] args) {
        //Www66IPFetcher www66IPFetcher = new Www66IPFetcher(10);
        //GoubanjiaFetcher goubanjiaFetcher = new GoubanjiaFetcher(10);
        KuaiDailiFetcher kuaiDailiFetcher = new KuaiDailiFetcher(100);
        //List<List<ProxyEntity>> list = new ArrayList<>();
        //list = www66IPFetcher.fetchAll();
        //list = goubanjiaFetcher.fetchAll();
        SQLServerUtil util = new SQLServerUtil();
        try {
            util.clearTableWithTableName("VariFlight", "CrawledIp");
        }catch (Exception e){
            e.getMessage();
        }
        //ArrayUtil.showListContent(kuaiDailiFetcher.fetchAll());
        util.insertIpIntoTable2(kuaiDailiFetcher.fetchAll());
    }
}
