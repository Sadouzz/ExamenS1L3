namespace Models
{
    using System.ComponentModel.DataAnnotations.Schema;
    public class LivraisonAffection
    {
        [Column("id")]
        public long Id { get; set; }
        [Column("commande_id")]
        public long CommandeId { get; set; }
        [Column("livreur_id")]
        public long LivreurId { get; set; }
        [Column("zone_id")]
        public long ZoneId { get; set; }
        [Column("statut")]
        public StatutLivraison Statut { get; set; }
    }
    public enum StatutLivraison { EN_ATTENTE, EN_COURS, TERMINEE }

}