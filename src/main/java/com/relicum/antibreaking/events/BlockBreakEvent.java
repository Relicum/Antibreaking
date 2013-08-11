package com.relicum.antibreaking.events;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

/**
 * Antibreaking
 * Simple plugin that Denies or Allows Breaking or Placing of Blocks
 * Has full Multi World Support
 * Most Settings can be altered in game without requiring a reload of the server
 *
 * @author Relicum
 * @version 0.1
 */
public class BlockBreakEvent extends PlayerEvent implements Cancellable {

    /**
     * Stores the HandlerList
     */
    private static final HandlerList handlers = new HandlerList();
    /**
     * Holds if the event has been cancelled or not
     */
    private boolean cancelled = false;

    /**
     * Stores the Location the BlockBreak took place
     */
    private Location location = null;

    /**
     * Default Constructor passing the player involved
     *
     * @param who Player
     */
    public BlockBreakEvent(Player who) {
        super(who);
        this.location = who.getWorld().getSpawnLocation();
    }

    /**
     * Default Constructor passing the player involved as well as the Location
     *
     * @param who Player
     * @param loc Location
     */
    public BlockBreakEvent(Player who, Location loc) {
        super(who);
        this.location = loc;

    }

    /**
     * Returns the Event Cancellable status as a boolean
     *
     * @return boolean
     */
    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    /**
     * Sets the Event cancellable status
     *
     * @param b boolean
     */
    @Override
    public void setCancelled(boolean b) {
        this.cancelled = b;
    }

    /**
     * Returns a list of Handlers using a static method
     *
     * @return handlersList
     */
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    /**
     * Returns a list of Handlers using a static method
     *
     * @return HandlerList
     */
    public static HandlerList getHandlerList() {
        return handlers;
    }
}
