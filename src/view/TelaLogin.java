package view;

import adt.ContaUsuarioADT;
import adt.SenhaUsuarioADT;
import controller.UsuarioService;
import dto.UsuarioDTO;
import util.Leitor;

/**
 * Classe respons�vel pela valida��o do Usu�rio no sistema.<br>
 *
 * @author Jos� Augusto Martins Nieviadonski
 *
 */
public class TelaLogin
{
    private static final UsuarioService sServico = new UsuarioService();

    public static void realizarLogin()
    {
        while (true)
        {
            System.out.println();
            System.out.println("Valida��o de Usu�rio");
            System.out.println();

            String tConta = lerConta();
            if (tConta == null || tConta.isEmpty())
            {
                System.out.println();
                System.out.println("Sistema encerrado");
                System.exit(9);
            }

            String tSenha = lerSenha();

            System.out.println();
            UsuarioDTO tDto = sServico.validarUsuario(tConta, tSenha);
            if (tDto.isOk())
            {
                System.out.println("Usu�rio validado com sucesso");
                return;
            }
            else
            {
                System.out.println(tDto.getAviso());
                System.out.println("Usu�rio inv�lido, reentre...");
            }
        }
    }

    private static String lerConta()
    {
        while (true)
        {
            String tConta = Leitor.readString("Conta......... : ").trim();
            if (tConta.isEmpty())
                break;
            String tErro = ContaUsuarioADT.consistir(tConta);
            if (tErro.isEmpty())
                return tConta;
            else
            {
                System.out.println("Erro.......... : " + tErro);
                System.out.println();
            }
        }
        return null;
    }

    private static String lerSenha()
    {
        while (true)
        {
            String tSenha = Leitor.readString("Senha......... : ").trim();
            if (tSenha.isEmpty())
                break;
            String tErro = SenhaUsuarioADT.consistir(tSenha);
            if (tErro.isEmpty())
                return tSenha;
            else
            {
                System.out.println("Erro.......... : " + tErro);
                System.out.println();
            }
        }
        return null;
    }
}
