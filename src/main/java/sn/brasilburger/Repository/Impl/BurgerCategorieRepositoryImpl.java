package sn.brasilburger.Repository.Impl;

import sn.brasilburger.Entity.BurgerCategorie;
import sn.brasilburger.Repository.BurgerCategorieRepository;
import sn.brasilburger.config.database.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class BurgerCategorieRepositoryImpl implements BurgerCategorieRepository {
    private Database database;

    public BurgerCategorieRepositoryImpl(Database database) {
        this.database = database;
    }



}
