package boot;

import bean.DetailFlightBean;
import util.IpFetcher.KuaiDailiFetcher;
import util.ProxyUtil;
import util.SQLServerUtil;
import util.flightFetcher.VariFlightFetcher;

import java.util.HashMap;
import java.util.List;

public class VariFlightBoot {
    public static void main(String[] args){
        //flush proxy pool and database
        KuaiDailiFetcher fetcher = new KuaiDailiFetcher(50);
        SQLServerUtil util = new SQLServerUtil();
        try{
            util.clearTableWithTableName("VariFlight", "CrawledIp");
        }catch (Exception e){
            System.out.println("[SQL Server]: " + e.getMessage());
        }
        util.insertIpIntoTable2(fetcher.fetchAll());
        ProxyUtil proxyUtil = new ProxyUtil();
        try{
            proxyUtil.autoFlushTable_CrawledIp();
        }catch (Exception e){
            System.out.println("[ProxyUtil]: All unusable proxy have been removed.");
        }

        //get all flight information, load into database
        //VariFlightFetcher variFlightFetcher = new VariFlightFetcher();
        //HashMap<String, List<DetailFlightBean>> detailFlightBeans = variFlightFetcher
                //.fetchCertainPage(variFlightFetcher.fetchAllPage());
    }
}
