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
    public DetailFlightBean parseHtml(String html){
        //Logger
        org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger("HtmlParserUtil.class");
        BasicConfigurator.configure();

        //get the certain flight's page
        Document doc = Jsoup.parse(html);

        /**
         * ================================================================================
         */
        Element _fly_list = doc.selectFirst("[class=f_content]");
        if(_fly_list != null)
            logger.info("[Found]: fly_list");
        else   //don't contains detail information
            return null;

        Element _detail_main = _fly_list.selectFirst("[class=detail_main]");
        if(_detail_main != null)
            logger.info("[Found]: detail_main");
        else
            return null;

        Element _tit = _detail_main.selectFirst("[class=tit]");
        if(_tit != null)
            logger.info("[Found]: tit");
        else
            ;
        /**
         * ================================================================================
         */

        return null;
    }
}
