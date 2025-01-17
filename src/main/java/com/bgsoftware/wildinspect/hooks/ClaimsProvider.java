package com.bgsoftware.wildinspect.hooks;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public interface ClaimsProvider {

    ClaimPlugin getClaimPlugin();

    boolean hasRole(Player player, Location location, String... role);

    boolean hasRegionAccess(Player player, Location location);

    enum ClaimPlugin {

        ACID_ISLAND,
        ASKYBLOCK,
        BENTOBOX,
        FACTIONSUUID,
        FACTIONSX,
        GRIEF_PREVENTION,
        MASSIVE_FACTIONS,
        SUPERIOR_SKYBLOCK,
        TOWNY,
        VILLAGES,
        PLOTSQUARED,

        NONE,
        DEFAULT

    }

}
