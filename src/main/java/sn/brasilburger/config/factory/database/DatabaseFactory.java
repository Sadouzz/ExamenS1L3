package sn.brasilburger.config.factory.database;

import sn.brasilburger.config.database.Database;
import sn.brasilburger.config.database.DatabaseImpl;

public final class DatabaseFactory {
    private static final SGDBName sgbdName = SGDBName.POSTGRESQL;

    private DatabaseFactory() {
    }

    public static Database getInstance() {
        return DatabaseImpl.getInstance(EntityManager.persistanceUnit(sgbdName));
    }
}
