package scobivu.combatsparks.mixin;

import scobivu.combatsparks.CombatSparksClient;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class ShieldBlockMixin {

    @Inject(method = "handleStatus", at = @At("HEAD"))
    private void onShieldBlockStatus(byte status, CallbackInfo ci) {
        if (status == 29) {
            LivingEntity self = (LivingEntity) (Object) this;
            if (self.getWorld().isClient) {
                CombatSparksClient.spawnShieldSparks(self);
            }
        }
    }
}
