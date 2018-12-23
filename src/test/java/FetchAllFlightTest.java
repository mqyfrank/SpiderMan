import bean.DetailFlightBean;
import util.flightFetcher.VariFlightFetcher;

import java.util.HashMap;
import java.util.List;

public class FetchAllFlightTest {
    public static void main(String[] args){
        VariFlightFetcher variFlightFetcher = new VariFlightFetcher();
        HashMap<String, List<DetailFlightBean>> detailFlightBeans = variFlightFetcher
                .fetchCertainPage(variFlightFetcher.fetchAllPage());

        /**
        for(String s : _keySet){
            List<DetailFlightBean> _list = detailFlightBeans.get(s);
            if(_list.size() != 0){
                for(int i = 0; i < _list.size(); i++){
                    for(int j = 0; j < _list.size(); j++){
                        System.out.println(" [航班]: " + _list.get(j).getFlightCode() +
                                " [航空公司]: " + _list.get(j).getFlightCompany() +
                                " [计划起飞]: " + _list.get(j).getPlanTakeOffTime() +
                                " [实际起飞]: " + _list.get(j).getActualTakeOffTime() +
                                " [起飞地点]: " + _list.get(j).getDeparture() +
                                " [计划到达]: " + _list.get(j).getPlanArriveTime() +
                                " [实际到达]: " + _list.get(j).getActualArriveTime() +
                                " [到达地点]: " + _list.get(j).getDestination() +
                                " [状态]: " + _list.get(j).getStatus());
                    }
                }
            }else {
                System.out.println("航班信息为空!");
            }
        }
         */

        //List<DetailFlightBean> detailFlightBeanList = detailFlightBeans.get("3K721");
        //System.out.println(detailFlightBeanList.size());
    }
}
