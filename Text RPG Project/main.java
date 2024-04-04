import java.util.Random;
import java.util.Scanner;

public class main
{
    public static void main(String[] args)
    {
        player user = new player();
        enemy goblin = new enemy();
        enemy orc = new enemy();
        boss hydra = new boss();
        Scanner keyScanner = new Scanner(System.in);
        int enemyTotal = goblin.getHealth();
        String name;

        utility.slowPrint("Enter Your Name: ");
        name = keyScanner.nextLine();
        user.setName(name);

        goblin.setName("Goblin");
        goblin.setHealth(50, 50);
        goblin.setDefense(2);
        goblin.setStrength(3);
        goblin.setBasePower(5);
        user.setHealMultiply(1);

        combat(user, goblin, keyScanner);

		if (user.isAlive() == false) {
			utility.slowPrint("You have lost the Battle and have Died");
			System.exit(0);
		}

		utility.slowPrint(user.getName() + " has Defeated " + goblin.getName() + '\n');

		user.fullHeal();

		utility.slowPrint(user.getName() + " has Leveled Up!" + '\n' + "Strength +10" + '\n' + "Defense +15" + '\n' + "Health +50" + '\n' + "Attack Power +10" + '\n');

		user.setHealth(user.getBaseHealth() + 50, user.getBaseHealth() + 50);
		user.setDefense(user.getDefense() + 15);
		user.setStrength(user.getStrength() + 10);
        user.setBasePower(user.getBasePower() +10);
        user.setHealMultiply(2);
        orc.setName("Orc");
        orc.setHealth(250, 250);
        orc.setDefense(5);
        orc.setStrength(25);
        orc.setBasePower(25);
        orc.setMaxHit(1);

        utility.slowPrint("An Orc Appears");

        combat(user, orc, keyScanner);

		if (user.isAlive() == false) {
			utility.slowPrint("You have lost the Battle and have Died");
			System.exit(0);
		}

        utility.slowPrint(user.getName() + " has Leveled Up!" + '\n' + "Strength +25" + '\n' + "Defense +20" + '\n' + "Health +75" + '\n' + "Attack Power +30" + '\n');
        utility.slowPrint(user.getName() + " has Found Orc Armor and Equiped it!" + '\n' + "Defense +50" + '\n');
        utility.slowPrint(user.getName() + " has Found a Steel Blade and Equiped it!" + '\n' + "Strength +20" + '\n' + "Attack Power +10" + '\n');

		user.setHealth(user.getBaseHealth() + 75, user.getBaseHealth() + 75);
		user.setDefense(user.getDefense() + 70);
		user.setStrength(user.getStrength() + 45);
        user.setBasePower(user.getBasePower() +40);
        user.setHealMultiply(3);
        hydra.setName("Hydra");
        hydra.setHealth(500, 500);
        hydra.setDefense(50);
        hydra.setStrength(45);
        hydra.setBasePower(30);

        utility.slowPrint("The Hydra Appears");

        bossCombat(user, hydra, keyScanner);

		if (user.isAlive() == false) {
			utility.slowPrint("You have lost the Battle and have Died");
			System.exit(0);
		}

		utility.slowPrint("You have Won the Battle and have Completed the Dungeon");

    }

    public static void combat(character user, character enemy, Scanner keyScanner){
        boolean userDefending = false;
        boolean enemyDefending = false;

		while (user.isAlive() == true && enemy.isAlive() == true){
			if (userDefending == true) {
				userDefending = false;
				user.setDefense(user.getDefense() / 2);
				utility.slowPrint(user.getName() + " Is No Longer Defending!" + '\n');
			}

			userDefending = userTurn(user, enemy, keyScanner);

			if (enemy.isAlive() == false) {
				break;
			}
			if (enemyDefending == true) {
				enemyDefending = false;
				enemy.setDefense(enemy.getDefense() / 2);
				utility.slowPrint(enemy.getName() + " Is No Longer Defending!" + '\n');
			}

			enemyDefending = enemyTurn(user, enemy, keyScanner);
			utility.slowPrint(utility.endOfRound() + '\n');

		}

	}

	public static void bossCombat(character user, character boss, Scanner keyScanner){
        boolean userDefending = false;
        boolean bossDefending = false;
        boolean bossWeakened = false;
        int bossReturn = 0;

		while (user.isAlive() == true && boss.isAlive() == true){
			if (userDefending == true) {
				userDefending = false;
				user.setDefense(user.getDefense() / 2);
				utility.slowPrint(user.getName() + " Is No Longer Defending!" + '\n');
			}

			userDefending = userTurn(user, boss, keyScanner);

			if (boss.isAlive() == false) {
				break;
			}
			if (bossDefending == true) {
				bossDefending = false;
				boss.setDefense(boss.getDefense() / 2);
				utility.slowPrint(boss.getName() + " Is No Longer Defending!" + '\n');
			}
			else if (bossWeakened == true) {
				bossWeakened = false;
				boss.setDefense(boss.getDefense() * 2);
				boss.setStrength(boss.getStrength() * 2);
				utility.slowPrint(boss.getName() + " Is No Longer Weakened!" + '\n');
			}

			bossReturn = bossTurn(user, boss, keyScanner);

			if (bossReturn == 1){
        		bossWeakened = true;
			}
			else if (bossReturn == 2) {
        		bossDefending = true;
			}

			utility.slowPrint(utility.endOfRound() + '\n');

		}

	}

	public static boolean userTurn(character user, character enemy, Scanner keyScanner){
		boolean moved = false;
        boolean userDefending = false;
        boolean checkMenu = true;
        String userChoice;

		while (moved == false) {
			utility.slowPrint("What would you like to do?" + '\n' + "- Attack"+ '\n' + "- Defend" + '\n' + "- Heal" + '\n' + "- Check" + '\n');
			userChoice = keyScanner.nextLine();
			System.out.println("");
			if (userChoice.toUpperCase().equals("ATTACK")) {
				user.attack(enemy);
				moved = true;
			}
			else if (userChoice.toUpperCase().equals("DEFEND")) {
				user.defend();
				userDefending = true;
				moved = true;
				System.out.println("");
			}
			else if (userChoice.toUpperCase().equals("HEAL")) {
				user.heal();
				moved = true;
				System.out.println("");
			}
			else if (userChoice.toUpperCase().equals("CHECK")) {
				while (checkMenu == true) {
					utility.slowPrint("Who Should you Check?" + '\n' + "- Self"+ '\n' + "- " + enemy.getName() + '\n' + "- Exit"+ '\n');
					userChoice = keyScanner.nextLine();
					if (userChoice.toUpperCase().equals("SELF")) {
						utility.slowPrint("Health: " + user.getHealth() + "/" + user.getBaseHealth() + '\n' + "Strength: " + user.getStrength() + '\n' + "Defense: " + user.getDefense() + '\n' + "Attack Power: " + user.getBasePower() + '\n');
					}
					else if (userChoice.toUpperCase().equals(enemy.getName().toUpperCase())) {
						utility.slowPrint("Health: " + enemy.getHealth() + "/" + enemy.getBaseHealth() + '\n' + "Strength: " + enemy.getStrength() + '\n' + "Defense: " + enemy.getDefense() + '\n' + "Attack Power: " + enemy.getBasePower() + '\n');
					}
					else if (!userChoice.toUpperCase().equals("EXIT")) {
						utility.slowPrint("That is Not a Valid Option" + '\n');
					}
					else {
						checkMenu = false;
					}
				}
			}
			else {
				utility.slowPrint("That is Not a Valid Option" + '\n');
			}
		}

		return userDefending;
	}

	public static boolean enemyTurn(character user, character enemy, Scanner keyScanner){
        int enemyChoice;
        boolean enemyDefending = false;
        String userChoice;
		Random random = new Random();
		enemyChoice = random.nextInt(10) + 1;

		if (enemyChoice < 8) {
			System.out.println(enemy.getName() + " Attacked");
			enemy.attack(user);
			System.out.println("");
		}
		else if (enemyChoice >= 8 && enemyChoice < 9) {
			enemy.defend();
			enemyDefending = true;
			System.out.println("");
		}
		else {
			enemy.heal();
			System.out.println("");
		}

		return enemyDefending;
	}

	public static int bossTurn(character user, character boss, Scanner keyScanner){
        int bossChoice;
        boolean bossDefending = false;
        boolean bossWeakened = false;
        String userChoice;
		Random random = new Random();
		bossChoice = random.nextInt(10) + 1;

		if (bossChoice <= 4) {
			System.out.println(boss.getName() + " Attacked");
			boss.attack(user);
			System.out.println("");
		}
		else if (bossChoice >= 5 && bossChoice < 8) {
			boss.defend();
			bossDefending = true;
			System.out.println("");
		}
		else {
			boss.heal();
			bossWeakened = true;
			System.out.println("");
		}

		if (bossWeakened == true) {
			return 1;
		}
		else if (bossDefending == true) {
			return 2;
		}
		else {
			return 3;
		}
	}

}