package game.items;

public class Weapon extends Item {
    private int damage;

    public Weapon(int id, String name, int price, int damage) {
        super(id, name, price);
        this.damage = damage;
    }

    public static Weapon[] weapons() {
        // Setting weapons manually
        Weapon[] weaponList = new Weapon[3];
        weaponList[0] = new Weapon(1, "Pistol", 25, 2);
        weaponList[1] = new Weapon(2, "Sword", 35, 4);
        weaponList[2] = new Weapon(3, "Rifle", 45, 6);

        return weaponList;
    }

    public static Weapon getWeaponObjById(int id) {
        // Return weapon object by checking weapon id.
        for (Weapon weapon : Weapon.weapons()) {
            if (weapon.getId() == id) {
                return weapon;
            }
        }
        return null;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
