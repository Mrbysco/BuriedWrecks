package com.mrbysco.buriedwrecks.datagen;

import com.mrbysco.buriedwrecks.BuriedWrecks;
import com.mrbysco.buriedwrecks.feature.ConfiguredBuriedStructureTags;
import com.mrbysco.buriedwrecks.registry.ModConfiguredStructureFeatures;
import com.mrbysco.buriedwrecks.util.BuriedBiomeTags;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraftforge.common.Tags.Biomes;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class BuriedDatagen {
	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		ExistingFileHelper helper = event.getExistingFileHelper();

		generator.addProvider(new BuriedStructureFeatureTagProvider(generator, helper));
		generator.addProvider(new BuriedBiomeTagProvider(generator, helper));
	}

	public static class BuriedStructureFeatureTagProvider extends TagsProvider<ConfiguredStructureFeature<?, ?>> {
		public BuriedStructureFeatureTagProvider(DataGenerator generator, @Nullable ExistingFileHelper existingFileHelper) {
			super(generator, BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, BuriedWrecks.MOD_ID, existingFileHelper);
		}

		@Override
		protected void addTags() {
			this.tag(ConfiguredBuriedStructureTags.BURIED_WRECK)
					.add(ModConfiguredStructureFeatures.BURIED_SHIPWRECK.getKey());
		}

		public String getName() {
			return "Configured Buried Wrecks Feature Tags";
		}
	}

	public static class BuriedBiomeTagProvider extends BiomeTagsProvider {
		public BuriedBiomeTagProvider(DataGenerator generator, @Nullable ExistingFileHelper existingFileHelper) {
			super(generator, BuriedWrecks.MOD_ID, existingFileHelper);
		}

		@Override
		protected void addTags() {
			this.tag(BuriedBiomeTags.HAS_BURIED_SHIPWRECK)
					.addTag(Biomes.IS_OVERWORLD);
		}
	}
}
