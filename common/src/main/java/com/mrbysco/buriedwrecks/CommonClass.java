package com.mrbysco.buriedwrecks;

import com.mrbysco.buriedwrecks.registration.ModStructurePieceTypes;
import com.mrbysco.buriedwrecks.registration.ModStructureTypes;

public class CommonClass {

	public static void init() {
		ModStructurePieceTypes.loadClass();
		ModStructureTypes.loadClass();
	}
}