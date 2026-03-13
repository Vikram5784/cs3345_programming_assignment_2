import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class AirportSim
{
  static class Plane
  {
    int id;
    int timeEntered;
    boolean isLanding;

    public Plane(int id, int timeEntered, boolean isLanding) {
            this.id = id;
            this.timeEntered = timeEntered;
            this.isLanding = isLanding;
        }
  }

  public static void main(String[] args) {
        if (args.length < 5) {
            System.out.println("Usage: java AirportSimulation <landRate> <takeoffRate> <totalTime> <landingTime> <takeoffTime>");
            return;
        }

        double landRate = Double.parseDouble(args[0]);
        double takeoffRate = Double.parseDouble(args[1]);
        int totalTime = Integer.parseInt(args[2]); // Total simulation time
        int landingTime = Integer.parseInt(args[3]);
        int takeoffTime = Integer.parseInt(args[4]);

        runSimulation(landRate, takeoffRate, totalTime, landingTime, takeoffTime);
    }

    public static void runSimulation(double landRate, double takeoffRate, int totalTime, int landingTime, int takeoffTime) {
        Queue<Plane> landingQueue = new LinkedList<>();
        Queue<Plane> takeoffQueue = new LinkedList<>();
        Random random = new Random();
        int planesLanded = 0, planesTakenOff = 0, planeId = 0;
        int totalWaitLanding = 0, totalWaitTakeoff = 0;
        int queueLengthSum = 0;

        for (int currentTime = 0; currentTime < totalTime; currentTime += 5) {
            // Generate arrivals
            if (random.nextDouble() < landRate) {
                landingQueue.add(new Plane(++planeId, currentTime, true));
            }
            if (random.nextDouble() < takeoffRate) {
                takeoffQueue.add(new Plane(++planeId, currentTime, false));
            }

            // Process Queues (Landing Priority)
            if (!landingQueue.isEmpty()) {
                Plane p = landingQueue.poll();
                totalWaitLanding += (currentTime - p.timeEntered);
                planesLanded++;
            } else if (!takeoffQueue.isEmpty()) {
                Plane p = takeoffQueue.poll();
                totalWaitTakeoff += (currentTime - p.timeEntered);
                planesTakenOff++;
            }
            
            queueLengthSum += (landingQueue.size() + takeoffQueue.size());
        }

        // Output Report
        System.out.println("--- Airport Simulation Report ---");
        System.out.println("Total Time: " + totalTime + " mins");
        System.out.println("Planes Landed: " + planesLanded);
        System.out.println("Planes Taken Off: " + planesTakenOff);
        System.out.println("Avg Landing Wait: " + (planesLanded == 0 ? 0 : (double) totalWaitLanding / planesLanded) + " mins");
        System.out.println("Avg Takeoff Wait: " + (planesTakenOff == 0 ? 0 : (double) totalWaitTakeoff / planesTakenOff) + " mins");
        System.out.println("Avg Queue Length: " + (double) queueLengthSum / (totalTime / 5));
    }
}
