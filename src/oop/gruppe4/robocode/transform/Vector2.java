package oop.gruppe4.robocode.transform;

import org.jetbrains.annotations.NotNull;

import static java.lang.Math.*;

/**
 * A two-dimensional vector using {@code double} coordinates.
 * {@code Vector2} is an immutable class.
 * @author Ask Hetland Berentsen
 */
public class Vector2 {

    /**
     * The x coordinate.
     */
    final private double x;

    /**
     * The y coordinate.
     */
    final private double y;

    /**
     * The null-vector.
     */
    final public static Vector2 NULL = new Vector2(0,0);

    /**
     * Class constructor.
     * @param x the x coordinate.
     * @param y the y coordinate.
     */
    public Vector2( double x, double y ){
        this.x = x;
        this.y = y;
    }

    /**
     * Gets the x coordinate of {@code this}.
     * @return a {@code double}
     */
    public double getX() {
        return x;
    }

    /**
     * Gets the y coordinate of {@code this}.
     * @return a {@code double}
     */
    public double getY(){
        return y;
    }

    /**
     * Adds two vectors together.
     * @param v another {@code Vector2}.
     * @return a {@code new Vector2}.
     */
    public Vector2 add( @NotNull Vector2 v ){
        return new Vector2( this.x + v.x, this.y + v.y );
    }

    /**
     * Sums many vectors together.
     * @param vectors a list of vectors
     * @return a {@code new Vector2}.
     */
    public Vector2 addAll( Vector2... vectors ){
        return this.add( sum( vectors ) );
    }

    /**
     * Sums many vectors together.
     * @param vectors a list of vectors
     * @return a {@code new Vector2}.
     */
    public static Vector2 sum( Vector2... vectors ){
        double x = 0, y = 0;
        for ( Vector2 v : vectors ){
            x += v.getX();
            y += v.getY();
        }
        return new Vector2(x,y);
    }

    /**
     * Multiplies {@code this} with the scalar {@code scalar}.
     * @param scalar a scalar to multiply with.
     * @return a {@code new Vector2}.
     */
    public Vector2 multiply( double scalar ){
        return new Vector2( this.x * scalar, this.y * scalar );
    }

    /**
     * Calculates the scalar of {@code this}.
     * @return a {@code double}.
     */
    public double getScalar(){
        return sqrt(x*x + y*y);
    }

    /**
     * Calculates the angle of {@code this}.
     * @return an unsigned radian between {@code 0} and {@code 2 PI} (Not including {@code 2 PI})
     *          where {@code 0} is north, {@code PI/2} is east, {@code PI} is south and {@code 3/2 PI} is west.
     */
    public double getTheta(){
        return (atan2(x,y) + 2*PI) % (2*PI);
    }

    /**
     * Rotates {@code this} to a new {@code Vector2} using the angle {@code theta}.
     * @param theta the radians to rotate {@code this} with. A {@code double} between
     * {@code -2 PI} and {@code 2 PI}.
     * @return a {@code new Vector2}.
     */
    public Vector2 rotate( double theta ){
        double rx = (x * Math.cos(theta)) - (y * Math.sin(theta));
        double ry = (x * Math.sin(theta)) + (y * Math.cos(theta));
        return new Vector2( rx, ry );
    }

    /**
     * Calculates the arc length when rotating {@code this} by an angle of {@code theta} radians.
     * @param theta the radians to rotate {@code this} with. A {@code double} between
     * {@code -2 PI} and {@code 2 PI}.
     * @return the arc length when rotating {@code this} by an angle of {@code theta} radians.
     */
    public double arcLength( double theta ){
        return getScalar() * theta;
    }

    /**
     * Subtracts a {@code Vector2} from {@code this}.
     * @param v another {@code Vector2}
     * @return a {@code new Vector2}.
     */
    public Vector2 subtract( @NotNull Vector2 v ){
        return new Vector2(this.x - v.x, this.y - v.y);
    }

    /**
     * Gets the distance between two vectors.
     * @param v another {@code Vector2}.
     * @return a {@code new Vector2}.
     */
    public double distance( @NotNull Vector2 v ){
        return this.subtract( v ).getScalar();
    }

    /**
     * Calculates a normal vector with the same direction and a scalar of {@code 1}.
     * @return a {@code new} normalized {@code Vector2}.
     */
    public Vector2 normalized(){
        return this.multiply( 1 / this.getScalar() );
    }

    /**
     * Calculates whether {@code this} is bounded inside a rectangle.
     * @param lx the lower bound of the x coordinate.
     * @param ly the lower bound of the y coordinate.
     * @param ux the upper bound of the x coordinate.
     * @param uy the upper bound of the y coordinate.
     * @return {@code true} if {@code this} is inside the rectangle {@code [lx,ly,ux,uy]}.
     *          {@code false} otherwise.
     */
    public boolean isContained( double lx, double ly, double ux, double uy ){
        return this.x >= lx && this.x <= ux && this.y >= ly && this.y <= uy;
    }

    @Override
    public String toString(){
        return String.format("<% g,% g>", x, y);
    }
}
