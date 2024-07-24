package net.jewwis.betterlightning;

import com.mojang.logging.LogUtils;
import net.jewwis.betterlightning.soundevent.ModSounds;
import net.jewwis.betterlightning.soundevent.SoundEventHandler;
import net.jewwis.betterlightning.ticktimer.TickEventHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

import java.lang.module.Configuration;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(BetterLightning.MODID)
public class BetterLightning {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "betterlightning";

    public static Configuration config;

    public BetterLightning() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.SPEC);

        ModSounds.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
        //FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
        //Register EventHandler
        MinecraftForge.EVENT_BUS.register(new SoundEventHandler());
        MinecraftForge.EVENT_BUS.register(new TickEventHandler());

    }

}

