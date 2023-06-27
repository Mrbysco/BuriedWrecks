package com.mrbysco.buriedwrecks.registry;

import com.mrbysco.buriedwrecks.BuriedWrecks;
import com.mrbysco.buriedwrecks.feature.BuriedWreckFeature;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModStructureTypes {
	public static final DeferredRegister<StructureType<?>> STRUCTURE_TYPES = DeferredRegister.create(Registries.STRUCTURE_TYPE, BuriedWrecks.MOD_ID);

	public static final RegistryObject<StructureType<BuriedWreckFeature>> BURIED_SHIPWRECK = STRUCTURE_TYPES.register("buried_shipwreck", () ->
			() -> BuriedWreckFeature.CODEC);
}
