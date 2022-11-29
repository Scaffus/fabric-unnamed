package net.scaffus.unnamed;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.scaffus.unnamed.hax.Flight;

public class UnnamedClient implements ClientModInitializer {
    private static KeyBinding unnamedKeyBinding;
    private static KeyBinding teleportKeyBinding;

    private static final Flight flight = new Flight();

    @Override
    public void onInitializeClient() {
        UnnamedMod.LOGGER.info("ClientMod initialized");

        ClientTickEvents.START_CLIENT_TICK.register(world -> {
            flight.tick(MinecraftClient.getInstance());
        });
        registerKeyBindings();
    }

    public void registerKeyBindings() {
        UnnamedKeyBindings.registerKeyBindings();
    }
}
