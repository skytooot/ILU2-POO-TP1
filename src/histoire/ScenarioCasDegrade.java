package histoire;

import personnages.Chef;
import personnages.Druide;
import personnages.Gaulois;
import villagegaulois.Etal;
import villagegaulois.Village;

public class ScenarioCasDegrade {
	public static void main(String[] args) {
		Etal etal = new Etal();
		System.out.println(etal.libererEtal());
		System.out.println("Fin du test");
	}
}
