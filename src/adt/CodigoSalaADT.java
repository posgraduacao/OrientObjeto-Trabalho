package adt;

public class CodigoSalaADT {
    public static String consistir(String pCodigo) {
        if (pCodigo.length() != 4)
            return "C�digo deve ter 4 caracteres";
        if (!pCodigo.matches("[A-Z]{4}"))
        	return "C�digo deve conter 4 caracteres alfab�ticos e mai�sculos";
        return "";
    }
}
