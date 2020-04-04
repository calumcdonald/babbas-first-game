import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Rectangle;

import java.util.List;

public abstract class Entity {

    protected Rectangle collisionBox;
    protected Rectangle nextCollisionBox;
    protected float x, y;

    public Entity(float x, float y) {
        this.x = x;
        this.y = y;

        collisionBox = new Rectangle(x, y, Game.SPRITE_SIZE, Game.SPRITE_SIZE);
        nextCollisionBox = new Rectangle(x, y, Game.SPRITE_SIZE, Game.SPRITE_SIZE);
    }

    public Rectangle getCollisionBox(){
        return collisionBox;
    }

    public boolean checkCollision(List<Rectangle> collisionList){
        for(Rectangle rectangle : collisionList) {
            if(nextCollisionBox.intersects(rectangle)){
                if(!rectangle.equals(nextCollisionBox) && !rectangle.equals(collisionBox)) {
                    return true;
                }
            }
        }
        return false;
    }

    public Rectangle checkPortalCollision(List<Rectangle> portalList){
        for(Rectangle rectangle : portalList) {
            if(nextCollisionBox.intersects(rectangle) && this instanceof Player){
                if(!rectangle.equals(nextCollisionBox) && !rectangle.equals(collisionBox)) {
                    return rectangle;
                }
            }
        }
        return null;
    }

    public void setNextLocation(){
        this.x = nextCollisionBox.getX();
        this.y = nextCollisionBox.getY();
        collisionBox = nextCollisionBox;
    }

    public float getX(){
        return x;
    }

    public float getY(){
        return y;
    }

    public abstract void update(GameContainer gc);

    public abstract Animation getSprite();
}
