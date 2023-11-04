package tn.esprit.brogram.backend.RestController;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.brogram.backend.DAO.Entities.Bloc;
import tn.esprit.brogram.backend.Services.IBlocService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("BlocRestController")
public class BlocRestController {
    @Autowired
    IBlocService iBlocService ;
    @GetMapping("findAll")
    List<Bloc> findAll(){
        return iBlocService.findAll();
    }

    @PostMapping("addBloc")
    Bloc AddBloc(@RequestBody Bloc b ){
        return iBlocService.addBloc(b);
    }

    @PostMapping("addAllBlocs")
    List<Bloc> addAllBlocs(@RequestBody List<Bloc> b){
        return iBlocService.addAllBlocs(b);
    }

    @PutMapping("editBloc")
    Bloc editBloc(@RequestBody Bloc b){
        return iBlocService.editBloc(b);
    }

    @GetMapping("findById/{id}")
    Bloc findById(@PathVariable("id") long id){
        return iBlocService.findById(id);
    }

    @DeleteMapping("deleteByID/{id}")
    void deleteByID(@PathVariable("id") long id){
        iBlocService.deleteByID(id);
    }

    @DeleteMapping("delete")
    void delete(@RequestBody Bloc b){
        iBlocService.delete(b);
    }
}
