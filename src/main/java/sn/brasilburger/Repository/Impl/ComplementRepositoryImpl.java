package sn.brasilburger.Repository.Impl;

import sn.brasilburger.Entity.Complement;
import sn.brasilburger.Entity.Enum.TypeComplement;
import sn.brasilburger.Repository.ComplementRepository;
import sn.brasilburger.config.database.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ComplementRepositoryImpl implements ComplementRepository {
    private Database database;

    public ComplementRepositoryImpl(Database database) {
        this.database = database;
    }




}
