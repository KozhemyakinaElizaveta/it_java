package Lab_3;

import java.util.HashMap;
import java.util.Iterator;

/**
 * This class stores the basic state necessary for the A* algorithm to compute a
 * path across a map.  This state includes a collection of "open waypoints" and
 * another collection of "closed waypoints."  In addition, this class provides
 * the basic operations that the A* pathfinding algorithm needs to perform its
 * processing.
 **/
public class AStarState
{
    private HashMap<Location, Waypoint> openWaypoints = new HashMap<Location, Waypoint>();
    private HashMap<Location, Waypoint> closeWaypoints = new HashMap<Location, Waypoint>();

    /** This is a reference to the map that the A* algorithm is navigating. **/
    private Map2D map;


    /**
     * Initialize a new state object for the A* pathfinding algorithm to use.
     **/
    public AStarState(Map2D map)
    {
        if (map == null)
            throw new NullPointerException("map cannot be null");

        this.map = map;
    }

    /** Returns the map that the A* pathfinder is navigating. **/
    public Map2D getMap()
    {
        return map;
    }

    /**
     * This method scans through all open waypoints, and returns the waypoint
     * with the minimum total cost.  If there are no open waypoints, this method
     * returns <code>null</code>.
     **/
    public Waypoint getMinOpenWaypoint()
    {
        if (this.numOpenWaypoints() == 0) {
            return null;
        }
        Waypoint bestWaypoint = null;
        
        Iterator<Location> keys = openWaypoints.keySet().iterator();
        float bestCost;
        if (keys.hasNext())
        bestCost = openWaypoints.get(keys.next()).getTotalCost();
        else return null;
        keys = openWaypoints.keySet().iterator();
        while (keys.hasNext()) {
            Location location = keys.next();
            Waypoint waypoint = openWaypoints.get(location); //получаем значение по ключу
            float totalCostWaipoint = waypoint.getTotalCost();

            if (totalCostWaipoint <= bestCost) {
                bestCost = totalCostWaipoint;
                bestWaypoint = waypoint;
            }
        }
        return bestWaypoint;
    }

    /**
     * This method adds a waypoint to (or potentially updates a waypoint already
     * in) the "open waypoints" collection.  If there is not already an open
     * waypoint at the new waypoint's location then the new waypoint is simply
     * added to the collection.  However, if there is already a waypoint at the
     * new waypoint's location, the new waypoint replaces the old one <em>only
     * if</em> the new waypoint's "previous cost" value is less than the current
     * waypoint's "previous cost" value.
     **/
    public boolean addOpenWaypoint(Waypoint newWP)
    {
        Location location = newWP.getLocation();
        if (!openWaypoints.containsKey(location)) {
            openWaypoints.put(location, newWP);
            return true;
        } 
        else {
            // Если вершина есть то сохраняем ее
            Waypoint currentWaypoint = openWaypoints.get(location);
            // Если стоимость новой вершины меньше, то заменяем ее в списке открытых вершин
            if (currentWaypoint.getPreviousCost() > newWP.getPreviousCost()) {
                openWaypoints.put(location, newWP);
                return true;
            } else {
                return false;
            }
        }
    }


    /** Returns the current number of open waypoints. **/
    public int numOpenWaypoints()
    {
        return openWaypoints.size();
    }


    /**
     * This method moves the waypoint at the specified location from the
     * open list to the closed list.
     **/
    public void closeWaypoint(Location loc)
    {
        // Сначала добавим вершину в список закрытых
        closeWaypoints.put(loc, openWaypoints.get(loc));
        // Удалим вершину из набора открытых
        openWaypoints.remove(loc);
    }

    /**
     * Returns true if the collection of closed waypoints contains a waypoint
     * for the specified location.
     **/
    public boolean isLocationClosed(Location loc)
    {
        return closeWaypoints.containsKey(loc);
    }
}

