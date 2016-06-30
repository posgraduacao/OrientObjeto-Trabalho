package adt;

public class TipoDocumentoADT {
	public static String consistir(String pTipoDocumento) {
		TipoDocumento[] tDocumentoADTs = TipoDocumento.values();
		String tErro = "";
		
		for (TipoDocumento tTipoDocumentoADT : tDocumentoADTs) {
			if (pTipoDocumento.equals(tTipoDocumentoADT.name())) {
				return "";
			} else {
				tErro = "O Tipo de Documento deve ser RG, PAS, TRAB ou CNH";
			}
		}
		
		return tErro;
	}
	
	
}
