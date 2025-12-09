package sn.brasilburger.Repository.Impl;

import sn.brasilburger.Entity.MenuComplement;
import sn.brasilburger.Repository.MenuComplementRepository;
import sn.brasilburger.config.database.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class MenuComplementRepositoryImpl implements MenuComplementRepository {
    private Database database;

    public MenuComplementRepositoryImpl(Database database) {
        this.database = database;
    }



}
