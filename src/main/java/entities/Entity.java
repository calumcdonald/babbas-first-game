package entities;

import collisions.Collidable;
import collisions.Collision;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Game;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Rectangle;

import java.util.List;

public abstract class Entity implements Collidable{

    protected Rectangle collisionBox;
    protected Rectangle nextCollisionBox;
    protected float x, y;

    public Entity(float x, float y) {
        this.x = x;
        this.y = y;

        collisionBox = new Rectangle(x, y, 16, 16);
        nextCollisionBox = new Rectangle(x, y, 16, 16);
    }

    public Rectangle getCollisionBox(){
        return collisionBox;
    }

    public Collision checkCollision(List<Collidable> colliders){
        for(Collidable c : colliders){
            if(c.collidesWith(this)){
                return new Collision(c, this);
            }
        }
        return null;
    }

    public boolean collidesWith(Collidable c){
        return c.collidesWith(this);
    }

    public void setNextLocation(){
        x = nextCollisionBox.getX();
        y = nextCollisionBox.getY();
        collisionBox = nextCollisionBox;
    }

    public void setLocation(float x, float y){
        this.x = x;
        this.y = y;
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
