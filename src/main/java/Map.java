import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.tiled.TiledMap;

import java.util.ArrayList;
import java.util.Random;

public class Map {
    
    public static final int SIZE = 32;

    private TiledMap map;
    private ArrayList<Entity> entities;
    private ArrayList<Rectangle> collisionList;
    private ArrayList<Rectangle> portalList;
    private ArrayList<Rectangle> starList;

    public Map(String mapPath) throws SlickException {
        map = new TiledMap(mapPath);
        entities = new ArrayList<>();
        collisionList = createCollisionList();
        portalList = createPortalList();
        starList = createStars();

        Player babba = new Player(SIZE + 8, SIZE + 8);
        entities.add(babba);
    }

    public TiledMap getMap(){
        return map;
    }

    public ArrayList<Rectangle> createCollisionList(){
        ArrayList<Rectangle> list = new ArrayList<>();

        int tileLayer = map.getLayerIndex("map");

        for(int i = 0; i < map.getWidth(); i++){
            for(int j = 0; j < map.getHeight(); j++){
                if(map.getTileId(i, j, tileLayer) == 2){
                    list.add(new Rectangle(i * SIZE, j * SIZE, SIZE, SIZE));
                }
            }
        }

        return list;
    }

    public ArrayList<Rectangle> createPortalList(){
        ArrayList<Rectangle> list = new ArrayList<>();

        int tileLayer = map.getLayerIndex("map");

        for(int i = 0; i < map.getWidth(); i++){
            for(int j = 0; j < map.getHeight(); j++){
                if(map.getTileId(i, j, tileLayer) == 3){
                    list.add(new Rectangle(i * SIZE, j * SIZE, SIZE, SIZE));
                }
            }
        }

        return list;
    }

    public ArrayList<Rectangle> createStars() throws SlickException{
        ArrayList<Rectangle> grass = new ArrayList<>();
        starList = new ArrayList<>();

        int tileLayer = map.getLayerIndex("map");

        for(int i = 0; i < map.getWidth(); i++){
            for(int j = 0; j < map.getHeight(); j++){
                if(map.getTileId(i, j, tileLayer) == 1){
                    grass.add(new Rectangle(i * SIZE, j * SIZE, SIZE, SIZE));
                }
            }
        }

        for(int i = 0; i < 5; i++){
            int rand = new Random().nextInt(grass.size());
            Rectangle tile = grass.get(rand);
            entities.add(new Star(tile.getX(), tile.getY()));
            starList.add(new Rectangle(tile.getX(), tile.getY(), SIZE, SIZE));
        }
        return starList;
    }

    public void updateCollisions(){
        createCollisionList();
        createPortalList();
    }

    public ArrayList<Rectangle> getCollisionList(){
        return collisionList;
    }

    public ArrayList<Rectangle> getPortalList(){
        return portalList;
    }

    public ArrayList<Rectangle> getStarList(){
        return starList;
    }

    public ArrayList<Entity> getEntities(){
        return entities;
    }
}
