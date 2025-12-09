package sn.brasilburger.View;

import sn.brasilburger.Entity.Burger;
import sn.brasilburger.Service.BurgerService;

import java.util.List;
import java.util.Scanner;

public class BurgerVue extends Vue {
    private BurgerService service;

    public BurgerVue(BurgerService service) {
        this.service = service;
    }

    public Burger saisieBurger(Scanner scanner) {
        Burger b = new Burger();
        b.setId(service.numberOfRows() + 1);

        b.setLibelle(saisieChaine(scanner, "Libellé : "));
        b.setDesc(saisieChaine(scanner, "Description : "));
        b.setPrix(Double.parseDouble(saisieChaine(scanner, "Prix : ")));
        b.setImageUrl(saisieChaine(scanner, "URL Image : "));
        b.setArchived(false);
        b.setBurgerCategorieId(Integer.parseInt(saisieChaine(scanner, "ID Catégorie : ")));

        return b;
    }

    public void afficheBurgers() {
        List<Burger> liste = service.selectAll();
        if (liste.isEmpty()) {
            System.out.println("Aucun burger.");
        } else {
            liste.forEach(System.out::println);
        }
    }
}
