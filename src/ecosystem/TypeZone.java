package ecosystem;

public enum TypeZone {
	FORET, PLAINE, DESERT
}


/*
 * On se fixe les fourchettes d'eau et de températures suivantes en fonction du type de Zone :
 * - Pour une forêt, les températures doivent etre entre -10° et 35° et le niveau d'eau entre 500 et 1000mm (précipitations par an)
 * - Pour une plaine, les températures doivent etre entre 0° et 40° et le niveau d'eau entre 250 et 500mm (précipitations par an)
 * - Pour un désert, la température doit être supérieure à 40° et le niveau d'eau inférieur à 250mm
*/
