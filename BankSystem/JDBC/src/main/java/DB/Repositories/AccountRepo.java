package DB.Repositories;

import DB.DBConnection;
import Helpers.CurrencyCodeHelper;
import Helpers.MoneyOperation;
import Models.Account;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AccountRepo {
    public void addAccount(Account account) throws SQLException {
        String query = "insert into account values(?,?,?,?)";
        Connection con = DBConnection.getConnection();
        PreparedStatement st = con.prepareStatement(query);

        st.setString(1, account.getId().toString());
        st.setString(2, account.getClientId().toString());
        st.setString(3, account.getAmount().toString());
        st.setString(4, account.getAccCode().toString());

        st.execute();
        st.close();
    }

    public List<Account> getAccountsByUser(UUID userId) throws SQLException {
        String query = "select * from account where client_id = ?";
        Connection con = DBConnection.getConnection();
        PreparedStatement st = con.prepareStatement(query);

        st.setString(1, userId.toString());

        ResultSet rs = st.executeQuery();
        st.close();

        List<Account> result = new ArrayList<>();
        while(rs.next()) {
            String id = rs.getString("id");
            String amount = rs.getString("amount");
            String accCode = rs.getString("acc_code");
            Account account = new Account(UUID.fromString(id), userId, BigDecimal.valueOf(Integer.parseInt(amount)), CurrencyCodeHelper.convert(accCode));
            result.add(account);
        }
        return result;
    }

    public Account getAccountById(UUID id) throws SQLException {
        String query = "select * from account where id = ?";
        Connection con = DBConnection.getConnection();
        PreparedStatement st = con.prepareStatement(query);

        st.setString(1, id.toString());

        ResultSet rs = st.executeQuery();
        st.close();

        if(rs.next()) {
            String clientId = rs.getString("client_id");
            String amount = rs.getString("amount");
            String accCode = rs.getString("acc_code");
            return new Account(id, UUID.fromString(clientId), BigDecimal.valueOf(Integer.parseInt(amount)), CurrencyCodeHelper.convert(accCode));
        } else {
            return null;
        }
    }

    public void updateAccountAmount(Account account) throws SQLException {
        String query = "update account set amount = ? where id = ?";
        Connection con = DBConnection.getConnection();
        PreparedStatement st = con.prepareStatement(query);

        st.setString(1, account.getAmount().toString());
        st.setString(2, account.getId().toString());

        st.executeUpdate();
        st.close();
    }
}
