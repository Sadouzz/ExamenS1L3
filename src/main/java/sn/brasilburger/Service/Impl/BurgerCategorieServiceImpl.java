package sn.brasilburger.Service.Impl;

import sn.brasilburger.Entity.BurgerCategorie;
import sn.brasilburger.Repository.BurgerCategorieRepository;
import sn.brasilburger.Service.BurgerCategorieService;

import java.util.List;
import java.util.Optional;

public class BurgerCategorieServiceImpl implements BurgerCategorieService {

    private BurgerCategorieRepository burgerCategorieRepository;

    public BurgerCategorieServiceImpl(BurgerCategorieRepository burgerCategorieRepository) {
        this.burgerCategorieRepository = burgerCategorieRepository;
    }

    @Override
    public void createBurgerCategorie(BurgerCategorie burgerCategorie) {
        burgerCategorieRepository.insert(burgerCategorie);
    }

}
