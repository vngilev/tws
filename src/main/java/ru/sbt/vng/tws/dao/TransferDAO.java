package ru.sbt.vng.tws.dao;

import org.springframework.stereotype.Repository;
import ru.sbt.vng.tws.model.Card;
import ru.sbt.vng.tws.model.Transfer;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class TransferDAO {
    private static final Map<Integer, Transfer> transferMap = new HashMap<Integer, Transfer>();

    static {
        initTransfers();
    }

    private static void initTransfers() {

    }

    public Transfer getTransfer(int id) {
        return transferMap.get(id);
    }

    public Transfer addTransfer(Card from, Card to, String amount) {
        Transfer currentTransfer = new Transfer(from, to, new BigDecimal(amount));
        transferMap.put(currentTransfer.getId(), currentTransfer);
        return currentTransfer;
    }

    public List<Transfer> getAllTransfers() {
/*        Collection<Transfer> c = transferMap.values();
        List<Transfer> list = new ArrayList<Transfer>();
        list.addAll(c);
        return list;*/
        return new ArrayList<>(transferMap.values());
    }

    public List<Transfer> getApprovedTransfers() {
        return new ArrayList<>(transferMap.values()).stream()
                .filter(Transfer::getApproved)
                .collect(Collectors.toList());
    }

    public List<Transfer> getTransfersBySenderName(String senderName){
        return new ArrayList<Transfer>(
                transferMap.values().stream()
                .filter(transfer -> transfer.getFrom().getCardId().equals(senderName))
                .collect(Collectors.toList())
        );
    }

    public List<Transfer> getTransfersByRecieverName(String recieverName){
        return new ArrayList<Transfer>(
                transferMap.values().stream()
                .filter(transfer -> transfer.getTo().getCardId().equals(recieverName))
                .collect(Collectors.toList())
        );
    }


}