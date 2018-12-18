package util;

import annotation.Note;
import bean.DetailFlightBean;
import org.apache.log4j.BasicConfigurator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;

public class HtmlParserUtil {

    /**
     * parse html text with given String
     * @param html
     * @return
     */
    public List<DetailFlightBean> parseHtml(String html){
        org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger("HtmlParserUtil.class");
        BasicConfigurator.configure();

        //get the certain flight's page
        Document doc = Jsoup.parse(html);

        /**
         * ================================================================================
         */
        Elements _fly_list = doc.select("[class=fly_list]");
        logger.info("[Size of fly_list]: " + _fly_list.size());
        /**
         * ================================================================================
         */

        return null;
    }
}
