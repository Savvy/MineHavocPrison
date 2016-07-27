package us.timberdnd.prisonheroes.heroes;

public interface Heroes {
    
    String getAbilityName();
    
    int getCooldown();
    
    int heroLevel();
    
    void activateAbility();
}
