package com.gmail.zendarva.capabilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dimdev.riftloader.listener.InitializationListener;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.Mixins;

public class MixinListener implements InitializationListener {
    private static final Logger LOGGER = LogManager.getLogger();
    @Override
    public void onInitialization() {
        LOGGER.info("Adding Capability Mixins");
        MixinBootstrap.init();
        Mixins.addConfiguration("mixins.capabilities.json");
    }
}
