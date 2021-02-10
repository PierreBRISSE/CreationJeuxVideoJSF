package outils;

public class DateValide {
	public DateValide() {
	}

	/**
	 * 
	 * @param j
	 * @param m
	 * @param a
	 * @return nbJour
	 */
	public static int jourMax(int j, int m, int a) {
		int nbJour;

		switch (m) {
		case 1:
			nbJour = 31;
			break;
		case 2:
			nbJour = jourFevrier(a);
			break;
		case 3:
			nbJour = 31;
			break;
		case 4:
			nbJour = 30;
			break;
		case 5:
			nbJour = 31;
			break;
		case 6:
			nbJour = 30;
			break;
		case 7:
			nbJour = 31;
			break;
		case 8:
			nbJour = 31;
			break;
		case 9:
			nbJour = 30;
			break;
		case 10:
			nbJour = 31;
			break;
		case 11:
			nbJour = 30;
			break;
		case 12:
			nbJour = 31;
			break;
		default:
			nbJour = 0;
		}
		return nbJour;
	}// -

	/**
	 * @param a
	 * @return nbJour
	 */
	public static int jourFevrier(int a) {
		int anneeBis, difAnnee, nbJour;

		anneeBis = 2016;
		difAnnee = a - anneeBis;

		if (difAnnee % 4 == 0) {
			nbJour = 29;
		}

		else {
			nbJour = 28;
		}

		return nbJour;
	}

}// FIN PRG
