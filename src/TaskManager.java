import components.standard.Standard;
/**
* Enhanced interface for PlayerTracker.
* Provides additional functionality for analyzing player statistics.
*/
public interface PlayerTracker extends PlayerTrackerKernel {
    /**
     * Computes the average value of a given stat across all players.
     * @param stat the stat to average
     * @return the average value of the stat
     * @requires at least one player exists
     * @ensures returns average of the stat across all players
     */
    double avgStat(String stat);

    /**
     * Finds the player with the highest value for a given stat.
     * @param stat the stat to check
     * @return the name of the player with the highest value
     * @requires at least one player exists
     * @ensures returns player with maximum stat value
     */
    String highestPlayer(String stat);
}
```[cite: 1]

---

### 2. `PlayerTrackerKernel.java` (Kernel Interface)[cite: 3]
```java
import java.util.Set;
import components.standard.Standard;

/**
 * Kernel interface for PlayerTracker. Minimal functionality for managing player
 * statistics.
 */
public interface PlayerTrackerKernel extends Standard<PlayerTrackerKernel> {

    void addPlayer(String playerName);

    void updateStat(String playerName, String stat, int value);

    int getStat(String playerName, String stat);

    Set<String> allPlayers();
}
```[cite: 3]

---

### 3. `PlayerTrackerSecondary.java` (Abstract Secondary Class)[cite: 3, 4, 5]
```java
import java.util.Set;

public abstract class PlayerTrackerSecondary implements PlayerTracker {

    @Override
    public double avgStat(String stat) {
        int total = 0;
        int count = 0;
        for (String player : this.allPlayers()) {
            total += this.getStat(player, stat);
            count++;
        }
        double result = 0.0;
        if (count > 0) {
            result = (double) total / count;
        }
        return result;
    }

    @Override
    public String highestPlayer(String stat) {
        String bestPlayer = "";
        int bestValue = -1;
        for (String player : this.allPlayers()) {
            int value = this.getStat(player, stat);
            if (value > bestValue) {
                bestValue = value;
                bestPlayer = player;
            }
        }
        return bestPlayer;
    }

    @Override
    public String toString() {
        String result = "";
        for (String player : this.allPlayers()) {
            result = result + player + ": " + "points="
                    + this.getStat(player, "points") + ", "
                    + "rebounds=" + this.getStat(player, "rebounds") + ", "
                    + "assists=" + this.getStat(player, "assists") + ", "
                    + "steals=" + this.getStat(player, "steals") + ", "
                    + "blocks=" + this.getStat(player, "blocks") + "\n";
        }
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        boolean result = false;
        if (obj instanceof PlayerTracker) {
            PlayerTracker other = (PlayerTracker) obj;
            result = this.allPlayers().size() == other.allPlayers().size();
            for (String player : this.allPlayers()) {
                if (!other.allPlayers().contains(player)
                        || this.getStat(player, "points") != other.getStat(player, "points")
                        || this.getStat(player, "rebounds") != other.getStat(player, "rebounds")
                        || this.getStat(player, "assists") != other.getStat(player, "assists")
                        || this.getStat(player, "steals") != other.getStat(player, "steals")
                        || this.getStat(player, "blocks") != other.getStat(player, "blocks")) {
                    result = false;
                }
            }
        }
        return result;
    }
}
```[cite: 3, 4, 5]

---

### 4. `PlayerTracker1L.java` (Implementation Class)[cite: 2, 3]
```java
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Kernel implementation of PlayerTracker using HashMap.
 */
public class PlayerTracker1L extends PlayerTrackerSecondary {

    // Representation maps player name (stat then value)
    private Map<String, Map<String, Integer>> rep;

    /**
     * Creates a new empty PlayerTracker.
     */
    public PlayerTracker1L() {
        this.rep = new HashMap<>();
    }

    @Override
    public void addPlayer(String playerName) {
        Map<String, Integer> stats = new HashMap<>();
        stats.put("points", 0);
        stats.put("rebounds", 0);
        stats.put("assists", 0);
        stats.put("steals", 0);
        stats.put("blocks", 0);
        this.rep.put(playerName, stats);
    }

    @Override
    public void updateStat(String playerName, String stat, int value) {
        this.rep.get(playerName).put(stat, value);
    }

    @Override
    public int getStat(String playerName, String stat) {
        return this.rep.get(playerName).get(stat);
    }

    @Override
    public Set<String> allPlayers() {
        return this.rep.keySet();
    }

    @Override
    public void clear() {
        this.rep.clear();
    }

    @Override
    public PlayerTracker newInstance() {
        return new PlayerTracker1L();
    }

    @Override
    public void transferFrom(PlayerTracker source) {
        PlayerTracker1L localSource = (PlayerTracker1L) source;
        this.rep = localSource.rep;
        localSource.rep = new HashMap<>();
    }
}
```[cite: 2, 3]

---

### 5. `PlayerTracker1LTest.java` (Test Class)[cite: 6, 7]
```java
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class PlayerTracker1LTest {

    @Test
    public void testAddPlayerAndGetStat() {
        PlayerTracker t = new PlayerTracker1L();
        t.addPlayer("LeBron James");
        assertEquals(0, t.getStat("LeBron James", "points"));
        assertEquals(0, t.getStat("LeBron James", "rebounds"));
    }

    @Test
    public void testUpdateStat() {
        PlayerTracker t = new PlayerTracker1L();
        t.addPlayer("Curry");
        t.updateStat("Curry", "points", 30);
        assertEquals(30, t.getStat("Curry", "points"));
    }

    @Test
    public void testAllPlayers() {
        PlayerTracker t = new PlayerTracker1L();
        t.addPlayer("A");
        t.addPlayer("B");
        assertEquals(2, t.allPlayers().size());
    }

    @Test
    public void testAvgStat() {
        PlayerTracker t = new PlayerTracker1L();
        t.addPlayer("A");
        t.addPlayer("B");
        t.updateStat("A", "points", 10);
        t.updateStat("B", "points", 20);
        assertEquals(15.0, t.avgStat("points"), 0.01);
    }
}

