package org.studentnr.backend.service;

import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.studentnr.backend.entities.Purchase;
import org.studentnr.backend.entities.Trip;
import org.studentnr.backend.entities.User;

import javax.persistence.EntityManager;
import java.time.LocalDate;

@Service
@Transactional
public class PurchaseService {

    @Autowired
    EntityManager em;



    //TODO: remove if not needed


}

