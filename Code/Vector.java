/**
 * @author Sahil Jain
 * @version 01/02/18
 * This class contains the instance variables and methods necessary to represent a Vector. The Vector object is 3D,
 *  meaning it contains x, y, and z components. Besides basic getters and setters for the components of the vector, this
 *  class also contains methods to add/subtract vectors, calculate the magnitude, and multiply the vector with a scalar.
 */

public class Vector {
    private double x;
    private double y;
    private double z;
    /**
     * One of the constructors for the Vector class. This constructor initializes the x, y, and z components of the
     *  vector to be 0.
     */
    public Vector()
    {
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }

    /**
     * The second constructor for the Vector class. This constructor initializes the x, y, and z components of the
     *  vector to be the given three parameters.
     * @param x The x component of the vector to be initialized
     * @param y The y component of the vector to be initialized
     * @param z The z component of the vector to be initialized
     */
    public Vector(double x, double y, double z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Sets the x component of the vector to the value given.
     * @param x The value that the x component of the vector should be set to
     */
    public void setX(double x)
    {
        this.x = x;
    }
    /**
     * Sets the y component of the vector to the value given.
     * @param y The value that the x component of the vector should be set to
     */
    public void setY(double y)
    {
       this.y = y;
    }
    /**
     * Sets the z component of the vector to the value given.
     * @param z The value that the x component of the vector should be set to
     */
    public void setZ(double z)
    {
        this.z = z;
    }
    /**
     * Sets the components of the vector to the values given.
     * @param x The value that the x component of the vector should be set to
     * @param y The value that the y component of the vector should be set to
     * @param z The value that the z component of the vector should be set to
     */
    public void setVector(double x, double y, double z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    /**
     * Returns the x component of the vector
     * @return the x component of the vector
     */
    public double getX()
    {
        return x;
    }
    /**
     * Returns the y component of the vector
     * @return the y component of the vector
     */
    public double getY()
    {
        return y;
    }
    /**
     * Returns the z component of the vector
     * @return the z component of the vector
     */
    public double getZ()
    {
        return z;
    }
    /**
     * Returns a vector with all components of the vector given are subtracted from the components of this vector
     * @param other The other vector from which this vector is to be subtracted
     * @return a vector with all components of the vector given are subtracted from the components of this vector
     */
    public Vector subtractOther(Vector other)
    {
        return new Vector(x-other.getX(),y-other.getY(),z-other.getZ());
    }
    /**
     * Returns a vector with all components of the vector given are added to the components of this vector
     * @param other The other vector to which this vector is to be added
     * @return a vector with all components of the vector given are added to the components of this vector
     */
    public Vector addOther(Vector other)
    {
        //System.out.println(z+other.getZ());
        return new Vector(x+other.getX(),y+other.getY(),z+other.getZ());
    }
    /**
     * Returns a new vector in which all components of this vector have been multiplied by the given scalar value
     * @param scalar The value that all components of the vector will be multiplied by
     * @return a new vector in which all components of this vector have been multiplied by the given scalar value
     */
    public Vector multiplyWithScalar (double scalar)
    {
        return new Vector(x*scalar, y*scalar, z*scalar);
    }
    /**
     * Returns a new vector in which all components of this vector have been divided by the given scalar value
     * @param scalar The value that all components of the vector will be divided by
     * @return a new vector in which all components of this vector have been divided by the given scalar value
     */
    public Vector divideByScalar (double scalar)
    {
        return new Vector(x/scalar, y/scalar, z/scalar);
    }

    /**
     * Returns the magnitude of this vector. Magnitude is calculated by taking the square root of the sum of each
     *  component squared.
     * @return the magnitude of the is vector.
     */
    public double magnitude()
    {
        return Math.sqrt(x*x + y*y + z*z);
    }
}
