package bootstrap;

import entity.ProxyEntity;
import util.fetcher.GoubanjiaFetcher;
import util.fetcher.KuaiDailiFetcher;
import util.fetcher.Www66IPFetcher;
import java.util.ArrayList;
import java.util.List;

public class ObtainIp {
    public static void main(String[] args) {
        //Www66IPFetcher www66IPFetcher = new Www66IPFetcher(10);
        //GoubanjiaFetcher goubanjiaFetcher = new GoubanjiaFetcher(10);
        KuaiDailiFetcher kuaiDailiFetcher = new KuaiDailiFetcher(10);
        List<List<ProxyEntity>> list = new ArrayList<>();
        //list = www66IPFetcher.fetchAll();
        //list = goubanjiaFetcher.fetchAll();
        list = kuaiDailiFetcher.fetchAll();
    }
}
