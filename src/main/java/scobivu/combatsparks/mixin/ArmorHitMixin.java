package scobivu.combatsparks.mixin;

import scobivu.combatsparks.CombatSparksClient;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class ArmorHitMixin {

    @Inject(method = "onDamaged", at = @At("HEAD"))
    private void onEntityDamaged(DamageSource damageSource, CallbackInfo ci) {
        LivingEntity self = (LivingEntity) (Object) this;
        if (self.getWorld().isClient && CombatSparksClient.hasArmor(self)) {
            CombatSparksClient.spawnArmorSparks(self.getWorld(), self.getPos().add(0, self.getHeight() * 0.55, 0));
        }
    }
}
