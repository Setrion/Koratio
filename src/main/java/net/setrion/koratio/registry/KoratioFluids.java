package net.setrion.koratio.registry;

import java.util.function.Consumer;

import org.jetbrains.annotations.NotNull;
import org.joml.Vector3f;

import com.mojang.blaze3d.shaders.FogShape;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.common.SoundActions;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.world.level.material.fluid.BloodFluid;
import net.setrion.koratio.world.level.material.fluid.ChocolateMilkFluid;

public class KoratioFluids {
	
	public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, Koratio.MOD_ID);
	public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, Koratio.MOD_ID);

	public static final RegistryObject<FluidType> CHOCOLATE_MILK_TYPE = FLUID_TYPES.register("chocolate_milk", () -> 
		new FluidType(FluidType.Properties.create()
	            .descriptionId("block.koratio.chocolate_milk")
	            .canSwim(false)
	            .canDrown(true)
	            .canPushEntity(true)
	            .pathType(BlockPathTypes.LAVA)
	            .adjacentPathType(null)
	            .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL_LAVA)
	            .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY_LAVA)
	            .density(3000)
	            .viscosity(6000)
	            .temperature(500)) {
			        @Override
			        public double motionScale(Entity entity) {
			            return 0.007D;
			        }
			
			        @Override
			        public void setItemMovement(ItemEntity entity) {
			            Vec3 vec3 = entity.getDeltaMovement();
			            entity.setDeltaMovement(vec3.x * (double)0.95F, vec3.y + (double)(vec3.y < (double)0.06F ? 5.0E-4F : 0.0F), vec3.z * (double)0.95F);
			        }
			
			        @Override
			        public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {
			            consumer.accept(new IClientFluidTypeExtensions() {
			
			                @Override
			                public ResourceLocation getStillTexture() {
			                    return new ResourceLocation(Koratio.MOD_ID, "block/chocolate_milk_still");
			                }
			
			                @Override
			                public ResourceLocation getFlowingTexture() {
			                    return new ResourceLocation(Koratio.MOD_ID, "block/chocolate_milk_flow");
			                }
			                
			                @Override
			                public @NotNull Vector3f modifyFogColor(Camera camera, float partialTick, ClientLevel level, int renderDistance, float darkenWorldAmount, Vector3f fluidFogColor) {
			                    return new Vector3f(139F / 255F, 86F / 255F, 54F / 255F);
			                }

			                @Override
			                public void modifyFogRender(Camera camera, FogRenderer.FogMode mode, float renderDistance, float partialTick, float nearDistance, float farDistance, FogShape shape) {
			                    RenderSystem.setShaderFogStart(0.5f);
			                    RenderSystem.setShaderFogEnd(1f); // distance when the fog starts
			                }
			            });
			        }
			    });
	
	public static final RegistryObject<FluidType> BLOOD_TYPE = FLUID_TYPES.register("blood", () -> 
	new FluidType(FluidType.Properties.create()
            .descriptionId("block.koratio.blood")
            .canSwim(false)
            .canDrown(true)
            .canPushEntity(true)
            .pathType(BlockPathTypes.LAVA)
            .adjacentPathType(null)
            .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL_LAVA)
            .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY_LAVA)
            .density(3000)
            .viscosity(6000)
            .temperature(500)) {
		        @Override
		        public double motionScale(Entity entity) {
		            return 0.007D;
		        }
		
		        @Override
		        public void setItemMovement(ItemEntity entity) {
		            Vec3 vec3 = entity.getDeltaMovement();
		            entity.setDeltaMovement(vec3.x * (double)0.95F, vec3.y + (double)(vec3.y < (double)0.06F ? 5.0E-4F : 0.0F), vec3.z * (double)0.95F);
		        }
		
		        @Override
		        public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {
		            consumer.accept(new IClientFluidTypeExtensions() {
		
		                @Override
		                public ResourceLocation getStillTexture() {
		                    return new ResourceLocation(Koratio.MOD_ID, "block/blood_still");
		                }
		
		                @Override
		                public ResourceLocation getFlowingTexture() {
		                    return new ResourceLocation(Koratio.MOD_ID, "block/blood_flow");
		                }
		                
		                @Override
		                public @NotNull Vector3f modifyFogColor(Camera camera, float partialTick, ClientLevel level, int renderDistance, float darkenWorldAmount, Vector3f fluidFogColor) {
		                    return new Vector3f(127F / 255F, 0F / 255F, 0F / 255F);
		                }

		                @Override
		                public void modifyFogRender(Camera camera, FogRenderer.FogMode mode, float renderDistance, float partialTick, float nearDistance, float farDistance, FogShape shape) {
		                    RenderSystem.setShaderFogStart(0.5f);
		                    RenderSystem.setShaderFogEnd(1f); // distance when the fog starts
		                }
		            });
		        }
		    });
	
	public static final RegistryObject<FlowingFluid> FLOWING_CHOCOLATE_MILK = FLUIDS.register("flowing_chocolate_milk", () -> new ChocolateMilkFluid.Flowing());
	public static final RegistryObject<FlowingFluid> CHOCOLATE_MILK = FLUIDS.register("chocolate_milk", () -> new ChocolateMilkFluid.Source());
	
	public static final RegistryObject<FlowingFluid> FLOWING_BLOOD = FLUIDS.register("flowing_blood", () -> new BloodFluid.Flowing());
	public static final RegistryObject<FlowingFluid> BLOOD = FLUIDS.register("blood", () -> new BloodFluid.Source());
}