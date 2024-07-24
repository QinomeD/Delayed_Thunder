package net.jewwis.betterlightning.soundevent;

import net.jewwis.betterlightning.BetterLightning;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, BetterLightning.MODID);

    public static final RegistryObject<SoundEvent> FARTHUNDER = registerSoundEvents("far_thunder");
    public static final RegistryObject<SoundEvent> MEDIUMTHUNDER = registerSoundEvents("medium_thunder");
    public static final RegistryObject<SoundEvent> SHORTTHUNDER = registerSoundEvents("short_thunder");
    public static final RegistryObject<SoundEvent> NORMALTHUNDER = registerSoundEvents("normal_thunder");

    private static RegistryObject<SoundEvent> registerSoundEvents(String name) {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(BetterLightning.MODID, name)));
    }

    public static void register(IEventBus eventBus){
        SOUND_EVENTS.register(eventBus);
    }
}
