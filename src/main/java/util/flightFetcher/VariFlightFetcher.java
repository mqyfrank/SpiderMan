package util.flightFetcher;

import bean.DetailFlightBean;
import bean.SimpleFlightBean;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import util.Constants;
import java.util.ArrayList;
import java.util.List;

public class VariFlightFetcher extends AbstractFlyFetcher<SimpleFlightBean, DetailFlightBean> {

    /**
     * Fetch all flights' simple information from main web page
     * @return List<SimpleFlightBean> 036925
     */
    @Override
    public List<SimpleFlightBean> fetchAllPage() {
        //base url
        String url = Constants.flight_number_info;
        String html = getPage(url);
        /**
         * <div class="list">
         *     <strong style="font-size:14px">2018-12-17航班列表</strong>&nbsp;&nbsp;&nbsp;&nbsp;
         *     <a href="/sitemap/flight?AE71649A58c77=">国内航段列表</a>
         *     <br>
         *     <br>
         *     <a href="/flight/fnum/036925.html?AE71649A58c77=">036925</a>
         *     <a href="/flight/fnum/036926.html?AE71649A58c77=">036926</a>
         *     <a href="/flight/fnum/3K691.html?AE71649A58c77=">3K691</a>
         *     <a href="/flight/fnum/3K692.html?AE71649A58c77=">3K692</a>
         *     <a href="/flight/fnum/3K695.html?AE71649A58c77=">3K695</a>
         *     <a href="/flight/fnum/3K696.html?AE71649A58c77=">3K696</a>
         *     <a href="/flight/fnum/3K697.html?AE71649A58c77=">3K697</a>
         *     <a href="/flight/fnum/3K698.html?AE71649A58c77=">3K698</a>
         *     <a href="/flight/fnum/3K721.html?AE71649A58c77=">3K721</a>
         * </div>
         */
        List<SimpleFlightBean> list = new ArrayList<>();
        Document doc = Jsoup.parse(html);
        Elements elements = doc.select("[class=list]");
        if(elements == null)
            return list;

        for(Element node : elements){
            Elements links = node.select("a");
            if(links == null) //current node don not have <href>
                continue;
            for(int i = 1; i < links.size(); i++){
                //because the first href isn't a correct flight href
                Element flightNode = links.get(i);
                String linkAttr = flightNode.attr("href");
                String linkText = flightNode.text();
                list.add(new SimpleFlightBean(linkText, linkAttr));
            }
        }
        return list;
    }

    @Override
    public List<DetailFlightBean> fetchCertainPage(List<SimpleFlightBean> allFlight) {
        List<DetailFlightBean> detailFlightBeans = new ArrayList<>();
        //allFlight contains all flights' link and flight's code

        for(SimpleFlightBean bean : allFlight){
            //obtain base url, like "http://www.variflight.com/flight/fnum/AA8893.html?AE71649A58c77="
            String certainUrl = Constants.FLIGHT_BASE + bean.getLink();
            String html = this.getPage(certainUrl); //get whole page

            System.out.println(html);
            Document doc = Jsoup.parse(html);
            /**
             * ==============================================================================================
             * <div class="fly_list">
             * 	<div class="tit">
             * 		<h1 title="AA8893">AA8893
             * 		    <span>12月17号周一 共1次航班</span>
             * 		</h1>
             * 	</div>
             * 	<div class="li_box" id="li_box">
             * 		<div class="t_tit" id="t_tit">
             * 			<p class="w260">航班信息</p>
             * 			<p class="w150">计划起飞</p>
             * 			<p class="w150">实际起飞</p>
             * 			<p class="w150">出发地</p>
             * 			<p class="w150">计划到达</p>
             * 			<p class="w150">实际到达</p>
             * 			<p class="w150">到达地</p>
             * 			<p class="w150">准点率</p>
             * 			<p class="w150">状态</p>
             * 			<p class="w150">订制</p>
             * 			<div class="clear"></div>
             * 		</div>
             * 		<ul id="list">
             * 			<li style="position: relative;">
             * 				<a class="searchlist_innerli" href="/schedule/BKK-HKG-AA8893.html?AE71649A58c77="></a>
             * 				<a class="list_share" href="javascript:;" title="实际承运为CX700" style="color:#fff;">共享航班</a>
             * 				<div class="li_com">
             * 					<span class="w260">
             * 						<b>
             * 							<a href="javascript:void(0);"title="美国航空">美国航空</a>
             * 							<a href="javascript:void(0);"title="AA8893">AA8893</a>
             * 						</b>
             * 				    </span>
             *
             * 					<span class="w150" dplan="08:25">
             * 						08:25
             * 						<em style="display:block; background-color:white; color:#000; font-size:10px;">
             * 							当地17号
             * 						</em>
             * 					</span>
             *
             * 					<span class="w150 randEle">
             * 						<img src="">
             * 				    </span>
             *
             * 					<span class="w150">曼谷(素万那普)T1</span>
             * 					<span class="w150" aplan="12:10">12:10</span>
             * 					<span class="w150 randEle">
             * 						<em style="display:block; background-color:white; color:#000; font-size:10px;">
             * 							当地17号
             * 						</em>
             * 					</span>
             * 					<span class="w150">香港赤鱲角T1</span>
             * 					<span class="w150">
             * 						<img src=""/>
             * 					</span>
             * 					<span class="w150 gre_cor">到达</span>
             * 					<span class="fob">
             * 						<a class="forbiddenBtn" onclick="forbiddenJump('到达');"href="javascript:;" style="color:#e1e5e6;z-index:6;position: relative;">[订制航班]</a>
             * 					</span>
             * 					<div class="clear"></div>
             * 				</div>
             * 			</li>
             * 		</ul>
             * </div>
             * </div>
             * =========================================================================================================
             */
            Elements _fly_list = doc.select("[class=fly_list]");
            if(_fly_list.size() == 0)
                System.out.println("fly list is null");
            for(Element flyListNode : _fly_list){
                Elements _tit = flyListNode.select("[class=tit]");
                Elements _title = _tit.select("title");
                if(_title.size() == 0) ;
                //get detail
                String flightDetail = _title.get(0).text();

                Elements _li_box = flyListNode.select("[class=li_box]");
                Elements _ul = _li_box.select("ul");
                Elements _li = _ul.select("li");
                Elements _li_com = _li.select("[class=li_com]");
                Elements _company = _li_com.select("[class=w260]").select("b").select("a");
                //company
                String company = _company.get(0).attr("title");
                //flight code
                String flightCode = _company.get(1).attr("title");

                Elements _w150 = _li_com.select("[class=w150]");
                if(_w150 == null) ;

                //planning departure time, like 08:57/当地17号
                String _departurePlanTime = _w150.get(0).text() + "/" + _w150.get(0).select("em").text();
                //departure place
                String _departure = _w150.get(1).text();
                //planning arriving time, like 09:03/当地17号
                String __arrivePlanTime = _w150.get(2).text() + "/" + _li_com.select("[class=w150 randEle]")
                        .get(1).select("em").text();
                //arrival
                String _arrival = _w150.get(3).text();
                //status
                String _status = _w150.get(5).text();

                //add new node
                detailFlightBeans.add(new DetailFlightBean(company, flightCode, _departurePlanTime, _departurePlanTime,
                        __arrivePlanTime, __arrivePlanTime, _departure, _arrival, _status, flightDetail));
                System.out.println("company: " + company + "\n" +
                                   "code   : " + flightCode + "\n" +
                                   "pleave : " + _departurePlanTime + "\n" +
                                   "parrive: " + __arrivePlanTime + "\n" +
                                   "departure:" + _departure + "\n" +
                                   "arrival: " + _arrival + "\n" +
                                    "status: " + _status + "\n" +
                                    "flight detail: " + flightDetail);
            }
        }
        return detailFlightBeans;
    }
}
