package bean;

public class IpBean2 {
    /**
     * ====================================
     * 123.123.123.123  8080
     * ====================================
     */
    private String _host;
    private int _port;

    public IpBean2(String _host, int _port){
        this._host = _host;
        this._port = _port;
    }

    public String get_host(){ return this._host; }
    public int get_port(){ return this._port; }
}
