package adt;

public class TelefoneADT {
    public static String consistir(String pTelefone){
    	if (pTelefone.matches("[0-9]{8}")) {
			return "";
		} else {
			return "Telefone deve seguir o padrão 99999999";
		}
    }
}
