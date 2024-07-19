package net.jewwis.betterlightning.ticktimer;

import net.jewwis.betterlightning.BetterLightning;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Mod.EventBusSubscriber(modid = BetterLightning.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class TickEventHandler {
    private static final List<TickTimer> timers = new ArrayList<>();

    public static void addTimer(TickTimer timer) {
        timers.add(timer);
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event){
        Iterator<TickTimer> iterator = timers.iterator();
        while (iterator.hasNext()) {
            TickTimer timer = iterator.next();
            timer.tick();
            if (timer.timerComplete()) {
                iterator.remove();
            }
        }
    }
}
