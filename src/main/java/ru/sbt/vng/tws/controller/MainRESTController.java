package ru.sbt.vng.tws.controller;

import java.util.List;

import ru.sbt.vng.tws.dao.CardDAO;
import ru.sbt.vng.tws.dao.EmployeeDAO;
import ru.sbt.vng.tws.dao.TransferDAO;
import ru.sbt.vng.tws.dto.TransferDTO;
import ru.sbt.vng.tws.model.Card;
import ru.sbt.vng.tws.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.sbt.vng.tws.model.Transfer;

@RestController
public class MainRESTController {

    @Autowired
    private EmployeeDAO employeeDAO;

    @Autowired
    private CardDAO cardDAO;

    @Autowired
    private TransferDAO transferDAO;


    @RequestMapping("/")
    @ResponseBody
    public String welcome() {
        return "Welcome to WebService TransfersCard2Card";
    }

    // URL:
    // http://localhost:8080/cards
    @RequestMapping(value = "/cards", //
            method = RequestMethod.GET, //
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public List<Card> getCards() {
        List<Card> list = cardDAO.getAllCards();
        return list;
    }

    // URL:
    // http://localhost:8080/transfers
    @RequestMapping(value = "/transfers", //
            method = RequestMethod.GET, //
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public List<Transfer> getTransfers() {
        List<Transfer> list = transferDAO.getAllTransfers();
        return list;
    }

    // URL:
    // http://localhost:8080/transfers/{cardFrom}/from
    @RequestMapping(value = "/transfers/{cardFrom}/from", //
            method = RequestMethod.GET, //
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public List<Transfer> getTransfersBySenderName(@PathVariable("cardFrom") String cardSender) {
        return transferDAO.getTransfersBySenderName(cardSender);
    }

    // URL:
    // http://localhost:8080/transfers/{cardFrom}/from
    @RequestMapping(value = "/transfers/{cardTo}/to", //
            method = RequestMethod.GET, //
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public List<Transfer> getTransfersByrecieverName(@PathVariable("cardTo") String cardReciever) {
        return transferDAO.getTransfersByRecieverName(cardReciever);
    }


    // URL:
    // http://localhost:8080/transfer
    @RequestMapping(value = "/transfer", //
            method = RequestMethod.POST, //
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public Transfer addTransfer(@RequestBody TransferDTO transferDto) {
        return transferDAO.addTransfer(cardDAO.getCard(transferDto.getFrom()),
                cardDAO.getCard(transferDto.getTo()),transferDto.getAmount().toString());
    }












    // URL:
    // http://localhost:8080/employees
    // http://localhost:8080/employees.xml
    // http://localhost:8080/employees.json
    @RequestMapping(value = "/employees", //
            method = RequestMethod.GET, //
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public List<Employee> getEmployees() {
        List<Employee> list = employeeDAO.getAllEmployees();
        return list;
    }

    // URL:
    // http://localhost:8080/SpringMVCRESTful/employee/{empNo}
    // http://localhost:8080/SpringMVCRESTful/employee/{empNo}.xml
    // http://localhost:8080/SpringMVCRESTful/employee/{empNo}.json
    @RequestMapping(value = "/employee/{empNo}", //
            method = RequestMethod.GET, //
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public Employee getEmployee(@PathVariable("empNo") String empNo) {
        return employeeDAO.getEmployee(empNo);
    }

    // URL:
    // http://localhost:8080/SpringMVCRESTful/employee
    // http://localhost:8080/SpringMVCRESTful/employee.xml
    // http://localhost:8080/SpringMVCRESTful/employee.json
    @RequestMapping(value = "/employee", //
            method = RequestMethod.POST, //
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public Employee addEmployee(@RequestBody Employee emp) {
        return employeeDAO.addEmployee(emp);
    }

    // URL:
    // http://localhost:8080/SpringMVCRESTful/employee
    // http://localhost:8080/SpringMVCRESTful/employee.xml
    // http://localhost:8080/SpringMVCRESTful/employee.json
    @RequestMapping(value = "/employee", //
            method = RequestMethod.PUT, //
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public Employee updateEmployee(@RequestBody Employee emp) {

        return employeeDAO.updateEmployee(emp);
    }

    // URL:
    // http://localhost:8080/SpringMVCRESTful/employee/{empNo}
    @RequestMapping(value = "/employees/{empNo}", //
            method = RequestMethod.DELETE, //
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public void deleteEmployee(@PathVariable("empNo") String empNo) {
        employeeDAO.deleteEmployee(empNo);
    }

}