package edu.javacourse.student.business;

import edu.javacourse.student.dao.StreetRepository;
import edu.javacourse.student.dao.StudentOrderRepository;
import edu.javacourse.student.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class StudentOrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentOrderService.class);

    @Autowired
    private StudentOrderRepository dao;

    @Autowired
    private StreetRepository daoStreet;

    @Transactional
    public void testSave() {
        StudentOrder so = new StudentOrder();
        so.setHusband(buildPerson(false));
        so.setWife(buildPerson(true));
        dao.save(so);
    }

    @Transactional
    public void testGet() {
        List<StudentOrder> sos = dao.findAll();
        LOGGER.info(sos.get(0).getWife().getGivenName());
    }

    private Adult buildPerson(boolean wife) {
        Adult p = new Adult();
        p.setDateOfBirth(LocalDate.now());
        Address address = new Address();
        address.setPostCode("190000");
        address.setBuilding("21");
        address.setExtension("B");
        address.setApartment("199");
        Street street = daoStreet.getOne(1L);
        address.setStreet(street);
        p.setAddress(address );
        if (wife){
            p.setSurName("Рюрик");
            p.setGivenName( "Марфа");
            p.setPatronymic("Васильевна");
            p.setPassportSeria("WIFE_S");
            p.setPassportNumber("WIFE_N");
            p.setIssueDate(LocalDate.now());
        } else {
            p.setSurName("Рюрик");
            p.setGivenName("Иван");
            p.setPatronymic("Васильевич");
            p.setPassportSeria("HUSBAND_S");
            p.setPassportNumber("HUSBAND_N");
            p.setIssueDate(LocalDate.now());
        }
        return p;
    }
}
