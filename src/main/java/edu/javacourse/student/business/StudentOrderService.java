package edu.javacourse.student.business;

import edu.javacourse.student.dao.*;
import edu.javacourse.student.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentOrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentOrderService.class);

    @Autowired
    private StudentOrderRepository dao;

    @Autowired
    private StreetRepository daoStreet;

    @Autowired
    private StudentOrderStatusRepository studentOrderStatusRepository;

    @Autowired
    private PassportOfficeRepository passportOfficeRepository;

    @Autowired
    private RegisterOfficeRepository registerOfficeRepository;

    @Autowired
    private UniversityRepository universityRepository;

    @Autowired
    private StudentOrderChildRepository studentOrderChildRepository;

    @Transactional
    public void testSave() {
        StudentOrder so = new StudentOrder();
        so.setStudentOrderDate(LocalDateTime.now());
        StudentOrderStatus status = studentOrderStatusRepository.getOne(1L);
        so.setStudentOrderStatus(status);
        so.setHusband(buildPerson(false));
        so.setWife(buildPerson(true));
        so.setRegisterOffice(registerOfficeRepository.getOne(1L ));
        so.setMarriageDate(LocalDate.now());
        so.setCertificateNumber("CERTIFICATE");
        dao.save(so);

        StudentOrderChild studentOrderChild = buildChild(so);
        studentOrderChildRepository.save(studentOrderChild);
    }

    @Transactional
    public void testGet() {
        List<StudentOrder> sos = dao.findAll();
        LOGGER.info(sos.get(0).getWife().getGivenName());
        LOGGER.info(sos.get(0).getChildrenOrders().get(0).getChild().getGivenName());
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
            p.setPassportOffice(passportOfficeRepository.getOne(1L));
            p.setStudentNumber("12345");
            p.setUniversity(universityRepository.getOne(1L));
        } else {
            p.setSurName("Рюрик");
            p.setGivenName("Иван");
            p.setPatronymic("Васильевич");
            p.setPassportSeria("HUSBAND_S");
            p.setPassportNumber("HUSBAND_N");
            p.setIssueDate(LocalDate.now());
            p.setPassportOffice(passportOfficeRepository.getOne(1L));
            p.setStudentNumber("54321");
            p.setUniversity(universityRepository.getOne(1L));
        }
        return p;
    }

    private StudentOrderChild buildChild(StudentOrder studentOrder) {
        StudentOrderChild childOrder = new StudentOrderChild();
        childOrder.setStudentOrder(studentOrder);
        Address address = new Address();
        address.setPostCode("190000");
        address.setBuilding("21");
        address.setExtension("B");
        address.setApartment("199");
        Street street = daoStreet.getOne(1L);
        address.setStreet(street);
        Child child = new Child();
        child.setDateOfBirth(LocalDate.now());
        child.setAddress(address);
        child.setSurName("Рюрик");
        child.setGivenName("Дмитрий");
        child.setPatronymic("Иванович");
        child.setRegisterOffice(registerOfficeRepository.getOne(1L));
        child.setCertificateDate(LocalDate.now());
        child.setCertificateNumber("BIRTH_NUMBER");
        childOrder.setChild(child);
        return childOrder;
    }
}
