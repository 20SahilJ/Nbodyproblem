# Nbodyproblem

This repository contains the code and sample graphs for the N-body problem, which involves modeling the following two senarios:
- Earth's orbit around the sun 

- The interaction of thousands of stars and tracking of their individual positions over time.

In terms of class structure, the GalaxyObject contains mass, position, and velocity components; the Star class extends the GalaxyObject class and contains the appropriate methods to return an arraylist of stars, each with a random position, mass, and velocity. The Vector class is utilized to appropriately store the x, y, and z components of position and velocity.

In order to model a group of stars, please run the main method in the Galaxy class and specify the number of stars desired, deltat, total simulation time, maximum value for each component of the position vectors, maximum value for each component of the velocity vectors, and the maximum mass value for the stars(used when generating stars at random positions, masses, and velocities).

Note: For the purposes of this experiment, position is measured in parsecs, velocity is measured in km/s, and mass is measured in solar masses.
