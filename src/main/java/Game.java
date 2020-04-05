import java.util.ArrayList;
import java.util.Random;
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
    //private ArrayList<Entity> entities;

    public Game(String gamename){
        super(gamename);
    }

    @Override
    public void init(GameContainer gc) throws SlickException {
        map = new Map("data/babba.tmx");
    }

    @Override
    public void update(GameContainer gc, int i) throws SlickException {
        for(Entity e : map.getEntities()){
            e.update(gc);

            if(!e.checkCollision(map.getCollisionList())) {
                e.setNextLocation();
            }
            
            if(e.checkPortalCollision(map.getPortalList()) != null){
                map.updateCollisions();
            }
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
        for(Rectangle wall: map.getCollisionList()){
            g.draw(wall);
        }

        for(Rectangle portal : map.getPortalList()){
            g.setColor(Color.magenta);
            g.draw(portal);
            g.setColor(Color.white);
        }

        for(Entity e : map.getEntities()){
            if(e instanceof Star){
                g.setColor(Color.yellow);
                g.draw(e.getCollisionBox());
                g.setColor(Color.white);
            }
            else if(e instanceof Player) {
                g.setColor(Color.red);
                g.draw(e.getCollisionBox());
                g.setColor(Color.white);
            }
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