package adt;

public class NomeADT {
	public static String consistir(String pNome) {
		if (pNome.isEmpty()) {
			return "Nome n�o pode ser nulo ou vazio!";
		}
		return "";
	}
}
