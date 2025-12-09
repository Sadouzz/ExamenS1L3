package sn.brasilburger.View;

import sn.brasilburger.Entity.MenuComplement;
import sn.brasilburger.Service.MenuComplementService;

import java.util.List;
import java.util.Scanner;

public class MenuComplementVue extends Vue {
    private MenuComplementService service;

    public MenuComplementVue(MenuComplementService service) {
        this.service = service;
    }

    public MenuComplement saisieMenuComplement(Scanner scanner) {
        MenuComplement mc = new MenuComplement();
        mc.setId(service.numberOfRows() + 1);

        mc.setMenuId(Integer.parseInt(saisieChaine(scanner, "ID Menu : ")));
        mc.setComplementId(Integer.parseInt(saisieChaine(scanner, "ID Complément : ")));
        mc.setQuantite(Integer.parseInt(saisieChaine(scanner, "Quantité : ")));

        return mc;
    }


}
