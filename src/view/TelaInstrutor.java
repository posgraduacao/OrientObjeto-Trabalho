package view;

import java.util.Vector;

import adt.CodigoADT;
import adt.DocumentoADT;
import adt.NomeADT;
import adt.TelefoneADT;
import adt.TipoDocumentoADT;
import controller.InstrutorService;
import dto.InstrutorDTO;
import model.Instrutor;
import util.Leitor;

public class TelaInstrutor {
	private static Integer sCodigo = 0;
	private static String sNome = "";
	private static String sEmpresa = "";
	private static long sTelefone = 0;
	private static String sDocumento;
	private static String sTipoDocumento = "";
	private static final InstrutorService sServico = new InstrutorService();

	public static void processar() {
		while (true) {
			System.out.println();
			System.out.println("Manutenção de Instrutores");
			System.out.println(" 1 - Incluir");
			System.out.println(" 2 - Alterar");
			System.out.println(" 3 - Excluir");
			System.out.println(" 4 - Consultar");
			System.out.println(" 5 - Listar");
			System.out.println(" 9 - Fim");
			System.out.println();

			int tOpcao = Leitor.readInt("Entre com a opção desejada : ");
			if (tOpcao == 9)
				break;

			limparDados();
			switch (tOpcao) {
			case 1:
				processarInclusao();
				break;
			case 2:
				processarAlteracao();
				break;
			case 3:
				processarExclusao();
				break;
			case 4:
				processarConsulta();
				break;
			case 5:
				processarRelacao();
				break;
			default:
				System.out.println("Opção inválida. Reentre...");
				break;
			}
		}
	}

	private static void processarInclusao() {
		System.out.println();
		System.out.println("Manutenção de Instrutores");
		System.out.println("Inclusão de Instrutor");
		System.out.println();

		sCodigo = lerCodigo();
		if (sCodigo == 0)
			return;

		sNome = lerNome();
		if (sNome == null || sNome.isEmpty()) {
			return;
		}

		sEmpresa = lerEmpresa();
		if (sEmpresa == null || sEmpresa.isEmpty()) {
			return;
		}

		sTelefone = lerTelefone();
		if (sTelefone == 0) {
			return;
		}

		sDocumento = lerDocumento();
		if (sDocumento == null) {
			return;
		}

		sTipoDocumento = lerTipoDocumento();
		if (sTipoDocumento == null)
			return;

		System.out.println();
		char tConf = Leitor.readChar("Confirma (s/n)... : ");
		System.out.println();

		if (Character.toUpperCase(tConf) == 'S') {
			InstrutorDTO tDto = sServico.processarInclusao(sCodigo, sNome, sEmpresa, sTelefone, sDocumento,
					sTipoDocumento);
			if (tDto.isOk()) {
				System.out.println(tDto.getAviso());
				mostrarInstrutor(tDto.getInstrutor());
			} else
				System.out.println(tDto.getAviso());
		} else
			System.out.println("Operação não realizada...");
	}

	private static void processarAlteracao() {
		System.out.println();
		System.out.println("Manutenção de Instrutor");
		System.out.println("Alteração de Instrutor");
		System.out.println();

		sCodigo = lerCodigo();
		if (sCodigo == 0)
			return;

		InstrutorDTO tDto = sServico.processarConsulta(sCodigo);
		if (!tDto.isOk()) {
			System.out.println(tDto.getAviso());
			return;
		}

		System.out.println("Instrutor a ser alterado");
		mostrarInstrutor(tDto.getInstrutor());

		System.out.println();
		sNome = lerNome();
		if (sNome == null || sNome.isEmpty()) {
			return;
		}

		sEmpresa = lerEmpresa();
		if (sEmpresa == null || sEmpresa.isEmpty()) {
			return;
		}

		sTelefone = lerTelefone();
		if (sTelefone == 0) {
			return;
		}

		sDocumento = lerDocumento();
		if (sDocumento == null) {
			return;
		}

		sTipoDocumento = lerTipoDocumento();
		if (sTipoDocumento == null)
			return;

		System.out.println();
		char tConf = Leitor.readChar("Confirma (s/n)... : ");
		System.out.println();

		if (Character.toUpperCase(tConf) == 'S') {
			tDto = sServico.processarAlteracao(sCodigo, sNome, sEmpresa, sTelefone, sDocumento, sTipoDocumento);
			if (tDto.isOk()) {
				System.out.println(tDto.getAviso());
				mostrarInstrutor(tDto.getInstrutor());
			} else
				System.out.println(tDto.getAviso());
		} else
			System.out.println("Operação não realizada...");
	}

	private static void processarExclusao() {
		System.out.println();
		System.out.println("Manutenção de Instrutores");
		System.out.println("Exclusão de Instrutor");
		System.out.println();

		sCodigo = lerCodigo();
		if (sCodigo == 0)
			return;

		InstrutorDTO tDto = sServico.processarConsulta(sCodigo);
		if (!tDto.isOk()) {
			System.out.println(tDto.getAviso());
			return;
		}

		System.out.println("Instrutor a ser excluído");
		mostrarInstrutor(tDto.getInstrutor());

		System.out.println();
		char tConf = Leitor.readChar("Confirma (s/n)... : ");
		System.out.println();

		if (Character.toUpperCase(tConf) == 'S') {
			tDto = sServico.processarExclusao(sCodigo);
			if (tDto.isOk()) {
				System.out.println(tDto.getAviso());
				mostrarInstrutor(tDto.getInstrutor());
			} else
				System.out.println(tDto.getAviso());
		} else
			System.out.println("Operação não realizada...");
	}

	private static void processarConsulta() {
		System.out.println();
		System.out.println("Manutenção de Instrutores");
		System.out.println("Consulta de Instrutor");
		System.out.println();

		sCodigo = lerCodigo();
		if (sCodigo == 0)
			return;

		InstrutorDTO tDto = sServico.processarConsulta(sCodigo);
		if (tDto.isOk()) {
			System.out.println(tDto.getAviso());
			mostrarInstrutor(tDto.getInstrutor());
		} else
			System.out.println(tDto.getAviso());
	}

	private static void processarRelacao() {
		System.out.println();
		System.out.println("Manutenção de Instrutores");
		System.out.println("Relação de Instrutores");
		System.out.println();

		InstrutorDTO tDto = sServico.processarRelacao();
		if (tDto.isOk()) {
			System.out.println(tDto.getAviso());
			Vector<Instrutor> tLista = tDto.getRelacao();
			for (Instrutor tInstrutor : tLista) {
				System.out.printf("%-10s - %-10s - %-10s - %-10s - %-10s - %-10s%n", tInstrutor.getCodigo(),
						tInstrutor.getNome(), tInstrutor.getEmpresa(), tInstrutor.getTelefone(),
						tInstrutor.getDocumento(), tInstrutor.getTipoDocumento());
			}
		} else
			System.out.println(tDto.getAviso());
	}

	private static int lerCodigo() {
		// Código com consistência
		while (true) {
			int tCodigo = Leitor.readInt("Código..... : ", sCodigo);
			String tErro = CodigoADT.consistir(tCodigo);
			if (tErro.isEmpty())
				return tCodigo;
			else {
				System.out.println("Erro.......... : " + tErro);
				System.out.println();
			}
			return 0;
		}
	}

	private static String lerNome() {
		// Código com consistência
		while (true) {
			String tNome = Leitor.readString("Nome......... : ", sNome).trim();
			String tErro = NomeADT.consistir(tNome);
			if (tErro.isEmpty()) {
				return tNome;
			} else {
				System.out.println("Erro.......... : " + tErro);
				System.out.println();
			}
			return null;
		}
	}

	private static String lerEmpresa() {
		// Código com consistência
		while (true) {
			String tNome = Leitor.readString("Empresa......... : ", sEmpresa).trim();
			String tErro = NomeADT.consistir(tNome);
			if (tErro.isEmpty()) {
				return tNome;
			} else {
				System.out.println("Erro.......... : " + tErro);
				System.out.println();
			}
			return null;
		}
	}

	private static long lerTelefone() {
		// Código com consistência
		while (true) {
			long tTelefone = Leitor.readLong("Telefone..... : ", sTelefone);		
			String tErro = TelefoneADT.consistir(String.valueOf(tTelefone));
			if (tErro.isEmpty())
				return tTelefone;
			else {
				System.out.println("Erro.......... : " + tErro);
				System.out.println();
				return 0;
			}
		}
	}

	private static String lerDocumento() {
		// Código com consistência
		while (true) {
			String tDocumento = Leitor.readString("Documento......... : ", sDocumento).trim();
			String tErro = DocumentoADT.consistir(tDocumento);
			if (tErro.isEmpty()) {
				return tDocumento;
			} else {
				System.out.println("Erro.......... : " + tErro);
				System.out.println();
			}
			return null;
		}
	}

	private static String lerTipoDocumento() {
		// Código com consistência
		while (true) {
			String tTipoDocumento = Leitor.readString("Tipo Documento..... : ", sTipoDocumento);
			String tErro = TipoDocumentoADT.consistir(tTipoDocumento);

			if (tErro.isEmpty())
				return tTipoDocumento;
			else {
				System.out.println("Erro.......... : " + tErro);
				System.out.println();
			}
			return null;
		}
		
	}

	private static void limparDados() {
		sCodigo = 0;
		sNome = "";
		sEmpresa = "";
		sTelefone = 0;
		sDocumento = "";
		sTipoDocumento = "";
	}

	private static void mostrarInstrutor(Instrutor pInstrutor) {
		sCodigo = pInstrutor.getCodigo();
		sNome = pInstrutor.getNome();
		sEmpresa = pInstrutor.getEmpresa();
		sTelefone = pInstrutor.getTelefone();
		sDocumento = pInstrutor.getDocumento();
		sTipoDocumento = pInstrutor.getTipoDocumento();

		System.out.println();
		System.out.println("Código........ : " + sCodigo);
		System.out.println("Nome.......... : " + sNome);
		System.out.println("Empresa....... : " + sEmpresa);
		System.out.println("Telefone...... : " + sTelefone);
		System.out.println("Documento..... : " + sDocumento);
		System.out.println("Tipo Documento : " + sTipoDocumento);
	}
}
