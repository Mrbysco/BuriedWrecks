package com.mrbysco.buriedwrecks.structure;

import com.mrbysco.buriedwrecks.BuriedWrecks;
import com.mrbysco.buriedwrecks.registry.ModStructurePieceTypes;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.StructurePieceAccessor;
import net.minecraft.world.level.levelgen.structure.TemplateStructurePiece;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockIgnoreProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;

import java.util.Map;

public class BuriedShipwreckPieces {
	static final BlockPos PIVOT = new BlockPos(4, 0, 15);
	private static final ResourceLocation[] STRUCTURE_LOCATION_BEACHED = new ResourceLocation[]{
			new ResourceLocation(BuriedWrecks.MOD_ID, "buried_shipwreck/with_mast"),
			new ResourceLocation(BuriedWrecks.MOD_ID, "buried_shipwreck/sideways_full"),
			new ResourceLocation(BuriedWrecks.MOD_ID, "buried_shipwreck/sideways_fronthalf"),
			new ResourceLocation(BuriedWrecks.MOD_ID, "buried_shipwreck/sideways_backhalf"),
			new ResourceLocation(BuriedWrecks.MOD_ID, "buried_shipwreck/rightsideup_full"),
			new ResourceLocation(BuriedWrecks.MOD_ID, "buried_shipwreck/rightsideup_fronthalf"),
			new ResourceLocation(BuriedWrecks.MOD_ID, "buried_shipwreck/rightsideup_backhalf"),
			new ResourceLocation(BuriedWrecks.MOD_ID, "buried_shipwreck/with_mast_degraded"),
			new ResourceLocation(BuriedWrecks.MOD_ID, "buried_shipwreck/rightsideup_full_degraded"),
			new ResourceLocation(BuriedWrecks.MOD_ID, "buried_shipwreck/rightsideup_fronthalf_degraded"),
			new ResourceLocation(BuriedWrecks.MOD_ID, "buried_shipwreck/rightsideup_backhalf_degraded")};
	private static final ResourceLocation[] STRUCTURE_LOCATION_OCEAN = new ResourceLocation[]{
			new ResourceLocation(BuriedWrecks.MOD_ID, "buried_shipwreck/with_mast"),
			new ResourceLocation(BuriedWrecks.MOD_ID, "buried_shipwreck/upsidedown_full"),
			new ResourceLocation(BuriedWrecks.MOD_ID, "buried_shipwreck/upsidedown_fronthalf"),
			new ResourceLocation(BuriedWrecks.MOD_ID, "buried_shipwreck/upsidedown_backhalf"),
			new ResourceLocation(BuriedWrecks.MOD_ID, "buried_shipwreck/sideways_full"),
			new ResourceLocation(BuriedWrecks.MOD_ID, "buried_shipwreck/sideways_fronthalf"),
			new ResourceLocation(BuriedWrecks.MOD_ID, "buried_shipwreck/sideways_backhalf"),
			new ResourceLocation(BuriedWrecks.MOD_ID, "buried_shipwreck/rightsideup_full"),
			new ResourceLocation(BuriedWrecks.MOD_ID, "buried_shipwreck/rightsideup_fronthalf"),
			new ResourceLocation(BuriedWrecks.MOD_ID, "buried_shipwreck/rightsideup_backhalf"),
			new ResourceLocation(BuriedWrecks.MOD_ID, "buried_shipwreck/with_mast_degraded"),
			new ResourceLocation(BuriedWrecks.MOD_ID, "buried_shipwreck/upsidedown_full_degraded"),
			new ResourceLocation(BuriedWrecks.MOD_ID, "buried_shipwreck/upsidedown_fronthalf_degraded"),
			new ResourceLocation(BuriedWrecks.MOD_ID, "buried_shipwreck/upsidedown_backhalf_degraded"),
			new ResourceLocation(BuriedWrecks.MOD_ID, "buried_shipwreck/sideways_full_degraded"),
			new ResourceLocation(BuriedWrecks.MOD_ID, "buried_shipwreck/sideways_fronthalf_degraded"),
			new ResourceLocation(BuriedWrecks.MOD_ID, "buried_shipwreck/sideways_backhalf_degraded"),
			new ResourceLocation(BuriedWrecks.MOD_ID, "buried_shipwreck/rightsideup_full_degraded"),
			new ResourceLocation(BuriedWrecks.MOD_ID, "buried_shipwreck/rightsideup_fronthalf_degraded"),
			new ResourceLocation(BuriedWrecks.MOD_ID, "buried_shipwreck/rightsideup_backhalf_degraded")};
	static final Map<String, ResourceLocation> MARKERS_TO_LOOT = Map.of(
			"map_chest", BuiltInLootTables.SHIPWRECK_MAP,
			"treasure_chest", BuiltInLootTables.SHIPWRECK_TREASURE,
			"supply_chest", BuiltInLootTables.SHIPWRECK_SUPPLY);

	public static void addPieces(StructureTemplateManager templateManager, BlockPos pos, Rotation rotation, StructurePieceAccessor pieceAccessor, RandomSource randomSource, boolean isBeached) {
		ResourceLocation resourcelocation = Util.getRandom(isBeached ? STRUCTURE_LOCATION_BEACHED : STRUCTURE_LOCATION_OCEAN, randomSource);
		pieceAccessor.addPiece(new BuriedShipwreckPiece(templateManager, resourcelocation, pos, rotation, isBeached));
	}

	public static class BuriedShipwreckPiece extends TemplateStructurePiece {
		private final boolean isBeached;

		public BuriedShipwreckPiece(StructureTemplateManager templateManager, ResourceLocation structureLocation, BlockPos pos, Rotation rotation, boolean beached) {
			super(ModStructurePieceTypes.BURIED_SHIPWRECK_PIECE.get(), 0, templateManager, structureLocation, structureLocation.toString(), makeSettings(rotation), pos);
			this.isBeached = beached;
		}

		public BuriedShipwreckPiece(StructureTemplateManager templateManager, CompoundTag tag) {
			super(ModStructurePieceTypes.BURIED_SHIPWRECK_PIECE.get(), tag, templateManager, (p_229383_) -> {
				return makeSettings(Rotation.valueOf(tag.getString("Rot")));
			});
			this.isBeached = tag.getBoolean("isBeached");
		}

		protected void addAdditionalSaveData(StructurePieceSerializationContext serializationContext, CompoundTag tag) {
			super.addAdditionalSaveData(serializationContext, tag);
			tag.putBoolean("isBeached", this.isBeached);
			tag.putString("Rot", this.placeSettings.getRotation().name());
		}

		private static StructurePlaceSettings makeSettings(Rotation rotation) {
			return (new StructurePlaceSettings()).setRotation(rotation).setMirror(Mirror.NONE).setRotationPivot(BuriedShipwreckPieces.PIVOT)
					.addProcessor(BlockIgnoreProcessor.STRUCTURE_AND_AIR);
		}

		protected void handleDataMarker(String marker, BlockPos pos, ServerLevelAccessor levelAccessor, RandomSource random, BoundingBox boundingBox) {
			ResourceLocation resourcelocation = BuriedShipwreckPieces.MARKERS_TO_LOOT.get(marker);
			if (resourcelocation != null) {
				RandomizableContainerBlockEntity.setLootTable(levelAccessor, random, pos.below(), resourcelocation);
			}
		}

		public void postProcess(WorldGenLevel worldGenLevel, StructureManager structureManager, ChunkGenerator chunkGenerator,
								RandomSource random, BoundingBox boundingBox, ChunkPos chunkPos, BlockPos pos) {
			int i = worldGenLevel.getMaxBuildHeight();
			int j = pos.getY();

			Vec3i vec3i = this.template.getSize();
			int i1 = this.isBeached ? i - vec3i.getY() / 2 - random.nextInt(3) : j;
			this.templatePosition = new BlockPos(this.templatePosition.getX(), i1, this.templatePosition.getZ());

			super.postProcess(worldGenLevel, structureManager, chunkGenerator, random, boundingBox, chunkPos, pos);
		}
	}
}