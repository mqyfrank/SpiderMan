package util;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;

public class ProxyUtil {

    /**
     * Check if ip is available
     * @param ip ip address
     * @param port port number
     * @return it is available?
     */
    public boolean verifyProxy(String ip, int port){
        boolean usable;
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL("http://www.variflight.com/sitemap.html?AE71649A58c77=");
            InetSocketAddress inetAddress = new InetSocketAddress(ip, port);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setConnectTimeout(4 * 1000);
            urlConnection.setInstanceFollowRedirects(false);
            urlConnection.setReadTimeout(6 * 1000);

            usable = urlConnection.getResponseCode() == 200;
        }catch (IOException e){
            System.out.println("@localhost: IOException caught: [ " + e.getMessage() + "]");
            usable = false;
        }finally {
            if(urlConnection != null)
                urlConnection.disconnect();
        }

        System.out.println(String.format("@localhost: [Verifying result]: host:[%15s], port:[%6d] $available:  " + usable,
                ip, port));
        return usable;
    }

    /**
     * Auto flush database, delete useless ip address
     * @throws Exception
     */
    public void autoFlushTable_CrawledIp() throws Exception{
        SQLServerUtil sqlServerUtil = new SQLServerUtil();
        Connection connection = sqlServerUtil.createNewConnection("VariFlight");
        Statement statement = sqlServerUtil.createNewStatement(connection);

        //draw out all host and port
        ResultSet _result = statement.executeQuery("SELECT _HOST, _PORT FROM CrawledIp;");

        while(_result.next()){
            if(!verifyProxy(_result.getString(1), _result.getInt(2))){
                //ip address is not available
                String _delete = "DELETE FROM CrawledIp WHERE _HOST = '" + _result.getString(1) + "';";
                statement.execute(_delete);
                System.out.println("@localhost: Unusable host has been deleted: host:[" + _result.getString(1)
                        + "]");
            }
        }
    }

    public static HashMap<String, Integer> getProxyPool(){
        SQLServerUtil util = new SQLServerUtil();
        HashMap<String, Integer> map = new HashMap<>();

        try {
            Connection connection = util.createNewConnection("VariFlight");
            Statement statement = util.createNewStatement(connection);

            String sql = "SELECT top 1 _HOST, _PORT FROM CrawledIp ORDER BY NEWID();";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()){
                String _host = resultSet.getString(1);
                int _port = resultSet.getInt(2);

                map.put(_host, _port);
            }
            return map;
        }catch (Exception e){
            System.out.println("[SQL Server]: " + e.getMessage());
            return null;
        }
    }
}
