package repository;

import hello.Song;
import lombok.var;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SongRepository {

    private Map<String, Song> songMap = new HashMap<>();

    public Song save(Song newSong) {
        validate(newSong);
        var id = UUID.randomUUID().toString();
        newSong.setId(id);
        songMap.put(id, newSong);
        return newSong;
    }

    private void validate(Song newSong) {
        if(newSong.getArtist() == null || newSong.getName()==null){
            throw new IllegalArgumentException();
        }
    }

    public Song findById(String id){
        var song = songMap.get(id);
        if(song == null){
            throw new SongNotFoundException();
        }else {
            return songMap.get(id);
        }
    }

    public Map<String, Song> findAll(){
        return songMap;
    }

    public Song deleteById(String id){
        return songMap.remove(id);
    }
}
