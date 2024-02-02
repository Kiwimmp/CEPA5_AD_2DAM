package service;

import dto.AutorDTO;

import java.util.List;

public interface AutorService {
    AutorDTO saveAutor(AutorDTO aDTO);
    AutorDTO getAutorByID(long id);
    List<AutorDTO> listAllAutor();
    void deleteAutor(long id);
}

