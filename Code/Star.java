import java.util.ArrayList;
/**
 * The following class contains the constants and constructor necessary to represent a Star, utilized in the
 *  testing of the N-Body Problem. This class, which extends the GalaxyObject class, is different in the fact that
 *  the units are in parsecs, km/s and solar masses. Please note, however, that code for the N-Body problem still runs
 *  in KMS units, as demonstrated by the constructor. There are three constants: the parsecstometers constant, which
 *  is the conversion factor between parsecs and meters; the kmstoms constant, which is the conversion factor between
 *  km/s and m/s; and the solarmassestokg constant, which is the conversion factor between solar masses and kilograms.
 *  The constructor of the Star class uses these constants to create a Star in KMS units, calling the constructor of
 *  GalaxyObject.
 * @author Sahil Jain
 * @version 1/12/18
 */
public class Star extends GalaxyObject{
    public static double parsecstometers = 3.086e16;
    public static double kmstoms = 1000;
    public static double solarmassestokg = 1.98855e30;

    /**
     * The constructor of the Star class that uses the above constants to create a Star in KMS units, calling the
     *  constructor of GalaxyObject.
     * @param position The position vector parameter(in parsecs)
     * @param velocity The velocity vector parameter(in km/s)
     * @param mass The mass parameter(in kg)
     */
    public Star(Vector position, Vector velocity, double mass)
    {
        super(position.multiplyWithScalar(parsecstometers),velocity.multiplyWithScalar(kmstoms),mass*solarmassestokg);
    }
    /**
     * Returns an ArrayList of numStars randomly generated Stars. Values for each component of the position and velocity
     *  vectors are randomly calculated, with the max value or each component being maxpos and maxveloc, respectively.
     *  Mass is also calculated, with the maximum mass being maxmass.
     * @param numStars The number of random stars to be generated
     * @param maxpos The maximum value for each position component
     * @param maxveloc The maximum value for each velocity component
     * @param maxmass The maximum value for each mass component
     * @return an ArrayList of numStars randomly generated Stars. Values for each component of the position and velocity
     *  vectors are randomly calculated, with the max value or each component being maxpos and maxveloc, respectively.
     *  Mass is also calculated, with the maximum mass being maxmass.
     */
    public static ArrayList<Star> createStars(double numStars, double maxpos, double maxveloc, double maxmass)
    {
        ArrayList<Star> stars =  new ArrayList<Star>();
        for (int i = 0; i <numStars ; i++) {
            double posx = Math.random()*maxpos*numStars;
            double posy = Math.random()*maxpos*numStars;
            double posz = Math.random()*maxpos*numStars;
            double velocx = Math.random()*maxveloc;
            double velocy = Math.random()*maxveloc;
            double velocz = Math.random()*maxveloc;
            double mass = Math.random()*maxmass + 0.25;
            stars.add(new Star(new Vector(posx,posy,posz),new Vector(velocx,velocy,velocz),mass));
        }
        return stars;
    }
}
