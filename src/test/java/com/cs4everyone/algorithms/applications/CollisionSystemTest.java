package com.cs4everyone.algorithms.applications;

import com.cs4everyone.algorithms.applications.collision.CollisionSystem;
import com.cs4everyone.algorithms.applications.collision.Particle;
import com.cs4everyone.algorithms.utils.In;
import com.cs4everyone.algorithms.utils.StdDraw;
import org.junit.Test;

import java.awt.*;

public class CollisionSystemTest {

    /**
     * Unit tests the {@code CollisionSystem} data type. Reads in the particle collision system from a
     * standard input (or generates {@code N} random particles if a command-line integer is
     * specified); simulates the system.
     */
    @Test
    public void collisionSystemTest(){

        StdDraw.setCanvasSize(600, 600);

        // enable double buffering
        StdDraw.enableDoubleBuffering();

        // the array of particles
        Particle[] particles;

        // brownian.txt or diffusion.txt
        String filename = "diffusion.txt";
        In in = new In(filename);
        int n = in.readInt();
        particles = new Particle[n];
        for (int i = 0; i < n; i++) {
            double rx = in.readDouble();
            double ry = in.readDouble();
            double vx = in.readDouble();
            double vy = in.readDouble();
            double radius = in.readDouble();
            double mass = in.readDouble();
            int r = in.readInt();
            int g = in.readInt();
            int b = in.readInt();
            Color color = new Color(r, g, b);
            particles[i] = new Particle(rx, ry, vx, vy, radius, mass, color);
        }

        // create collision system and simulate
        CollisionSystem system = new CollisionSystem(particles);
        system.simulate(10000);
    }

}
