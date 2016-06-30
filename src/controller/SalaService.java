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
            // Criando o objeto DAO para a inclusão
            Dao<Sala, String> tDao = DaoFactory.getSalaDao();

            // Criando o objeto para persistência
            Sala tSala = new Sala(pCodigo, pNome, pCapacidade, pEndereco);

            // Persistindo o objeto e verificando se deu OK
            if (tDao.gravar(tSala))
                return new SalaDTO(true, "Sala incluída com sucesso", tSala);

            return new SalaDTO(false, "Sala já existe no cadastro");
        }
        catch (DaoException tExcept)
        {
            return new SalaDTO(false, "Problemas na inclusão da sala", tExcept);
        }
    }

    public SalaDTO processarAlteracao(String pCodigo, String pNome, int pCapacidade, String pEndereco)
    {
        try
        {
            // Criando o objeto DAO para a alteração
            Dao<Sala, String> tDao = DaoFactory.getSalaDao();

            // Criando o objeto para persistência
            Sala tSala = new Sala(pCodigo, pNome, pCapacidade, pEndereco);

            // Alterando o objeto e verificando se deu OK
            if (tDao.regravar(tSala))
                return new SalaDTO(true, "Sala atualizada com sucesso", tSala);

            return new SalaDTO(false, "Sala não existe no cadastro");
        }
        catch (DaoException tExcept)
        {
            return new SalaDTO(false, "Problemas na atualização da sala", tExcept);
        }
    }

    public SalaDTO processarExclusao(String pCodigo)
    {
        try
        {
            // Criando o objeto DAO para a exclusão
            Dao<Sala, String> tDao = DaoFactory.getSalaDao();

            // Realizando a leitura do objeto
            Sala tSala = tDao.ler(pCodigo);

            // Verificando se o usuário foi lido
            if (tSala == null)
                return new SalaDTO(false, "Sala não existe no cadastro");

            // Excluindo o objeto e verificando se deu OK
            if (tDao.excluir(pCodigo))
                return new SalaDTO(true, "Sala excluída com sucesso", tSala);

            return new SalaDTO(false, "Sala não existe no cadastro");
        }
        catch (DaoException tExcept)
        {
            return new SalaDTO(false, "Problemas na exclusão do Sala", tExcept);
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

            // Verificando se o usuário foi lido
            if (tSala == null)
                return new SalaDTO(false, "Sala não existe no cadastro");

            return new SalaDTO(true, "Sala recuperada com sucesso", tSala);
        }
        catch (DaoException tExcept)
        {
            return new SalaDTO(false, "Problemas na recuperação da sala", tExcept);
        }
    }

    public SalaDTO processarRelacao()
    {
        try
        {
            // Criando o objeto DAO para a recuperação da lista
            Dao<Sala, String> tDao = DaoFactory.getSalaDao();

            // Reuperando a lista de usuários
            Vector<Sala> tLista = tDao.obterRelacaoGeral();

            // verificando se a lista tem usuários ou está vazia
            if (tLista.isEmpty())
                return new SalaDTO(false, "Não existem salas cadastradas");

            return new SalaDTO(true, "Salas cadastradas", tLista);
        }
        catch (DaoException tExcept)
        {
            return new SalaDTO(false, "Problemas na inclusão da sala", tExcept);
        }
    }

}
