package util;

import bean.DetailFlightBean;
import bean.ProxyBean;
import bean.SimpleFlightBean;

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
     * Insert ip address into table "CrawledIp", using List<List<ProxyBean>>
     * @param _proxy
     */
    public void insertIpIntoTable2(List<List<ProxyBean>> _proxy){
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
            System.out.println("@localhost: [SQL Server]: " + count + " record has been inserted.");
        }catch (Exception e){
            System.out.println("[Fatal error occurs]: " + e.getMessage());

        }
    }

    /**
     * Avoiding same record insertion
     * @param dbName database name
     * @param tableName table name
     * @throws Exception
     */
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

    /**
     * Insert main flight page's content into database
     * @param simpleFlightBeans
     * @throws Exception
     */
    public void insertSimpleFlightBean(List<SimpleFlightBean> simpleFlightBeans) throws Exception{
        Connection connection = createNewConnection("VariFlight");

        //sql
        String sql = "INSERT INTO SimpleFlight(_FLIGHT) VALUES(?);";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        for(int i = 0; i < simpleFlightBeans.size(); i++){
            preparedStatement.setString(1, simpleFlightBeans.get(i).getFlight());
            preparedStatement.execute();
        }

        //release source
        releaseSource(connection, null);
    }

    /**
     * Insert DetailFlightBean into database
     * @param detailFlightBeans
     * @throws Exception
     */
    public void insertDetailFlightBean(List<DetailFlightBean> detailFlightBeans) throws Exception{
        Connection connection = createNewConnection("VariFlight");

        //sql
        String sql = "INSERT INTO DetailFlightBean(航班号, 航空公司, 计划起飞, 实际起飞, 计划到达, 实际到达, 起飞地点, " +
                "到达地点, 状态) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?);";

        PreparedStatement statement = connection.prepareStatement(sql);

        for(int i = 0; i < detailFlightBeans.size(); i++){
            statement.setString(1, detailFlightBeans.get(i).getFlightCode());
            statement.setString(2, detailFlightBeans.get(i).getFlightCompany());
            statement.setString(3, detailFlightBeans.get(i).getPlanTakeOffTime());
            statement.setString(4, detailFlightBeans.get(i).getActualTakeOffTime());
            statement.setString(5, detailFlightBeans.get(i).getPlanArriveTime());
            statement.setString(6, detailFlightBeans.get(i).getActualArriveTime());
            statement.setString(7, detailFlightBeans.get(i).getDeparture());
            statement.setString(8, detailFlightBeans.get(i).getDestination());
            statement.setString(9, detailFlightBeans.get(i).getStatus());

            statement.execute();
        }
        releaseSource(connection, null);
    }
}
