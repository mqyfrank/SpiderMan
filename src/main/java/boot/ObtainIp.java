package boot;

import util.ProxyUtil;
import util.SQLServerUtil;
import util.IpFetcher.KuaiDailiFetcher;

public class ObtainIp {
    public static void main(String[] args) {
        KuaiDailiFetcher kuaiDailiFetcher = new KuaiDailiFetcher(10);
        SQLServerUtil util = new SQLServerUtil();
        try {
            util.clearTableWithTableName("VariFlight", "CrawledIp");
        }catch (Exception e){
            e.getMessage();
        }

        util.insertIpIntoTable2(kuaiDailiFetcher.fetchAll());
        ProxyUtil proxyUtil = new ProxyUtil();
        try {
            proxyUtil.autoFlushTable_CrawledIp();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
