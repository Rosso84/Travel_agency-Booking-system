package org.studentnr.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Service
@Transactional
public class TripService {

    @Autowired
    EntityManager em;

    public Long createTrip(){
        return null;
    }
}
