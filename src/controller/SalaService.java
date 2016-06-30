package controller;

import java.util.Vector;

import dao.Dao;
import dao.DaoFactory;
import dto.SalaDTO;
import model.Sala;
import util.DaoException;

public class SalaService
{

    public SalaDTO processarInclusao(String pCodigo, String pNome, int pCapacidade, String pEndereco)
    {
        try
        {
            // Criando o objeto DAO para a inclus�o
            Dao<Sala, String> tDao = DaoFactory.getSalaDao();

            // Criando o objeto para persist�ncia
            Sala tSala = new Sala(pCodigo, pNome, pCapacidade, pEndereco);

            // Persistindo o objeto e verificando se deu OK
            if (tDao.gravar(tSala))
                return new SalaDTO(true, "Sala inclu�da com sucesso", tSala);

            return new SalaDTO(false, "Sala j� existe no cadastro");
        }
        catch (DaoException tExcept)
        {
            return new SalaDTO(false, "Problemas na inclus�o da sala", tExcept);
        }
    }

    public SalaDTO processarAlteracao(String pCodigo, String pNome, int pCapacidade, String pEndereco)
    {
        try
        {
            // Criando o objeto DAO para a altera��o
            Dao<Sala, String> tDao = DaoFactory.getSalaDao();

            // Criando o objeto para persist�ncia
            Sala tSala = new Sala(pCodigo, pNome, pCapacidade, pEndereco);

            // Alterando o objeto e verificando se deu OK
            if (tDao.regravar(tSala))
                return new SalaDTO(true, "Sala atualizada com sucesso", tSala);

            return new SalaDTO(false, "Sala n�o existe no cadastro");
        }
        catch (DaoException tExcept)
        {
            return new SalaDTO(false, "Problemas na atualiza��o da sala", tExcept);
        }
    }

    public SalaDTO processarExclusao(String pCodigo)
    {
        try
        {
            // Criando o objeto DAO para a exclus�o
            Dao<Sala, String> tDao = DaoFactory.getSalaDao();

            // Realizando a leitura do objeto
            Sala tSala = tDao.ler(pCodigo);

            // Verificando se o usu�rio foi lido
            if (tSala == null)
                return new SalaDTO(false, "Sala n�o existe no cadastro");

            // Excluindo o objeto e verificando se deu OK
            if (tDao.excluir(pCodigo))
                return new SalaDTO(true, "Sala exclu�da com sucesso", tSala);

            return new SalaDTO(false, "Sala n�o existe no cadastro");
        }
        catch (DaoException tExcept)
        {
            return new SalaDTO(false, "Problemas na exclus�o do Sala", tExcept);
        }
    }

    public SalaDTO processarConsulta(String pCodigo)
    {
        try
        {
            // Criando o objeto DAO para a leitura
            Dao<Sala, String> tDao = DaoFactory.getSalaDao();

            // Realizando a leitura do objeto
            Sala tSala = tDao.ler(pCodigo);

            // Verificando se o usu�rio foi lido
            if (tSala == null)
                return new SalaDTO(false, "Sala n�o existe no cadastro");

            return new SalaDTO(true, "Sala recuperada com sucesso", tSala);
        }
        catch (DaoException tExcept)
        {
            return new SalaDTO(false, "Problemas na recupera��o da sala", tExcept);
        }
    }

    public SalaDTO processarRelacao()
    {
        try
        {
            // Criando o objeto DAO para a recupera��o da lista
            Dao<Sala, String> tDao = DaoFactory.getSalaDao();

            // Reuperando a lista de usu�rios
            Vector<Sala> tLista = tDao.obterRelacaoGeral();

            // verificando se a lista tem usu�rios ou est� vazia
            if (tLista.isEmpty())
                return new SalaDTO(false, "N�o existem salas cadastradas");

            return new SalaDTO(true, "Salas cadastradas", tLista);
        }
        catch (DaoException tExcept)
        {
            return new SalaDTO(false, "Problemas na inclus�o da sala", tExcept);
        }
    }

}
