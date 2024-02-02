package controller;

import com.example.prueba1.Prueba1Application;
import dto.LibroDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.LibroService;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/libros")
public class libroController {
    private static final Logger myLog= (Logger) LoggerFactory.getLogger(Prueba1Application.class);
    @Autowired
    private LibroService libroService;
    @Autowired
    private HttpServletRequest context;

    @GetMapping
    public List<LibroDTO> listLibros() {
        myLog.info(context.getMethod() + " from " + context.getRemoteHost());
        List<LibroDTO> losLibros = libroService.listAllLibro();
        return losLibros;
    }

    @GetMapping("/{id}")
    public ResponseEntity<LibroDTO> showLibroById(@PathVariable long id) {
        LibroDTO elLibro = libroService.getLibroByID(id);

        if (elLibro != null) {
            return new ResponseEntity<>(elLibro, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public ResponseEntity<LibroDTO> addLibro(@RequestBody LibroDTO newLibro) {
        myLog.info(context.getMethod()+context.getRequestURI());

        LibroDTO elLibro = libroService.saveLibro(newLibro);
        if (elLibro == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(elLibro, HttpStatus.OK);
        }
    }

    @PutMapping
    public ResponseEntity<LibroDTO> updateLibro(@RequestBody LibroDTO updLibro) {
        myLog.info(context.getMethod() + context.getRequestURI());
        LibroDTO elLibro = libroService.getLibroByID(updLibro.getId());

        if (elLibro == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            LibroDTO elLibroUPD = libroService.saveLibro(updLibro);
            return new ResponseEntity<>(elLibroUPD, HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLibro(@PathVariable Long id) {
        libroService.deleteLibro(id);
        return new ResponseEntity<>("Libro borrado correctamente", HttpStatus.OK);
    }
}
