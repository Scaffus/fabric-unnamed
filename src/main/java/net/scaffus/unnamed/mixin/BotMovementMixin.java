package net.scaffus.unnamed.mixin;

import net.minecraft.network.packet.c2s.play.PlayerMoveC2SPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(PlayerMoveC2SPacket.class)
public class BotMovementMixin {
    @ModifyVariable(method = "Lnet/minecraft/network/packet/c2s/play/PlayerMoveC2SPacket;<init>(DDDFFZZZ)V", at = @At("HEAD"), ordinal = 0)
    private static double injedctedx(double x) {
        return Math.round(x * 1000) % 10;
    }

    @ModifyVariable(method = "Lnet/minecraft/network/packet/c2s/play/PlayerMoveC2SPacket;<init>(DDDFFZZZ)V", at = @At("HEAD"), ordinal = 2)
    private static double injectedz(double z) {
        return Math.round(z * 1000) % 10;
    }
}
