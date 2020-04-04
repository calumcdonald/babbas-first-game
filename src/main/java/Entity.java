import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Rectangle;

public abstract class Entity {

    protected Rectangle collisionBox;
    protected Rectangle nextCollisionBox;
    protected double  x, y;

    public Entity(double x, double y) {
        this.x = x;
        this.y = y;

        collisionBox = new Rectangle((int) x, (int) y, Game.SPRITE_SIZE, Game.SPRITE_SIZE);
        nextCollisionBox = new Rectangle((int) x, (int) y, Game.SPRITE_SIZE, Game.SPRITE_SIZE);
    }

    public Rectangle getCollisionBox(){
        return collisionBox;
    }

    public Rectangle getNextCollisionBox(){
        return nextCollisionBox;
    }

    public void setNextLocation(){
        this.x = nextCollisionBox.getX();
        this.y = nextCollisionBox.getY();
        collisionBox = nextCollisionBox;
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public abstract void update(GameContainer gc);

    public abstract Animation getSprite();
}
