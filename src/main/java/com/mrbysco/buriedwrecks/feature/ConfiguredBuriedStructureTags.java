package com.mrbysco.buriedwrecks.feature;

import com.mrbysco.buriedwrecks.BuriedWrecks;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;

public interface ConfiguredBuriedStructureTags {
	TagKey<ConfiguredStructureFeature<?, ?>> BURIED_WRECK = create(BuriedWrecks.MOD_ID + ":buried_wreck");

	private static TagKey<ConfiguredStructureFeature<?, ?>> create(String id) {
		return TagKey.create(Registry.CONFIGURED_STRUCTURE_FEATURE_REGISTRY, new ResourceLocation(id));
	}
}
