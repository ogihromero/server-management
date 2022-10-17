package com.example.servermanagement.service.implementation;

import com.example.servermanagement.enumeration.Status;
import com.example.servermanagement.model.Server;
import com.example.servermanagement.repo.ServerRepo;
import com.example.servermanagement.service.ServerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.io.IOException;

import java.net.InetAddress;

import java.util.Collection;
import java.util.Random;

import static org.springframework.data.domain.PageRequest.of;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class ServerServiceImplementation implements ServerService {
    private final ServerRepo serverRepo;

    @Override
    public Server create(Server server) {
        log.info("Salvando novo server: {}", server.getName());
        server.setImageUrl(setServerImageUrl());
        return serverRepo.save(server);
    }

    private String setServerImageUrl() {
        String[] imagenames = {"server1.png", "server2.png", "server3.png","server4.png"};
        return ServletUriComponentsBuilder.fromCurrentContextPath().path("/server/image" + imagenames[new Random().nextInt(4)]).toUriString();
    }

    @Override
    public Server ping(String ipAddress) throws IOException {
        log.info("Realizando ping no servidor IP: {}", ipAddress);
        Server server = serverRepo.findByIpAddress(ipAddress);
        InetAddress address = InetAddress.getByName(ipAddress);
        server.setStatus(address.isReachable(1000)?  Status.SERVER_UP : Status.SERVER_DOWN);
        serverRepo.save(server);
        return server;
    }

    @Override
    public Collection<Server> list(int limit) {
        log.info("Buscando os servidores");
        return serverRepo.findAll(of(0, limit)).toList();
    }

    @Override
    public Server get(Long id) {
        log.info("Buscando o servidor por id: {}", id);
        return serverRepo.findById(id).get();
    }

    @Override
    public Server update(Server server) {
        log.info("Atualizando o servidor: {}", server.getName());
        return serverRepo.save(server);
    }

    @Override
    public Boolean delete(Long id) {
        log.info("Deletando o servidor por id: {}", id);
        serverRepo.deleteById(id);
        return Boolean.TRUE;
    }
}
