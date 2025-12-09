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







}
