package controller;

import com.example.prueba1.Prueba1Application;
import dto.AutorDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.AutorService;

import java.util.List;
import java.util.logging.Logger;


@RestController
public class autorController {
    private static final Logger myLog= (Logger) LoggerFactory.getLogger(Prueba1Application.class);

    @Autowired
    private AutorService autorService;

    @Autowired
    private HttpServletRequest context;
    @Value("${app.name}")
    private String appName;

    @Value("${developer.name}")
    private String devName;
    @GetMapping("/")
    public String index(){
        String res="Hello from Spring\n";
        res+= "You are running"+appName+"\n";
        res += "Developed by "+devName;
        return res;
    }
    //Get All
    @GetMapping("/autores")
    public List<AutorDTO> listAutores(){
        myLog.info(context.getMethod()+ "from "+ context.getRemoteHost());
        List<AutorDTO> losAutores=autorService.listAllAutor();
        return losAutores;
    }

    @GetMapping("/autores/{id}")
    public ResponseEntity<AutorDTO> showAutorByID(@PathVariable long id) {
        myLog.info(context.getMethod() + " from " + context.getRemoteHost());

        AutorDTO elAutor = autorService.getAutorByID(id);

        if (elAutor != null) {
            return new ResponseEntity<>(elAutor, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/autores")
    public ResponseEntity<AutorDTO> addDirector(@RequestBody AutorDTO newAutor){
        myLog.info(context.getMethod()+context.getRequestURI());
        AutorDTO elAutor=autorService.saveAutor(newAutor);
            if (elAutor==null)
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            else
                return new ResponseEntity<>(elAutor,HttpStatus.OK);
    }
    @PutMapping("/clientes")
    public ResponseEntity<AutorDTO> updateAutor(@RequestBody AutorDTO updAutor) {
        myLog.info(context.getMethod() + context.getRequestURI());
        AutorDTO elAutor= autorService.getAutorByID(updAutor.getId());
        if (elAutor==null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else{
            AutorDTO elAutorUPD = autorService.saveAutor(updAutor);
            return new ResponseEntity<>(elAutorUPD,HttpStatus.OK);
        }
    }
    @DeleteMapping("/autor/{id}")
    public ResponseEntity<String> deleteAutor(@PathVariable Long id){
        autorService.deleteAutor(id);
        return new ResponseEntity<>("Autor borrado correctamente",HttpStatus.OK);
    }
}
