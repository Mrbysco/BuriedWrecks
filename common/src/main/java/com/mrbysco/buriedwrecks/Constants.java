package com.mrbysco.buriedwrecks;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.structure.Structure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Constants {
	public static final String MOD_ID = "buriedwrecks";
	public static final String MOD_NAME = "Buried Wrecks";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

	public static final TagKey<Structure> HAS_BURIED_WRECK = TagKey.create(Registries.STRUCTURE, modLoc("has_buried_wreck"));
	public static final TagKey<Biome> HAS_BURIED_SHIPWRECK = TagKey.create(Registries.BIOME, Constants.modLoc("has_structure/buried_shipwreck"));

	public static Identifier modLoc(String name) {
		return Identifier.fromNamespaceAndPath(MOD_ID, name);
	}
}