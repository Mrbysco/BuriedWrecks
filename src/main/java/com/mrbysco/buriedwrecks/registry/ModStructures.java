package com.mrbysco.buriedwrecks.registry;

import com.mrbysco.buriedwrecks.BuriedWrecks;
import com.mrbysco.buriedwrecks.feature.BuriedWreckFeature;
import com.mrbysco.buriedwrecks.util.BuriedBiomeTags;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.TerrainAdjustment;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.Map;

public class ModStructures {
	public static final DeferredRegister<Structure> STRUCTURES = DeferredRegister.create(Registry.STRUCTURE_REGISTRY, BuriedWrecks.MOD_ID);

	public static final RegistryObject<Structure> BURIED_SHIPWRECK = STRUCTURES.register("buried_shipwreck", () ->
			new BuriedWreckFeature(
					new Structure.StructureSettings(biomes(BuriedBiomeTags.HAS_BURIED_SHIPWRECK), Map.of(),
							GenerationStep.Decoration.UNDERGROUND_STRUCTURES, TerrainAdjustment.NONE),
					false, -20));

	private static HolderSet<Biome> biomes(TagKey<Biome> biomeTagKey) {
		return BuiltinRegistries.BIOME.getOrCreateTag(biomeTagKey);
	}
}
