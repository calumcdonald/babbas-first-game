package collisions;

public class Collision {

    private Collidable c1, c2;

    public Collision(Collidable c1, Collidable c2){
        this.c1 = c1;
        this.c2 = c2;
    }

    public Collidable getc1(){
        return c1;
    }

    public Collidable getc2() {
        return c2;
    }
}
