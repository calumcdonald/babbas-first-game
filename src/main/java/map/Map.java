package map;

import collisions.Collidable;
import collisions.LabelledCollidable;
import entities.Entity;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.tiled.TiledMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Map {
    
    public static final int TILE_SIZE = 32;

    private TiledMap map;
    private int id;
    private ArrayList<Entity> entities;
    private List<Collidable> colliders;

    public Map(String mapPath, int id) throws SlickException {
        map = new TiledMap(mapPath);
        this.id = id;
        entities = new ArrayList<>();

        colliders = new ArrayList<>();

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
                    colliders.add(new LabelledCollidable(
                            new Rectangle(i * TILE_SIZE, j * TILE_SIZE, TILE_SIZE, TILE_SIZE),
                            "wall"));
                }
                else if(map.getTileId(i, j, tileLayer) == 3){
                    colliders.add(new LabelledCollidable(
                            new Rectangle(i * TILE_SIZE, j * TILE_SIZE, TILE_SIZE, TILE_SIZE),
                            "portal"));
                }
            }
        }
    }

    public void createStars(){
        ArrayList<Rectangle> grass = new ArrayList<>();
        int tileLayer = map.getLayerIndex("map");

        for(int i = 0; i < map.getWidth(); i++){
            for(int j = 0; j < map.getHeight(); j++){
                if(map.getTileId(i, j, tileLayer) == 1){
                    grass.add(new Rectangle(i * TILE_SIZE, j * TILE_SIZE, TILE_SIZE, TILE_SIZE));
                }
            }
        }

        for(int i = 0; i < 5; i++){
            int rand = new Random().nextInt(grass.size());
            Rectangle tile = grass.get(rand);
            colliders.add(new LabelledCollidable(new Rectangle(tile.getX() + 8, tile.getY() + 8, 16, 16),
                    "star"));
        }
    }

    public void updateCollisions(){
        createCollisionLists();
    }

    public ArrayList<Entity> getEntities(){
        return entities;
    }

    public int getId(){
        return id;
    }

    public void removeStar(Collidable star){
        entities.remove(star);
        colliders.remove(star);
    }

    public void addEntity(Entity entity){
        entities.add(entity);
    }

    public List<Collidable> getColliders(){
        return colliders;
    }
}
