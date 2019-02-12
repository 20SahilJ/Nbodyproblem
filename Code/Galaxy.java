import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Sahil Jain
 * @version 01/02/18
 * The Galaxy class contains the methods necessary to model a Galaxy of Stars using the N-Body Problem. The positions
 *  of different bodies are modeled and observed over time. The laws of gravitation, as proposed by Newton, are utilized
 *  to create differential equations modeling both the velocities and positions of each of these bodies. Each of these
 *  bodies have their velocities and positions affected based on the other bodies around them.
 *  Design and further information for this class is written below.
 *  Please note that this class utilizes the three other class contained within this project:
 *  the GalaxyObject class, which is the superclass of the Star class and contains methods necessary to model a Star,
 *  the Star class, and the Vector class, which is utilized to model position and velocity vectors. All code is run in
 *  KMS units. For all differential equations, designs, and physics behind the N-Body Problem model, please go to the
 *  document entitled "N-Body Problem v1" by Dr. Eric Nelson.
 * Design: One method will calculate the next set of velocities of each of the objects in the galaxy using the
 *  differential equations given in the N-Body Problem document. Then, using these velocities, there will be one
 *  method that updates the velocities and the positions of the objects. As the loop is running updating these vectors,
 *  there will be an extra loop running from the first object to the current object checking to make sure that none of
 *  the positions of the objects are exactly the same. If that is the case, one of the objects will have a velocity
 *  according to the document's equation, and a velocity of both of the objects' masses combined, leaving the other
 *  object's mass to be zero. An outer method, called runSimulation, will repeatedly calculate the new positions and
 *  velocities until the time given, using deltat as a timestep. This method will print out the position values of
 *  each of the objects at 3 different times: the beginning, the middle, and the end. In the main method, the
 *  runSimulation method will be called with an ArrayList of randomly generated Stars(from the Star class). In this main
 *  method, the following user input will be collected:number of stars desired, deltat, total time,
 *  maximum value for each component of position vectors, maximum value for each component of velocity vectors
 *  and maximum mass value for the stars.
 *
 * Note: There is one constant in this class, called G, which is used as the gravitational constant in the differential
 *  equations.
 */
public class Galaxy
{
    private static final double G = 6.67408e-11;
    /**
     * Calculates the new velocities of each of the objects given. Velocities are calculated using
     *  the differential equations and their derivations are found in the document entitled "N-Body Problem." This
     *  method is called every deltat seconds in the runSimulation method.
     * @param objects The Star objects given for which the new velocities are to be calculated.
     * @param deltat The deltat, or timestep, which is utilized in the calculations of the velocities.
     * @return an ArrayList of the new velocities of each of the objects given. Velocities are calculated using
     *  the differential equations and their derivations are found in the document entitled "N-Body Problem."
     */
    public ArrayList<Vector> calculateVelocities(ArrayList<Star> objects, double deltat)
    {
        double numbodies = objects.size();
        ArrayList<Vector> velocities = new ArrayList<Vector>();
        for (int j = 0; j < numbodies; j++)
        {
            GalaxyObject current = objects.get(j);
            Vector initvelocity = current.getVelocity();
            Vector initposition = current.getPosition();
            //Performing the summation
            Vector sum = new Vector(0,0,0);
            for (int i = 0; i < numbodies; i++) {
                if (i != j) {
                    GalaxyObject compared = objects.get(i);
                    Vector positionc = compared.getPosition();
                    Vector subtractedposition = initposition.subtractOther(positionc);
                    double magnitude = subtractedposition.magnitude();
                    double scalar = (compared.getMass()*G*deltat)/(Math.pow(magnitude, 3));
                    Vector newvelocity = subtractedposition.multiplyWithScalar(scalar);
                    sum = sum.addOther(newvelocity);

                }
            }
            velocities.add(initvelocity.subtractOther(sum));
        }// Calculating the new velocity of each object
        return velocities;
    }

    /**
     * Updates the positions and velocities of each of the objects at once using the velocities calculated in the
     *  calculateVelocities method above. In addition, an extra loop inside checks to make sure that none of the objects
     *  have the same position.
     * @param objects The objects for which the positions and velocities should be updated.
     * @param velocities The new velocities calculated using the calculatedVelocities method above.
     * @param deltat The deltat, or timestep, utilized in the calculation of the new positions.
     */
    public void updateAllObjects(ArrayList<Star> objects, ArrayList<Vector> velocities, double deltat)
    {
        for (int i = 0; i < objects.size(); i++) {
            GalaxyObject current = objects.get(i);
            Vector currPosition = current.getPosition();
            Vector newVelocity = velocities.get(i);
            current.setVelocity(newVelocity);
            current.setPosition(currPosition.addOther(newVelocity.multiplyWithScalar(deltat)));
            for (int j = 0; j <i; j++)
            {
                Vector subtracted = objects.get(i).getPosition().subtractOther(objects.get(j).getPosition());
                    if(subtracted.getZ()==0&&subtracted.getY()==0&&subtracted.getZ()==0)
                    {
                        Vector velocity1 = objects.get(i).getVelocity();
                        double mass1 = objects.get(i).getMass();
                        Vector velocity2 = objects.get(j).getVelocity();
                        double mass2 = objects.get(j).getMass();
                        Vector component1 = velocity1.multiplyWithScalar(mass1);
                        Vector component2 = velocity2.multiplyWithScalar(mass2);
                        Vector numerator = component1.addOther(component2);
                        Vector newVelocity2 = numerator.divideByScalar(mass1+mass2);
                        GalaxyObject num1 = objects.get(i);
                        GalaxyObject num2 = objects.get(j);
                        num1.setVelocity(newVelocity2);
                        num2.setVelocity(new Vector());
                        num1.setMass(mass1 + mass2);
                        num2.setMass(0);
                    }
                }
            }//Solving for the case that two objects are in the exact same position
        }//Updating the positions and velocities of the objects while solving for the case that two objects are in the exact same position

    /**
     * Runs a simulation for a galaxy of Stars using an ArrayList of stars, a timestep, and a total amount of time. It
     *  repeatedly calculates the new positions and velocities until the time given, using deltat as a timestep.
     *  This method prints out the position values of each of the objects at 3 different times: the beginning, the middle, and the end.
     * @param deltat The given timestep
     * @param objects The ArrayList of Stars for which the positions and velocities are repeatedly calculated
     * @param totaltime The amount of time the simulation should be run
     * @throws FileNotFoundException
     */
    public void runSimulation(double deltat, ArrayList<Star> objects, double totaltime) throws FileNotFoundException {
        FileOutputStream fileOutputStream = new FileOutputStream("output");
        PrintStream out = new PrintStream(fileOutputStream);
        out.println("{ time, object number, <posx, posy, posz>, velmagnitude}");
        for (int i = 0; i < totaltime; i += deltat) {
            if (i == 0 || i == totaltime/2) {
                for (int j = 1; j < objects.size(); j++) {
                    GalaxyObject current = objects.get(j);
                    Vector velocity = current.getVelocity();
                    Vector position = current.getPosition();
                    out.println("{ " /**+ i + " , " + j + " , < "**/ + position.getX() / Star.parsecstometers + " , " + position.getY() / Star.parsecstometers + " , " +
                            position.getZ() / Star.parsecstometers  /**+" > , " + velocity.magnitude()**/  + " },");
                }
                out.println("End of this part");
            }
            updateAllObjects(objects, calculateVelocities(objects, deltat), deltat);
            System.out.println(i);
        }//Calculates new positions and velocities and prints out position values and beginning and middle
        for (int j = 0; j < objects.size(); j++) {
            GalaxyObject current = objects.get(j);
            Vector velocity = current.getVelocity();
            Vector position = current.getPosition();
            double toparsecs = Star.parsecstometers;
            out.println("{ " /**+ i + " , " + j + " , < "**/ + position.getX() / toparsecs + " , " + position.getY() / toparsecs + " , " +
                    position.getZ() / toparsecs + /**" > , " + velocity.magnitude()  +**/ " },");
        }//Prints out position values at the end
        System.out.println(totaltime);
        out.close();
    }

    /**
     * Runs the simulation similar to the runSimulation method, except with a ending condition of distance rather than
     *  time. It repeatedly calculates the new positions and velocities until the two given objects have reached a
     *  certain distance apart, using deltat as a timestep.
     * @param deltat The given timestep
     * @param objects The two given objects for which the velocities and positions should be calculated
     * @param distanceapart When the two bodies have reached this distance apart, the simulation should stop
     * @throws FileNotFoundException
     */
    public void runSimulationForTwoObjects(double deltat, ArrayList<Star> objects, double distanceapart) throws FileNotFoundException
    {
        FileOutputStream fileOutputStream = new FileOutputStream("/Users/sahiljain/IdeaProjects/N-Body Problem/src/output.java");
        PrintStream out = new PrintStream(fileOutputStream);
        out.println("{ distance, object number, <posx, posy, posz>, velmagnitude}");
        double distance = (objects.get(0).getPosition().subtractOther(objects.get(1).getPosition())).magnitude();
        double time = 0;
        while(distance>distanceapart)
        {
            for (int j = 0; j < objects.size(); j++) {
                GalaxyObject current = objects.get(j);
                Vector velocity = current.getVelocity();
                Vector position = current.getPosition();
                out.println("{ "+ distance + " , " + j + " , < " + position.getX() + " , "+ position.getY()+ " , "+
                        position.getZ() + " > , " + velocity.magnitude() + " }");
            }
            ArrayList<Vector> velocities = calculateVelocities(objects, deltat);
            for (int i = 0; i <velocities.size(); i++) {
                //System.out.println(velocities.get(i).magnitude());
            }
            updateAllObjects(objects, velocities, deltat);
            distance = (objects.get(0).getPosition().subtractOther(objects.get(1).getPosition())).magnitude();
            time += deltat;
        }
        out.println(time);

        out.close();
    }

    /**
     * Main method for the Galaxy class that tests an N-Body Problem and allows the user to run a simulation of stars
     *  in a galaxy. In this main method, the following user input will be collected:number of stars desired, deltat, total time,
     *  maximum value for each component of position vectors, maximum value for each component of velocity vectors
     *  and maximum mass value for the stars. These pieces of input are utilized to create an ArrayList of stars that
     *  is fed into the runSimulation method.
     * @param args The arguments of the main method.
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        Galaxy galaxy = new Galaxy();

        /**ArrayList<Star> stars = new ArrayList<Star>();
        Star sun = new Star(new Vector(0,0,0), new Vector(0,0,0), 1);
        stars.add(sun);
        Star earth = new Star(new Vector(0,4.848355484e-6,0), new Vector(30.0,0,0), 0.0000030026345939320004);
        stars.add(earth);
        galaxy.runSimulation(780,stars,3.469e7);**/
        //The above code segment was utilized to test the Sun and Moon system.
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the following, separated by spaces: number of stars desired, deltat, total time" +
                "maximum value for each component of position vectors, maximum value for each component of velocity vectors" +
                "and maximum mass value for the stars");
        String input = scanner.next();
        int index = input.indexOf(" ");
        ArrayList<Double> inputvals = new ArrayList<Double>();
        for (int i = 0; i < 6; i++)
        {
            inputvals.add(Double.parseDouble(input.substring(0,index)));
            input = input.substring(index+1);
        }

        ArrayList<Star> objects = Star.createStars(inputvals.get(0),inputvals.get(3),inputvals.get(4),inputvals.get(5));
        galaxy.runSimulation(inputvals.get(1), objects, inputvals.get(2));


    }
}

