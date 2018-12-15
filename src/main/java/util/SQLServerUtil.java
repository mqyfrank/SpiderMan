package util;

import entity.ProxyEntity;
import raw.IpBean;

import java.sql.*;
import java.util.List;

public class SQLServerUtil {
    /**
     * This method is designed for fix the "ResultSet has been closed" problem
     * cz each "Statement" can only support one "ResultSet"
     * @param _connection the given Connection instance
     * @return Statement A new Statement instance
     */
    public Statement createNewStatement(Connection _connection) throws Exception{
        return _connection.createStatement();
    }

    /**
     * This method is designed for fix the "Connection has been closed" problem
     * cz each "Connection" can only support one "Statement"
     * @param dbName database name
     * @return Connection A new Connection instance
     * @throws Exception
     */
    public Connection createNewConnection(String dbName) throws Exception{
        Class.forName(Constants.driverName);
        return DriverManager.getConnection(Constants.dbURL + dbName,
                Constants.userName, Constants.userPwd);
    }

    /**
     * Release system source
     * @param _connection
     * @param _statement
     * @return
     */
    public boolean releaseSource(Connection _connection, Statement _statement) {
        try {
            if (_connection != null)
                _connection.close();
            if (_statement != null)
                _statement.close();
            return true;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Using given list of IP, insert them into database
     * @param _free_ip_pool
     * @throws Exception
     */
    @Deprecated
    public void insertIpIntoTable1(List<IpBean> _free_ip_pool) throws Exception{
        Connection connection = createNewConnection("VariFlight");
        //define sql statement module
        String _insert_ip = "INSERT INTO free_ip_pool" +
                "(_NUM, _FROM, _PORT, _ANONYMITY, _HOST, _COUNTRY, _TYPE, _RESPONSE_TIME, _EXPORT_ADDRESS)" +
                "VALUES" +
                "(?, ?, ?, ?, ?, ?, ?, ?, ?);";

        if(connection != null) {
            PreparedStatement ps = connection.prepareStatement(_insert_ip);
            for (int i = 0; i < _free_ip_pool.size(); i++) {

                ps.setInt   (1, i + 1);
                ps.setString(2, _free_ip_pool.get(i).get_from());
                ps.setInt   (3, _free_ip_pool.get(i).get_port());
                ps.setString(4, _free_ip_pool.get(i).get_anonymity());
                ps.setString(5, _free_ip_pool.get(i).get_host());
                ps.setString(6, _free_ip_pool.get(i).get_country());
                ps.setString(7, _free_ip_pool.get(i).get_type());
                ps.setDouble(8, _free_ip_pool.get(i).get_response_time());
                ps.setString(9, _free_ip_pool.get(i).get_export_address().get(0));
                ps.execute();
            }
        }

        //release system source
        releaseSource(connection, null);
    }

    /**
     * Insert ip address into table "CrawledIp", using List<List<ProxyEntity>>
     * @param _proxy
     */
    public void insertIpIntoTable2(List<List<ProxyEntity>> _proxy){
        try{
            int count = 0;
            Connection connection;
                connection = createNewConnection("VariFlight");



            //sql
            String _sql = "INSERT INTO CrawledIp(_HOST, _PORT, _LOCATION, _AGENT_TYPE, _LAST_VALIDATE)" +
                          " VALUES(?, ?, ?, ?, ?);";



                PreparedStatement ps = connection.prepareStatement(_sql);

                for(int i = 0; i < _proxy.size(); i++){
                    for(int j = 0; j < _proxy.get(i).size(); j++){
                        ps.setString(1, _proxy.get(i).get(j).getIp());
                        ps.setInt(2, _proxy.get(i).get(j).getPort());
                        ps.setString(3, _proxy.get(i).get(j).getLocation());
                        ps.setString(4, _proxy.get(i).get(j).getAgentType());
                        ps.setString(5, _proxy.get(i).get(j).getLastValidateTime().toGMTString());
                        ps.execute();
                        count++;
                    }
                }

                releaseSource(connection, null);
                System.out.println(count + " record has been inserted.");


        }catch (Exception e){
            System.out.println("[Fatal error occurs]: " + e.getMessage());

        }
    }

    public void clearTableWithTableName(String dbName, String tableName) throws Exception{
        //sql
        String _clear = "DELETE FROM " + tableName + ";";
        Connection connection = createNewConnection(dbName);
        Statement statement = createNewStatement(connection);

        if(statement.execute(_clear))
            return;
        else
            throw new RuntimeException("SQL statement execution failed.");
    }
}
