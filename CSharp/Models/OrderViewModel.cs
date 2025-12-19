namespace Models
{
    using Models;
    using System.Collections.Generic;

    public class OrderViewModel
    {
        public string ItemType { get; set; } 
        public long ItemId { get; set; }
        public string ItemName { get; set; }

        public List<Complement> AvailableComplements { get; set; } = new List<Complement>();
    }

}