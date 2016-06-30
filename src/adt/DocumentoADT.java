package adt;

public class DocumentoADT {
	public static String consistir(String pDocumento) {
		if (pDocumento.isEmpty()) {
			return "Nome não pode ser nulo ou vazio!";
		}
		return "";
	}
}
