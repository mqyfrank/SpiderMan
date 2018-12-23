package bean;

import java.io.Serializable;

@Deprecated
public class SimpleFlightBean implements Serializable {
    /**
     * Flight Code
     */
    String flight;

    /**
     * flight link
     */
    String link;

    public SimpleFlightBean(String flight, String link){
        this.flight = flight;
        this.link = link;
    }

    public void setFlight(String flight){ this.flight = flight; }
    public String getFlight(){ return this.flight; }

    public void setLink(String link){ this.link = link; }
    public String getLink(){ return this.link; }
}
