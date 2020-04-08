import java.util.logging.Level;
import java.util.logging.Logger;

import collisions.Collidable;
import collisions.Collision;
import entities.Entity;
import entities.Player;
import map.Map;
import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;

public class Game extends BasicGame{

    public static final int SCALE = 2;
    public static final int TILE_SIZE = 32;

    private Map level1, level2, level3, map;
    private Player babba;

    public Game(String gamename){
        super(gamename);
    }

    @Override
    public void init(GameContainer gc) throws SlickException {
        level1 = new Map("data/babba.tmx", 0);
        level2 = new Map("data/babba2.tmx", 1);
        level3 = new Map("data/babba3.tmx", 2);
        map = level1;

        babba = new Player(TILE_SIZE + 8, TILE_SIZE + 8);

        level1.addEntity(babba);
        level2.addEntity(babba);
        level3.addEntity(babba);
    }

    @Override
    public void update(GameContainer gc, int i) throws SlickException {
        Collidable starToRemove = null;

        for(Entity e : map.getEntities()){
            e.update(gc);

            Collision c = e.checkCollision(map.getColliders());
            if(c == null) {
                e.setNextLocation();
            }
            else if(c.getc1().getDescription().equals("player") && c.getc2().getDescription().equals("portal")){
                if(map.getId() == 0){
                    map = level2;
                    e.setLocation(TILE_SIZE * 8 + 8, TILE_SIZE + 8);
                }
                else if(map.getId() == 1){
                    map = level3;
                    e.setLocation(TILE_SIZE + 8, TILE_SIZE + 8);
                }
                else if(map.getId() == 2){
                    map = level1;
                    e.setLocation(TILE_SIZE + 8, TILE_SIZE + 8);
                }
                map.updateCollisions();
            }
            else if(c.getc1().getDescription().equals("player") && c.getc2().getDescription().equals("star")){
                starToRemove = c.getc2();
            }
        }

        if(starToRemove != null){
            map.removeEntity(starToRemove);
        }
    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException{
        g.scale(SCALE, SCALE);
        gc.setShowFPS(false);
        map.getMap().render(0, 0);

        for(Entity e : map.getEntities()){
            e.getSprite().draw(e.getX(), e.getY());
        }
        //renderCollisionBoxes(g);
    }

    public void renderCollisionBoxes(Graphics g){
        for(Collidable wall: map.getColliders()){
            if(wall.getDescription().equals("wall")){
                g.setColor(Color.white);
            }
            else if(wall.getDescription().equals("portal")){
                g.setColor(Color.magenta);
            }
            g.draw(wall.getCollisionBox());
            g.setColor(Color.white);
        }

        for(Entity e : map.getEntities()){
            if(e.getDescription().equals("star")){
                g.setColor(Color.yellow);
            }
            else if(e.getDescription().equals("player")) {
                g.setColor(Color.red);
            }
            g.draw(e.getCollisionBox());
            g.setColor(Color.white);
        }
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