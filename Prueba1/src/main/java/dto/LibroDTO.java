package dto;

import model.Autor;
import model.Libro;

public class LibroDTO {
    public static LibroDTO convertDTO(Libro libro){
        LibroDTO libroDTO = new LibroDTO();
        libroDTO.setId(libro.getId());
        libroDTO.setAutor(libro.getAutor());
        libroDTO.setTitulo(libro.getTitulo());
        return libroDTO;
    }

    public static Libro convertEntity(LibroDTO libroDTO){
        Libro libro = new Libro();
        libro.setId(libroDTO.getId());
        libro.setAutor(libroDTO.getAutor());
        libro.setTitulo(libroDTO.getTitulo());
        return libro;
    }
    private Long id;
    private String titulo;
    private Autor autor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }
}
