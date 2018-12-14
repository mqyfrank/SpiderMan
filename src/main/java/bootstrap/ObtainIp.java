package bootstrap;

import entity.ProxyEntity;
import util.fetcher.Www66IPFetcher;

import java.util.ArrayList;
import java.util.List;

public class ObtainIp {
    public static void main(String[] agrs) {
        Www66IPFetcher www66IPFetcher = new Www66IPFetcher(10);

        List<? extends Object> list = new ArrayList<>();
        list = www66IPFetcher.fetchAll();
    }
}
