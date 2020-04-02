import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;

public abstract class Entity {

    private Rectangle collisionBox, deltaRec;
    private GameContainer gc;
    protected double  x, y;

    public Entity(double x, double y, GameContainer gc){
        this.x = x;
        this.y = y;
        this.gc = gc;

        collisionBox = new Rectangle((int)x, (int)y, Game.SPRITE_SIZE, Game.SPRITE_SIZE);
        deltaRec = new Rectangle((int)x, (int)y, Game.SPRITE_SIZE, Game.SPRITE_SIZE);
    }

    public abstract void update();

    public Rectangle getCollisionBox(){
        return collisionBox;
    }

    public Input getInput(){
        return gc.getInput();
    }

    public void setLocation(int x, int y){
        this.x = x;
        this.y = y;
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public abstract Animation getSprite();
}
