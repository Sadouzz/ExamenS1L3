using Microsoft.AspNetCore.Mvc;
using Models;
using Services;

public class CatalogController : Controller
{
    private readonly BurgerService _burgerService;
    private readonly MenuService _menuService;
    private readonly ComplementService _complementService;

    public CatalogController(BurgerService burgerService, MenuService menuService, ComplementService complementService)
    {
        _burgerService = burgerService;
        _menuService = menuService;
        _complementService = complementService;
    }

    public IActionResult Index()
    {
        var vm = new Catalog
        {
            Burgers = _burgerService.GetAll(),
            Menus = _menuService.GetAll()
        };

        return View(vm);
    }

    public IActionResult Burger(long id)
    {
        var burger = _burgerService.GetById(id);
        return View("~/Views/Burger/Details.cshtml", burger);
    }

    public IActionResult Menu(long id)
    {
        var menu = _menuService.GetById(id);
        return View("~/Views/Menu/Details.cshtml", menu);
    }


    public IActionResult BurgerDetails(long id)
    {
        var burger = _burgerService.GetById(id);
        if (burger == null) return NotFound();
        return View(burger);
    }

    public IActionResult MenuDetails(long id)
    {
        var menu = _menuService.GetById(id);
        if (menu == null) return NotFound();
        return View(menu);
    }
}
