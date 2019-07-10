package hello;

import java.util.concurrent.atomic.AtomicLong;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import repository.SongNotFoundException;
import repository.SongRepository;


@RestController
@Slf4j
public class GreetingController {

    private static final String template = "Hello, %s!";
    private static final String error = "error";
    private final AtomicLong counter = new AtomicLong();

    private SongRepository repository = new SongRepository();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                String.format(template, name));
    }

    @GetMapping("/songs")
    public Map<String, Song> getAll(){
        return repository.findAll();
    }

    @PostMapping("/songs")
    @ResponseStatus(HttpStatus.CREATED)
    public Song newSong(@RequestBody Song newSong) {
        log.info("New Song Created: {}", newSong);
        return repository.save(newSong);
    }

    @PutMapping("/songs/{id}")
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    public Song updateSong(@PathVariable String id, @RequestBody Song updatedSong) {
        return repository.findAndUpdate(id, updatedSong);
    }

    @RequestMapping("/songs/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public Song foundSong(@PathVariable String id){
        return repository.findById(id);
    }

    @DeleteMapping("/songs/delete/{id}")
    public Song deleteById(@PathVariable String id){
        return repository.deleteById(id);
    }

    @ExceptionHandler(SongNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String songNotFoundHandler(SongNotFoundException ex) {
        log.error("Song was not found", ex);
        return "Song Not Found";
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String illegalArgumentException(IllegalArgumentException ex) {
        log.error("artist or name was passed a null", ex);
        return "Artist and Name cannot be null";
    }

}

