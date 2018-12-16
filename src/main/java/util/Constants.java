package util;

import java.io.File;

public class Constants {
    /**
     * flight information
     */
    public static final String flight_number_info = "www.variflight.com/sitemap.html?AE71649A58c77=";

    /**
     * free ip pool
     */
    public static final String free_ip_pool = "https://bean.githubusercontent.com/fate0/proxylist/master/proxy.list";


    /**
     * maximum read
     */
    public static final int MAXIMUM_READ = 600;

    /**
     * root directory
     */
    public static final String _root = System.getProperty("user.dir") + File.separator;

    /**
     * JDBC connection
     */
    public static final String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    public static final String dbURL = "jdbc:sqlserver://localhost:1433;DataBaseName=";
    public static final String userName = "sa";
    public static final String userPwd = "CHINA19980223`";

    /**
     * Verify url
     */
    public static final String VERIFY_URL = "http://www.baidu.com";
}
