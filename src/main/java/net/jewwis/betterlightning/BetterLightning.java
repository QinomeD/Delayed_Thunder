package net.jewwis.betterlightning;

import com.mojang.logging.LogUtils;
import net.jewwis.betterlightning.ticktimer.TickEventHandler;
import net.jewwis.betterlightning.ticktimer.TickTimer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterClientCommandsEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

import java.beans.EventHandler;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(BetterLightning.MODID)
public class BetterLightning {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "betterlightning";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public BetterLightning() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        MinecraftForge.EVENT_BUS.register(this);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
        //Register EventHandler
        MinecraftForge.EVENT_BUS.register(new SoundEventHandler());
        MinecraftForge.EVENT_BUS.register(new TickEventHandler());
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        TickEventHandler.addTimer(new TickTimer(100, () -> System.out.println("Example Tick Timer!")));
    }
}

