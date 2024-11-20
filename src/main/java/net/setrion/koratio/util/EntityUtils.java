package net.setrion.koratio.util;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.setrion.koratio.world.entity.demonic.Necromancer;
import net.setrion.koratio.world.entity.demonic.NecromancerSkull;

import java.util.List;
import java.util.UUID;

public class EntityUtils {

    public static List<Entity> getNecromancerSkullOwner(Level level, UUID uuid) {
        for (ServerLevel serverlevel1 : level.getServer().getAllLevels()) {
            Entity entity = serverlevel1.getEntity(uuid);
            if (entity != null) {
                if (entity instanceof Necromancer) {
                    return List.of(entity);
                }
                break;
            }
        }

        return List.of();
    }

    public static List<Entity> getNecromancerSkull(Level level, UUID uuid) {
        for (ServerLevel serverlevel1 : level.getServer().getAllLevels()) {
            Entity entity = serverlevel1.getEntity(uuid);
            if (entity != null) {
                if (entity instanceof NecromancerSkull) {
                    return List.of(entity);
                }
                break;
            }
        }

        return List.of();
    }
}