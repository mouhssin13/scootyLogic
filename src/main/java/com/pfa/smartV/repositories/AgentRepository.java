package com.pfa.smartV.repositories;


import com.pfa.smartV.models.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AgentRepository
                extends JpaRepository<Agent, Long> {
    Optional<Agent> findAgentByEmail(String email);
    Optional<Agent> findAgentByCIN(String cin);
}