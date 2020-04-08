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
    private ArrayList<Star> starList;

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
            Star newStar = new Star(tile.getX() + 8, tile.getY() + 8, i);
            entities.add(newStar);
            starList.add(newStar);
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

    public ArrayList<Entity> getEntities(){
        return entities;
    }

    public ArrayList<Star> getStarList(){
        return starList;
    }

    public int getId(){
        return id;
    }

    public void addEntity(Entity entity){
        entities.add(entity);
    }

    public void removeStar(Star star){
        for(Star s : starList){
            if(star.getId() == s.getId()){
                starList.remove(star);
            }
        }

        for(Entity e : entities){
            if(e.getX() == star.getX() && e.getY() == star.getY() && e instanceof Star){
                entities.remove(e);
            }
        }
    }
    public void removeEntity(Entity entity){
        entities.remove(entity);
    }
}
