package sn.brasilburger.Repository.Impl;

import sn.brasilburger.Entity.Burger;
import sn.brasilburger.Repository.BurgerRepository;
import sn.brasilburger.config.database.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class BurgerRepositoryImpl implements BurgerRepository {
    private Database database;

    public BurgerRepositoryImpl(Database database) {
        this.database = database;
    }





    private Burger toEntity(ResultSet rs) throws SQLException {
        Burger burger = new Burger();
        burger.setId(rs.getInt("id"));
        burger.setLibelle(rs.getString("libelle"));
        burger.setDesc(rs.getString("description"));
        burger.setPrix(rs.getDouble("prix"));
        burger.setImageUrl(rs.getString("image_url"));
        burger.setArchived(rs.getBoolean("is_archived"));
        burger.setBurgerCategorieId(rs.getInt("burger_categorie_id"));
        return burger;
    }

}
