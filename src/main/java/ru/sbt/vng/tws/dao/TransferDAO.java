package ru.sbt.vng.tws.dao;

import org.springframework.stereotype.Repository;
import ru.sbt.vng.tws.model.Card;
import ru.sbt.vng.tws.model.Transfer;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class TransferDAO {
    private static final Map<Integer, Transfer> transferMap = new HashMap<>();

    static {
        initTransfers();
    }

    private static void initTransfers() {

    }

    public Transfer getTransfer(int id) {
        return transferMap.get(id);
    }

    public Transfer addTransfer(String from, String to, BigDecimal amount, Boolean approved) {
        Transfer currentTransfer = new Transfer(from, to, amount, approved);
        transferMap.put(currentTransfer.getId(), currentTransfer);
        return currentTransfer;
    }

    public List<Transfer> getAllTransfers() {
        return new ArrayList<>(transferMap.values());
    }

    public List<Transfer> getApprovedTransfers() {
        return new ArrayList<>(transferMap.values()).stream()
                .filter(Transfer::getApproved)
                .collect(Collectors.toList());
    }

    public List<Transfer> getTransfersByCardSender(String cardSender){
        return new ArrayList<Transfer>(
                transferMap.values().stream()
                .filter(transfer -> transfer.getFrom().equals(cardSender))
                .collect(Collectors.toList())
        );
    }

    public List<Transfer> getTransfersByCardReceiver(String cardReceiver){
        return new ArrayList<Transfer>(
                transferMap.values().stream()
                .filter(transfer -> transfer.getTo().equals(cardReceiver))
                .collect(Collectors.toList())
        );
    }


}