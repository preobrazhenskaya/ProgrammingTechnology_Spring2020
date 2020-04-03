package DB.Repositories;

import DB.DBConnection;
import Models.User;

import java.sql.*;
import java.util.UUID;

public class UserRepo {
    public void addUser(User user) throws SQLException {
        String query = "insert into user values(?,?,?,?,?)";
        Connection con = DBConnection.getConnection();
        PreparedStatement st = con.prepareStatement(query);

        st.setString(1, user.getId().toString());
        st.setString(2, user.getLogin());
        st.setString(3, user.getPassword());
        st.setString(4, user.getAddress());
        st.setString(5, user.getPhone());

        st.execute();
    }

    public User getUserByLogin(String login) throws SQLException {
        String query = "select * from user where login = ?";
        Connection con = DBConnection.getConnection();
        PreparedStatement st = con.prepareStatement(query);

        st.setString(1, login);

        ResultSet rs = st.executeQuery();

        if(rs.next()) {
            UUID id = UUID.fromString(rs.getString("id"));
            String password = rs.getString("password");
            String address = rs.getString("address");
            String phone = rs.getString("phone");
            return new User(id, login, password, address, phone);
        } else {
            return null;
        }
    }

    public User getUserByPhone(String phone) throws SQLException {
        String query = "select * from user where phone = ?";
        Connection con = DBConnection.getConnection();
        PreparedStatement st = con.prepareStatement(query);

        st.setString(1, phone);

        ResultSet rs = st.executeQuery();

        if(rs.next()) {
            UUID id = UUID.fromString(rs.getString("id"));
            String login = rs.getString("login");
            String password = rs.getString("password");
            String address = rs.getString("address");
            return new User(id, login, password, address, phone);
        } else {
            return null;
        }
    }
}
