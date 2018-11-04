package com.nomad.skyscanner.model;

public class DestinationModel {
    private Long price;
    private String destination;
    private FlightDetailModel flightDetail;

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public FlightDetailModel getFlightDetail() {
        return flightDetail;
    }

    public void setFlightDetail(FlightDetailModel flightDetail) {
        this.flightDetail = flightDetail;
    }
}
