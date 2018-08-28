package dl.data;

import dl.util.DamageDirection;
import dl.util.DamageType;

public class LogFile {
	
	//Constructor
	public LogFile(double damage, DamageDirection direction, DamageType type, String attacker, String defender) {
		this.damage = damage;
		this.direction = direction;
		this.type = type;
		this.attacker = attacker;
		this.defender = defender;
	}
	
	//Variablen
	private final double damage;
	private final DamageDirection direction;
	private final DamageType type;
	private final String attacker;
	private final String defender;
	
	//Getter
	public double getDamage() {
		return damage;
	}
	public DamageDirection getDirection() {
		return direction;
	}
	public DamageType getType() {
		return type;
	}
	public String getAttacker() {
		return attacker;
	}
	public String getDefender() {
		return defender;
	}
}
