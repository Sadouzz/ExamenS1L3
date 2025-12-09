package sn.brasilburger.View;

import java.util.List;
import java.util.Scanner;
import sn.brasilburger.Entity.Menu;
import sn.brasilburger.Service.MenuService;

public class MenuVue extends Vue {
    private MenuService menuService;

    public MenuVue(MenuService menuService) {
        this.menuService = menuService;
    }

    public Menu saisieMenu(Scanner scanner) {
        Menu menu = new Menu();
        menu.setId(menuService.numberOfRows() + 1);

        menu.setLibelle(saisieChaine(scanner, "Saisir le libellé : "));
        menu.setImageUrl(saisieChaine(scanner, "Saisir l'URL de l'image : "));
        menu.setArchived(false);
        menu.setPrix(Double.parseDouble(saisieChaine(scanner, "Saisir le prix : ")));

        return menu;
    }


}
