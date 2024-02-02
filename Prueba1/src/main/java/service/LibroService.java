package service;

import dto.LibroDTO;

import java.util.List;

public interface LibroService {
    LibroDTO saveLibro(LibroDTO lDTO);
    LibroDTO getLibroByID(long id);
    List<LibroDTO> listAllLibro();
    void deleteLibro(long id);
}
