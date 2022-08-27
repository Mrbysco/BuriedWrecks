package com.mrbysco.buriedwrecks.registry;

import com.mrbysco.buriedwrecks.BuriedWrecks;
import com.mrbysco.buriedwrecks.structure.BuriedShipwreckPieces;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModStructurePieceTypes {
	public static final DeferredRegister<StructurePieceType> STRUCTURE_PIECE_TYPES = DeferredRegister.create(Registry.STRUCTURE_PIECE_REGISTRY, BuriedWrecks.MOD_ID);

	public static final RegistryObject<StructurePieceType> BURIED_SHIPWRECK_PIECE = STRUCTURE_PIECE_TYPES.register("buried_shipwreck", () -> BuriedShipwreckPieces.BuriedShipwreckPiece::new);

}
