package bootstrap;

import constants.Constants;
import raw.IpBean;
import util.AvaliableIpUtil;
import util.SQLServerUtil;

import java.util.List;

public class ObtainIp {
    public static void main(String[] agrs) {
        AvaliableIpUtil avaliableIpUtil = new AvaliableIpUtil();
        SQLServerUtil sqlServerUtil = new SQLServerUtil();

        try {
            avaliableIpUtil.getJsonFromUrl(Constants.free_ip_pool);
            List<IpBean> free_ip_pool = avaliableIpUtil.get_ipList();

            sqlServerUtil.insertIpIntoDb(free_ip_pool);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
