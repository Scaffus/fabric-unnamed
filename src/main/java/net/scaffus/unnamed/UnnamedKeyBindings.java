package net.scaffus.unnamed;

import com.mojang.authlib.minecraft.client.MinecraftClient;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;
import org.lwjgl.glfw.GLFW;

public class UnnamedKeyBindings {
    public static KeyBinding unnamedMenuKeyBinding;
    public static KeyBinding sendToActionBarKeyBinding;
    public static KeyBinding botMovementsKeyBinding;
    public static KeyBinding flightKeyBinding;

    public static KeyBinding increaseFlightSpeedKeyBinding;
    public static KeyBinding decreaseFlightSpeedKeyBinding;

    public static void registerKeyBindings() {
        // Unnamed Menu
        unnamedMenuKeyBinding = KeyBindingHelper.registerKeyBinding(UnnamedHelper.createKeyBinding("menu", GLFW.GLFW_KEY_RIGHT_SHIFT));
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (unnamedMenuKeyBinding.wasPressed()) {
                client.setScreen(new UnnamedMenuScreen(true, false));
            }
        });

        // Send To Action Bar
        sendToActionBarKeyBinding = KeyBindingHelper.registerKeyBinding(UnnamedHelper.createKeyBinding("sendToActionBar", -1));
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (sendToActionBarKeyBinding.wasPressed()) {
                UnnamedOptions.sendToActionBar.toggle();
            }
        });

        // Bot Movements
        botMovementsKeyBinding = KeyBindingHelper.registerKeyBinding(UnnamedHelper.createKeyBinding("botMovements", -1));
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (botMovementsKeyBinding.wasPressed()) {
                UnnamedOptions.botMovements.toggle();
            }
        });

        // Flight
        flightKeyBinding = KeyBindingHelper.registerKeyBinding(UnnamedHelper.createKeyBinding("flight", -1));
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (flightKeyBinding.wasPressed()) {
                UnnamedOptions.flight.toggle();
            }
        });

        // Decrease Flight Speed
        decreaseFlightSpeedKeyBinding = KeyBindingHelper.registerKeyBinding(UnnamedHelper.createKeyBinding("flight.decreaseSpeed", -1));
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (decreaseFlightSpeedKeyBinding.wasPressed()) {
                if (UnnamedOptions.flightSpeed > 0.25) {
                    UnnamedOptions.flightSpeed -= 0.25;
                    client.player.sendMessage(UnnamedHelper.unnamedMessage(new TranslatableText("message.unnamed.flight.setSpeedTo").append(String.valueOf(UnnamedOptions.flightSpeed))), UnnamedOptions.sendToActionBar.isEnabled());
                } else {
                    client.player.sendMessage(UnnamedHelper.unnamedMessage(new TranslatableText("message.unnamed.flight.reachedMinimumSpeed")), UnnamedOptions.sendToActionBar.isEnabled());
                }
            }
        });

        // Increase Flight Speed
        increaseFlightSpeedKeyBinding = KeyBindingHelper.registerKeyBinding(UnnamedHelper.createKeyBinding("flight.increaseSpeed", -1));
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (increaseFlightSpeedKeyBinding.wasPressed()) {
                if (UnnamedOptions.flightSpeed < 2.5) {
                    UnnamedOptions.flightSpeed += 0.25;
                    client.player.sendMessage(UnnamedHelper.unnamedMessage(new TranslatableText("message.unnamed.flight.setSpeedTo").append(String.valueOf(UnnamedOptions.flightSpeed))), UnnamedOptions.sendToActionBar.isEnabled());
                } else {
                    client.player.sendMessage(UnnamedHelper.unnamedMessage(new TranslatableText("message.unnamed.flight.reachedMaximumSpeed")), UnnamedOptions.sendToActionBar.isEnabled());
                }
            }
        });
    }
}
