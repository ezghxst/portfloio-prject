import components.standard.Standard;
import java.util.Set;

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
