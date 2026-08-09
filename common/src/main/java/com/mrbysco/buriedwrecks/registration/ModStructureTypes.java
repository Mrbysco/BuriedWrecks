package com.mrbysco.buriedwrecks.registration;

import com.mrbysco.buriedwrecks.Constants;
import com.mrbysco.buriedwrecks.feature.BuriedWreckFeature;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.StructureType;

import java.util.function.Supplier;

public class ModStructureTypes {
	public static final RegistrationProvider<StructureType<?>> STRUCTURE_TYPES = RegistrationProvider.get(Registries.STRUCTURE_TYPE, Constants.MOD_ID);

	public static final Supplier<StructureType<BuriedWreckFeature>> BURIED_SHIPWRECK = STRUCTURE_TYPES.register("buried_shipwreck", () ->
			() -> BuriedWreckFeature.CODEC);

	// Called in the mod initializer / constructor in order to make sure that items are registered
	public static void loadClass() {
	}
}
