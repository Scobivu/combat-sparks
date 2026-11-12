package scobivu.combatsparks.mixin;

import scobivu.combatsparks.CombatSparksClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.entity.Entity;
import net.minecraft.network.packet.s2c.play.EntityStatusS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayNetworkHandler.class)
public class TotemPopMixin {

    @Inject(method = "onEntityStatus", at = @At("TAIL"))
    private void onTotemPop(EntityStatusS2CPacket packet, CallbackInfo ci) {
        if (packet.getStatus() == 35) {
            ClientPlayNetworkHandler handler = (ClientPlayNetworkHandler) (Object) this;
            if (handler.getWorld() != null) {
                Entity entity = packet.getEntity(handler.getWorld());
                if (entity != null) {
                    CombatSparksClient.spawnTotemBurst(entity);
                }
            }
        }
    }
}
