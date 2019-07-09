package hello;


import java.util.concurrent.atomic.AtomicLong;
import java.util.Map;
import org.springframework.web.bind.annotation.*;
import repository.SongRepository;


@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
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
    public Song newSong(@RequestBody Song newSong) {
        return repository.save(newSong);
    }

    @RequestMapping("/songs/{id}")
    public Song foundSong(@PathVariable String id){
        return repository.findById(id);
    }

    @DeleteMapping("/songs/delete/{id}")
    public Song deleteById(@PathVariable String id){
        return repository.deleteById(id);
    }


}