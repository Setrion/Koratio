package net.setrion.koratio.registry;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.PointedDripstoneBlock;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.SoundActions;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.world.level.material.fluid.BloodFluid;
import net.setrion.koratio.world.level.material.fluid.ChocolateMilkFluid;
import net.setrion.koratio.world.level.material.fluid.MoltenSugarFluid;

public class KoratioFluids {
	
	public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(BuiltInRegistries.FLUID, Koratio.MOD_ID);
	public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(NeoForgeRegistries.FLUID_TYPES, Koratio.MOD_ID);

	public static final DeferredHolder<FluidType, FluidType> MOLTEN_SUGAR_TYPE = FLUID_TYPES.register("molten_sugar", () -> createSugarType("molten_sugar", KoratioBlocks.MOLTEN_SUGAR_CAULDRON.get(), KoratioParticles.DRIPPING_DRIPSTONE_SUGAR.get()));
	public static final DeferredHolder<FluidType, FluidType> MOLTEN_BLUE_SUGAR_TYPE = FLUID_TYPES.register("molten_blue_sugar", () -> createSugarType("molten_blue_sugar", KoratioBlocks.MOLTEN_BLUE_SUGAR_CAULDRON.get(), KoratioParticles.DRIPPING_DRIPSTONE_BLUE_SUGAR.get()));
	public static final DeferredHolder<FluidType, FluidType> MOLTEN_GREEN_SUGAR_TYPE = FLUID_TYPES.register("molten_green_sugar", () -> createSugarType("molten_green_sugar", KoratioBlocks.MOLTEN_GREEN_SUGAR_CAULDRON.get(), KoratioParticles.DRIPPING_DRIPSTONE_GREEN_SUGAR.get()));
	public static final DeferredHolder<FluidType, FluidType> MOLTEN_YELLOW_SUGAR_TYPE = FLUID_TYPES.register("molten_yellow_sugar", () -> createSugarType("molten_yellow_sugar", KoratioBlocks.MOLTEN_YELLOW_SUGAR_CAULDRON.get(), KoratioParticles.DRIPPING_DRIPSTONE_YELLOW_SUGAR.get()));
	public static final DeferredHolder<FluidType, FluidType> MOLTEN_RED_SUGAR_TYPE = FLUID_TYPES.register("molten_red_sugar", () -> createSugarType("molten_red_sugar", KoratioBlocks.MOLTEN_RED_SUGAR_CAULDRON.get(), KoratioParticles.DRIPPING_DRIPSTONE_RED_SUGAR.get()));

	private static FluidType createSugarType(String name, Block cauldron, ParticleOptions particle) {
		return new FluidType(FluidType.Properties.create()
				.descriptionId("block.koratio." + name)
				.canSwim(false)
				.canDrown(true)
				.canPushEntity(true)
				.pathType(PathType.LAVA)
				.adjacentPathType(null)
				.sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
				.sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY)
				.density(3000)
				.motionScale(0.023D)
				.viscosity(6000)
				.temperature(500)
				.addDripstoneDripping(PointedDripstoneBlock.LAVA_TRANSFER_PROBABILITY_PER_RANDOM_TICK, particle, cauldron, SoundEvents.POINTED_DRIPSTONE_DRIP_LAVA_INTO_CAULDRON)){

			@Override
			public void setItemMovement(ItemEntity entity) {
				Vec3 vec3 = entity.getDeltaMovement();
				entity.setDeltaMovement(vec3.x * (double)0.95F, vec3.y + (double)(vec3.y < (double)0.06F ? 5.0E-4F : 0.0F), vec3.z * (double)0.95F);
			}
		};
	}

	public static final DeferredHolder<FluidType, FluidType> CHOCOLATE_MILK_TYPE = FLUID_TYPES.register("chocolate_milk", () ->
		new FluidType(FluidType.Properties.create()
	            .descriptionId("block.koratio.chocolate_milk")
	            .canSwim(false)
	            .canDrown(true)
	            .canPushEntity(true)
	            .pathType(PathType.LAVA)
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
			    });
	
	public static final DeferredHolder<FluidType, FluidType> BLOOD_TYPE = FLUID_TYPES.register("blood", () ->
	new FluidType(FluidType.Properties.create()
            .descriptionId("block.koratio.blood")
            .canSwim(false)
            .canDrown(true)
            .canPushEntity(true)
            .pathType(PathType.LAVA)
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
		    });

	public static final DeferredHolder<Fluid, FlowingFluid> FLOWING_MOLTEN_SUGAR = FLUIDS.register("flowing_molten_sugar", MoltenSugarFluid.FlowingWhite::new);
	public static final DeferredHolder<Fluid, FlowingFluid> MOLTEN_SUGAR = FLUIDS.register("molten_sugar", MoltenSugarFluid.SourceWhite::new);
	public static final DeferredHolder<Fluid, FlowingFluid> FLOWING_MOLTEN_BLUE_SUGAR = FLUIDS.register("flowing_molten_blue_sugar", MoltenSugarFluid.FlowingBlue::new);
	public static final DeferredHolder<Fluid, FlowingFluid> MOLTEN_BLUE_SUGAR = FLUIDS.register("molten_blue_sugar", MoltenSugarFluid.SourceBlue::new);
	public static final DeferredHolder<Fluid, FlowingFluid> FLOWING_MOLTEN_GREEN_SUGAR = FLUIDS.register("flowing_molten_green_sugar", MoltenSugarFluid.FlowingGreen::new);
	public static final DeferredHolder<Fluid, FlowingFluid> MOLTEN_GREEN_SUGAR = FLUIDS.register("molten_green_sugar", MoltenSugarFluid.SourceGreen::new);
	public static final DeferredHolder<Fluid, FlowingFluid> FLOWING_MOLTEN_YELLOW_SUGAR = FLUIDS.register("flowing_molten_yellow_sugar", MoltenSugarFluid.FlowingYellow::new);
	public static final DeferredHolder<Fluid, FlowingFluid> MOLTEN_YELLOW_SUGAR = FLUIDS.register("molten_yellow_sugar", MoltenSugarFluid.SourceYellow::new);
	public static final DeferredHolder<Fluid, FlowingFluid> FLOWING_MOLTEN_RED_SUGAR = FLUIDS.register("flowing_molten_red_sugar", MoltenSugarFluid.FlowingRed::new);
	public static final DeferredHolder<Fluid, FlowingFluid> MOLTEN_RED_SUGAR = FLUIDS.register("molten_red_sugar", MoltenSugarFluid.SourceRed::new);

	public static final DeferredHolder<Fluid, FlowingFluid> FLOWING_CHOCOLATE_MILK = FLUIDS.register("flowing_chocolate_milk", ChocolateMilkFluid.Flowing::new);
	public static final DeferredHolder<Fluid, FlowingFluid> CHOCOLATE_MILK = FLUIDS.register("chocolate_milk", ChocolateMilkFluid.Source::new);
	
	public static final DeferredHolder<Fluid, FlowingFluid> FLOWING_BLOOD = FLUIDS.register("flowing_blood", BloodFluid.Flowing::new);
	public static final DeferredHolder<Fluid, FlowingFluid> BLOOD = FLUIDS.register("blood", BloodFluid.Source::new);
}