package com.mrbysco.buriedwrecks.registry;

import com.mrbysco.buriedwrecks.BuriedWrecks;
import com.mrbysco.buriedwrecks.structure.BuriedShipwreckPieces;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModStructurePieceTypes {
	public static final DeferredRegister<StructurePieceType> STRUCTURE_PIECE_TYPES = DeferredRegister.create(Registries.STRUCTURE_PIECE, BuriedWrecks.MOD_ID);

	public static final RegistryObject<StructurePieceType> BURIED_SHIPWRECK_PIECE = setTemplatePieceId(BuriedShipwreckPieces.BuriedShipwreckPiece::new, "buried_shipwreck");

	private static RegistryObject<StructurePieceType> setFullContextPieceId(StructurePieceType pieceType, String id) {
		return STRUCTURE_PIECE_TYPES.register(id, () -> pieceType);
	}

	private static RegistryObject<StructurePieceType> setTemplatePieceId(StructurePieceType.StructureTemplateType structureTemplateType, String id) {
		return setFullContextPieceId(structureTemplateType, id);
	}
}
