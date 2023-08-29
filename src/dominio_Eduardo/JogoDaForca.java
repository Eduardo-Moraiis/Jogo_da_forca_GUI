package dominio_Eduardo;

import java.security.SecureRandom;
import java.util.ArrayList;
import servicos_Eduardo.TextFileApps;

public class JogoDaForca {
    private String palavra;
    private ArrayList<String> palavras;
    private int numTentativas;
    private boolean venceu;
    private String verifica;

    //metodos
    public boolean verificarFimDeJogo(String letra){
        if(numTentativas==0){
            return true;
        }
        return this.venceu;
    }

    public ArrayList<Integer> verificarLetra(String letra){
        ArrayList<Integer> controle = new ArrayList<>();
        controle.clear();
        char[] palavraArray = this.palavra.toLowerCase().toCharArray();
        char aux = letra.charAt(0);
        for(Integer i=0; i<palavraArray.length; i++){
            if(aux == palavraArray[i]){
                controle.add(i);
            }
        }
        return controle;
    }

    public void sorteiaPalavraJogo(){
        SecureRandom random = new SecureRandom();
        this.palavra = palavras.get(random.nextInt(palavras.size()-1));
    }

    public void gameReset(String nomearquivo){
        this.palavra="";
        this.palavras = TextFileApps.lerArquivo(nomearquivo);
        this.numTentativas = 15;
        this.venceu = false;
        this.verifica = "";
    }

    //construtor
    public JogoDaForca(String nomearquivo){
        this.palavra="";
        this.palavras = TextFileApps.lerArquivo(nomearquivo);
        this.numTentativas = 15;
        this.venceu = false;
        this.verifica = "";
    }

    //get and set

    public String getPalavra() {
        return palavra;
    }

    public void setPalavra(String palavra) {
        this.palavra = palavra;
    }

    public ArrayList<String> getPalavras() {
        return palavras;
    }

    public void setPalavras(ArrayList<String> palavras) {
        this.palavras = palavras;
    }

    public int getNumTentativas() {
        return numTentativas;
    }

    public void setNumTentativas(int numTentativas) {
        this.numTentativas = numTentativas;
    }

    public boolean isVenceu() {
        return venceu;
    }

    public void setVenceu(boolean venceu) {
        this.venceu = venceu;
    }

    public String getVerifica() {
        return verifica;
    }

    public void setVerifica(String verifica) {
        this.verifica = verifica;
    }
}
