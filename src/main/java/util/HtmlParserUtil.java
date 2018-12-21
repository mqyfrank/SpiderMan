package util;

import annotation.Array;
import annotation.Note;
import bean.DetailFlightBean;
import org.apache.log4j.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class HtmlParserUtil {
    @Note //certain flight's conclusion, such as "12月19号周三 共3次航班"
    String flightConclusion;


    /**
     * parse html text with given String
     * @param html
     * @return
     */
    public List<DetailFlightBean> parseHtmlOfCertainFlight(String html){
        @Note
        String flightCode = "尚无信息";
        @Note
        String flightCompany = "尚无信息";
        @Note
        String planDeparture = "尚无信息";
        @Note
        String planArrive = "尚无信息";
        @Note
        String departure = "尚无信息";
        @Note
        String arrival = "尚无信息";
        @Note
        String actualDeparture = "尚无信息";
        @Note
        String actualArrive = "尚无信息";
        @Note
        String status = "尚无信息";
        @Array
        List<DetailFlightBean> list = new ArrayList<>();

        //Logger
        org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger("HtmlParserUtil.class");
        BasicConfigurator.configure();

        //get the certain flight's page
        Document doc = Jsoup.parse(html);

        Element _f_content = doc.selectFirst("[class=fly_list]");
        if(_f_content != null){
            flightConclusion = _f_content.selectFirst("[class=tit]").select("h1 > span").text();
            //flightName = _f_content.selectFirst("[class=tit]").selectFirst("h1").attr("title");
            if(flightConclusion.trim().length() == 0){
                flightConclusion = "暂无信息";
            }
            Elements _relative = _f_content.select("div.fly_list > div#li_box.li_box > ul#list > li");
            if(_relative.size() != 0 ){
                for(Element _each_flight : _relative){
                    Elements _company = _each_flight.select("div.li_com > span.w260 > b > a");
                    if(_company != null){
                        flightCompany = _company.first().text();
                        flightCode = _company.last().text();
                    }else{
                        flightCompany = "暂无信息";
                        flightCode = "暂无信息";
                    }

                    Elements _timeAndPlace = _each_flight.select("div.li_com > span.w150");
                    if(_timeAndPlace.size() != 0){
                        planDeparture = _timeAndPlace.get(0).text();
                        actualDeparture = _timeAndPlace.get(1).text();
                        departure = _timeAndPlace.get(2).text();
                        planArrive = _timeAndPlace.get(3).text();
                        actualArrive = _timeAndPlace.get(4).text();
                        arrival = _timeAndPlace.get(5).text();
                        status = _timeAndPlace.get(7).text();

                        //standard format
                        if(planDeparture.trim().length() == 0)
                            planDeparture = "--";
                        if(planArrive.trim().length() == 0)
                            planArrive = "--";
                        if(actualArrive.trim().length() == 0)
                            actualArrive = "--";
                        if(actualDeparture.trim().length() == 0)
                            actualDeparture = "--";
                    }
                    System.out.println(" [航班]: " + flightCode +
                            " [航空公司]: " + flightCompany +
                            " [计划起飞]: " + planDeparture +
                            " [实际起飞]: " + actualDeparture +
                            " [起飞地点]: " + departure +
                            " [计划到达]: " + planArrive +
                            " [实际到达]: " + actualArrive +
                            " [到达地点]: " + arrival +
                            " [状态]: " + status);
                    list.add(new DetailFlightBean(flightCompany, flightCode, planDeparture, actualDeparture,
                            planArrive, actualArrive, departure, arrival, status));
                }
            }
        }
        return list;
    }

    public String getFlightConclusion(){ return this.flightConclusion; }
}
