import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

public class Game extends BasicGame{

    public static final int SCALE = 2;
    public static final int SIZE = 32;
    public static final int SPRITE_SIZE = 16;

    private Map map;
    private ArrayList<Entity> entities;
    private ArrayList<Rectangle> collisionList;
    private ArrayList<Rectangle> portalList;

    public Game(String gamename){
        super(gamename);
    }

    @Override
    public void init(GameContainer gc) throws SlickException {
        map = new Map();
        entities = new ArrayList<>();
        collisionList = createCollisionList();
        portalList = createPortalList();

        Player babba = new Player(SIZE + 8, SIZE + 8, 0.1f);
        entities.add(babba);
    }

    @Override
    public void update(GameContainer gc, int i) throws SlickException {
        for(Entity e : entities){
            e.update(gc);

            if(!e.checkCollision(collisionList)) {
                e.setNextLocation();
            }
            
            if(e.checkPortalCollision(portalList) != null){
                map.setMap();
                updateCollisions();
            }
        }
    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException{
        g.scale(SCALE, SCALE);
        gc.setShowFPS(false);
        map.getMap().render(0, 0);

        for(Entity e : entities){
            e.getSprite().draw((int)e.getX(), (int)e.getY());
        }
        renderCollisionBoxes(g);
    }

//    public boolean checkCollision(Rectangle rec){
//        for(Rectangle rectangle : collisionList) {
//            if(rec.intersects(rectangle)){
//                return false;
//            }
//        }
//        System.out.println("bang");
//        return true;
//    }

    public void renderCollisionBoxes(Graphics g){
        for(Rectangle wall: collisionList){
            g.draw(wall);
        }

        for(Rectangle portal : portalList){
            g.setColor(Color.magenta);
            g.draw(portal);
            g.setColor(Color.white);
        }

        for(Entity e : entities){
            g.draw(e.getCollisionBox());
        }
    }

    public void updateCollisions(){
        collisionList = createCollisionList();
        portalList = createPortalList();
    }

    public ArrayList<Rectangle> createCollisionList(){
        ArrayList<Rectangle> list = new ArrayList<>();

        int tileLayer = map.getMap().getLayerIndex("map");

        for(int i = 0; i < map.getMap().getWidth(); i++){
            for(int j = 0; j < map.getMap().getHeight(); j++){
                if(map.getMap().getTileId(i, j, tileLayer) == 2){
                    list.add(new Rectangle(i * SIZE, j * SIZE, SIZE, SIZE));
                }
            }
        }

        return list;
    }

    public ArrayList<Rectangle> createPortalList(){
        ArrayList<Rectangle> list = new ArrayList<>();

        int tileLayer = map.getMap().getLayerIndex("map");

        for(int i = 0; i < map.getMap().getWidth(); i++){
            for(int j = 0; j < map.getMap().getHeight(); j++){
                if(map.getMap().getTileId(i, j, tileLayer) == 3){
                    list.add(new Rectangle(i * SIZE, j * SIZE, SIZE, SIZE));
                }
            }
        }

        return list;
    }

    public static void main(String[] args){
        try{
            AppGameContainer appgc;
            appgc = new AppGameContainer(new Game("Babba's First Game"));
            appgc.setDisplayMode(320 * SCALE, 320 * SCALE, false);
            appgc.start();
        } catch(SlickException ex){
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}