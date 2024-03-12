package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;


public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private Marche marche;

	public Village(String nom, int nbVillageoisMaximum, int nbEtalsMax) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
		marche = new Marche(nbEtalsMax);
	}
	
	private static class Marche{
		private Etal[] etals;
		
		private Marche(int nbEtalsMax) {
			etals = new Etal[nbEtalsMax];
			for(int i=0;i<nbEtalsMax;i++)etals[i] = new Etal();
		}
		
		private void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
			etals[indiceEtal].occuperEtal(vendeur,produit,nbProduit);
		}
		
		private int trouverEtalLibre() {
			for(int i =0;i<etals.length;i++) {
				if(!etals[i].isEtalOccupe())return i;
			}
			return -1;
		}
		
		private Etal[] trouverEtals(String produit){
			int max=0;
			int j=0;
			for(int i =0;i<etals.length;i++) {
				if(etals[i].isEtalOccupe() && etals[i].contientProduit(produit))max++;
			}
			Etal[] etalProduit = new Etal[max];
			for(int i =0;i<etals.length;i++) {
				if(etals[i].isEtalOccupe() && etals[i].contientProduit(produit)) {
					etalProduit[j++]=etals[i];
				}
			}
			if(max == 0 )return null;
			return etalProduit;
		}
		
		private Etal trouverVendeur(Gaulois gaulois) {
			for(int i =0;i<etals.length;i++) {
				if(etals[i].getVendeur()==gaulois)return etals[i];
			}
			return null;
		}
		
		private String afficherMarche() {
			int nbEtalVide=0;
			String texte = "";
			for(int i =0;i<etals.length;i++) {
				if(etals[i].isEtalOccupe())texte+=etals[i].afficherEtal();
				else nbEtalVide++;
			}
			texte+= "Il reste " + nbEtalVide + " étals non utilisés dans le marché.\n";
			return texte;
		}
		
	}
	public String installerVendeur(Gaulois vendeur, String produit,int nbProduit) {
		StringBuilder chaine = new StringBuilder();
		chaine.append(vendeur.getNom()+" cherche un endroit pour vendre "+nbProduit+" "+produit+".\n");
		int i = marche.trouverEtalLibre();
		marche.utiliserEtal(i,vendeur,produit,nbProduit);
		chaine.append("Le vendeur "+vendeur.getNom()+" vend des "+produit+" à l'étal n°"+(i+1)+".\n");
		return chaine.toString();
	}
	
	public String rechercherVendeursProduit(String produit) {
		StringBuilder chaine = new StringBuilder();
		Etal[] etals = marche.trouverEtals(produit);
		if(etals==null)chaine.append("Il n'y a pas de vendeur qui propose des "+produit+" au marché.\n");
		else {
			if(etals.length == 1)chaine.append("Seul le vendeur "+etals[0].getVendeur().getNom()+" propose des "+produit+" au marché.\n");
			else {
				chaine.append("Les vendeurs qui proposent des "+produit+" sont :\n");
				for(int i = 0;i<etals.length;i++)chaine.append("- "+etals[i].getVendeur().getNom()+"\n");
			}
		}
		return chaine.toString();
	}
	
	public Etal rechercherEtal(Gaulois vendeur) {
		return marche.trouverVendeur(vendeur);
	}
	
	

	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef "
					+ chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom()
					+ " vivent les lÃ©gendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
}