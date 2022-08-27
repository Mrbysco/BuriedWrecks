package com.mrbysco.buriedwrecks.registry;

import com.mrbysco.buriedwrecks.BuriedWrecks;
import com.mrbysco.buriedwrecks.feature.configuration.BuriedShipwreckConfiguration;
import com.mrbysco.buriedwrecks.util.BuriedBiomeTags;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModConfiguredStructureFeatures {
	public static final DeferredRegister<ConfiguredStructureFeature<?, ?>> CONFIGURED_STRUCTURES = DeferredRegister.create(Registry.CONFIGURED_STRUCTURE_FEATURE_REGISTRY, BuriedWrecks.MOD_ID);

	public static final RegistryObject<ConfiguredStructureFeature<?, ?>> BURIED_SHIPWRECK = CONFIGURED_STRUCTURES.register("buried_shipwreck", () ->
			ModStructureFeatures.BURIED_SHIPWRECK.get().configured(new BuriedShipwreckConfiguration(false, -20), BuriedBiomeTags.HAS_BURIED_SHIPWRECK));
}
