package sn.brasilburger.Repository.Impl;

import sn.brasilburger.Entity.MenuBurger;
import sn.brasilburger.Repository.MenuBurgerRepository;
import sn.brasilburger.config.database.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class MenuBurgerRepositoryImpl implements MenuBurgerRepository {
    private Database database;

    public MenuBurgerRepositoryImpl(Database database) {
        this.database = database;
    }

    @Override
    public int numberOfRows() {
        int count = 0;
        try {
            if (!database.isConnected()) {
                throw new SQLException("Erreur de connexion à la BD");
            }
            Connection conn = database.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) FROM menu_burger");
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                count = rs.getInt(1);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }



    private MenuBurger toEntity(ResultSet rs) throws SQLException {
        MenuBurger mb = new MenuBurger();
        mb.setId(rs.getInt("id"));
        mb.setMenuId(rs.getInt("menu_id"));
        mb.setBurgerId(rs.getInt("burger_id"));
        mb.setQuantite(rs.getInt("quantite"));
        return mb;
    }

}
