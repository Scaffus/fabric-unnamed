package net.scaffus.unnamed;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.TranslatableText;

public class UnnamedMenuScreen extends Screen {
    private final boolean showMenu;
    private final boolean openedFromTitleScreen;

    public UnnamedMenuScreen(Boolean showMenu, boolean openedFromTitleScreen) {
        super(new TranslatableText("menu.unnamed"));
        this.showMenu = showMenu;
        this.openedFromTitleScreen = openedFromTitleScreen;
    }

    protected void init() {
        if (this.showMenu) {
            this.initWidgets();
        }
    }

    private void initWidgets() {
        // * Line 1
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 152, this.height / 4 + 8, 148, 20, UnnamedHelper.optionToggleMessage(UnnamedOptions.sendToActionBar, false), (button -> {
            UnnamedOptions.sendToActionBar.toggle();
            button.setMessage(UnnamedHelper.optionToggleMessage(UnnamedOptions.sendToActionBar, false));
        })));

        this.addDrawableChild(new ButtonWidget(this.width / 2 + 4, this.height / 4 + 8, 148, 20, UnnamedHelper.optionToggleMessage(UnnamedOptions.botMovements, false), (button -> {
            UnnamedOptions.botMovements.toggle();
            button.setMessage(UnnamedHelper.optionToggleMessage(UnnamedOptions.botMovements, false));
        })));

        // * Line 2
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 152, this.height / 4 + 32, 148, 20, UnnamedHelper.optionToggleMessage(UnnamedOptions.flight, false), (button -> {
            UnnamedOptions.flight.toggle();
            button.setMessage(UnnamedHelper.optionToggleMessage(UnnamedOptions.flight, false));
        })));

        this.addDrawableChild(new ButtonWidget(this.width / 2 - 102, this.height / 4 + 120, 154, 20, new TranslatableText("menu.unnamed.return"), (button -> {
            if (this.openedFromTitleScreen) {
                MinecraftClient.getInstance().setScreen(new TitleScreen());
            } else {
                MinecraftClient.getInstance().setScreen(null);
            }
        })));
    }

    public void tick() {
        super.tick();
    }

    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        if (this.showMenu) {
            this.renderBackground(matrices);
            drawCenteredText(matrices, this.textRenderer, this.title, this.width / 2, 40, 16777215);
        } else {
            drawCenteredText(matrices, this.textRenderer, this.title, this.width / 2, 10, 16777215);
        }

        super.render(matrices, mouseX, mouseY, delta);
    }
}
