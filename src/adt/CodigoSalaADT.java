package adt;

public class CodigoSalaADT {
    public static String consistir(String pCodigo) {
        if (pCodigo.length() != 4)
            return "Código deve ter 4 caracteres";
        if (!pCodigo.matches("[A-Z]{4}"))
        	return "Código deve conter 4 caracteres alfabéticos e maiúsculos";
        return "";
    }
}
