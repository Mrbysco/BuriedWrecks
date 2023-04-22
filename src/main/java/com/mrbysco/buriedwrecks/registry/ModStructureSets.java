package com.mrbysco.buriedwrecks.registry;

import com.mrbysco.buriedwrecks.BuriedWrecks;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class ModStructureSets {
	public static final DeferredRegister<StructureSet> STRUCTURE_SETS = DeferredRegister.create(Registry.STRUCTURE_SET_REGISTRY, BuriedWrecks.MOD_ID);

	public static final RegistryObject<StructureSet> BURIED_SHIPWRECKS = STRUCTURE_SETS.register("buried_shipwrecks", () ->
			new StructureSet(List.of(StructureSet.entry(ModStructures.BURIED_SHIPWRECK.getHolder().get())),
					new RandomSpreadStructurePlacement(24, 4, RandomSpreadType.LINEAR, 15869703)));

}
