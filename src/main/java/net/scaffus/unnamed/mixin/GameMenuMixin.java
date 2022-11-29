package net.scaffus.unnamed.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.advancement.AdvancementsScreen;
import net.minecraft.client.gui.screen.world.SelectWorldScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.scaffus.unnamed.UnnamedHelper;
import net.scaffus.unnamed.UnnamedMenuScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameMenuScreen.class)
public abstract class GameMenuMixin extends Screen {

    protected GameMenuMixin(Text title) {
        super(title);
    }

    @Inject(at=@At("RETURN"), method = "initWidgets")
    private void addUnnamedMenuButton(CallbackInfo ci) {
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 102 + 209, this.height / 4 + 24 - 16, 50, 20, new TranslatableText("gui.unnamed.menu"), (button) -> {
            this.client.setScreen(new UnnamedMenuScreen(true, false));
        }));
    }
}
