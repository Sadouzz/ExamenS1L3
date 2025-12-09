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



}
