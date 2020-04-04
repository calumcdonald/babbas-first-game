import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class Map {

    private TiledMap map;
    private int mapId = 0;

    public Map() throws SlickException {
        map = new TiledMap("data/babba.tmx");
    }

    public TiledMap getMap(){
        return map;
    }

    public void setMap() throws SlickException{
        if(mapId == 0) {
            map = new TiledMap("data/babba2.tmx");
            mapId = 1;
        }
        else{
            map = new TiledMap("data/babba.tmx");
            mapId = 0;
        }
    }
}
