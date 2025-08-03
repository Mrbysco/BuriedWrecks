package com.mrbysco.buriedwrecks.datagen;

import com.mrbysco.buriedwrecks.BuriedWrecks;
import com.mrbysco.buriedwrecks.registry.ModStructureSets;
import com.mrbysco.buriedwrecks.registry.ModStructures;
import com.mrbysco.buriedwrecks.util.BuriedBiomeTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import org.jetbrains.annotations.Nullable;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class BuriedDatagen {
	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		PackOutput packOutput = generator.getPackOutput();
		CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
		ExistingFileHelper helper = event.getExistingFileHelper();

		generator.addProvider(true, new BuriedWrecksDatapackProvider(
				packOutput, lookupProvider, Set.of(BuriedWrecks.MOD_ID)));

		generator.addProvider(event.includeServer(), new DatapackBuiltinEntriesProvider(
				packOutput, CompletableFuture.supplyAsync(BuriedDatagen::getPatchedRegistries), Set.of(BuriedWrecks.MOD_ID)));

		generator.addProvider(event.includeServer(), new BuriedStructureFeatureTagProvider(packOutput, lookupProvider, helper));
		generator.addProvider(event.includeServer(), new BuriedStructureFeatureTagProvider(packOutput, CompletableFuture.supplyAsync(() ->
				BuriedWrecksDatapackProvider.BUILDER.build(RegistryAccess.fromRegistryOfRegistries(BuiltInRegistries.REGISTRY))), helper // Otherwise it fails to find structures
		);
		generator.addProvider(event.includeServer(), new BuriedBiomeTagProvider(packOutput, lookupProvider, helper));
		generator.addProvider(event.includeServer(), new StructureUpdater("structure/buried_shipwreck",
				BuriedWrecks.MOD_ID, helper, packOutput));
		generator.addProvider(event.includeClient(), new BuriedStructureLanguageProvider(packOutput));
	}

	public static class BuriedWrecksDatapackProvider extends DatapackBuiltinEntriesProvider {
		public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
				.add(Registries.STRUCTURE, ModStructures::bootstrap)
				.add(Registries.STRUCTURE_SET, ModStructureSets::bootstrap)
				.add(Registries.BIOME, $ -> {
				});

		public BuriedWrecksDatapackProvider(PackOutput output, CompletableFuture<Provider> registries, Set<String> modIds) {
			super(output, registries, BUILDER, modIds);
		}
	}

	public static class BuriedStructureLanguageProvider extends LanguageProvider {
		public BuriedStructureLanguageProvider(PackOutput output) {
			super(output, BuriedWrecks.MOD_ID, "en_us");
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
			this.add(BuriedWrecks.MOD_ID + ".configuration." + path, name);
			if (description != null && !description.isEmpty())
				this.add(BuriedWrecks.MOD_ID + ".configuration." + path + ".tooltip", description);
		}
	}

	public static class BuriedStructureFeatureTagProvider extends TagsProvider<Structure> {
		public BuriedStructureFeatureTagProvider(PackOutput generator, CompletableFuture<HolderLookup.Provider> completableFuture,
		                                         @Nullable ExistingFileHelper existingFileHelper) {
			super(generator, Registries.STRUCTURE, completableFuture, BuriedWrecks.MOD_ID, existingFileHelper);
		}

		@Override
		protected void addTags(HolderLookup.Provider provider) {
			this.tag(BuriedWrecks.HAS_BURIED_WRECK)
					.add(ModStructures.BURIED_SHIPWRECK);
		}
	}

	public static class BuriedBiomeTagProvider extends BiomeTagsProvider {
		public BuriedBiomeTagProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> completableFuture,
		                              @Nullable ExistingFileHelper existingFileHelper) {
			super(packOutput, completableFuture, BuriedWrecks.MOD_ID, existingFileHelper);
		}

		@Override
		protected void addTags(HolderLookup.Provider provider) {
			this.tag(BuriedBiomeTags.HAS_BURIED_SHIPWRECK)
					.addTag(BiomeTags.IS_OVERWORLD);
		}
	}
}
