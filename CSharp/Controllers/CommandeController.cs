using Microsoft.AspNetCore.Mvc;
using Models;
using Services;
using Data;
using Microsoft.EntityFrameworkCore;
using System;
using System.Linq;
using System.Security.Claims;

public class CommandeController : Controller
{
    private readonly BurgerService _burgerService;
    private readonly MenuService _menuService;
    private readonly ComplementService _complementService;
    private readonly QuartierService _quartierService;
    private readonly ZoneService _zoneService;
    private readonly CommandeService _commandeService;
    private readonly AppDbContext _db;

    public CommandeController(
        BurgerService burgerService,
        MenuService menuService,
        ComplementService complementService,
        QuartierService quartierService,
        ZoneService zoneService,
        CommandeService commandeService,
        AppDbContext db)
    {
        _burgerService = burgerService;
        _menuService = menuService;
        _complementService = complementService;
        _quartierService = quartierService;
        _zoneService = zoneService;
        _commandeService = commandeService;
        _db = db;
    }

    // GET : /commande/commander?type=burger&id=2
    [HttpGet]
    public IActionResult Commander(string type, long id)
    {
        ViewBag.Type = type;

        if (type == "burger")
        {
            var burger = _burgerService.GetById(id);
            if (burger == null) return NotFound();
            ViewBag.Item = burger;
            ViewBag.ItemName = burger.Libelle;  // ⭐ Ajoutez le nom
            ViewBag.ItemId = burger.Id;         // ⭐ Ajoutez l'ID
            return RedirectToAction("ChoixComplements", new { id });
        }
        else if (type == "menu")
        {
            var menu = _menuService.GetById(id);
            if (menu == null) return NotFound();
            ViewBag.Item = menu;
            ViewBag.ItemName = menu.Libelle;    // ⭐ Ajoutez le nom
            ViewBag.ItemId = menu.Id;           // ⭐ Ajoutez l'ID
            return RedirectToAction("InfosLivraison", new { type = "menu", itemId = id });
        }
        else
        {
            return BadRequest("Type d'article inconnu");
        }

        // ⭐ CORRECTION: Changez Complements en AvailableComplements
        ViewBag.AvailableComplements = _complementService.GetAll();
        ViewBag.quartiers = _quartierService.GetAll();
        ViewBag.zones = _zoneService.GetAll();

        return View(); // vue Commander.cshtml
    }

    [HttpGet]
    public IActionResult ChoixComplements(long id)
    {
        var burger = _burgerService.GetById(id);
        if (burger == null) return NotFound();

        ViewBag.Burger = burger;
        ViewBag.Complements = _complementService.GetAll();

        return View();
    }

    [HttpPost, HttpGet]
    public IActionResult InfosLivraison(
    string type,
    long itemId,
    long[] selectedComplements)
    {
        ViewBag.Type = type;
        ViewBag.ItemId = itemId;

        List<Complement> complements = new List<Complement>();

        if (selectedComplements != null)
        {
            complements = selectedComplements
                .Select(id => _complementService.GetById(id))
                .Where(c => c != null)
                .ToList();
        }

        ViewBag.SelectedComplements = complements;
        object mainItem;
        if(type == "menu")
        {
            mainItem = _menuService.GetById(itemId);
        }
        else
        {
            mainItem = _burgerService.GetById(itemId);
        }
        ViewBag.Quartiers = _quartierService.GetAll();
        ViewBag.MainItem = mainItem;

        return View();
    }


    // POST : /commande/create
    [HttpPost]
    public IActionResult Create(string type, long itemId, string adresse, TypeRetrait typeRetrait, long quartier, int quantite = 1, long[] selectedComplements = null)
    {
        var userIdClaim = User.FindFirst(ClaimTypes.NameIdentifier);
        var userId = long.Parse(userIdClaim.Value);
        var commande = new Commande
        {
            Adresse = adresse,
            CreatedAt = DateTime.UtcNow,
            UpdatedAt = DateTime.UtcNow,
            IsPaid = false,
            Statut = StatutCommande.EN_ATTENTE,
            TypeRetrait = typeRetrait,
            QuartierId = quartier,
            ClientId = userId
        };

        _db.Commandes.Add(commande);
        _db.SaveChanges();

        double total = 0;

        // Ajouter l'item principal (burger ou menu)
        if (type == "burger")
        {
            var burger = _burgerService.GetById(itemId);
            var item = new CommandeItem
            {
                CommandeId = commande.Id,
                BurgerId = burger.Id,
                Quantite = quantite,
                PrixTotal = burger.Prix * quantite
            };
            total += item.PrixTotal;
            _db.CommandeItems.Add(item);
        }
        else if (type == "menu")
        {
            var menu = _menuService.GetById(itemId);
            var item = new CommandeItem
            {
                CommandeId = commande.Id,
                MenuId = menu.Id,
                Quantite = quantite,
                PrixTotal = menu.Prix * quantite
            };
            total += item.PrixTotal;
            _db.CommandeItems.Add(item);
        }

        // Ajouter les compléments sélectionnés
        if (selectedComplements != null)
        {
            foreach (var compId in selectedComplements)
            {
                var comp = _complementService.GetById(compId);
                var item = new CommandeItem
                {
                    CommandeId = commande.Id,
                    ComplementId = comp.Id,
                    Quantite = 1,
                    PrixTotal = comp.Prix
                };
                total += comp.Prix;
                _db.CommandeItems.Add(item);
            }
        }

        commande.MontantHorsLivraison = total;
        double taxes = 0;
        if (typeRetrait == TypeRetrait.LIVRAISON)
        {
            taxes = _zoneService.GetById(_quartierService.GetById(quartier).ZoneId).PrixLivraison;
        }
        commande.MontantTotal = total + taxes;

        _db.SaveChanges();

        return RedirectToAction("UserCommandes", new { id = userId });
    }

    // GET : /commande/confirmation/5
    [HttpGet]
    public IActionResult Confirmation(long id)
    {
        var commande = _db.Commandes
                          .Include(c => c.CommandeItems)
                          .ThenInclude(ci => ci.Burger)
                          .Include(c => c.CommandeItems)
                          .ThenInclude(ci => ci.Menu)
                          .Include(c => c.CommandeItems)
                          .ThenInclude(ci => ci.Complement)
                          .FirstOrDefault(c => c.Id == id);

        if (commande == null) return NotFound();

        return View(commande); // vue Confirmation.cshtml
    }

    public IActionResult UserCommandes(long id)
    {
        var userIdClaim = User.FindFirst(ClaimTypes.NameIdentifier);
        var userId = long.Parse(userIdClaim.Value);
        var commandes = _commandeService.GetByUserId(id);

        ViewBag.Commandes = commandes;
        return View();
    }
}
