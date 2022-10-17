package com.example.servermanagement.repo;

import com.example.servermanagement.model.Server;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServerRepo extends JpaRepository <Server, Long>{
    //Server findbyName(String name);
    Server findByIpAddress(String ipAddress);

}
