package Services;

import DB.Repositories.OperationRepo;
import Helpers.CurrencyCode;
import Models.Operation;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class OperationService {
    private OperationRepo operationRepo;
    private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    public OperationService(OperationRepo operationRepo) {
        this.operationRepo = operationRepo;
    }

    public boolean createOperation(CurrencyCode currencyCode, UUID accountFrom, UUID accountTo, BigDecimal money, BigDecimal moneyBefor, BigDecimal moneyAfter) {
        Date date = new Date();
        Operation operation = new Operation(UUID.randomUUID(), dateFormat.format(date), currencyCode, accountFrom, accountTo, money, moneyBefor, moneyAfter);
        try {
            operationRepo.addOperation(operation);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public List<Operation> getOperationByAccount(UUID accountId) {
        try {
            return operationRepo.getOperationByUser(accountId);
        } catch (Exception e) {
            return null;
        }
    }
}
