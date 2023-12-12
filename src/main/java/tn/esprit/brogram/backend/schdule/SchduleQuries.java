package tn.esprit.brogram.backend.schdule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tn.esprit.brogram.backend.Services.IReservationService;

@Component
public class SchduleQuries {
    IReservationService iReservationService ;
    /*@Scheduled(fixedDelay = 10000)
    public void fixedDelayMethod() {
        System.out.println("Method with fixed delay");
    }*/


}
