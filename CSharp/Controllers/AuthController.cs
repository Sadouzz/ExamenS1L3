using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.Authentication;
using Microsoft.AspNetCore.Authentication.Cookies;
using Models;
using Models.ViewModels;
using System.Security.Claims;
using Data;

public class AuthController : Controller
{
    private readonly AppDbContext _db;
    private readonly PasswordHasher<User> _hasher = new();

    public AuthController(AppDbContext db)
    {
        _db = db;
    }

    // ===== LOGIN =====
    public IActionResult Login() => View();

    [HttpPost]
    public async Task<IActionResult> Login(LoginViewModel model)
    {
        if (!ModelState.IsValid) return View(model);

        var user = _db.Users.FirstOrDefault(u => u.Email == model.Email && !u.IsArchived);
        if (user == null)
        {
            ModelState.AddModelError("", "Email ou mot de passe incorrect");
            return View(model);
        }

        var result = _hasher.VerifyHashedPassword(user, user.Password, model.Password);
        if (result == PasswordVerificationResult.Failed)
        {
            ModelState.AddModelError("", "Email ou mot de passe incorrect");
            return View(model);
        }

        var claims = new List<Claim>
        {
            new Claim(ClaimTypes.NameIdentifier, user.Id.ToString()),
            new Claim(ClaimTypes.Name, $"{user.Prenom} {user.Nom}"),
            new Claim(ClaimTypes.Role, user.Role.ToString()),
        };

        var identity = new ClaimsIdentity(claims, CookieAuthenticationDefaults.AuthenticationScheme);
        await HttpContext.SignInAsync(
            CookieAuthenticationDefaults.AuthenticationScheme,
            new ClaimsPrincipal(identity));

        return RedirectToAction("Index", "Catalog");
    }

    // ===== REGISTER =====
    public IActionResult Register() => View();

    [HttpPost]
    public IActionResult Register(RegisterViewModel model)
    {
        if (!ModelState.IsValid) return View(model);

        if (_db.Users.Any(u => u.Email == model.Email))
        {
            ModelState.AddModelError("Email", "Cet email existe déjà");
            return View(model);
        }

        var user = new User
        {
            Nom = model.Nom,
            Prenom = model.Prenom,
            Tel = model.Tel,
            Email = model.Email,
            Role = RoleUser.CLIENT,
            CreatedAt = DateTime.UtcNow,
            IsArchived = false
        };

        user.Password = _hasher.HashPassword(user, model.Password);

        _db.Users.Add(user);
        _db.SaveChanges();

        return RedirectToAction("Login");
    }

    public async Task<IActionResult> Logout()
    {
        await HttpContext.SignOutAsync();
        return RedirectToAction("Login");
    }
}
