package com.mrbysco.buriedwrecks.registration;

import com.mrbysco.buriedwrecks.Constants;
import com.mrbysco.buriedwrecks.structure.BuriedShipwreckPieces;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;

import java.util.function.Supplier;

public class ModStructurePieceTypes {
	public static final RegistrationProvider<StructurePieceType> STRUCTURE_PIECE_TYPES = RegistrationProvider.get(Registries.STRUCTURE_PIECE, Constants.MOD_ID);

	public static final Supplier<StructurePieceType> BURIED_SHIPWRECK_PIECE = setTemplatePieceId(BuriedShipwreckPieces.BuriedShipwreckPiece::new, "buried_shipwreck");

	private static Supplier<StructurePieceType> setFullContextPieceId(StructurePieceType pieceType, String id) {
		return STRUCTURE_PIECE_TYPES.register(id, () -> pieceType);
	}

	private static Supplier<StructurePieceType> setTemplatePieceId(StructurePieceType.StructureTemplateType structureTemplateType, String id) {
		return setFullContextPieceId(structureTemplateType, id);
	}

	// Called in the mod initializer / constructor in order to make sure that items are registered
	public static void loadClass() {
	}
}
