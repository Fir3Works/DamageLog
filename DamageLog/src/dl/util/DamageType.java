package dl.util;

import dl.files.FileManager;

public enum DamageType {
	
	BLOCK_EXPLOSION,
	CONTACT,
	CRAMMING,
	CUSTOM,
	DRAGON_BREATH,
	DROWNING,
	DRYOUT,
	ENTITY_ATTACK,
	ENTITY_EXPLOSION,
	ENTITY_SWEEP_ATTACK,
	FALL,
	FALLING_BLOCK,
	FIRE,
	FIRE_TICK,
	FLY_INTO_WALL,
	HOT_FLOOR,
	LAVA,
	LIGHTNING,
	MAGIC,
	MELTING,
	POISON,
	PROJECTILE,
	STARVATION,
	SUFFOCATION,
	SUICIDE,
	THORNS,
	VOID,
	WITHER;
	
	public String getDisplayName() {
		return FileManager.damageLanguage.getString(this.toString());
	}
	
}
