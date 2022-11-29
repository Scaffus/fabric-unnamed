package net.scaffus.unnamed;

import net.minecraft.client.option.KeyBinding;
import net.minecraft.text.LiteralText;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;

public class UnnamedHelper {
    public static Text unnamedMessage(Text message) {
        MutableText fullMessage = new LiteralText("");
        fullMessage.append(UnnamedMod.MESSAGE_PREFIX);
        fullMessage.append(" ");
        fullMessage.append(message);
        return fullMessage;
    }

    public static Text optionToggleMessage(UnnamedOption option, boolean prefix) {
        MutableText message = new LiteralText("");
        message.append(option.displayName);
        message.append(" ");
        message.append(option.enabled ? new LiteralText("ON").formatted(Formatting.GREEN) : new LiteralText("OFF").formatted(Formatting.RED));
        return prefix ? unnamedMessage(message) : message;
    }

    public static KeyBinding createKeyBinding(String name, int key) {
        return new KeyBinding(
                "key.unnamed." + name,
                key,
                "category.unnamed.Unnamed"
        );
    }
}
