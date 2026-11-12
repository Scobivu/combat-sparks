package scobivu.combatsparks;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.Random;

public class CombatSparksClient implements ClientModInitializer {
    private static final Random RANDOM = new Random();

    @Override
    public void onInitializeClient() {
        // Инициализация теперь полностью работает через клиентские миксины
    }

    public static boolean hasArmor(LivingEntity entity) {
        for (EquipmentSlot slot : new EquipmentSlot[]{
                EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET
        }) {
            ItemStack stack = entity.getEquippedStack(slot);
            if (!stack.isEmpty() && stack.getItem() instanceof ArmorItem) {
                return true;
            }
        }
        return false;
    }

    public static void spawnArmorSparks(World world, Vec3d pos) {
        for (int i = 0; i < 20; i++) {
            double vx = (RANDOM.nextDouble() - 0.5) * 0.35;
            double vy = RANDOM.nextDouble() * 0.3 + 0.08;
            double vz = (RANDOM.nextDouble() - 0.5) * 0.35;

            world.addParticle(ParticleTypes.CRIT, pos.x, pos.y, pos.z, vx, vy, vz);
            if (i % 2 == 0) {
                world.addParticle(ParticleTypes.FLAME, pos.x, pos.y, pos.z, vx * 0.6, vy * 0.6, vz * 0.6);
            }
            if (i % 3 == 0) {
                world.addParticle(ParticleTypes.WAX_ON, pos.x, pos.y, pos.z, vx * 1.1, vy * 1.1, vz * 1.1);
            }
        }
    }

    public static void spawnShieldSparks(LivingEntity entity) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.world == null) return;

        Vec3d look = entity.getRotationVec(1.0F);
        Vec3d origin = entity.getEyePos().add(look.multiply(0.65)).add(0, -0.25, 0);

        for (int i = 0; i < 24; i++) {
            double spreadX = (RANDOM.nextDouble() - 0.5) * 0.6;
            double spreadY = (RANDOM.nextDouble() - 0.3) * 0.5;
            double spreadZ = (RANDOM.nextDouble() - 0.5) * 0.6;

            Vec3d vel = look.multiply(0.25).add(spreadX, spreadY, spreadZ);
            client.world.addParticle(ParticleTypes.CRIT, origin.x, origin.y, origin.z, vel.x, vel.y, vel.z);

            if (i % 2 == 0) {
                client.world.addParticle(ParticleTypes.FLAME, origin.x, origin.y, origin.z, vel.x * 0.7, vel.y * 0.7, vel.z * 0.7);
            }
            if (i % 4 == 0) {
                client.world.addParticle(ParticleTypes.WAX_ON, origin.x, origin.y, origin.z, vel.x * 1.3, vel.y * 1.3, vel.z * 1.3);
            }
        }
    }

    public static void spawnTotemBurst(Entity entity) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.world == null) return;

        Vec3d center = entity.getPos().add(0, entity.getHeight() * 0.5, 0);
        for (int i = 0; i < 40; i++) {
            double angle = RANDOM.nextDouble() * Math.PI * 2;
            double speed = 0.15 + RANDOM.nextDouble() * 0.45;
            double vx = Math.cos(angle) * speed;
            double vy = (RANDOM.nextDouble() - 0.2) * 0.6;
            double vz = Math.sin(angle) * speed;

            client.world.addParticle(ParticleTypes.TOTEM_OF_UNDYING, center.x, center.y, center.z, vx, vy, vz);
            if (i % 3 == 0) {
                client.world.addParticle(ParticleTypes.END_ROD, center.x, center.y, center.z, vx * 0.8, vy * 0.8, vz * 0.8);
            }
        }
    }
}
