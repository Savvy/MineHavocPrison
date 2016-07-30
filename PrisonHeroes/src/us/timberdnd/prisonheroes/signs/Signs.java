package us.timberdnd.prisonheroes.signs;

import java.util.ArrayList;
import java.util.List;

public class Signs {
    
    public List<PrisonSigns> signs = new ArrayList<>();
   
    public void registerSign() {
	registerSigns(
		new CoinsEnchant());
    }
    
    public void registerSigns(PrisonSigns...signs) {
	for(PrisonSigns sign: signs) {
	    this.signs.add(sign);
	}
    }
}
