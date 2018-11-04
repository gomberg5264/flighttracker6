package com.nomad.skyscanner.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nomad.skyscanner.model.DestinationModel;
import com.nomad.skyscanner.model.FlightDetailModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SkyscannerApiService {
    private static String api_key = "ha481926326453504950878142434677";
    private static String baseUrl = "http://partners.api.skyscanner.net/apiservices/browseroutes/v1.0/DE/eur/en-US/";
    private static String default_locale = "en-US";
    private RestTemplate restTemplate;

    @Autowired
    public SkyscannerApiService(RestTemplateBuilder restTemplateBuilder){
        this.restTemplate = restTemplateBuilder.build();
    }

    private String buildApiUrl(LocalDate start_date, LocalDate end_date, String location_code){
        String rst = baseUrl;
        String start_date_str = start_date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String end_date_str = end_date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        rst = rst + location_code + "/anywhere/";
        rst = rst + start_date_str + "/";
        rst = rst + end_date_str;
        rst = rst + "?apikey=" + api_key;
        return rst;
    }

    private JsonNode requestInfo(LocalDate start_date, LocalDate end_date, String location){
        String url = buildApiUrl(start_date, end_date, location);
        String str = restTemplate.getForObject(url, String.class);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node;
        try{
            node = mapper.readTree(str);
        } catch (IOException ex){
            node = null;
        }
        return node;
    }

    private Map<Long, String> parsePlaces(JsonNode node){
        Map<Long, String> dict = new HashMap<Long, String>();
        JsonNode places = node.get("Places");
        if(places.isArray()){
            for(final JsonNode objNode : places){
                Long key = objNode.get("PlaceId").asLong();
                String val = objNode.get("Name").asText();
                dict.put(key, val); // insert the value
            }
        }
        return dict;
    }

    private Map<Long, String> parseAirlines(JsonNode node){
        Map<Long, String> dict = new HashMap<Long, String>();
        JsonNode carriers = node.get("Carriers");
        if(carriers.isArray()){
            for(final JsonNode objNode: carriers){
                Long key = objNode.get("CarrierId").asLong();
                String val = objNode.get("Name").asText();
                dict.put(key, val); // insert the value
            }
        }
        return dict;
    }

    private Map<Long, FlightDetailModel> parseFlightDetail(JsonNode node){
        Map<Long, FlightDetailModel> dict = new HashMap<Long, FlightDetailModel>();

        Map<Long, String> airlineMap = parseAirlines(node);

        Map<Long, String> places = parsePlaces(node);

        JsonNode quotes = node.get("Quotes");
        if(quotes.isArray()){
            for(final JsonNode objNode: quotes){
                Long key = objNode.get("QuoteId").asLong();
                FlightDetailModel model = new FlightDetailModel();

                for(final JsonNode outboundNode: objNode.get("OutboundLeg").get("CarrierIds")){
                    model.setOutboundAirline(airlineMap.get(outboundNode.asLong()));
                    model.setDestination(places.get(objNode.get("OutboundLeg").get("DestinationId").asLong()));
                    break;
                }

                for(final JsonNode inboudNode: objNode.get("InboundLeg").get("CarrierIds")){
                    model.setInboundAirline(airlineMap.get(inboudNode.asLong()));
                    break;
                }

                dict.put(key, model);
            }
        }

        return dict;
    }

    private List<DestinationModel> parseDestination(JsonNode node){
        JsonNode routesNode = node.get("Routes");
        List<DestinationModel> dests = new ArrayList<DestinationModel>();
        Map<Long, FlightDetailModel> flightDetailModelMap = parseFlightDetail(node);
        Map<Long, String> placesMap = parsePlaces(node);

        if(routesNode.isArray()){
            for(final JsonNode routeNode : routesNode){
                if(routeNode.hasNonNull("Price")){
                    // valid, start parsing
                    DestinationModel dest = new DestinationModel();
                    dest.setPrice(routeNode.get("Price").asLong());
                    for(final JsonNode quote: routeNode.get("QuoteIds")){
                        Long quoteId = quote.asLong();
                        dest.setFlightDetail(flightDetailModelMap.get(quoteId));
                    }
                    dest.setDestination(placesMap.get(routeNode.get("DestinationId").asLong()));
                    dests.add(dest);
                }
            }
        }
        return dests;
    }

    public List<DestinationModel> getResult(LocalDate start, LocalDate end, String location){
        JsonNode node = requestInfo(start, end, location);
        return parseDestination(node);
    }


    public static void main(String[] args){
        SkyscannerApiService serv = new SkyscannerApiService(new RestTemplateBuilder());

        List<DestinationModel> models = serv.getResult(LocalDate.now().plusDays(3), LocalDate.now().plusDays(7), "bre");
        models.forEach(model -> System.out.println(model.getFlightDetail().getDestination()));
    }
}
