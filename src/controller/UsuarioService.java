package controller;

import java.util.Vector;

import dao.Dao;
import dao.DaoFactory;
import dto.UsuarioDTO;
import model.Usuario;
import util.DaoException;

public class UsuarioService
{

    public UsuarioDTO validarUsuario(String pConta, String pSenha)
    {
        try
        {
            // Criando o objeto DAO para a leitura
            Dao<Usuario, String> tDao = DaoFactory.getUsuarioDao();

            // Realizando a leitura do objeto
            Usuario tUsuario = tDao.ler(pConta);

            // Verificando se o usu�rio foi lido
            if (tUsuario == null)
                return new UsuarioDTO(false, "Usu�rio n�o existe no cadastro");

            // Validando a senha
            if (tUsuario.getSenha().equals(pSenha))
                return new UsuarioDTO(true, "Usu�rio validado com sucesso", tUsuario);

            return new UsuarioDTO(false, "Senha do usu�rio inv�lida");
        }
        catch (DaoException tExcept)
        {
            return new UsuarioDTO(false, "Problemas na recupera��o do usu�rio", tExcept);
        }
    }

    public UsuarioDTO processarInclusao(String tConta, String tSenha)
    {
        try
        {
            // Criando o objeto DAO para a inclus�o
            Dao<Usuario, String> tDao = DaoFactory.getUsuarioDao();

            // Criando o objeto para persist�ncia
            Usuario tUsuario = new Usuario(tConta, tSenha);

            // Persistindo o objeto e verificando se deu OK
            if (tDao.gravar(tUsuario))
                return new UsuarioDTO(true, "Usu�rio inclu�do com sucesso", tUsuario);

            return new UsuarioDTO(false, "Usu�rio j� existe no cadastro");
        }
        catch (DaoException tExcept)
        {
            return new UsuarioDTO(false, "Problemas na inclus�o do usu�rio", tExcept);
        }
    }

    public UsuarioDTO processarAlteracao(String pConta, String pSenha)
    {
        try
        {
            // Criando o objeto DAO para a altera��o
            Dao<Usuario, String> tDao = DaoFactory.getUsuarioDao();

            // Criando o objeto para persist�ncia
            Usuario tUsuario = new Usuario(pConta, pSenha);

            // Alterando o objeto e verificando se deu OK
            if (tDao.regravar(tUsuario))
                return new UsuarioDTO(true, "Usu�rio atualizado com sucesso", tUsuario);

            return new UsuarioDTO(false, "Usu�rio n�o existe no cadastro");
        }
        catch (DaoException tExcept)
        {
            return new UsuarioDTO(false, "Problemas na atualiza��o do usu�rio", tExcept);
        }
    }

    public UsuarioDTO processarExclusao(String pConta)
    {
        try
        {
            // Criando o objeto DAO para a exclus�o
            Dao<Usuario, String> tDao = DaoFactory.getUsuarioDao();

            // Realizando a leitura do objeto
            Usuario tUsuario = tDao.ler(pConta);

            // Verificando se o usu�rio foi lido
            if (tUsuario == null)
                return new UsuarioDTO(false, "Usu�rio n�o existe no cadastro");

            // Excluindo o objeto e verificando se deu OK
            if (tDao.excluir(pConta))
                return new UsuarioDTO(true, "Usu�rio exclu�do com sucesso", tUsuario);

            return new UsuarioDTO(false, "Usu�rio n�o existe no cadastro");
        }
        catch (DaoException tExcept)
        {
            return new UsuarioDTO(false, "Problemas na exclus�o do usu�rio", tExcept);
        }
    }

    public UsuarioDTO processarConsulta(String pConta)
    {
        try
        {
            // Criando o objeto DAO para a leitura
            Dao<Usuario, String> tDao = DaoFactory.getUsuarioDao();

            // Realizando a leitura do objeto
            Usuario tUsuario = tDao.ler(pConta);

            // Verificando se o usu�rio foi lido
            if (tUsuario == null)
                return new UsuarioDTO(false, "Usu�rio n�o existe no cadastro");

            return new UsuarioDTO(true, "Usu�rio recuperado com sucesso", tUsuario);
        }
        catch (DaoException tExcept)
        {
            return new UsuarioDTO(false, "Problemas na recupera��o do usu�rio", tExcept);
        }
    }

    public UsuarioDTO processarRelacao()
    {
        try
        {
            // Criando o objeto DAO para a recupera��o da lista
            Dao<Usuario, String> tDao = DaoFactory.getUsuarioDao();

            // Reuperando a lista de usu�rios
            Vector<Usuario> tLista = tDao.obterRelacaoGeral();

            // verificando se a lista tem usu�rios ou est� vazia
            if (tLista.isEmpty())
                return new UsuarioDTO(false, "N�o existem usu�rios cadastrados");

            return new UsuarioDTO(true, "Usu�rios cadastrados", tLista);
        }
        catch (DaoException tExcept)
        {
            return new UsuarioDTO(false, "Problemas na inclus�o do usu�rio", tExcept);
        }
    }

}
