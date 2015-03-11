
package modelo;

public class Mp3 {

  private int id;
  private String musica;
  private String musicobanda;
  private int ano;
  private Genero genero;
  
  public Mp3(){
      
  }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMusica() {
        return musica;
    }

    public void setMusica(String musica) {
        this.musica = musica;
    }

    public String getMusicobanda() {
        return musicobanda;
    }

    public void setMusicobanda(String musicobanda) {
        this.musicobanda = musicobanda;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

  
  
}
