package com.mrbysco.buriedwrecks.registry;

import com.mrbysco.buriedwrecks.BuriedWrecks;
import com.mrbysco.buriedwrecks.feature.BuriedWreckFeature;
import com.mrbysco.buriedwrecks.feature.configuration.BuriedShipwreckConfiguration;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModStructureFeatures {
	public static final DeferredRegister<StructureFeature<?>> STRUCTURES = DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, BuriedWrecks.MOD_ID);

	public static final RegistryObject<BuriedWreckFeature> BURIED_SHIPWRECK = STRUCTURES.register("buried_shipwreck", () ->
			new BuriedWreckFeature(BuriedShipwreckConfiguration.CODEC));
}
