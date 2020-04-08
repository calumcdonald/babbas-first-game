package entities;

import collisions.Collidable;
import collisions.Collision;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Game;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Rectangle;

import java.util.List;

public abstract class Entity implements Collidable{

    public static final int SPRITE_SIZE = 16;

    protected Rectangle collisionBox;
    protected Rectangle nextCollisionBox;
    protected float x, y;

    public Entity(float x, float y) {
        this.x = x;
        this.y = y;

        collisionBox = new Rectangle(x, y, SPRITE_SIZE, SPRITE_SIZE);
        nextCollisionBox = new Rectangle(x, y, SPRITE_SIZE, SPRITE_SIZE);
    }

    public Rectangle getCollisionBox(){
        return collisionBox;
    }

    public Collision checkCollision(List<Collidable> colliders){
        for(Collidable c : colliders){
            if(c.collidesWith(this)){
                return new Collision(this, c);
            }
        }
        return null;
    }

    public boolean collidesWith(Collidable c) {
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

    public Rectangle getRectangle(){
        return collisionBox;
    }
}
