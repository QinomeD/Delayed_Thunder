package net.jewwis.betterlightning;

import net.jewwis.betterlightning.soundevent.ModSounds;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(BetterLightning.MODID)
public class BetterLightning {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "betterlightning";

    public BetterLightning() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.SPEC);

        ModSounds.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
    }

}

