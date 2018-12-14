package bootstrap;

import constants.Constants;
import raw.IpBean;
import util.fetcher.ProxyListFetcher;
import util.SQLServerUtil;

import java.util.List;

public class ObtainIp {
    public static void main(String[] agrs) {
        ProxyListFetcher proxyListFetcher = new ProxyListFetcher();
        SQLServerUtil sqlServerUtil = new SQLServerUtil();

        try {
            proxyListFetcher.getJsonFromUrl(Constants.free_ip_pool);
            List<IpBean> free_ip_pool = proxyListFetcher.get_ipList();

            sqlServerUtil.insertIpIntoDb(free_ip_pool);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
