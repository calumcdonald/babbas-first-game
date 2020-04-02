import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class Map {

    private TiledMap grassMap;

    public Map() throws SlickException {
        grassMap = new TiledMap("data/babba.tmx");
    }

    public TiledMap getMap(){
        return grassMap;
    }
}
