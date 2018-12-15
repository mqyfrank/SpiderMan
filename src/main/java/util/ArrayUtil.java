package util;

import entity.ProxyEntity;

import java.util.List;

public class ArrayUtil {

    public static void showListContent(List<List<ProxyEntity>> _proxy){
        for(int i = 0; i < _proxy.size(); i++)
            for(int j = 0; j < _proxy.get(i).size(); j++){
                System.out.println("==========================================");
                System.out.println("[Host]: " + _proxy.get(i).get(j).getIp());
                System.out.println("[Port]: " + _proxy.get(i).get(j).getPort());
                System.out.println("[Location]: " + _proxy.get(i).get(j).getLocation());
                System.out.println("[Agent Type]: " + _proxy.get(i).get(j).getAgentType());
                System.out.println("[Last Validated Time]: " + _proxy.get(i).get(j).getLastValidateTime().toGMTString());
                System.out.println("==========================================");
            }
    }
}
