package sn.brasilburger.View;

import java.util.Scanner;

public class Vue {
    protected static Scanner scanner = new Scanner(System.in);

    public static String saisieChaine(Scanner scanner, String message) {
        String chaine;
        System.out.print(message);
        do {
            chaine = scanner.nextLine();
        } while (chaine.isEmpty());
        return chaine;
    }



}
