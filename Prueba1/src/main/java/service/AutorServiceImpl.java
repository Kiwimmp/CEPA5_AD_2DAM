package service;

import dto.AutorDTO;
import model.Autor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.AutorRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AutorServiceImpl implements AutorService {

    private final AutorRepository autorRepository;

    @Autowired
    public AutorServiceImpl(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    @Override
    public AutorDTO saveAutor(AutorDTO aDTO) {
        Autor autor = AutorDTO.convertEntity(aDTO);
        autorRepository.save(autor);
        return aDTO;
    }

    @Override
    public AutorDTO getAutorByID(long id) {
        Optional<Autor> autor = autorRepository.findById(id);
        return autor.map(AutorDTO::convertDTO).orElse(null);
    }

    @Override
    public List<AutorDTO> listAllAutor() {
        List<Autor> lista = autorRepository.findAll();
        List<AutorDTO> listaResultado = new ArrayList<AutorDTO>();
        for (Autor autor : lista) {
            listaResultado.add(AutorDTO.convertDTO(autor));
        }
        return listaResultado;
    }

    @Override
    public void deleteAutor(long id) {
        autorRepository.deleteById(id);
    }

}
