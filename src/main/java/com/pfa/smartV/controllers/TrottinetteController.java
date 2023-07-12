package com.pfa.smartV.controllers;


import com.pfa.smartV.models.Station;
import com.pfa.smartV.models.Trottinette;
import com.pfa.smartV.services.TrottinetteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/smartV/trottinette")
public class TrottinetteController {

    private final TrottinetteService trottinetteService;

    @Autowired

    public TrottinetteController(TrottinetteService trottinetteService){
        this.trottinetteService = trottinetteService;
    }

    @GetMapping
    public List<Trottinette> getAllTrottinettes(){
        return trottinetteService.getAllTrottinettes();
    }
    @GetMapping("{id}")
    public ResponseEntity<Trottinette> getTrottinetteById(@PathVariable("id") Long id){
        return  new ResponseEntity<Trottinette>(trottinetteService.getTrottinetteById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Trottinette> postTrottinette(@RequestBody Trottinette trottinette){
        return new ResponseEntity<Trottinette>(trottinetteService.postTorttinette(trottinette), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Trottinette> updateTrottinette(@PathVariable("id") Long id, @RequestParam(required = false) double x, @RequestParam(required = false) double y){
        return new ResponseEntity<Trottinette>(trottinetteService.updateTrottinette(id,x,y),HttpStatus.OK);
    }

    @PutMapping("updateStation/{id}/{staID}")
    public ResponseEntity<Trottinette> updateTrottinetteStation(@PathVariable("id") Long troID, @PathVariable Long staID){
        return new ResponseEntity<Trottinette>(trottinetteService.updateTrottinetteStation(troID,staID),HttpStatus.OK);
    }

    @DeleteMapping("id")
    public ResponseEntity<String> deleteTrottinette(@PathVariable("id") Long id){
        trottinetteService.deleteTrottinette(id);
        return new ResponseEntity<String>("Trottinette deleted successfully", HttpStatus.OK);
    }


}
