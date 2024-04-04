import java.util.Random;

public abstract class character extends utility{ //Is absent since a character specific object never needs to be made
    String name = "Jim";
    int strength;
    int defense;
    int health;
    int baseHealth;
    int basePower;
    int healMultiply;

    public character() {
        health = 100;
        baseHealth = 100;
        strength = 10;
        defense = 5;
        basePower = 5;
        healMultiply = 1;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getHealth(){
		return health;
    }

    public int getBaseHealth(){
		return baseHealth;
    }

    public void setHealth(int health){
		this.health = health;
    }

    public void setHealth(int health, int baseHealth){
		this.health = health;
		this.baseHealth = baseHealth;
    }

    public int getStrength(){
		return strength;
    }

    public void setStrength(int strength){
		this.strength = strength;
    }

    public int getDefense(){
		return defense;
    }

    public void setDefense(int defense){
		this.defense = defense;
    }

    public int getBasePower(){
		return basePower;
    }

    public void setBasePower(int basePower){
		this.basePower = basePower;
    }

    public void setHealMultiply(int healMultiply){
		this.healMultiply = healMultiply;
    }

    public boolean isAlive(){
		if (health <= 0) {
			return false;
		}
		return true;
	}

    public abstract void attack(character opponent); //Absent Method is used here to force a method to be created in all classes that extend from this class

    public void defend(){
        defense *= 2;
        utility.slowPrint(name + " Is now Defending");
    }

    public void heal(){
		Random random = new Random();
		int overflow = 0;
		int healed = (random.nextInt(20) + 10) * healMultiply;
		health += healed;
		if (health > baseHealth) {
			overflow = health - baseHealth;
			health = baseHealth;
		}
        utility.slowPrint(name + " Healed " + (healed - overflow) + " HP" + '\n' + name + "'s Current Health: " + health);
    }
}