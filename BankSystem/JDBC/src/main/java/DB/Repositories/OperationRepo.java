package DB.Repositories;

import DB.DBConnection;
import Helpers.CurrencyCodeHelper;
import Models.Operation;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OperationRepo {
    public void addOperation(Operation operation) throws SQLException {
        String query = "insert into operation values(?,?,?,?,?,?,?,?)";
        Connection con = DBConnection.getConnection();
        PreparedStatement st = con.prepareStatement(query);

        st.setString(1, operation.getId().toString());
        st.setString(2, operation.getDate());
        st.setString(3, operation.getCurrency().toString());
        st.setString(4, operation.getFromAccount().toString());
        st.setString(5, operation.getToAccount().toString());
        st.setString(6, operation.getAmount().toString());
        st.setString(7, operation.getAmountBefore().toString());
        st.setString(8, operation.getAmountAfter().toString());

        st.execute();
    }

    public List<Operation> getOperationByUser(UUID accountId) throws SQLException {
        String query = "select * from operation where from_account = ?";
        Connection con = DBConnection.getConnection();
        PreparedStatement st = con.prepareStatement(query);

        st.setString(1, accountId.toString());
        st.setString(2, accountId.toString());

        ResultSet rs = st.executeQuery();

        List<Operation> result = new ArrayList<>();
        while(rs.next()) {
            String id = rs.getString("id");
            String date = rs.getString("date");
            String accCode = rs.getString("currency");
            String fromAccount = rs.getString("from_account");
            String toAccount = rs.getString("to_account");
            BigDecimal amount = rs.getBigDecimal("amount");
            BigDecimal amountBefore = rs.getBigDecimal("amount_before");
            BigDecimal amountAfter = rs.getBigDecimal("amount_after");
            Operation operation = new Operation(UUID.fromString(id), date, CurrencyCodeHelper.convert(accCode), UUID.fromString(fromAccount), UUID.fromString(toAccount), amount, amountBefore, amountAfter);
            result.add(operation);
        }
        return result;
    }
}
