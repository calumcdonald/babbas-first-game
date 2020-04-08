import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.tiled.TiledMap;

import java.util.ArrayList;
import java.util.Random;

public class Map {
    
    public static final int SIZE = 32;

    private TiledMap map;
    private int id;
    private ArrayList<Entity> entities;
    private ArrayList<Rectangle> collisionList;
    private ArrayList<Rectangle> portalList;
    private ArrayList<Rectangle> starList;

    public Map(String mapPath, int id) throws SlickException {
        map = new TiledMap(mapPath);
        this.id = id;
        entities = new ArrayList<>();
        collisionList = new ArrayList<>();
        portalList = new ArrayList<>();
        starList = new ArrayList<>();
        createStars();
        createCollisionLists();
    }

    public TiledMap getMap(){
        return map;
    }

    public void createCollisionLists(){
        int tileLayer = map.getLayerIndex("map");

        for(int i = 0; i < map.getWidth(); i++){
            for(int j = 0; j < map.getHeight(); j++){
                if(map.getTileId(i, j, tileLayer) == 2){
                    collisionList.add(new Rectangle(i * SIZE, j * SIZE, SIZE, SIZE));
                }
                else if(map.getTileId(i, j, tileLayer) == 3){
                    portalList.add(new Rectangle(i * SIZE, j * SIZE, SIZE, SIZE));
                }
            }
        }

        for(Entity e : entities){
            if(e instanceof Star){
                starList.add(new Rectangle(e.getX() + 8, e.getY() + 8, 16, 16));
            }
        }
    }

    public void createStars() throws SlickException{
        ArrayList<Rectangle> grass = new ArrayList<>();
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
            entities.add(new Star(tile.getX() + 8, tile.getY() + 8, i));
        }
    }

    public void updateCollisions(){
        createCollisionLists();
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

    public int getId(){
        return id;
    }

    public void addEntity(Entity entity){
        entities.add(entity);
    }

    public void removeEntity(Entity entity){
        entities.remove(entity);
    }
}
