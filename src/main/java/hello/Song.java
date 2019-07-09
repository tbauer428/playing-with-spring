package hello;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Song {
    private String id;
    private String name;
    private String artist;

}
