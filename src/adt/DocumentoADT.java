package adt;

public class DocumentoADT {
	public static String consistir(String pDocumento) {
		if (pDocumento.isEmpty()) {
			return "Nome n�o pode ser nulo ou vazio!";
		}
		return "";
	}
}
