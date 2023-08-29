package servicos_Eduardo;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class TextFileApps {

    public static ArrayList<String> lerArquivo(String nome){
        try {
            Scanner leitor = new Scanner(Paths.get(nome));
            ArrayList<String> retorno = new ArrayList<>();
            while (leitor.hasNext()){
                retorno.add(leitor.next());
            }
            leitor.close();
            return retorno;
        }catch (IOException e){
            e.printStackTrace();
            System.err.println("Arquivo não aberto.");
        }
        return null;
    }

}
