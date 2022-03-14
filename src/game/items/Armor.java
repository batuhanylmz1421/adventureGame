package game.items;

public class Armor extends Item {
    private int block;

    public Armor(int id, String name, int price, int block) {
        super(id, name, price);
        this.block = block;
    }

    public static Armor[] armors() {
        // Setting armors manually
        Armor[] armorList = new Armor[3];
        armorList[0] = new Armor(1, "Light", 15, 1);
        armorList[1] = new Armor(2, "Medium", 25, 3);
        armorList[2] = new Armor(3, "Heavy", 40, 5);

        return armorList;
    }

    public static Armor getArmorObjById(int id) {
        // Return armor object by checking armor id.
        for (Armor armor : Armor.armors()) {
            if (armor.getId() == id) {
                return armor;
            }
        }
        return null;
    }

    public int getBlock() {
        return block;
    }

    public void setBlock(int block) {
        this.block = block;
    }
}
