package gui_Eduardo;

import dominio_Eduardo.JogoDaForca;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

public class JanelaPrincipal extends JFrame implements ActionListener {
    private JogoDaForca jogoDaForca;
    private String[] auxiliar = {"Q","W","E","R","T","Y","U","I","O","P",
                                "A","S","D","F","G","H","J","K","L","Ç",
                                "Z","X","C","V","B","N","M"};
    private JButton botaoNovo;
    private JButton botaoConferir;
    private JButton botaoSair;

    private JLabel textoAcima;
    private JLabel textoAbaixo;

    private JTextField[] letrasPalavra;

    private JButton[] letrasTeclado;

    private JPanel sul;
    private JPanel centro;
    private JPanel norte;

    private JPanel teclado;
    private JPanel linhaTec1;
    private JPanel linhaTec2;
    private JPanel linhaTec3;

    public JanelaPrincipal() {
        super("Jogo da Forca");
        this.jogoDaForca = new JogoDaForca("palavras7letras");
        jogoDaForca.sorteiaPalavraJogo();
        instanciarComponentes();
        adicionarComponentes();
        registrarHandlerDeEvento();
    }

    private void instanciarComponentes() {
        botaoNovo = new JButton("Novo jogo");
        botaoConferir = new JButton("Conferir");
        botaoSair = new JButton("Sair");

        textoAcima = new JLabel("Adivinhe a palavra");
        textoAbaixo = new JLabel("Você tem " + jogoDaForca.getNumTentativas() + " tentativas");

        letrasPalavra = new JTextField[7];
        for(int i=0;i<letrasPalavra.length;i++) {
            letrasPalavra[i] = new JTextField(" ");
        }

        letrasTeclado = new JButton[27];
        for(int i=0;i<letrasTeclado.length;i++) {
            letrasTeclado[i] = new JButton(auxiliar[i]);
        }

        norte = new JPanel();
        sul = new JPanel();
        centro = new JPanel();
        teclado = new JPanel();

        linhaTec1 = new JPanel();
        linhaTec2 = new JPanel();
        linhaTec3 = new JPanel();
    }

    private void adicionarComponentes() {
        setLayout(new BorderLayout());
        add(textoAcima, BorderLayout.NORTH);
        add(textoAbaixo,BorderLayout.SOUTH);

        add(centro,BorderLayout.CENTER);
        centro.setLayout(new GridLayout(3,1));
        norte.setLayout(new GridLayout(1,7));
        centro.add(norte);
        centro.add(teclado);
        centro.add(sul);
        for(JTextField campo: letrasPalavra) {
            norte.add(campo);
        }
        sul.setLayout(new GridLayout(1,3));
        sul.add(botaoNovo);
        sul.add(botaoConferir);
        sul.add(botaoSair);

        teclado.setLayout(new GridLayout(3,1));
        teclado.add(linhaTec1);
        teclado.add(linhaTec2);
        teclado.add(linhaTec3);

        linhaTec1.setLayout(new GridLayout(1,10));
        linhaTec2.setLayout(new GridLayout(1,10));
        linhaTec3.setLayout(new GridLayout(1,7));

        for(int i=0;i<10; i++) {
            linhaTec1.add(letrasTeclado[i]);
        }
        for(int i=10;i<20; i++) {
            linhaTec2.add(letrasTeclado[i]);
        }
        for(int i=20;i<27; i++) {
            linhaTec3.add(letrasTeclado[i]);
        }
    }

    private void registrarHandlerDeEvento() {
        botaoNovo.addActionListener(this);
        botaoConferir.addActionListener(this);
        botaoSair.addActionListener(this);
        for(int i=0; i<letrasTeclado.length;i++){
            letrasTeclado[i].addActionListener(this);
        }
        for(int i=0; i<letrasPalavra.length; i++){
            letrasPalavra[i].addActionListener(this);
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (((JButton)e.getSource()).getText().equals("Conferir")){
            JOptionPane.showMessageDialog(this, "A palavra era: " + jogoDaForca.getPalavra() , "Jogo da forca", JOptionPane.INFORMATION_MESSAGE);
        }
        if (((JButton)e.getSource()).getText().equals("Sair")){
            System.exit(0);
        }
        if (((JButton)e.getSource()).getText().equals("Novo jogo")){
            jogoDaForca.gameReset("palavras7letras");
            jogoDaForca.sorteiaPalavraJogo();
            textoAbaixo.setText("Você tem " + jogoDaForca.getNumTentativas() + " tentativas");
            for(JTextField campo: letrasPalavra) {
                campo.setText("");
            }
            JOptionPane.showMessageDialog(this, "O jogo foi reiniciado!\nBoa sorte!", "Jogo da forca", JOptionPane.INFORMATION_MESSAGE);
        }
        if(!((((JButton)e.getSource()).getText().equals("Novo jogo")) | (((JButton)e.getSource()).getText().equals("Sair")) | (((JButton)e.getSource()).getText().equals("Conferir")))){
            ArrayList<Integer> control;
            control = jogoDaForca.verificarLetra(((JButton)e.getSource()).getText().toLowerCase());
            if(control.size() != 0){

                for(Integer aux : control){
                    letrasPalavra[aux].setText(((JButton)e.getSource()).getText());
                }
            }else{
                jogoDaForca.setNumTentativas(jogoDaForca.getNumTentativas()-1);
                textoAbaixo.setText("Você tem " + jogoDaForca.getNumTentativas() + " tentativas");
                if(jogoDaForca.verificarFimDeJogo(null)){
                    JOptionPane.showMessageDialog(this, "Game Over!", "Jogo da forca", JOptionPane.WARNING_MESSAGE);
                }
            }
        }
    }

    public static void main(String[] args) {
        JanelaPrincipal janela = new JanelaPrincipal();
        janela.setSize(500, 450);

        janela.setVisible(true);
    }


}
