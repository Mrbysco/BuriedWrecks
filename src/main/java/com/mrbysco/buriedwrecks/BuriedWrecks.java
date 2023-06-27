package com.mrbysco.buriedwrecks;

import com.mojang.logging.LogUtils;
import com.mrbysco.buriedwrecks.config.BuriedConfig;
import com.mrbysco.buriedwrecks.registry.ModStructurePieceTypes;
import com.mrbysco.buriedwrecks.registry.ModStructureTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.levelgen.structure.Structure;
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

	public static TagKey<Structure> HAS_BURIED_WRECK = TagKey.create(Registries.STRUCTURE, new ResourceLocation(BuriedWrecks.MOD_ID + ":has_buried_wreck"));

	public BuriedWrecks() {
		IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, BuriedConfig.commonSpec);

		ModStructureTypes.STRUCTURE_TYPES.register(eventBus);
		ModStructurePieceTypes.STRUCTURE_PIECE_TYPES.register(eventBus);
	}
}
