package repository;

import hello.Song;
import lombok.var;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SongRepository {

    private Map<String, Song> songMap = new HashMap<>();

    public Song save(Song newSong) {
        var id = UUID.randomUUID().toString();
        newSong.setId(id);
        songMap.put(id, newSong);
        return newSong;
    }

    public Song findById(String id){
        return songMap.get(id);
    }

    public Map<String, Song> findAll(){
        return songMap;
    }

    public Song deleteById(String id){
        return songMap.remove(id);
    }
}
