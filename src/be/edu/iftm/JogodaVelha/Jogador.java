package be.edu.iftm.JogodaVelha;

public class Jogador {

    protected String nome;
    protected char Simbolo;
    protected Jogador joga;
    

    public Jogador(String nome, char simbolo){
        this.nome = nome;
        Simbolo = simbolo;
    }
/*
    public Jogador(Jogador joga){
        this.joga = joga;
    }
*/
    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }
    
    public char getSimbolo(){
        return Simbolo;
    }

    public void setSimbolo(char simbolo){
        Simbolo = simbolo;
    }
}
