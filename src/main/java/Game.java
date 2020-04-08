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
    public static final int SIZE = 32;

    private Map level1, level2, level3, map;
    private Player babba;
    private Image starImage;

    public Game(String gamename){
        super(gamename);
    }

    @Override
    public void init(GameContainer gc) throws SlickException {
        level1 = new Map("data/babba.tmx", 0);
        level2 = new Map("data/babba2.tmx", 1);
        level3 = new Map("data/babba3.tmx", 2);
        map = level1;

        starImage = new Image("data/star1.png");

        babba = new Player(SIZE + 8, SIZE + 8);
        level1.addEntity(babba);
        level2.addEntity(babba);
        level3.addEntity(babba);
    }

    @Override
    public void update(GameContainer gc, int i) throws SlickException {
        for(Entity e : map.getEntities()){
            e.update(gc);

            Collision c = e.checkCollision(map.getColliders());
            if(c == null) {
                e.setNextLocation();
            }
            else if(c.getOne().getDescription().equals("player") && c.getTwo().getDescription().equals("portal")){
                if(map.getId() == 0){
                    map = level2;
                    e.setLocation(SIZE * 8 + 8, SIZE + 8);
                }
                else if(map.getId() == 1){
                    map = level3;
                    e.setLocation(SIZE + 8, SIZE + 8);
                }
                else if(map.getId() == 2){
                    map = level1;
                    e.setLocation(SIZE + 8, SIZE + 8);
                }
                map.updateCollisions();
            }
            else if(c.getOne().getDescription().equals("player") && c.getTwo().getDescription().equals("star")){
                map.removeStar(c.getTwo());
                babba.addToScore(10);
                System.out.println(babba.getScore());
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
        for(Collidable c : map.getColliders())
            if(c.getDescription().equals("star"))
                g.drawImage(starImage, c.getRectangle().getX(), c.getRectangle().getY());

        renderCollisionBoxes(g);
    }

    public void renderCollisionBoxes(Graphics g){
        for(Collidable c : map.getColliders()){
            g.draw(c.getRectangle());
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