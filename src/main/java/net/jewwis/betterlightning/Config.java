package net.jewwis.betterlightning;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

@Mod.EventBusSubscriber(modid = BetterLightning.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config {

    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    private static final ForgeConfigSpec.ConfigValue<Integer> Short_Distance = BUILDER.comment("Blocks (Default: 65)")
            .define("Short Threshold", 65);
    private static final ForgeConfigSpec.ConfigValue<Integer> Medium_Distance = BUILDER.comment("Blocks (Default: 90)")
            .define("Medium Threshold", 90);
    private static final ForgeConfigSpec.ConfigValue<Integer> Far_Distance = BUILDER.comment("Blocks (Default: 115)")
            .define("Far Threshold", 115);
    private static final ForgeConfigSpec.ConfigValue<Integer> Short_Delay = BUILDER.comment("Ticks (Default: 40)")
            .define("Close Delay", 40);
    private static final ForgeConfigSpec.ConfigValue<Integer> Medium_Delay = BUILDER.comment("Ticks (Default: 70)")
            .define("Medium Delay", 85);
    private static final ForgeConfigSpec.ConfigValue<Integer> Far_Delay = BUILDER.comment("Ticks (Default: 130)")
            .define("Far Delay", 130);



    static final ForgeConfigSpec SPEC = BUILDER.build();

    public static int distanceShort;
    public static int distanceMedium;
    public static int distanceFar;
    public static int timeShort;
    public static int timeMedium;
    public static int timeLong;

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event){
        distanceShort = Short_Distance.get();
        distanceMedium = Medium_Distance.get();
        distanceFar = Far_Distance.get();

        timeShort = Short_Delay.get();
        timeMedium = Medium_Delay.get();
        timeLong = Far_Delay.get();
    }

}
