package hello;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import lombok.var;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;




@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private static final String songtemplate = "Artist: %s";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                String.format(template, name));
    }

    @RequestMapping("/songs")
    public List<Song> getSongs(@RequestParam(value="artist", defaultValue = "") String artist) {
        List<Song> songs = Arrays.asList(
                new Song("Spirit of Radio", "Rush"),
                new Song("The Twilight Zone", "Rush"),
                new Song("Heads Will Rolls", "Yeah Yeah Yeahs")
        );
        if(artist.equals("")){
            return songs;
        }else{
            return songs.stream().filter(song -> song.getArtist().equals(artist)).collect(Collectors.toList());

        }
    }
}