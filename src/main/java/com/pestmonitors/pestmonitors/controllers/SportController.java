package com.pestmonitors.pestmonitors.controllers;

import com.pestmonitors.pestmonitors.models.SportDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/sports")
public class SportController {

    @GetMapping("/sports")
    public List<SportDTO> getAll(){
        return List.of(new SportDTO(1,"climb"), new SportDTO(2,"Yoga"));
    }

    @GetMapping("/sports/{id}")
    public ResponseEntity<SportDTO> getById(@PathVariable Integer id){
        SportDTO sportDTO = new SportDTO(1,"basket Ball");
        return ResponseEntity.ok(sportDTO);
    }
    
    @GetMapping("/sports/{name}")
    public SportDTO getByName(@PathVariable String name){
        SportDTO sportDTO = new SportDTO(1,"Tennis");
        return sportDTO;
    }

    @DeleteMapping("/sports/{id}")
    public void deleteById(@PathVariable Integer id){
    }

    @DeleteMapping("/sports")
    public void delete(@RequestBody SportDTO sportDTO){
    }

    @PostMapping("/sports")
    public String create(@RequestBody SportDTO sportDTO){
        //aquí lo creo
        return "http://localhost:8080/users/" + sportDTO.getId();
    }

    @PutMapping("/sports")
    public SportDTO update(@RequestBody SportDTO sportDTO){
        //modify sport
        return new SportDTO(1,"bingo");
    }

}
