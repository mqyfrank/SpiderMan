package util.fetcher;

import util.Constants;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import raw.IpBean;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ProxyListFetcher {
    /**
     * each json string was parsed into <code>_ip</code> for using
     */
    List<IpBean> _ipList = new ArrayList<>();

    /**
     * JSONArray to store json object
     */
    JSONArray jsonArray = null;

    /**
     * note the line of maximum read
     */
    int count = 0;

    /**
     * Using given url to get the json string
     * @param url
     * @return String
     */
    public boolean getJsonFromUrl(String url){
        URL _free_ip_url;
        BufferedReader reader;
        StringBuffer buffer = new StringBuffer();

        try {
            _free_ip_url = new URL(url);
            reader = new BufferedReader(new InputStreamReader(_free_ip_url.openStream(),
                    "utf-8"));


            String _read;   //temporary string instance

            while((_read = reader.readLine()) != null){
                buffer.append(_read + "]\n");
                String each_line = "[" + buffer.toString();
                count++;
                //if(count >= Constants.MAXIMUM_READ)
                    //break;

                System.out.println(each_line);
                //parse the each json string line
                parseJsonObject(each_line);

                //after each reading, reset the buffer
                int buffer_length = buffer.length();
                buffer.delete(0, buffer_length);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    /**
     * parse the json string
     */
    private void parseJsonObject(String _json){

        try{
            jsonArray = new JSONArray(_json);
            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject obj = jsonArray.getJSONObject(i); //object element
                List<String> _export_address_array = new ArrayList<>();

                JSONArray _export_address_jsonArray = obj.getJSONArray("export_address");
                for(int j = 0; j < _export_address_jsonArray.length(); j++){
                    _export_address_array.add(_export_address_jsonArray.getString(j));
                }

                if(checkIpAvailability(obj.getString("host"), obj.getInt("port"))) {
                    _ipList.add(new IpBean(obj.getString("from"), _export_address_array,
                            obj.getString("anonymity"), obj.getString("host"), obj.getInt("port"),
                            obj.getString("type"), obj.getString("country"),
                            obj.getDouble("response_time")));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Check if the ip address can reach Internet correctly
     * @param host,port
     * @return
     * @throws Exception
     */
    public boolean checkIpAvailability(String host, int port){
        //check if one ip address is usable
        try {
            Jsoup.connect(Constants.flight_number_info)
                    .timeout(30 * 1000)
                    .proxy(host, port)
                    .get();
            return true;
        }catch (Exception e){
            return false;
        }
    }



    public List<IpBean> get_ipList(){ return this._ipList; }
}
