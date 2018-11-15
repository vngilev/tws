package ru.sbt.vng.tws.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import ru.sbt.vng.tws.dto.TransferDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.sbt.vng.tws.model.Transfer;
import ru.sbt.vng.tws.service.TransferService;

import javax.servlet.http.HttpServletResponse;

@RestController
public class MainRESTController {

   @Autowired
    private TransferService transferService;

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
    public Map<String, BigDecimal> getCards() {
        return transferService.getCards();
    }

    // URL:
    // http://localhost:8080/transfers
    @RequestMapping(value = "/transfers", //
            method = RequestMethod.GET, //
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public List<Transfer> getTransfers() { return transferService.getTransfers(); }

    // URL:
    // http://localhost:8080/transfers/{cardFrom}/from
    @RequestMapping(value = "/transfers/{cardFrom}/from", //
            method = RequestMethod.GET, //
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public List<Transfer> getTransfersBySenderName(@PathVariable("cardFrom") String cardSender) {
        return transferService.getTransfersByCardSender(cardSender);
    }

    // URL:
    // http://localhost:8080/transfers/{cardFrom}/from
    @RequestMapping(value = "/transfers/{cardTo}/to", //
            method = RequestMethod.GET, //
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public List<Transfer> getTransfersByrecieverName(@PathVariable("cardTo") String cardReciever) {
        return transferService.getTransfersByCardReceiver(cardReciever);
    }

    // URL:
    // http://localhost:8080/transfer
    @RequestMapping(value = "/transfer", //
            method = RequestMethod.POST, //
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public Transfer addTransfer(@RequestBody TransferDTO transferDto) {
        return transferService.addTransfer(transferDto);
    }

}