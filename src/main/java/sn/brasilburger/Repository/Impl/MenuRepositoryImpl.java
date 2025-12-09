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



}
