package com.mrbysco.buriedwrecks.registry;

import com.mrbysco.buriedwrecks.BuriedWrecks;
import com.mrbysco.buriedwrecks.structure.BuriedShipwreckPieces;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModStructurePieceTypes {
	public static final DeferredRegister<StructurePieceType> STRUCTURE_PIECE_TYPES = DeferredRegister.create(Registries.STRUCTURE_PIECE, BuriedWrecks.MOD_ID);

	public static final Supplier<StructurePieceType> BURIED_SHIPWRECK_PIECE = setTemplatePieceId(BuriedShipwreckPieces.BuriedShipwreckPiece::new, "buried_shipwreck");

	private static Supplier<StructurePieceType> setFullContextPieceId(StructurePieceType pieceType, String id) {
		return STRUCTURE_PIECE_TYPES.register(id, () -> pieceType);
	}

	private static Supplier<StructurePieceType> setTemplatePieceId(StructurePieceType.StructureTemplateType structureTemplateType, String id) {
		return setFullContextPieceId(structureTemplateType, id);
	}
}
