package us.timberdnd.prisonheroes.heroes;

public enum Heroes {
    
    SUPERMAN(1, "Ground Slam", 3),
    BATMAN(1, "Bat Swarm", 3),
    WOLVERINE(1, "Fast Attack", 3),
    HAWKEYE(1, "Bow Explosion", 3);
    
    private int heroLevel;
    private String abilityName;
    private  int cooldown;
    Heroes(int heroLevel, String abilityName, int cooldown) {
	this.setHeroLevel(heroLevel);
	this.setAbilityName(abilityName);
	this.setCooldown(cooldown);
    }
    public int getCooldown() {
	return cooldown;
    }
    
    public void setCooldown(int cooldown) {
	this.cooldown = cooldown;
    
    }
    
    public String getAbilityName() {
	return abilityName;
    }
    
    public void setAbilityName(String abilityName) {
	this.abilityName = abilityName;
    }
    
    public int getHeroLevel() {
	return heroLevel;
    }
    
    public void setHeroLevel(int heroLevel) {
	this.heroLevel = heroLevel;
    }
}
