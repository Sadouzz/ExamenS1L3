namespace Models
{
    using System.ComponentModel.DataAnnotations.Schema;
    public class MenuBurger
    {
        [Column("id")]
        public long Id { get; set; }
        [Column("menu_id")]
        public long MenuId { get; set; }
        [Column("burger_id")]
        public long BurgerId { get; set; }
        [Column("quantite")]
        public int Quantite { get; set; }

        public Burger Burger { get; set; }
        public Menu Menu { get; set; }
    }

}