package raw;

import java.util.List;

public class IpBean {
    /**
     * 从https://raw.githubusercontent.com/fate0/proxylist/master/proxy.list
     * =====================================================================
     * {
     * 	"from": "proxylist",
     * 	"export_address": ["35.224.248.29"],
     * 	"anonymity": "transparent",
     * 	"host": "35.224.248.29",
     * 	"port": 3128,
     * 	"type": "http",
     * 	"country": "US",
     * 	"response_time": 2.02
     * }
     * ======================================================================    */

    public String _from;
    public List<String> _export_address;
    public String _anonymity;
    public String _host;
    public int _port;
    public String _type;
    public String _country;
    public double _response_time;

    public IpBean(String _from, List<String> _export_address, String _anonymity, String _host, int _port
            , String _type, String _country, double _response_time){
        this._from = _from;
        this._export_address = _export_address;
        this._anonymity = _anonymity;
        this._host = _host;
        this._port = _port;
        this._type = _type;
        this._country = _country;
        this._response_time = _response_time;
    }

    public void set_from(String _from){ this._from = _from; }
    public void set_export_address(List<String> _export_address){ this._export_address = _export_address; }
    public void set_anonymity(String _anonymity){ this._anonymity = _anonymity; }
    public void set_host(String _host){ this._host = _host; }
    public void set_type(String _type){ this._type = _type; }
    public void set_country(String _country){ this._country = _country; }
    public void set_response_time(double _response_time){ this._response_time = _response_time; }
    public void set_port(int _port) { this._port = _port; }

    public String get_from(){ return this._from; }
    public List<String> get_export_address(){ return _export_address; }
    public String get_anonymity(){ return _anonymity; }
    public String get_host(){ return this._host; }
    public int get_port(){ return this._port; }
    public String get_type(){ return this._type; }
    public String get_country(){ return this._country; }
    public double get_response_time(){ return this._response_time; }
}
