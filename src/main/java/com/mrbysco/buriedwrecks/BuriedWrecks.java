package com.mrbysco.buriedwrecks;

import com.mojang.logging.LogUtils;
import com.mrbysco.buriedwrecks.config.BuriedConfig;
import com.mrbysco.buriedwrecks.registry.ModConfiguredStructureFeatures;
import com.mrbysco.buriedwrecks.registry.ModStructureFeatures;
import com.mrbysco.buriedwrecks.registry.ModStructurePieceTypes;
import com.mrbysco.buriedwrecks.registry.ModStructureSets;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(BuriedWrecks.MOD_ID)
public class BuriedWrecks {
	public static final String MOD_ID = "buriedwrecks";
	public static final Logger LOGGER = LogUtils.getLogger();

	public BuriedWrecks() {
		IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, BuriedConfig.commonSpec);

		ModStructureFeatures.STRUCTURES.register(eventBus);
		ModStructurePieceTypes.STRUCTURE_PIECE_TYPES.register(eventBus);
		ModConfiguredStructureFeatures.CONFIGURED_STRUCTURES.register(eventBus);
		ModStructureSets.STRUCTURE_SETS.register(eventBus);
	}
}
