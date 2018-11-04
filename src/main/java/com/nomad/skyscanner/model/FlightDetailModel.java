package com.nomad.skyscanner.model;

public class FlightDetailModel {
    private String outboundAirline;
    private String inboundAirline;
    private String destination;

    public String getOutboundAirline() {
        return outboundAirline;
    }

    public void setOutboundAirline(String outboundAirline) {
        this.outboundAirline = outboundAirline;
    }

    public String getInboundAirline() {
        return inboundAirline;
    }

    public void setInboundAirline(String inboundAirline) {
        this.inboundAirline = inboundAirline;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
}
