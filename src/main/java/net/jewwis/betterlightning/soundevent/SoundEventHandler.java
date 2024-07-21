package net.jewwis.betterlightning.soundevent;

import net.jewwis.betterlightning.BetterLightning;
import net.jewwis.betterlightning.ticktimer.TickEventHandler;
import net.jewwis.betterlightning.ticktimer.TickTimer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.Sound;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.sound.PlaySoundEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Objects;

import static net.minecraftforge.client.ForgeHooksClient.playSound;

@Mod.EventBusSubscriber(modid = BetterLightning.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class SoundEventHandler {

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void onPlaySound(PlaySoundEvent event){

        if (event.getName().equalsIgnoreCase("entity.lightning_bolt.thunder")){
            float x2 = (float) Objects.requireNonNull(event.getSound()).getX();
            float y2 = (float) Objects.requireNonNull(event.getSound()).getY();
            float z2 = (float) Objects.requireNonNull(event.getSound()).getZ();
            Player player = Minecraft.getInstance().player;

            if (player != null){
                BlockPos lightningPos = new BlockPos((int)event.getSound().getX(), (int)event.getSound().getY(), (int)event.getSound().getZ());
                BlockPos playerPos = player.blockPosition();
                float x1 = (float) player.getX();
                float y1 = (float) player.getY();
                float z1 = (float) player.getZ();
                int a = getDistance(x1, y1, z1, x2, y2, z2);
                player.sendSystemMessage(Component.literal("Position Thunder: " + lightningPos));
                player.sendSystemMessage(Component.literal("Position Player: " + playerPos));
                player.sendSystemMessage(Component.literal("Distance: " + a));

                if (a >= 60 && a < 80) {
                    event.setSound(null);
                    TickEventHandler.addTimer(new TickTimer(60, () -> {
                        player.sendSystemMessage(Component.literal("3 Seconds Delay"));
                        player.playSound(ModSounds.SHORTTHUNDER.get());
                    }));
                } else if (a >= 80 && a < 115) {
                    event.setSound(null);
                    TickEventHandler.addTimer(new TickTimer(100, () -> {
                        player.sendSystemMessage(Component.literal("5 Seconds Delay"));
                        player.playSound(ModSounds.MEDIUMTHUNDER.get());
                    }));
                } else if (a >= 115) {
                    event.setSound(null);
                    TickEventHandler.addTimer(new TickTimer(160, () -> {
                        player.sendSystemMessage(Component.literal("8 Seconds Delay"));
                        player.playSound(ModSounds.FARTHUNDER.get());
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