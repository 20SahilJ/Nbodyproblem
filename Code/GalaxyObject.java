/**
 * The GalaxyObject class contains the instance variables and methods necessary to represent an object within a galaxy,
 *  utilized in the testing of the N-Body Problem. These objects contain three major components: a position vector, a
 *  velocity vector, and a mass. All units are in the KMS scale. Other than getters and setters, this class contains
 *  two constructors, one parametrized constructor and one default constructor.
 * @author Sahil Jain
 * @version 01/02/18
 */
public class GalaxyObject {
    private Vector position;
    private Vector velocity;
    private double mass;
    /**
     * Default constructor for the GalaxyObject class. The position and velocity vectors and initialized and the
     *  mass of the object is set to 0.
     */
    public GalaxyObject()
    {
       this.position= new Vector();
       this.velocity = new Vector();
       this.mass = 0;
    }

    /**
     * Parametrized constructor for the GalaxyObject class. All components of the GalaxyObject, including velocity
     *  vector, position vector, and mass, are set to the given parameters.
     * @param position The position vector parameter (in m)
     * @param velocity The velocity vector parameter (in m/s)
     * @param mass The mass parameter (in kg)
     */
    public GalaxyObject(Vector position, Vector velocity, double mass)
    {
        this.position=position;
        this.velocity=velocity;
        this.mass=mass;
    }
    /**
     * Returns the position vector component of the GalaxyObject
     * @return the position vector component of the GalaxyObject
     */
    public Vector getPosition()
    {
        return position;
    }
    /**
     * Sets the position vector to the given vector
     * @param position The position vector parameter (in m)
     */
    public void setPosition(Vector position)
    {
        this.position = position;
    }
    /**
     * Returns the velocity vector component of the GalaxyObject
     * @return the velocity vector component of the GalaxyObject
     */
    public Vector getVelocity()
    {
        return velocity;
    }
    /**
     * Sets the velocity vector to the given vector
     * @param velocity The velocity vector parameter (in m)
     */
    public void setVelocity(Vector velocity)
    {
        this.velocity = velocity;
    }
    /**
     * Returns the mass component of the GalaxyObject
     * @return the mass component of the GalaxyObject
     */
    public double getMass()
    {
        return mass;
    }
    /**
     * Sets the mass of the GalaxyObject to the value given
     * @param mass The mass parameter (in kg)
     */
    public void setMass(double mass)
    {
        this.mass = mass;
    }
    /**
     * Sets the components of the GalaxyObject, including velocity
     *  vector, position vector, and mass, are set to the given parameters.
     * @param position The position vector parameter (in m)
     * @param velocity The velocity vector parameter (in m/s)
     * @param mass The mass parameter (in kg)
     */
    public void setGalaxyObject(Vector position, Vector velocity, double mass)
    {
        this.position=position;
        this.mass=mass;
        this.velocity=velocity;
    }
}