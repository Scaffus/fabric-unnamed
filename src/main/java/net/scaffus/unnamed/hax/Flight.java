package net.scaffus.unnamed.hax;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;
import net.scaffus.unnamed.UnnamedOptions;

public class Flight {
    int toggle = 0;
    double MAX_SPEED = UnnamedOptions.flightSpeed;
    double FALL_SPEED = -0.035;
    double acceleration = 0.1;

    public void tick(MinecraftClient client) {
        if (client.player != null && UnnamedOptions.flight.isEnabled()) {
            boolean jumpPressed = client.options.jumpKey.isPressed();
            boolean forwardPressed = client.options.forwardKey.isPressed();
            boolean leftPressed = client.options.leftKey.isPressed();
            boolean rightPressed = client.options.rightKey.isPressed();
            boolean backPressed = client.options.backKey.isPressed();

            Entity entity = client.player;
            if (client.player.hasVehicle()) {
                entity = client.player.getVehicle();
            }

            Vec3d velocity = entity.getVelocity();
            Vec3d newVelocity = new Vec3d(velocity.x, -FALL_SPEED, velocity.z);
            if (jumpPressed) {
                if (forwardPressed) {
                    newVelocity = client.player.getRotationVector().multiply(acceleration);
                }
                if (leftPressed && !client.player.hasVehicle()) {
                    newVelocity = client.player.getRotationVector().multiply(acceleration).rotateY(3.1415927F / 2);
                    newVelocity = new Vec3d(newVelocity.x, 0, newVelocity.z);
                }
                if (rightPressed && !client.player.hasVehicle()) {
                    newVelocity = client.player.getRotationVector().multiply(acceleration).rotateY(-3.1415927F / 2);
                    newVelocity = new Vec3d(newVelocity.x, 0, newVelocity.z);
                }
                if (backPressed) {
                    newVelocity = client.player.getRotationVector().negate().multiply(acceleration);
                }

                newVelocity = new Vec3d(newVelocity.x, (toggle == 0 && newVelocity.y > FALL_SPEED) ? FALL_SPEED : newVelocity.y + .2, newVelocity.z);
                entity.setVelocity(newVelocity);

                if (forwardPressed || leftPressed || rightPressed || backPressed) {
                    if (acceleration < MAX_SPEED) acceleration += 0.1;
                } else if (acceleration > 0.2) {
                    acceleration -= 0.2;
                }
            }

            if (toggle == 0 || newVelocity.y <= -0.035) {
                toggle = 40;
            }
            toggle--;
        }
    }
}
