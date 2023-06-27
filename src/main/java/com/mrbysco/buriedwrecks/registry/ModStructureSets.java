package com.mrbysco.buriedwrecks.registry;

import com.mrbysco.buriedwrecks.BuriedWrecks;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadType;

import java.util.List;

public class ModStructureSets {
	public static final ResourceKey<StructureSet> BURIED_SHIPWRECKS = register("buried_shipwrecks");

	public static void bootstrap(BootstapContext<StructureSet> context) {
		HolderGetter<Structure> structureGetter = context.lookup(Registries.STRUCTURE);
		HolderGetter<Biome> biomeGetter = context.lookup(Registries.BIOME);

		context.register(BURIED_SHIPWRECKS, new StructureSet(List.of(StructureSet.entry(structureGetter.getOrThrow(ModStructures.BURIED_SHIPWRECK))),
				new RandomSpreadStructurePlacement(24, 4, RandomSpreadType.LINEAR, 15869703)));

	}

	private static ResourceKey<StructureSet> register(String name) {
		return ResourceKey.create(Registries.STRUCTURE_SET, new ResourceLocation(BuriedWrecks.MOD_ID, name));
	}
}
