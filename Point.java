package Java_Project;


import java.util.ArrayList;

public class Point {
    private ArrayList<Point> neighbors;
    private int              currentState;
    private int              nextState;

    private final int DEAD  = 0;

    private final int MIN_FRIENDS = 2;
    private final int MAX_FRIENDS = 3;

    public Point() {
        currentState = 0;
        nextState = 0;
        neighbors = new ArrayList<Point>();
    }

    public int getState() {
        return currentState;
    }

    public void setState(int s) {
        currentState = s;
    }

    public void calculateNewState() {
        //TODO: insert logic which updates according to currentState and number of active neighbors
        int s = getState();
        if (s == 1) {
            nextState = shouldWheatDie() ? DEAD : s;
        } else if (s == 2) {
            nextState = shouldCockroachDie();
        } else if (s == 3) {
            nextState = shouldThisDie() ? DEAD : s;
        } else {
            nextState = shouldThisBeBorn();
        }
    }

    public void changeState() {
        currentState = nextState;
    }

    public void addNeighbor(Point nei) {
        neighbors.add(nei);
    }

    private boolean shouldWheatDie() {
        int aliveFriends = countAliveFriends(2);

        boolean shouldDie = aliveFriends >= 1;
        System.out.println("alive friends: " + aliveFriends + "; " + (shouldDie ? "dying" : "living"));

        return shouldDie;
    }
    private int shouldCockroachDie() {
        int state = 0;
        int aliveFriends = countAliveFriendsC();
        if (aliveFriends > 0)  state = (aliveFriends > MAX_FRIENDS || aliveFriends < MIN_FRIENDS)?0:2;
        else if (aliveFriends == -1) state = 0;

        return state;
    }
    private boolean shouldThisDie() {
        int aliveFriends = countAliveFriends(3);

        boolean shouldDie = aliveFriends > MAX_FRIENDS || aliveFriends < MIN_FRIENDS;
        System.out.println("alive friends: " + aliveFriends + "; " + (shouldDie ? "dying" : "living"));

        return shouldDie;
    }

    private int shouldThisBeBorn() {
        boolean shouldStart = false;
        int[] state = countAliveFriends();
        if (state != null) shouldStart = state[0] == MAX_FRIENDS;

        if (shouldStart) return state[1];
        else return 0;
    }

    private int countAliveFriends(int s) {
        int aliveFriends = 0;

        System.out.print("friends: " + neighbors.size() + "; ");
        for (Point neighbor : neighbors) {
            if (neighbor.getState() == s) {
                ++aliveFriends;
            }
        }
        return aliveFriends;
    }
    private int countAliveFriendsC() {
        int aliveFriends = 0;

        System.out.print("friends: " + neighbors.size() + "; ");
        for (Point neighbor : neighbors) {
            if (neighbor.getState() == 2) {
                ++aliveFriends;
            }
            if (neighbor.getState() == 3) {
                aliveFriends = -1;
                break;
            }
        }
        return aliveFriends;
    }
    private int[] countAliveFriends() {

        System.out.print("friends: " + neighbors.size() + "; ");
        int cockroach = 0, ladybug = 0;
        for (Point neighbor : neighbors) {
            if (neighbor.getState() == 2) {
                cockroach += 1;
            } else if (neighbor.getState() == 3) ladybug += 1;
        }
        if (cockroach != 0 || ladybug != 0) return cockroach>ladybug? new int[]{cockroach, 2} : new int[]{ladybug, 3};
        else return null;
    }
}