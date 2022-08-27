package com.mrbysco.buriedwrecks.feature;

import com.mojang.serialization.Codec;
import com.mrbysco.buriedwrecks.config.BuriedConfig;
import com.mrbysco.buriedwrecks.feature.configuration.BuriedShipwreckConfiguration;
import com.mrbysco.buriedwrecks.structure.BuriedShipwreckPieces;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.GenerationStep.Decoration;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGenerator;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGeneratorSupplier;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePiecesBuilder;

public class BuriedWreckFeature extends StructureFeature<BuriedShipwreckConfiguration> {
	public BuriedWreckFeature(Codec<BuriedShipwreckConfiguration> configurationCodec) {
		super(configurationCodec, PieceGeneratorSupplier.simple(BuriedWreckFeature::checkLocation, BuriedWreckFeature::generatePieces));
	}

	private static boolean checkLocation(PieceGeneratorSupplier.Context<BuriedShipwreckConfiguration> configurationContext) {
		return true;
	}

	private static void generatePieces(StructurePiecesBuilder piecesBuilder, PieceGenerator.Context<BuriedShipwreckConfiguration> configurationContext) {
		Rotation rotation = Rotation.getRandom(configurationContext.random());
		int yLevel = BuriedConfig.COMMON.overwriteYLevel.get() ? BuriedConfig.COMMON.yLevel.get() : configurationContext.config().yLevel;
		BlockPos blockpos = new BlockPos(configurationContext.chunkPos().getMinBlockX(), yLevel, configurationContext.chunkPos().getMinBlockZ());
		BuriedShipwreckPieces.addPieces(configurationContext.structureManager(), blockpos, rotation, piecesBuilder, configurationContext.random(), configurationContext.config());
	}

	@Override
	public Decoration step() {
		return Decoration.UNDERGROUND_STRUCTURES;
	}
}
