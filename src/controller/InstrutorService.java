package controller;

import java.util.Vector;

import dao.Dao;
import dao.DaoFactory;
import dto.InstrutorDTO;
import model.Instrutor;
import util.DaoException;

public class InstrutorService
{

    public InstrutorDTO processarInclusao(int pCodigo, String pNome, String pEmpresa, long pTelefone, String pDocumento,
			String pTipoDocumento)
    {
        try
        {
            // Criando o objeto DAO para a inclus�o
            Dao<Instrutor, String> tDao = DaoFactory.getInstrutorDao();

            // Criando o objeto para persist�ncia
            Instrutor tInstrutor = new Instrutor(pCodigo, pNome, pEmpresa, pTelefone, pDocumento,
        			pTipoDocumento);

            // Persistindo o objeto e verificando se deu OK
            if (tDao.gravar(tInstrutor))
                return new InstrutorDTO(true, "Instrutor inclu�do com sucesso", tInstrutor);

            return new InstrutorDTO(false, "Instrutor j� existe no cadastro");
        }
        catch (DaoException tExcept)
        {
            return new InstrutorDTO(false, "Problemas na inclus�o do instrutor", tExcept);
        }
    }

    public InstrutorDTO processarAlteracao(int pCodigo, String pNome, String pEmpresa, long pTelefone, String pDocumento,
			String pTipoDocumento)
    {
        try
        {
            // Criando o objeto DAO para a altera��o
            Dao<Instrutor, String> tDao = DaoFactory.getInstrutorDao();

            // Criando o objeto para persist�ncia
            Instrutor tInstrutor = new Instrutor(pCodigo, pNome, pEmpresa, pTelefone, pDocumento,
        			pTipoDocumento);

            // Alterando o objeto e verificando se deu OK
            if (tDao.regravar(tInstrutor))
                return new InstrutorDTO(true, "Instrutor atualizado com sucesso", tInstrutor);

            return new InstrutorDTO(false, "Instrutor n�o existe no cadastro");
        }
        catch (DaoException tExcept)
        {
            return new InstrutorDTO(false, "Problemas na atualiza��o do instrutor", tExcept);
        }
    }

    public InstrutorDTO processarExclusao(int pCodigo)
    {
        try
        {
            // Criando o objeto DAO para a exclus�o
            Dao<Instrutor, String> tDao = DaoFactory.getInstrutorDao();

            // Realizando a leitura do objeto
            Instrutor tInstrutor = tDao.ler(String.valueOf(pCodigo));

            // Verificando se o usu�rio foi lido
            if (tInstrutor == null)
                return new InstrutorDTO(false, "Instrutor n�o existe no cadastro");

            // Excluindo o objeto e verificando se deu OK
            if (tDao.excluir(String.valueOf(pCodigo)))
                return new InstrutorDTO(true, "Instrutor exclu�do com sucesso", tInstrutor);

            return new InstrutorDTO(false, "Instrutor n�o existe no cadastro");
        }
        catch (DaoException tExcept)
        {
            return new InstrutorDTO(false, "Problemas na exclus�o do instrutor", tExcept);
        }
    }

    public InstrutorDTO processarConsulta(int pCodigo)
    {
        try
        {
            // Criando o objeto DAO para a leitura
            Dao<Instrutor, String> tDao = DaoFactory.getInstrutorDao();

            // Realizando a leitura do objeto
            Instrutor tInstrutor = tDao.ler(String.valueOf(pCodigo));

            // Verificando se o usu�rio foi lido
            if (tInstrutor == null)
                return new InstrutorDTO(false, "Instrutor n�o existe no cadastro");

            return new InstrutorDTO(true, "Instrutor recuperado com sucesso", tInstrutor);
        }
        catch (DaoException tExcept)
        {
            return new InstrutorDTO(false, "Problemas na recupera��o do instrutor", tExcept);
        }
    }

    public InstrutorDTO processarRelacao()
    {
        try
        {
            // Criando o objeto DAO para a recupera��o da lista
            Dao<Instrutor, String> tDao = DaoFactory.getInstrutorDao();

            // Reuperando a lista de usu�rios
            Vector<Instrutor> tLista = tDao.obterRelacaoGeral();

            // verificando se a lista tem usu�rios ou est� vazia
            if (tLista.isEmpty())
                return new InstrutorDTO(false, "N�o existem instrutores cadastrados");

            return new InstrutorDTO(true, "Instrutores cadastrados", tLista);
        }
        catch (DaoException tExcept)
        {
            return new InstrutorDTO(false, "Problemas na inclus�o do instrutor", tExcept);
        }
    }

}
