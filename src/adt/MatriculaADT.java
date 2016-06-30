package adt;

public class MatriculaADT
{
    private static final String MENSAGEM_ERRO = "Matr�cula tem que ser maior que zero";

    public static void validar(int pMatricula)
    {
        if (pMatricula <= 0)
            throw new IllegalArgumentException(MENSAGEM_ERRO);
    }

    public static String consistir(int pMatricula)
    {
        if (pMatricula <= 0)
            return MENSAGEM_ERRO;
         return "";
    }

}
