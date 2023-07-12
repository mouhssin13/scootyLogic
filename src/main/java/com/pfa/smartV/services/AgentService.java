package com.pfa.smartV.services;

import com.pfa.smartV.models.Agent;
import com.pfa.smartV.models.User;
import com.pfa.smartV.repositories.AgentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AgentService {

    private final AgentRepository agentRepository;

    @Autowired
    public AgentService(AgentRepository agentRepository){
        this.agentRepository = agentRepository;
    }

    public List<Agent> getAllAgents(){
        return agentRepository.findAll();
    }

    public Agent getAgentById(Long id){
        Optional<Agent> agent = agentRepository.findById(id);
        if(agent.isPresent()){
            return agent.get();
        }else{
            throw new IllegalStateException("Agent doesn't exist");
        }
    }

    public Agent addAgent(Agent agent){
        Optional<Agent> agentByEmail = agentRepository.findAgentByEmail(agent.getEmail());
        Optional<Agent> agentByCIN = agentRepository.findAgentByCIN(agent.getCIN());

        if(agentByEmail.isPresent() || agentByCIN.isPresent()){
            throw  new IllegalStateException("this agent is already exist");
        }
        return agentRepository.save(agent);
    }

    @Transactional
    public Agent updateAgent(Long id, String name, String email, String tel, String cin){
        Agent agent = agentRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("Agent not exist")
        );

        if(email != null && email.length() > 0 && !Objects.equals(agent.getEmail(),email)){
            Optional<Agent> agentByEmail = agentRepository.findAgentByEmail(email);
            if(agentByEmail.isPresent()){
                throw new IllegalStateException("Email already token");
            }
            agent.setEmail(email);
        }
        if(cin != null && cin.length() > 0 && !Objects.equals(agent.getCIN(),cin)){
            Optional<Agent> agentByCIN = agentRepository.findAgentByCIN(cin);
            if(agentByCIN.isPresent()){
                throw new IllegalStateException("CIN already token");
            }
            agent.setCIN(cin);
        }
        if(name != null && name.length() > 0 && !Objects.equals(agent.getFullName(),name)){
            agent.setFullName(name);
        }
        if(tel != null && tel.length() > 0 && !Objects.equals(agent.getTel(),tel)){
            agent.setTel(tel);
        }

        return agentRepository.save(agent);

    }

    public void deleteAgent(Long id){
        boolean exist = agentRepository.existsById(id);
        if(exist){
            agentRepository.deleteById(id);
        }else {
            throw new IllegalStateException("this Agent already not exist");
        }
    }
}
