package sn.brasilburger.config.factory.repository;




public final class RepositoryFactory {
    private static final PersistanceName PERSISTANCE_NAME = PersistanceName.Database;

    public static Object getInstance(EntityName entityName) {
        switch (PERSISTANCE_NAME) {
            case List:
                return null;
            case Database:
                return null;
            default:
                return null;
        }
    }

    public static Object getRepositoryDatabase(EntityName entityName) {
        switch (entityName) {
            case Burger:
                return null;
            case BurgerCategorie:
                return null;
            case Complement:
                return null;
            case Menu:
                return null;
            case MenuBurger:
                return null;
            case MenuComplement:
                return null;
            default:
                return null;
        }
    }
}
