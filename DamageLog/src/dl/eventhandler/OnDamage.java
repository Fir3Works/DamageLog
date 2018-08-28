package dl.eventhandler;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import dl.Core;
import dl.DamageLog;
import dl.data.LogFile;
import dl.files.FileManager;
import dl.util.DamageDirection;
import dl.util.DamageType;
import dl.util.EntityNames;
import dl.util.MathUtil;
import dl.util.damageValues;

public class OnDamage implements Listener{
	
	private final Set<DamageCause> NoNatural = new HashSet<>(Arrays.asList(DamageCause.ENTITY_ATTACK, DamageCause.ENTITY_SWEEP_ATTACK, DamageCause.PROJECTILE, DamageCause.ENTITY_EXPLOSION));
	
	//Events
	@EventHandler
	public void entityDamage(EntityDamageEvent event) {
		
		if(!(event.getEntity() instanceof Player)) {
			return;
		}
		
		if(this.NoNatural.contains(event.getCause())) {
			return;
		}
		
		double damage;
		DamageDirection direction;
		DamageType type;
		Player defender;
		String attackername;
		String defendername;
		
		damage = MathUtil.roundToQuarter(event.getFinalDamage());
		direction = DamageDirection.IN;
		type = DamageType.valueOf(event.getCause().toString());
		defender = (Player) event.getEntity();
		attackername = EntityNames.ENVIRONMENT.getDisplayName();
		defendername = defender.getDisplayName();
		
		LogFile logFile = new LogFile(damage, direction, type, attackername, defendername);
		
		Core.players.get(defender).addLog(logFile);
		
		damageValues.updateDamageValue(defender, logFile);
	}
	
	@EventHandler
	public void entityDamagebyEntity(EntityDamageByEntityEvent event) {
		
		if(!(this.isRelevant(event.getDamager()) != null || this.isRelevant(event.getEntity()) != null)) return;
		
		double damage;
		DamageDirection attackerdirection;
		DamageDirection defenderdirection;
		DamageType type;
		Entity attacker;
		Entity defender;
		String attackername;
		String defendername;
		DamageLog attackerlog;
		DamageLog defenderlog;
		LogFile attackerfile;
		LogFile defenderfile;
		
		damage = MathUtil.roundToQuarter(event.getFinalDamage());
		attackerdirection = DamageDirection.OUT;
		defenderdirection = DamageDirection.IN;
		type = DamageType.valueOf(event.getCause().toString());
		attacker = event.getDamager();
		defender = event.getEntity();
		attackername = this.getEntityName(attacker);
		defendername = this.getEntityName(defender);

		
		if(attacker instanceof Player && defender instanceof Player) {
			attackerlog = Core.players.get(attacker);
			defenderlog = Core.players.get(defender);
			
			attackerfile = new LogFile(damage, attackerdirection, type, attackername, defendername);
			defenderfile = new LogFile(damage, defenderdirection, type, attackername, defendername);
			
			attackerlog.addLog(attackerfile);
			defenderlog.addLog(defenderfile);
			
			damageValues.updateDamageValue((Player) attacker, attackerfile);
			damageValues.updateDamageValue((Player) defender, defenderfile);
			
			return;
		}
		
		if(attacker instanceof Player) {
			attackerlog = Core.players.get(attacker);
			
			attackerfile = new LogFile(damage, attackerdirection, type, attackername, defendername) ;
			
			attackerlog.addLog(attackerfile);
			
			damageValues.updateDamageValue((Player) attacker, attackerfile);
			
			return;
		}
		
		if(defender instanceof Player) {
			defenderlog = Core.players.get(defender);
			
			defenderfile = new LogFile(damage, defenderdirection, type, attackername, defendername);
			
			
			defenderlog.addLog(defenderfile);
			
			damageValues.updateDamageValue((Player) defender, defenderfile);
			
			return;
		}
		
		if(attacker instanceof Projectile) {
			Projectile projectile = (Projectile) attacker;
				
			if(projectile.getShooter() instanceof Player) {
				Player player = (Player) projectile.getShooter();
				
				LogFile logFile = new LogFile(damage, attackerdirection, type, attackername, defendername);
				
				Core.players.get(player).addLog(new LogFile(damage, attackerdirection, type, attackername, defendername));
				damageValues.updateDamageValue((Player) attacker, logFile);
			}
			else {
				LogFile logFile = new LogFile(damage, defenderdirection, type, attackername, defendername);
				
				Core.players.get(defender).addLog(logFile);	
				damageValues.updateDamageValue((Player) defender, logFile);
			}
		}
			
	}
	
	
	//Methoden
	private Player isRelevant(Entity entity) {
		Player player = null;
		
		if(entity instanceof Projectile) {
			Projectile projectile = (Projectile) entity;
			if(projectile.getShooter() instanceof Player) {
				player = (Player) projectile.getShooter();
			}
		}
		else if(entity instanceof Player){
			player = (Player) entity;
		}
		
		return player;
	}
	
	private String getEntityName(Entity entity) {
		Entity finalentity = entity;

		
		if(entity instanceof Projectile) {
			Projectile projectile = (Projectile) entity;
			if(projectile.getShooter() instanceof Entity) {
				finalentity = (Entity) projectile.getShooter();
			}
		}
		
		if(finalentity instanceof Player) {
			Player player = (Player) finalentity;
			return player.getName();
		}
		
		if(finalentity instanceof Entity) {
			if(finalentity.getCustomName() != null) {
				return finalentity.getCustomName();
			}
			
			return FileManager.entityLanguage.getString(finalentity.getType().toString());
		}
		else {
			return FileManager.entityLanguage.getString(EntityType.UNKNOWN.toString());
		}
		
		
	}
	
}
