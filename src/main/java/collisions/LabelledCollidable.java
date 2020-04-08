package collisions;

import org.newdawn.slick.geom.Rectangle;

public class LabelledCollidable implements Collidable{

    private Rectangle collisionBox;
    private String description;

    public LabelledCollidable(Rectangle collisionBox, String description){
        this.collisionBox = collisionBox;
        this.description = description;
    }

    public Rectangle getCollisionBox(){
        return collisionBox;
    }

    public String getDescription(){
        return description;
    }

    public boolean collidesWith(Collidable c){
        return collisionBox.intersects(c.getCollisionBox());
    }
}
