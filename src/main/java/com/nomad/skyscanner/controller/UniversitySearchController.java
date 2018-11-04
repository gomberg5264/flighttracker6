package com.nomad.skyscanner.controller;

import com.nomad.skyscanner.dao.UniversityRepository;
import com.nomad.skyscanner.dao.VacationRepository;
import com.nomad.skyscanner.model.DestinationModel;
import com.nomad.skyscanner.model.University;
import com.nomad.skyscanner.model.Vacation;
import com.nomad.skyscanner.model.VacationDestination;
import com.nomad.skyscanner.service.SkyscannerApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class UniversitySearchController {
    @Autowired
    private UniversityRepository universityRepository;

    @Autowired
    private VacationRepository vacationRepository;

    @Autowired
    SkyscannerApiService skyscannerApiService;

    @GetMapping("/search/vacation/{uid}/{vid}")
    public List<DestinationModel> searchByVacations(@PathVariable Long uid, @PathVariable Long vid){
        // universityRepository.findAll().forEach(uni -> System.out.println(uni.getId()));
        University university = universityRepository.findAll().iterator().next();
        Vacation vacation = vacationRepository.findAll().iterator().next();
        return skyscannerApiService.getResult(vacation.getStart_date(), vacation.getEnd_date(), university.getLocation_code());
    }

    @GetMapping("/search/university/{id}")
    public List<VacationDestination> searchByUniversity(@PathVariable Long id){
        return universityRepository.findById(id).map(university -> {
            ArrayList<VacationDestination> result = new ArrayList<VacationDestination>();
            for(Vacation vacation: university.getAvailable_vacations()){
                VacationDestination destinationResult = new VacationDestination();
                destinationResult.setStart_date(vacation.getStart_date());
                destinationResult.setEnd_date(vacation.getEnd_date());
                destinationResult.setDestinations(skyscannerApiService
                        .getResult(vacation.getStart_date(), vacation.getEnd_date(), university.getLocation_code()));
                result.add(destinationResult);
            }
            return result;
        }).orElse(null);
    }

    @GetMapping("/add_test_data")
    public University addData(){
        // ---- test uni 1
        University university = new University();
        university.setName("Jacobs University");
        university.setLocation_code("bre");

        Vacation vacation = new Vacation();
        vacation.setStart_date(LocalDate.parse("2018-12-22"));
        vacation.setEnd_date(LocalDate.parse("2018-12-30"));
        vacation.setDay_of_vacation(8);

        Vacation vacation1 = new Vacation();
        vacation1.setStart_date(LocalDate.parse("2018-11-20"));
        vacation1.setEnd_date(LocalDate.parse("2018-11-25"));
        vacation1.setDay_of_vacation(5);

        Vacation vacation2 = new Vacation();
        vacation2.setStart_date(LocalDate.parse("2019-01-01"));
        vacation2.setEnd_date(LocalDate.parse("2019-01-30"));

        List<Vacation> vList = new ArrayList<>();
        vList.add(vacation);
        vList.add(vacation1);
        vList.add(vacation2);
        university.setAvailable_vacations(vList);
        universityRepository.save(university);

        university = new University();
        university.setName("Uni Bremen");
        university.setLocation_code("bre");

        vacation = new Vacation();
        vacation.setStart_date(LocalDate.parse("2018-12-20"));
        vacation.setEnd_date(LocalDate.parse("2018-12-26"));
        vacation.setDay_of_vacation(8);

        vacation1 = new Vacation();
        vacation1.setStart_date(LocalDate.parse("2018-11-10"));
        vacation1.setEnd_date(LocalDate.parse("2018-11-15"));
        vacation1.setDay_of_vacation(5);

        vacation2 = new Vacation();
        vacation2.setStart_date(LocalDate.parse("2019-01-04"));
        vacation2.setEnd_date(LocalDate.parse("2019-01-20"));

        vList = new ArrayList<>();
        vList.add(vacation);
        vList.add(vacation1);
        vList.add(vacation2);
        university.setAvailable_vacations(vList);
        universityRepository.save(university);


        university = new University();
        university.setName("Hamburg University");
        university.setLocation_code("HAMB");

        vacation = new Vacation();
        vacation.setStart_date(LocalDate.parse("2018-12-09"));
        vacation.setEnd_date(LocalDate.parse("2018-12-28"));
        vacation.setDay_of_vacation(8);

        vacation1 = new Vacation();
        vacation1.setStart_date(LocalDate.parse("2018-11-26"));
        vacation1.setEnd_date(LocalDate.parse("2018-11-30"));
        vacation1.setDay_of_vacation(5);

        vacation2 = new Vacation();
        vacation2.setStart_date(LocalDate.parse("2019-01-15"));
        vacation2.setEnd_date(LocalDate.parse("2019-01-20"));

        vList = new ArrayList<>();
        vList.add(vacation);
        vList.add(vacation1);
        vList.add(vacation2);
        university.setAvailable_vacations(vList);
        return universityRepository.save(university);
    }
}
