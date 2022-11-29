package net.scaffus.unnamed;

import net.minecraft.client.MinecraftClient;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;

public class UnnamedOption {
    String id;
    boolean enabled = false;
    TranslatableText displayName;

    public UnnamedOption(String id, boolean enabled, String translationPath) {
        this.id = id;
        this.enabled = enabled;
        this.displayName = new TranslatableText(translationPath);
    }

    public void toggle() {
        this.enabled = !enabled;
        if(MinecraftClient.getInstance().world != null) {
            assert MinecraftClient.getInstance().player != null;
            MinecraftClient.getInstance().player.sendMessage(UnnamedHelper.optionToggleMessage(this, true), UnnamedOptions.sendToActionBar.isEnabled());
        }
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isEnabled() {
        return this.enabled;
    }
}
