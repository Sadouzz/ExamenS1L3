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
