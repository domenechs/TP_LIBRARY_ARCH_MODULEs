package fr.training.spring.library.exposition;

import fr.training.spring.library.application.LibraryService;
import fr.training.spring.library.domain.Livre;
import fr.training.spring.library.domain.Type;
import fr.training.spring.library.domain.Library;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class LibraryResource {

    @Autowired
    LibraryService libraryService;
    @Autowired
    LibraryAdapter libraryAdapter;

    @PostMapping("/libraries/create")
    @ResponseStatus(HttpStatus.CREATED)
    public String createLibrary(@Valid @RequestBody LibraryDTO libraryDTO){
        Library library = libraryAdapter.mapToLibrary(libraryDTO);
        String idLibrary = libraryService.createLibrary(library);
//        System.out.println("ID LIBRARY: " + newLibrary.getId());
//        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
//                                                    .buildAndExpand(library.getId()).toUri();
//        return ResponseEntity.created(location).build();
        return idLibrary;
    }


    @GetMapping("/detail/{id}")
    @ResponseStatus(HttpStatus.OK)
    public LibraryDTO afficherDetailLibrary(@PathVariable String id){
        return libraryAdapter.mapToDTO(libraryService.afficheDetailLibrary(id));
    }

    @GetMapping("/listall")
    @ResponseStatus(HttpStatus.OK)
    public List<LibraryDTO> listLibrarys(){
        return libraryAdapter.mapToDTO(libraryService.findAllLibraries());
    }

    @GetMapping("/listbytype")
    @ResponseStatus(HttpStatus.OK)
    public List<LibraryDTO> listLibraryByType(@RequestParam Type type){
        return libraryAdapter.mapToDTO(libraryService.findLibraryByType(type));
    }

    @GetMapping("/listbyprenomdirecteur")
    @ResponseStatus(HttpStatus.OK)
    public List<LibraryDTO> listLibraryByNameDirecteur(@RequestParam String prenom){
        return libraryAdapter.mapToDTO(libraryService.findLibraryByDirecteurPrenom(prenom));
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public void updateLibrary(@RequestBody LibraryDTO libraryDTO){
        Library library = libraryAdapter.mapToLibrary(libraryDTO);
        libraryService.createLibrary(library);
    }

    @PutMapping("/updateifexist")
    public void updateLibraryIfExist(@RequestBody LibraryDTO libraryDTO){
        Library library = libraryAdapter.mapToLibrary(libraryDTO);
        libraryService.updateIfExist(library);
    }


    @DeleteMapping("/deletelibrary/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteLibrary(@PathVariable String id){
        libraryService.deleteLibrary(id);
    }

    @PutMapping("/addbook/{libraryId}")
    @ResponseStatus(HttpStatus.OK)
    public void addBookToLibrary(@RequestBody AddLivreDTO addLivreDTO,@PathVariable String libraryId){
        libraryService.addBookToLibrary(addLivreDTO.getGenre(),addLivreDTO.getIsbn(),libraryId);

    }

}
