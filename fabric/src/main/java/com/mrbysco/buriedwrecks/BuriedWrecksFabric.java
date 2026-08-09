package com.mrbysco.buriedwrecks;

import com.mrbysco.buriedwrecks.config.BuriedConfig;
import fuzs.forgeconfigapiport.fabric.api.v5.ConfigRegistry;
import net.fabricmc.api.ModInitializer;
import net.neoforged.fml.config.ModConfig;

public class BuriedWrecksFabric implements ModInitializer {

	@Override
	public void onInitialize() {
		ConfigRegistry.INSTANCE.register(Constants.MOD_ID, ModConfig.Type.COMMON, BuriedConfig.commonSpec);

		CommonClass.init();
	}
}
