package net.jewwis.betterlightning.soundevent;

import net.jewwis.betterlightning.BetterLightning;
import net.jewwis.betterlightning.Config;
import net.jewwis.betterlightning.ticktimer.TickEventHandler;
import net.jewwis.betterlightning.ticktimer.TickTimer;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.sound.PlaySoundEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Objects;

import static net.minecraftforge.client.ForgeHooksClient.playSound;

@Mod.EventBusSubscriber(modid = BetterLightning.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class SoundEventHandler {

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent(priority = EventPriority.LOW)
    public static void onPlaySound(PlaySoundEvent event){

        if (event.getName().equalsIgnoreCase("entity.lightning_bolt.thunder")){
            float x2 = (float) Objects.requireNonNull(event.getSound()).getX();
            float y2 = (float) Objects.requireNonNull(event.getSound()).getY();
            float z2 = (float) Objects.requireNonNull(event.getSound()).getZ();

            Player player = Minecraft.getInstance().player;
            if (player != null){
                float x1 = (float) player.getX();
                float y1 = (float) player.getY();
                float z1 = (float) player.getZ();
                int a = getDistance(x1, y1, z1, x2, y2, z2);
                player.sendSystemMessage(Component.literal("Distance: " + a));
                event.setSound(null);

                if (a >= Config.distanceShort && a < Config.distanceMedium) {
                    TickEventHandler.addTimer(new TickTimer(Config.timeShort, () -> {
                        System.out.println("2.25 Seconds Delay");
                        player.playSound(ModSounds.SHORTTHUNDER.get(), 0.8f, 1.0f);
                    }));
                } else if (a >= Config.distanceMedium && a < Config.distanceFar) {
                    TickEventHandler.addTimer(new TickTimer(Config.timeMedium, () -> {
                        System.out.println("4.5 Seconds Delay");
                        player.playSound(ModSounds.MEDIUMTHUNDER.get(), 2.0f, 1.0f);
                    }));
                } else if (a >= Config.distanceFar) {
                    TickEventHandler.addTimer(new TickTimer(Config.timeLong, () -> {
                        System.out.println("7.5 Seconds Delay");
                        player.playSound(ModSounds.FARTHUNDER.get(), 1.0f, 1.0f);
                    }));
                } else {
                    player.playSound(ModSounds.NORMALTHUNDER.get(), 1.0f, 1.0f);
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