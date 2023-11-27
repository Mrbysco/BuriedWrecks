package com.mrbysco.buriedwrecks.config;

import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.ModConfigSpec.BooleanValue;
import org.apache.commons.lang3.tuple.Pair;

public class BuriedConfig {
	public static class Common {
		public final BooleanValue overwriteYLevel;
		public final ModConfigSpec.IntValue yLevel;

		Common(ModConfigSpec.Builder builder) {
			builder.comment("Generation settings")
					.push("Generation");

			overwriteYLevel = builder
					.comment("Overwrite the default yLevel with the y level specified in 'yLevel' [default: false]")
					.define("overwriteYLevel", false);

			yLevel = builder
					.comment("The Y level it'll try to generate ship wrecks at [default: -20]")
					.defineInRange("yLevel", -20, Integer.MIN_VALUE, Integer.MAX_VALUE);

			builder.pop();
		}
	}

	public static final ModConfigSpec commonSpec;
	public static final Common COMMON;

	static {
		final Pair<Common, ModConfigSpec> specPair = new ModConfigSpec.Builder().configure(Common::new);
		commonSpec = specPair.getRight();
		COMMON = specPair.getLeft();
	}
}
