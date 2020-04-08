package map;

import collisions.Collidable;
import collisions.LabelledCollidable;
import entities.Entity;
import entities.Star;
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
    private List<Entity> entities;
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
                    Rectangle collisionBox = new Rectangle(i * TILE_SIZE, j * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                    LabelledCollidable collider = new LabelledCollidable(collisionBox, "wall");
                    colliders.add(collider);
                }
                else if(map.getTileId(i, j, tileLayer) == 3){
                    Rectangle collisionBox = new Rectangle(i * TILE_SIZE, j * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                    LabelledCollidable collider = new LabelledCollidable(collisionBox, "portal");
                    colliders.add(collider);
                }
            }
        }
    }

    public void createStars() throws SlickException{
        ArrayList<Rectangle> invalidTiles = new ArrayList<>();
        int tileLayer = map.getLayerIndex("map");

        for(int i = 0; i < map.getWidth(); i++){
            for(int j = 0; j < map.getHeight(); j++){
                if(map.getTileId(i, j, tileLayer) == 1){
                    Rectangle tile = new Rectangle(i * TILE_SIZE, j * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                    if(!(tile.getX() == TILE_SIZE && tile.getY() == TILE_SIZE) && !(tile.getX() == TILE_SIZE * 8 && tile.getY() == tile.getY())) {
                        invalidTiles.add(tile);
                    }
                }
            }
        }

        for(int i = 0; i < 5; i++){
            int rand = new Random().nextInt(invalidTiles.size());
            Rectangle tile = invalidTiles.get(rand);
            invalidTiles.remove(tile);
            Star newStar = new Star(tile.getX() + 8, tile.getY() + 8);
            Rectangle collisionBox = new Rectangle(tile.getX() + 8, tile.getY() + 8, 16, 16);
            LabelledCollidable collider = new LabelledCollidable(collisionBox, "star");
            entities.add(newStar);
            colliders.add(collider);
        }
    }

    public void updateCollisions(){
        createCollisionLists();
    }

    public List<Collidable> getColliders(){
        return colliders;
    }

    public List<Entity> getEntities(){
        return entities;
    }

    public int getId(){
        return id;
    }

    public void addEntity(Entity entity){
        entities.add(entity);
    }

    public void removeEntity(Collidable c){
        if(c != null){
            colliders.remove(c);
            Rectangle cBox = c.getCollisionBox();
            entities.removeIf(e -> e.getX() == cBox.getX() && e.getY() == cBox.getY());
        }
    }
}
