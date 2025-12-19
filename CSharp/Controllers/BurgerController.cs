using Microsoft.AspNetCore.Mvc;
using Models;
using Data;
using Microsoft.EntityFrameworkCore;
using Services;
using System.Linq;

namespace Controllers
{
    public class BurgerController : Controller
    {
        private readonly BurgerService _service;
        //private const int PageSize = 5;

        public BurgerController(AppDbContext db)
        {
            _service = new BurgerService(db);
        }

        public IActionResult Index()
        {
            var burgers = _service.GetAll();
            return View(burgers);
        }


    }
}
