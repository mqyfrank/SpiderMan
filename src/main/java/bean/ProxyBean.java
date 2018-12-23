package bean;

import java.io.Serializable;
import java.util.Date;

@Deprecated
public  class ProxyBean implements Serializable{
    private String ip;
    private int port;
    private String location;
    private String agentType;
    private Date lastValidateTime;
    private boolean usable;

    //constructor
    public ProxyBean() {}

    /**
     * IP address
     */
    public String getIp() { return ip; }
    public void setIp(String ip) { this.ip = ip; }

    /**
     * Port
     */
    public int getPort() { return port; }
    public void setPort(int port) { this.port = port; }

    /**
     * Location
     */
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    /**
     * AgentType
     */
    public String getAgentType() { return agentType; }
    public void setAgentType(String agentType) { this.agentType = agentType; }

    /**
     * Last validating time
     */
    public Date getLastValidateTime() { return lastValidateTime; }
    public void setLastValidateTime(Date lastValidateTime) { this.lastValidateTime = lastValidateTime; }

    /**
     * Is it usable?
     */
    public boolean isUsable() { return usable; }
    public void setUsable(boolean usable) { this.usable = usable; }

    @Override
    public String toString() {
        return "RawProxy{" + "ip='" + ip + '\'' + ", port=" + port + ", location='" + location + '\'' +
                ", agentType='" + agentType + '\'' + ", lastValidateTime='" + lastValidateTime + '\'' + '}';
    }
}
