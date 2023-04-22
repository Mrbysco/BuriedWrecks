package com.mrbysco.buriedwrecks.datagen;

import com.mrbysco.buriedwrecks.BuriedWrecks;
import com.mrbysco.buriedwrecks.registry.ModStructures;
import com.mrbysco.buriedwrecks.util.BuriedBiomeTags;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class BuriedDatagen {
	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		ExistingFileHelper helper = event.getExistingFileHelper();

		generator.addProvider(event.includeServer(), new BuriedStructureFeatureTagProvider(generator, helper));
		generator.addProvider(event.includeServer(), new BuriedBiomeTagProvider(generator, helper));
	}

	public static class BuriedStructureFeatureTagProvider extends TagsProvider<Structure> {
		public BuriedStructureFeatureTagProvider(DataGenerator generator, @Nullable ExistingFileHelper existingFileHelper) {
			super(generator, BuiltinRegistries.STRUCTURES, BuriedWrecks.MOD_ID, existingFileHelper);
		}

		@Override
		protected void addTags() {
			this.tag(BuriedWrecks.HAS_BURIED_WRECK)
					.add(ModStructures.BURIED_SHIPWRECK.getKey());
		}

		public String getName() {
			return "Buried Wrecks Structure Tags";
		}
	}

	public static class BuriedBiomeTagProvider extends BiomeTagsProvider {
		public BuriedBiomeTagProvider(DataGenerator generator, @Nullable ExistingFileHelper existingFileHelper) {
			super(generator, BuriedWrecks.MOD_ID, existingFileHelper);
		}

		@Override
		protected void addTags() {
			this.tag(BuriedBiomeTags.HAS_BURIED_SHIPWRECK)
					.addTag(BiomeTags.IS_OVERWORLD);
		}
	}
}
