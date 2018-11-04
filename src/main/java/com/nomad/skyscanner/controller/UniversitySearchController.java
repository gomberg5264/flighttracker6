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
        University university = new University();
        university.setName("Uni Bremen");
        university.setLocation_code("bre");
        Vacation vacation = new Vacation();
        vacation.setStart_date(LocalDate.now().plusDays(3));
        vacation.setEnd_date(LocalDate.now().plusDays(6));
        vacation.setDay_of_vacation(3);
        List<Vacation> vList = new ArrayList<>();
        vList.add(vacation);
        university.setAvailable_vacations(vList);
        return universityRepository.save(university);
    }
}
