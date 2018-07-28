package de.rieckpil.learning.ping.control;

import de.rieckpil.learning.ping.entity.Ping;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

@Stateless
public class PingManager implements PingService {

    @Inject
    PingManufacturer pingManufacturer;

    private Ping ping;

    @PostConstruct
    public void init() {
        this.ping = new Ping("Hello" + pingManufacturer.manufacturePing() + "!");
    }

    public Ping getPing() {
        return ping;
    }

}
