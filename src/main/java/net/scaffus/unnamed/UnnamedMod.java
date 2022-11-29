package net.scaffus.unnamed;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.text.LiteralText;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class UnnamedMod implements ModInitializer {
	public static boolean SEND_TO_BAR = true;
	public static MutableText MESSAGE_PREFIX = new LiteralText("");
	public static final Logger LOGGER = LoggerFactory.getLogger("Unnamed");


	@Override
	public void onInitialize() {
		MESSAGE_PREFIX.append(new LiteralText("[").formatted(Formatting.DARK_GRAY));
		MESSAGE_PREFIX.append(new LiteralText("Unnamed").formatted(Formatting.GRAY));
		MESSAGE_PREFIX.append(new LiteralText("]").formatted(Formatting.DARK_GRAY));
		LOGGER.info("Mod initialized");
	}
}
