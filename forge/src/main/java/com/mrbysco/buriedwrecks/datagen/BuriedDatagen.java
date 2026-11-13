package com.mrbysco.buriedwrecks.datagen;

import com.mrbysco.buriedwrecks.Constants;
import com.mrbysco.buriedwrecks.registration.ModStructureSets;
import com.mrbysco.buriedwrecks.registration.ModStructures;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.server.packs.PackType;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.data.event.GatherDataRegistryEntriesEvent;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

@EventBusSubscriber
public class BuriedDatagen {

	@SubscribeEvent
	static void onGatherRegistries(GatherDataRegistryEntriesEvent event) {
		event.add(Registries.STRUCTURE, ModStructures::bootstrap);
		event.add(Registries.STRUCTURE_SET, ModStructureSets::bootstrap);
	}

	@SubscribeEvent
	public static void gatherData(GatherDataEvent.Client event) {
		DataGenerator generator = event.getGenerator();
		PackOutput packOutput = generator.getPackOutput();

		event.createProvider(BuriedStructureFeatureTagProvider::new);
		event.createProvider(BuriedBiomeTagProvider::new);
		generator.addProvider(true, new StructureUpdater("structure/buried_shipwreck", packOutput, event.getResourceManager(PackType.SERVER_DATA)));
		event.createProvider(BuriedStructureLanguageProvider::new);
	}

	public static class BuriedStructureLanguageProvider extends LanguageProvider {
		public BuriedStructureLanguageProvider(PackOutput output) {
			super(output, Constants.MOD_ID, "en_us");
		}

		@Override
		protected void addTranslations() {
			addConfig("generation", "Generation", "Generation Settings");
			addConfig("overwriteYLevel", "Overwrite Y Level", "Overwrite the default yLevel with the y level specified in 'yLevel'");
			addConfig("yLevel", "Y Level", "The Y level it'll try to generate ship wrecks at");
		}

		/**
		 * Add the translation for a config entry
		 *
		 * @param path        The path of the config entry
		 * @param name        The name of the config entry
		 * @param description The description of the config entry (optional in case of targeting "title" or similar entries that have no tooltip)
		 */
		private void addConfig(String path, String name, @Nullable String description) {
			this.add(Constants.MOD_ID + ".configuration." + path, name);
			if (description != null && !description.isEmpty())
				this.add(Constants.MOD_ID + ".configuration." + path + ".tooltip", description);
		}
	}

	public static class BuriedStructureFeatureTagProvider extends TagsProvider<Structure> {
		public BuriedStructureFeatureTagProvider(PackOutput generator, CompletableFuture<Provider> completableFuture) {
			super(generator, Registries.STRUCTURE, completableFuture, Constants.MOD_ID);
		}

		@Override
		protected void addTags(Provider provider) {
			this.tag(Constants.HAS_BURIED_WRECK)
					.add(ModStructures.BURIED_SHIPWRECK);
		}
	}

	public static class BuriedBiomeTagProvider extends BiomeTagsProvider {
		public BuriedBiomeTagProvider(PackOutput packOutput, CompletableFuture<Provider> completableFuture) {
			super(packOutput, completableFuture, Constants.MOD_ID);
		}

		@Override
		protected void addTags(Provider provider) {
			this.tag(Constants.HAS_BURIED_SHIPWRECK)
					.addTag(BiomeTags.IS_OVERWORLD);
		}
	}
}
