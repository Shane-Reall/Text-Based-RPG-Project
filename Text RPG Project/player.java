import java.util.Random;

final public class player extends character { //This class is final due to it never needing to be passed to another method

	@Override
	public void attack(character opponent){
		utility.slowPrint(name + " Attacked " + opponent.getName());
		Random random = new Random();
		int damageBoost = random.nextInt(20);
		int damage = (basePower * strength) / opponent.getDefense();
		damage += damageBoost % 3;
		opponent.setHealth(opponent.getHealth() - damage);
		utility.slowPrint(opponent.getName() + " Lost " + damage + " HP" + '\n' + opponent.getName() + "'s Current HP: " + opponent.getHealth() + '\n');
	}

	public void fullHeal(){
		health = baseHealth;
	}
}