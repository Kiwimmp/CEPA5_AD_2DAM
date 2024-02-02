package service;

import dto.LibroDTO;
import model.Libro;
import repository.LibroRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LibroServiceImpl implements LibroService{

    private final LibroRepository libroRepository;

    public LibroServiceImpl(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    @Override
    public LibroDTO saveLibro(LibroDTO lDTO) {
       Libro libro =LibroDTO.convertEntity(lDTO);
       libroRepository.save(libro);
        return lDTO;
    }

    @Override
    public LibroDTO getLibroByID(long id) {
        Optional<Libro> libro = libroRepository.findById(id);
        return libro.map(LibroDTO::convertDTO).orElse(null);
    }

    @Override
    public List<LibroDTO> listAllLibro() {
        List<Libro> lista = libroRepository.findAll();
        List<LibroDTO> listaResultado = new ArrayList<LibroDTO>();
        for (Libro libro : lista) {
            listaResultado.add(LibroDTO.convertDTO(libro));
        }
        return listaResultado;
    }

    @Override
    public void deleteLibro(long id) {
        libroRepository.deleteById(id);
    }
}
