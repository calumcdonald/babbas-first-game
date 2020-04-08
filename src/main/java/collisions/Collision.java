package collisions;

public class Collision {
    private Collidable one, two;

    public Collision(Collidable one, Collidable two){
        this.one = one;
        this.two = two;
    }

    public Collidable getOne() {
        return one;
    }

    public Collidable getTwo() {
        return two;
    }
}
