package me.alexfrocha;

import java.io.*;
import java.nio.file.NoSuchFileException;

public class JNotes {
    private static String usuario = System.getProperty("user.name");
    private static String diretorioDoArquivo;
    private static String diretorioDaPlanilha;

    public static void lerArquivo() throws Exception {
        FileReader leituraDoArquivo = new FileReader(new File(diretorioDoArquivo));
        BufferedReader arquivo = new BufferedReader(leituraDoArquivo);

        String linha = arquivo.readLine();
        while(linha != null) {

            if(linha.contains("JNOTES")) {
                linha = arquivo.readLine();
                while(!linha.contains("JEND")) {
                    String produto = linha;
                    produto = produto.replaceAll(" ", "").replace("-", ",");
                    transferindoDados(produto);

                    linha = arquivo.readLine();
                }
            }


            linha = arquivo.readLine();
        }

        arquivo.close();
        leituraDoArquivo.close();
    }

    public static void transferindoDados(String dados) throws Exception {
        diretorioDaPlanilha = diretorioDoArquivo.replace(".txt", ".csv");
        Writer arquivo = new BufferedWriter(new FileWriter(diretorioDaPlanilha, true));
            arquivo.append(dados);
            arquivo.append("\n");
        arquivo.close();
    }

    public static void selecionarOArquivo(String diretorioDoArquivoSelecionado) throws NoSuchFileException {
        if(diretorioDoArquivoSelecionado == null) throw new NoSuchFileException("Arquivo não encontrado");
        diretorioDoArquivo = diretorioDoArquivoSelecionado;
    }

}
