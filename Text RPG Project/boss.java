import java.util.Random;

final public class boss extends enemy { //This class is final due to it never needing to be passed to another method

	public void attack(character opponent){ //Overides the Previous attack method from the enemy class (Polymorphism)
		Random random = new Random();
		int damage = 0;
		int damageBoost;
		for (int i = 0; i < 3; i++) {
			damage = (basePower * strength) / opponent.getDefense();
			damageBoost = random.nextInt(50) + 1;
			damage += (damageBoost % 5);
			opponent.setHealth(opponent.getHealth() - damage);
			utility.slowPrint((i + 1) + " Hit(s)" + '\n' + "Lost " + damage + " HP" + '\n' + opponent.getName() + "'s Current HP: " + opponent.getHealth());
		}
	}

    public void defend(){ //Overrides the defend method from the larger character class (Polymorphism)
        defense *= 2.5;
        utility.slowPrint(name + " Is now Defending");
        utility.slowPrint(name + "'s Defense Seems to Have Risen Higher Than Normal");
    }

    public void heal(){ //Overrides the heal method from the larger character class (Polymorphism)
		Random random = new Random();
		int overflow = 0;
		int healed = random.nextInt(60);
		health += healed;
		if (health > baseHealth) {
			utility.slowPrint(name + "'s Health has Risen Higher than Believed");
		}
        utility.slowPrint(name + " Healed " + (healed - overflow) + " HP" + '\n' + name + "'s Current Health: " + health);
        utility.slowPrint(name + " Seems to Have Lowered it's Defense");
        defense /= 2;
        strength /= 2;
    }
}