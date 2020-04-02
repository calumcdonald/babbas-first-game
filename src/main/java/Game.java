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

    private Map grassMap;
    private ArrayList<Entity> entities;
    private ArrayList<Rectangle> collisionList;

    public Game(String gamename){
        super(gamename);
    }

    @Override
    public void init(GameContainer gc) throws SlickException {
        grassMap = new Map();
        entities = new ArrayList<>();
        collisionList = createCollisionList();

        Player babba = new Player(SIZE + 1, SIZE + 1);
        entities.add(babba);
    }

    @Override
    public void update(GameContainer gc, int i) throws SlickException {
        for(Entity e : entities){
            if(checkCollision(e.getCollisionBox())) {
                e.update(gc);
            }
        }
    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException{
        g.scale(SCALE, SCALE);
        gc.setShowFPS(false);
        grassMap.getMap().render(0, 0);

        for(Entity e : entities){
            e.getSprite().draw((int)e.getX(), (int)e.getY());
        }
        renderCollisionBoxes(g);
    }

    public boolean checkCollision(Rectangle rec){
        for(Rectangle rectangle : collisionList) {
            if(rec.intersects(rectangle)) {
                System.out.println("boink");
                return false;
            }
        }
        return true;
    }

    public void renderCollisionBoxes(Graphics g){
        for(int i = 0; i < collisionList.size(); i++){
            g.draw(collisionList.get(i));
        }

        for(Entity e : entities){
            g.draw(e.getCollisionBox());
        }
    }

    public ArrayList<Rectangle> createCollisionList(){
        ArrayList<Rectangle> list = new ArrayList<>();

        int tileLayer = grassMap.getMap().getLayerIndex("map");

        for(int i = 0; i < grassMap.getMap().getWidth(); i++){
            for(int j = 0; j < grassMap.getMap().getHeight(); j++){
                if(grassMap.getMap().getTileId(i, j, tileLayer) == 2){
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