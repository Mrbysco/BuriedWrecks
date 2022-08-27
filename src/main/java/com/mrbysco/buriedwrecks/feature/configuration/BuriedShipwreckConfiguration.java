package com.mrbysco.buriedwrecks.feature.configuration;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.levelgen.feature.configurations.ShipwreckConfiguration;

public class BuriedShipwreckConfiguration extends ShipwreckConfiguration {

	public static final Codec<BuriedShipwreckConfiguration> CODEC = RecordCodecBuilder.create(
			instance -> instance.group(
					Codec.BOOL.fieldOf("is_beached").orElse(false).forGetter((BuriedShipwreckConfiguration o) -> o.isBeached),
					Codec.INT.fieldOf("y_level").forGetter((BuriedShipwreckConfiguration o) -> o.yLevel)
			).apply(instance, BuriedShipwreckConfiguration::new)
	);

	public final int yLevel;

	public BuriedShipwreckConfiguration(boolean beached, int yLevel) {
		super(beached);
		this.yLevel = yLevel;
	}
}
