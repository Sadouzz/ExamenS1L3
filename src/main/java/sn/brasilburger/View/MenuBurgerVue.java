package sn.brasilburger.View;

import sn.brasilburger.Entity.MenuBurger;
import sn.brasilburger.Service.MenuBurgerService;

import java.util.List;
import java.util.Scanner;

public class MenuBurgerVue extends Vue {
    private MenuBurgerService service;

    public MenuBurgerVue(MenuBurgerService service) {
        this.service = service;
    }

    public MenuBurger saisieMenuBurger(Scanner scanner) {
        MenuBurger mb = new MenuBurger();
        mb.setId(service.numberOfRows() + 1);

        mb.setMenuId(Integer.parseInt(saisieChaine(scanner, "ID Menu : ")));
        mb.setBurgerId(Integer.parseInt(saisieChaine(scanner, "ID Burger : ")));
        mb.setQuantite(Integer.parseInt(saisieChaine(scanner, "Quantité : ")));

        return mb;
    }

    public void afficheMenuBurgers() {
        List<MenuBurger> liste = service.selectAll();
        if (liste.isEmpty()) {
            System.out.println("Aucun menu-burger.");
        } else {
            liste.forEach(System.out::println);
        }
    }
}
