package dto;
import lombok.Getter;
import lombok.Setter;
import model.Autor;
import model.Libro;
import java.util.List;

public class AutorDTO {
    public static AutorDTO convertDTO(Autor autor){
        AutorDTO autorDTO = new AutorDTO();
        autorDTO.setId(autor.getId());
        autorDTO.setNombre(autor.getNombre());
        autorDTO.setLibros(autor.getLibros());
        return autorDTO;
    }
    public static Autor convertEntity(AutorDTO autorDTO){
        Autor autor= new Autor();
        autor.setId(autorDTO.getId());
        autor.setNombre(autorDTO.getNombre());
        return autor;
    }
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private String nombre;
    private List libros;

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }
}