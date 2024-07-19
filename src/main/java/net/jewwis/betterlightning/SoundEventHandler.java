package net.jewwis.betterlightning;

import net.jewwis.betterlightning.ticktimer.TickEventHandler;
import net.jewwis.betterlightning.ticktimer.TickTimer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.Sound;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.sound.PlaySoundEvent;
import net.minecraftforge.client.event.sound.SoundEvent;
import net.minecraftforge.common.data.SoundDefinition;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BetterLightning.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class SoundEventHandler {

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void onPlaySound(PlaySoundEvent event){
        String soundName = event.getName();
        Minecraft minecraft = Minecraft.getInstance();
        Player player = minecraft.player;

        if (soundName.equalsIgnoreCase("entity.lightning_bolt.thunder")){
            BlockPos lightningPos = new BlockPos((int)event.getSound().getX(), (int)event.getSound().getY(), (int)event.getSound().getZ());
            float x2 = (float) event.getSound().getX();
            float y2 = (float) event.getSound().getY();
            float z2 = (float) event.getSound().getZ();
            System.out.println("Position: " + lightningPos);

            if (player != null){
                BlockPos playerPos = player.blockPosition();
                System.out.println("Position: " + playerPos);
                float x1 = (float) player.getX();
                float y1 = (float) player.getY();
                float z1 = (float) player.getZ();
                int a = getDistance(x1, y1, z1, x2, y2, z2);
                player.sendSystemMessage(Component.literal("Position Thunder: " + lightningPos));
                player.sendSystemMessage(Component.literal("Position Player: " + playerPos));
                player.sendSystemMessage(Component.literal("Distance: " + a));

                if (a > 60 && a < 80) {
                    event.setSound(null);
                    TickEventHandler.addTimer(new TickTimer(10, () -> {
                        player.sendSystemMessage(Component.literal("0.5 Seconds Delay"));
                    }));
                } else if (a >= 80 && a < 100) {
                    event.setSound(null);
                    TickEventHandler.addTimer(new TickTimer(20, () -> {
                        player.sendSystemMessage(Component.literal("1.0 Seconds Delay"));
                    }));
                } else if (a >= 100) {
                    event.setSound(null);
                    TickEventHandler.addTimer(new TickTimer(40, () -> {
                        player.sendSystemMessage(Component.literal("2 Seconds Delay"));
                    }));
                } else if (a < 60) {
                    event.setSound(null);
                    TickEventHandler.addTimer(new TickTimer(100, () -> {
                        player.sendSystemMessage(Component.literal("5 Seconds Delay"));
                        //player.playSound(SoundEvents.LIGHTNING_BOLT_THUNDER, 1.0f, 1.0f);
                    }));
                }
            }
        }


    }

    private static int getDistance(float x1, float y1, float z1, float x2, float y2, float z2){
        float xdis = (x2-x1)*(x2-x1);
        float ydis = (y2-y1)*(y2-y1);
        float zdis = (z2-z1)*(z2-z1);
        float a = xdis + ydis + zdis;
        if (a > 2) return (int)sqrt(a);
        else return 2;
    }

    private static float sqrt(float a){
        float t;
        float sqrtRoot = a/2;
        do {
            t=sqrtRoot;
            sqrtRoot = (t + (a/t)) / 2;
        } while ((t-sqrtRoot) != 0);
        return sqrtRoot;
    }
}



//String soundName = event.getName();
//System.out.println("Sound: " + soundName);
//Sound: entity.lightning_bolt.thunder
//Sound: entity.lightning_bolt.impact
//<60 <80 <100
//10T 20T 40T