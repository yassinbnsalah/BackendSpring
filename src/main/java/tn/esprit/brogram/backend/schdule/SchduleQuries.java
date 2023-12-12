package tn.esprit.brogram.backend.schdule;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tn.esprit.brogram.backend.Services.IChamberService;

@Component
@AllArgsConstructor

@Slf4j //bch nkharej msg fii blsate system.out
public class SchduleQuries {

    /*@Scheduled(fixedDelay = 10000)
    public void fixedDelayMethod() {
        System.out.println("Method with fixed delay");
    }*/

    IChamberService iChamberService;


    @Scheduled(fixedRate = 60000)
    void affiche(){
        iChamberService.listeChamberParBloc();
    }

    @Scheduled(fixedRate = 300000)
    void affiche2(){
        iChamberService.pourcentageChamberParTypeChamber();
    }
}
