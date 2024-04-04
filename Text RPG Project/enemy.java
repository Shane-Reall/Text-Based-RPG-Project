import java.util.Random;

public class enemy extends character {

	private int maxHit = 5;

	public void setMaxHit (int maxHit) {
		this.maxHit = maxHit;
	}

	@Override
	public void attack(character opponent){
		Random random = new Random();
		int damage = 0;
		int damageBoost;
		int hitCount = random.nextInt(maxHit) + 1;
		for (int i = 0; i < hitCount; i++) {
			damage = (basePower * strength) / opponent.getDefense();
			damageBoost = random.nextInt(20) + 1;
			damage += (damageBoost % 3);
			opponent.setHealth(opponent.getHealth() - damage);
			utility.slowPrint((i + 1) + " Hit(s)" + '\n' + "Lost " + damage + " HP" + '\n' + opponent.getName() + "'s Current HP: " + opponent.getHealth());
			if (opponent.isAlive() == false) {
				i = hitCount;
			}
		}
	}
}