package com.cs4everyone.algorithms.applications.collision;

import com.cs4everyone.algorithms.queue.MinPQ;
import com.cs4everyone.algorithms.utils.StdDraw;

public class CollisionSystem {

  // number of redraw events per clock tick
  private static final double HZ = 0.5;

  // the priority queue
  private MinPQ<Event> pq;

  // simulation clock time
  private double t = 0.0;

  // the array of particles
  private Particle[] particles;

  /**
   * Initializes a system with the specified collection of particles. The individual particles will
   * be mutated during the simulation.
   *
   * @param particles the array of particles
   */
  public CollisionSystem(Particle[] particles) {
    // defensive copy
    this.particles = particles.clone();
  }

  // updates priority queue with all new events for particle a
  private void predict(Particle a, double limit) {
    if (a == null) return;

    // particle-particle collisions
    for (int i = 0; i < particles.length; i++) {
      double dt = a.timeToHit(particles[i]);
      if (t + dt <= limit) pq.insert(new Event(t + dt, a, particles[i]));
    }

    // particle-wall collisions
    double dtX = a.timeToHitVerticalWall();
    double dtY = a.timeToHitHorizontalWall();
    if (t + dtX <= limit) pq.insert(new Event(t + dtX, a, null));
    if (t + dtY <= limit) pq.insert(new Event(t + dtY, null, a));
  }

  // redraw all particles
  private void redraw(double limit) {
    StdDraw.clear();
    for (int i = 0; i < particles.length; i++) {
      particles[i].draw();
    }
    StdDraw.show();
    StdDraw.pause(20);
    if (t < limit) {
      pq.insert(new Event(t + 1.0 / HZ, null, null));
    }
  }

  /**
   * Simulates the system of particles for the specified amount of time.
   *
   * @param limit the amount of time
   */
  public void simulate(double limit) {

    // initialize PQ with collision events and redraw event
    pq = new MinPQ<Event>();

    for (int i = 0; i < particles.length; i++) {
      predict(particles[i], limit);
    }

    // redraw event
    pq.insert(new Event(0, null, null));

    // the main event-driven simulation loop
    while (!pq.isEmpty()) {

      // get impending event, discard if invalidated
      Event e = pq.delMin();
      if (!e.isValid()) continue;

      Particle a = e.particleA();
      Particle b = e.particleB();

      // physical collision, so update positions, and then simulation clock
      for (int i = 0; i < particles.length; i++) {
        particles[i].move(e.time() - t);
      }

      t = e.time();

      // process event
      // particle-particle collision
      if (a != null && b != null) a.bounceOff(b);
      // particle-wall collision
      else if (a != null && b == null) a.bounceOffVerticalWall();
      // particle-wall collision
      else if (a == null && b != null) b.bounceOffHorizontalWall();
      // redraw event
      else if (a == null && b == null) redraw(limit);

      // update the priority queue with new collisions involving a or b
      predict(a, limit);
      predict(b, limit);
    }
  }
}
