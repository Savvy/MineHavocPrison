package us.timberdnd.prisonheroes.heroes.hero;

import us.timberdnd.prisonheroes.heroes.Heroes;

public class Superman implements Heroes {
    
    public Superman() {
	
    }
    
    @Override
    public String getAbilityName() {
	return "Ground Slam";
    }

    @Override
    public int getCooldown() {
	return 0;
    }

    @Override
    public int heroLevel() {
	// TODO Auto-generated method stub
	return 0;
    }

    @Override
    public void activateAbility() {
	// TODO Auto-generated method stub
	
    }

}
