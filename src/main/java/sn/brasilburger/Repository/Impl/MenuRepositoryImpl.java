package sn.brasilburger.Repository.Impl;

import sn.brasilburger.Entity.Menu;
import sn.brasilburger.Repository.MenuRepository;
import sn.brasilburger.config.database.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class MenuRepositoryImpl implements MenuRepository {
    private Database database;

    public MenuRepositoryImpl(Database database) {
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
            PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) FROM menus");
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



    private Menu toEntity(ResultSet rs) throws SQLException {
        Menu menu = new Menu();
        menu.setId(rs.getInt("id"));
        menu.setLibelle(rs.getString("libelle"));
        menu.setImageUrl(rs.getString("image_url"));
        menu.setArchived(rs.getBoolean("is_archived"));
        menu.setPrix(rs.getDouble("prix"));
        return menu;
    }

}
