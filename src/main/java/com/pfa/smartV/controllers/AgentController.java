package com.pfa.smartV.controllers;


import com.pfa.smartV.models.Agent;
import com.pfa.smartV.services.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/smartV/agents")
public class AgentController {
    private final AgentService agentService;

    @Autowired
    public AgentController(AgentService agentService2){
        this.agentService = agentService2;
    }

    @GetMapping
    public List<Agent> getAllAgents(){
        return agentService.getAllAgents();
    }

    @GetMapping("{id}")
    public ResponseEntity<Agent> getAgentByID(@PathVariable("id") Long id){
        return new ResponseEntity<Agent>(agentService.getAgentById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Agent> addAgent(@RequestBody Agent agent){
        return new ResponseEntity<Agent>(agentService.addAgent(agent), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Agent> updateAgent(@PathVariable("id") Long id, @RequestParam(required = false) String fullName, @RequestParam(required = false) String email,
                                             @RequestParam(required = false) String tel , @RequestParam(required = false) String cin){
        return new ResponseEntity<Agent>(agentService.updateAgent(id,fullName,email,tel,cin), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteAgent(@PathVariable("id") Long id){
        agentService.deleteAgent(id);
        return new ResponseEntity<String>("Agent deleted successfully", HttpStatus.OK);
    }
}
