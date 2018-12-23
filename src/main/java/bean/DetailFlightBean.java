package bean;

import java.io.Serializable;
@Deprecated
public class DetailFlightBean implements Serializable {
    /**
     * flight company
     */
    String flightCompany;

    /**
     * flight code
     */
    String flightCode;

    /**
     * planning time to take off
     */
    String planTakeOffTime;

    /**
     * actual time to take off
     */
    String actualTakeOffTime;

    /**
     * departure
     */
    String departure;

    /**
     * destination
     */
    String destination;

    /**
     * plan to arrive
     */
    String planArriveTime;

    /**
     * actual arrive time
     */
    String actualArriveTime;

    /**
     *  punctuality
     */
    double  punctuality;

    /**
     * status
     */
    String status;

    /**
     * flight details conclusion
     */
    String conclusion;

    public DetailFlightBean(String company, String flightCode,
                            String planTakeOffTime, String actualTakeOffTime,
                            String planArriveTime,  String actualArriveTime,
                            String departure, String arrival,
                            String status){
        this.flightCompany = company;
        this.flightCode = flightCode;
        this.planTakeOffTime = planTakeOffTime;
        this.actualTakeOffTime = actualTakeOffTime;
        this.planArriveTime = planArriveTime;
        this.actualArriveTime = actualArriveTime;
        this.status = status;
        this.departure = departure;
        this.destination = arrival;
    }

    public String getFlightCompany(){ return this.flightCompany; }
    public void setFlightCompany(String flightCompany){ this.flightCompany = flightCompany; }

    public String getFlightCode(){ return this.flightCode; }
    public void setFlightCode(String flightCode){ this.flightCode = flightCode; }

    public String getPlanTakeOffTime(){ return this.planTakeOffTime; }
    public void setPlanTakeOffTime(String planTakeOffTime){ this.planTakeOffTime = planTakeOffTime; }

    public String getActualTakeOffTime(){ return this.actualTakeOffTime; }
    public void setActualTakeOffTime(String actualTakeOffTime){ this.actualTakeOffTime = actualTakeOffTime; }

    public String getDeparture(){ return this.departure; }
    public void setDeparture(String departure){ this.departure = departure; }

    public String getDestination(){ return this.destination; }
    public void setDestination(String destination){ this.destination = destination; }

    public String getPlanArriveTime(){ return this.planArriveTime; }
    public void setPlanArriveTime(String planArriveTime){ this.planArriveTime = planArriveTime; }

    public String getActualArriveTime(){ return this.actualArriveTime; }
    public void setActualArriveTime(String actualArriveTime){ this.actualArriveTime = actualArriveTime; }

    public String getStatus(){ return this.status; }
    public void setStatus(String status){ this.status = status; }

    public double getPunctuality(){ return this.punctuality; }
    public void setPunctuality(double punctuality){ this.punctuality = punctuality; }
}
