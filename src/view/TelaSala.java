package view;

import java.util.Vector;

import adt.CapacidadeSalaADT;
import adt.CodigoSalaADT;
import adt.NomeADT;
import controller.SalaService;
import dto.SalaDTO;
import model.Sala;
import util.Leitor;

public class TelaSala {
	private static String sCodigo = "";
	private static String sNome = "";
	private static int sCapacidade = 0;
	private static String sEndereco = "";
	private static final SalaService sServico = new SalaService();

	public static void processar() {
		while (true) {
			System.out.println();
			System.out.println("Manuten��o de Salas");
			System.out.println(" 1 - Incluir");
			System.out.println(" 2 - Alterar");
			System.out.println(" 3 - Excluir");
			System.out.println(" 4 - Consultar");
			System.out.println(" 5 - Listar");
			System.out.println(" 9 - Fim");
			System.out.println();

			int tOpcao = Leitor.readInt("Entre com a op��o desejada : ");
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
				System.out.println("Op��o inv�lida. Reentre...");
				break;
			}
		}
	}

	private static void processarInclusao() {
		System.out.println();
		System.out.println("Manuten��o de Salas");
		System.out.println("Inclus�o de Sala");
		System.out.println();

		sCodigo = lerCodigo();
		if (sCodigo == null || sCodigo.isEmpty()) {
			return;
		}

		sNome = lerNome();
		if (sNome == null || sNome.isEmpty()) {
			return;
		}

		sCapacidade = lerCapacidade();
		if (sCapacidade == 0)
			return;

		sEndereco = lerEndereco();
		if (sEndereco == null || sEndereco.isEmpty()) {
			return;
		}

		System.out.println();
		char tConf = Leitor.readChar("Confirma (s/n)... : ");
		System.out.println();

		if (Character.toUpperCase(tConf) == 'S') {
			SalaDTO tDto = sServico.processarInclusao(sCodigo, sNome, sCapacidade, sEndereco);
			if (tDto.isOk()) {
				System.out.println(tDto.getAviso());
				mostrarSala(tDto.getSala());
			} else
				System.out.println(tDto.getAviso());
		} else
			System.out.println("Opera��o n�o realizada...");
	}

	private static void processarAlteracao() {
		System.out.println();
		System.out.println("Manuten��o de Sala");
		System.out.println("Altera��o de Sala");
		System.out.println();

		sCodigo = lerCodigo();
		if (sCodigo == null || sCodigo.isEmpty())
			return;

		SalaDTO tDto = sServico.processarConsulta(sCodigo);
		if (!tDto.isOk()) {
			System.out.println(tDto.getAviso());
			return;
		}

		System.out.println("Sala a ser alterada");
		mostrarSala(tDto.getSala());

		System.out.println();
		sNome = lerNome();
		if (sNome == null || sNome.isEmpty()) {
			return;
		}

		sCapacidade = lerCapacidade();
		if (sCapacidade == 0)
			return;

		sEndereco = lerEndereco();
		if (sEndereco == null || sEndereco.isEmpty()) {
			return;
		}

		System.out.println();
		char tConf = Leitor.readChar("Confirma (s/n)... : ");
		System.out.println();

		if (Character.toUpperCase(tConf) == 'S') {
			tDto = sServico.processarAlteracao(sCodigo, sNome, sCapacidade, sEndereco);
			if (tDto.isOk()) {
				System.out.println(tDto.getAviso());
				mostrarSala(tDto.getSala());
			} else
				System.out.println(tDto.getAviso());
		} else
			System.out.println("Opera��o n�o realizada...");
	}

	private static void processarExclusao() {
		System.out.println();
		System.out.println("Manuten��o de Salas");
		System.out.println("Exclus�o de Sala");
		System.out.println();

		sCodigo = lerCodigo();
		if (sCodigo == null || sCodigo.isEmpty())
			return;

		SalaDTO tDto = sServico.processarConsulta(sCodigo);
		if (!tDto.isOk()) {
			System.out.println(tDto.getAviso());
			return;
		}

		System.out.println("Sala a ser exclu�da");
		mostrarSala(tDto.getSala());

		System.out.println();
		char tConf = Leitor.readChar("Confirma (s/n)... : ");
		System.out.println();

		if (Character.toUpperCase(tConf) == 'S') {
			tDto = sServico.processarExclusao(sCodigo);
			if (tDto.isOk()) {
				System.out.println(tDto.getAviso());
				mostrarSala(tDto.getSala());
			} else
				System.out.println(tDto.getAviso());
		} else
			System.out.println("Opera��o n�o realizada...");
	}

	private static void processarConsulta() {
		System.out.println();
		System.out.println("Manuten��o de Salas");
		System.out.println("Consulta de Sala");
		System.out.println();

		sCodigo = lerCodigo();
		if (sCodigo == null || sCodigo.isEmpty())
			return;

		SalaDTO tDto = sServico.processarConsulta(sCodigo);
		if (tDto.isOk()) {
			System.out.println(tDto.getAviso());
			mostrarSala(tDto.getSala());
		} else
			System.out.println(tDto.getAviso());
	}

	private static void processarRelacao() {
		System.out.println();
		System.out.println("Manuten��o de Salas");
		System.out.println("Rela��o de Salas");
		System.out.println();

		SalaDTO tDto = sServico.processarRelacao();
		if (tDto.isOk()) {
			System.out.println(tDto.getAviso());
			Vector<Sala> tLista = tDto.getRelacao();
			for (Sala tSala : tLista) {
				System.out.printf("%-10s - %-10s - %-10s - %-10s%n", tSala.getCodigo(), tSala.getNome(),
						tSala.getCapacidade(), tSala.getEndereco());
			}
		} else
			System.out.println(tDto.getAviso());
	}

	private static String lerCodigo() {
		// C�digo com consist�ncia
		while (true) {
			String tCodigo = Leitor.readString("C�digo......... : ", sCodigo).trim();
			String tErro = CodigoSalaADT.consistir(tCodigo);
			if (tErro.isEmpty())
				return tCodigo;
			else {
				System.out.println("Erro.......... : " + tErro);
				System.out.println();
				break;
			}
		}
		return null;
	}

	private static String lerNome() {
		// C�digo com consist�ncia
		while (true) {
			String tNome = Leitor.readString("Nome......... : ", sNome).trim();
			String tErro = NomeADT.consistir(tNome);
			if (tErro.isEmpty()) {
				return tNome;
			} else {
				System.out.println("Erro.......... : " + tErro);
				System.out.println();
				break;
			}

		}
		return null;
	}

	private static int lerCapacidade() {
		// C�digo com consist�ncia
		while (true) {
			int tCapacidade = Leitor.readInt("Capacidade..... : ", sCapacidade);
			String tErro = CapacidadeSalaADT.consistir(tCapacidade);
			if (tErro.isEmpty())
				return tCapacidade;
			else {
				System.out.println("Erro.......... : " + tErro);
				System.out.println();
				break;
			}
		}
		return 0;
	}

	private static String lerEndereco() {
		// C�digo com consist�ncia
		while (true) {
			String tNome = Leitor.readString("Nome......... : ", sNome).trim();
			String tErro = NomeADT.consistir(tNome);
			if (tErro.isEmpty()) {
				return tNome;
			} else {
				System.out.println("Erro.......... : " + tErro);
				System.out.println();
				break;
			}

		}
		return null;
	}

	private static void limparDados() {
		sCodigo = "";
		sNome = "";
		sCapacidade = 0;
		sEndereco = "";
	}

	private static void mostrarSala(Sala pSala) {
		sCodigo = pSala.getCodigo();
		sNome = pSala.getNome();
		sCapacidade = pSala.getCapacidade();
		sEndereco = pSala.getEndereco();

		System.out.println();
		System.out.println("C�digo........ : " + sCodigo);
		System.out.println("Nome.......... : " + sNome);
		System.out.println("Capacidade.... : " + sCapacidade);
		System.out.println("Endere�o...... : " + sEndereco);
	}
}
