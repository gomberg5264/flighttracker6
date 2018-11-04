package com.nomad.skyscanner.model;

import java.time.LocalDate;
import java.util.List;

public class VacationDestination {
    private LocalDate start_date;
    private LocalDate end_date;
    private List<DestinationModel> destinations;

    public LocalDate getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalDate start_date) {
        this.start_date = start_date;
    }

    public LocalDate getEnd_date() {
        return end_date;
    }

    public void setEnd_date(LocalDate end_date) {
        this.end_date = end_date;
    }

    public List<DestinationModel> getDestinations() {
        return destinations;
    }

    public void setDestinations(List<DestinationModel> destinations) {
        this.destinations = destinations;
    }
}
