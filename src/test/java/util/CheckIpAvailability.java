package util;

import org.jsoup.Jsoup;

public class CheckIpAvailability {
    public static void main(String[] args) {
        try {
            //http://1212.ip138.com/ic.asp 可以换成任何比较快的网页
            Jsoup.connect("www.baidu.com")
                    .timeout(5 * 1000)
                    .proxy("192.168.17.1", 8080)
                    .get();
            System.out.println("IP is available!");
        } catch (Exception e) {
            System.out.println("IP is not available!");
        }
    }
}
