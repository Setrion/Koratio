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

	public static final DeferredHolder<FluidType, FluidType> MOLTEN_WHITE_SUGAR_TYPE = FLUID_TYPES.register("molten_white_sugar", () -> createSugarType("molten_white_sugar", KoratioBlocks.MOLTEN_WHITE_SUGAR_CAULDRON.get(), KoratioParticles.DRIPPING_DRIPSTONE_WHITE_SUGAR.get()));
	public static final DeferredHolder<FluidType, FluidType> MOLTEN_LIGHT_GRAY_SUGAR_TYPE = FLUID_TYPES.register("molten_light_gray_sugar", () -> createSugarType("molten_light_gray_sugar", KoratioBlocks.MOLTEN_LIGHT_GRAY_SUGAR_CAULDRON.get(), KoratioParticles.DRIPPING_DRIPSTONE_LIGHT_GRAY_SUGAR.get()));
	public static final DeferredHolder<FluidType, FluidType> MOLTEN_GRAY_SUGAR_TYPE = FLUID_TYPES.register("molten_gray_sugar", () -> createSugarType("molten_gray_sugar", KoratioBlocks.MOLTEN_GRAY_SUGAR_CAULDRON.get(), KoratioParticles.DRIPPING_DRIPSTONE_GRAY_SUGAR.get()));
	public static final DeferredHolder<FluidType, FluidType> MOLTEN_BLACK_SUGAR_TYPE = FLUID_TYPES.register("molten_black_sugar", () -> createSugarType("molten_black_sugar", KoratioBlocks.MOLTEN_BLACK_SUGAR_CAULDRON.get(), KoratioParticles.DRIPPING_DRIPSTONE_BLACK_SUGAR.get()));
	public static final DeferredHolder<FluidType, FluidType> MOLTEN_BROWN_SUGAR_TYPE = FLUID_TYPES.register("molten_brown_sugar", () -> createSugarType("molten_brown_sugar", KoratioBlocks.MOLTEN_BROWN_SUGAR_CAULDRON.get(), KoratioParticles.DRIPPING_DRIPSTONE_BROWN_SUGAR.get()));
	public static final DeferredHolder<FluidType, FluidType> MOLTEN_RED_SUGAR_TYPE = FLUID_TYPES.register("molten_red_sugar", () -> createSugarType("molten_red_sugar", KoratioBlocks.MOLTEN_RED_SUGAR_CAULDRON.get(), KoratioParticles.DRIPPING_DRIPSTONE_RED_SUGAR.get()));
	public static final DeferredHolder<FluidType, FluidType> MOLTEN_ORANGE_SUGAR_TYPE = FLUID_TYPES.register("molten_orange_sugar", () -> createSugarType("molten_orange_sugar", KoratioBlocks.MOLTEN_ORANGE_SUGAR_CAULDRON.get(), KoratioParticles.DRIPPING_DRIPSTONE_ORANGE_SUGAR.get()));
	public static final DeferredHolder<FluidType, FluidType> MOLTEN_YELLOW_SUGAR_TYPE = FLUID_TYPES.register("molten_yellow_sugar", () -> createSugarType("molten_yellow_sugar", KoratioBlocks.MOLTEN_YELLOW_SUGAR_CAULDRON.get(), KoratioParticles.DRIPPING_DRIPSTONE_YELLOW_SUGAR.get()));
	public static final DeferredHolder<FluidType, FluidType> MOLTEN_LIME_SUGAR_TYPE = FLUID_TYPES.register("molten_lime_sugar", () -> createSugarType("molten_lime_sugar", KoratioBlocks.MOLTEN_LIME_SUGAR_CAULDRON.get(), KoratioParticles.DRIPPING_DRIPSTONE_LIME_SUGAR.get()));
	public static final DeferredHolder<FluidType, FluidType> MOLTEN_GREEN_SUGAR_TYPE = FLUID_TYPES.register("molten_green_sugar", () -> createSugarType("molten_green_sugar", KoratioBlocks.MOLTEN_GREEN_SUGAR_CAULDRON.get(), KoratioParticles.DRIPPING_DRIPSTONE_GREEN_SUGAR.get()));
	public static final DeferredHolder<FluidType, FluidType> MOLTEN_CYAN_SUGAR_TYPE = FLUID_TYPES.register("molten_cyan_sugar", () -> createSugarType("molten_cyan_sugar", KoratioBlocks.MOLTEN_CYAN_SUGAR_CAULDRON.get(), KoratioParticles.DRIPPING_DRIPSTONE_CYAN_SUGAR.get()));
	public static final DeferredHolder<FluidType, FluidType> MOLTEN_LIGHT_BLUE_SUGAR_TYPE = FLUID_TYPES.register("molten_light_blue_sugar", () -> createSugarType("molten_light_blue_sugar", KoratioBlocks.MOLTEN_LIGHT_BLUE_SUGAR_CAULDRON.get(), KoratioParticles.DRIPPING_DRIPSTONE_LIGHT_BLUE_SUGAR.get()));
	public static final DeferredHolder<FluidType, FluidType> MOLTEN_BLUE_SUGAR_TYPE = FLUID_TYPES.register("molten_blue_sugar", () -> createSugarType("molten_blue_sugar", KoratioBlocks.MOLTEN_BLUE_SUGAR_CAULDRON.get(), KoratioParticles.DRIPPING_DRIPSTONE_BLUE_SUGAR.get()));
	public static final DeferredHolder<FluidType, FluidType> MOLTEN_PURPLE_SUGAR_TYPE = FLUID_TYPES.register("molten_purple_sugar", () -> createSugarType("molten_purple_sugar", KoratioBlocks.MOLTEN_PURPLE_SUGAR_CAULDRON.get(), KoratioParticles.DRIPPING_DRIPSTONE_PURPLE_SUGAR.get()));
	public static final DeferredHolder<FluidType, FluidType> MOLTEN_MAGENTA_SUGAR_TYPE = FLUID_TYPES.register("molten_magenta_sugar", () -> createSugarType("molten_magenta_sugar", KoratioBlocks.MOLTEN_MAGENTA_SUGAR_CAULDRON.get(), KoratioParticles.DRIPPING_DRIPSTONE_MAGENTA_SUGAR.get()));
	public static final DeferredHolder<FluidType, FluidType> MOLTEN_PINK_SUGAR_TYPE = FLUID_TYPES.register("molten_pink_sugar", () -> createSugarType("molten_pink_sugar", KoratioBlocks.MOLTEN_PINK_SUGAR_CAULDRON.get(), KoratioParticles.DRIPPING_DRIPSTONE_PINK_SUGAR.get()));

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

	public static final DeferredHolder<Fluid, FlowingFluid> FLOWING_MOLTEN_WHITE_SUGAR = FLUIDS.register("flowing_molten_white_sugar", MoltenSugarFluid.FlowingWhite::new);
	public static final DeferredHolder<Fluid, FlowingFluid> MOLTEN_WHITE_SUGAR = FLUIDS.register("molten_white_sugar", MoltenSugarFluid.SourceWhite::new);
	public static final DeferredHolder<Fluid, FlowingFluid> FLOWING_MOLTEN_LIGHT_GRAY_SUGAR = FLUIDS.register("flowing_molten_light_gray_sugar", MoltenSugarFluid.FlowingLightGray::new);
	public static final DeferredHolder<Fluid, FlowingFluid> MOLTEN_LIGHT_GRAY_SUGAR = FLUIDS.register("molten_light_gray_sugar", MoltenSugarFluid.SourceLightGray::new);
	public static final DeferredHolder<Fluid, FlowingFluid> FLOWING_MOLTEN_GRAY_SUGAR = FLUIDS.register("flowing_molten_gray_sugar", MoltenSugarFluid.FlowingGray::new);
	public static final DeferredHolder<Fluid, FlowingFluid> MOLTEN_GRAY_SUGAR = FLUIDS.register("molten_gray_sugar", MoltenSugarFluid.SourceGray::new);
	public static final DeferredHolder<Fluid, FlowingFluid> FLOWING_MOLTEN_BLACK_SUGAR = FLUIDS.register("flowing_molten_black_sugar", MoltenSugarFluid.FlowingBlack::new);
	public static final DeferredHolder<Fluid, FlowingFluid> MOLTEN_BLACK_SUGAR = FLUIDS.register("molten_black_sugar", MoltenSugarFluid.SourceBlack::new);
	public static final DeferredHolder<Fluid, FlowingFluid> FLOWING_MOLTEN_BROWN_SUGAR = FLUIDS.register("flowing_molten_brown_sugar", MoltenSugarFluid.FlowingBrown::new);
	public static final DeferredHolder<Fluid, FlowingFluid> MOLTEN_BROWN_SUGAR = FLUIDS.register("molten_brown_sugar", MoltenSugarFluid.SourceBrown::new);
	public static final DeferredHolder<Fluid, FlowingFluid> FLOWING_MOLTEN_RED_SUGAR = FLUIDS.register("flowing_molten_red_sugar", MoltenSugarFluid.FlowingRed::new);
	public static final DeferredHolder<Fluid, FlowingFluid> MOLTEN_RED_SUGAR = FLUIDS.register("molten_red_sugar", MoltenSugarFluid.SourceRed::new);
	public static final DeferredHolder<Fluid, FlowingFluid> FLOWING_MOLTEN_ORANGE_SUGAR = FLUIDS.register("flowing_molten_orange_sugar", MoltenSugarFluid.FlowingOrange::new);
	public static final DeferredHolder<Fluid, FlowingFluid> MOLTEN_ORANGE_SUGAR = FLUIDS.register("molten_orange_sugar", MoltenSugarFluid.SourceOrange::new);
	public static final DeferredHolder<Fluid, FlowingFluid> FLOWING_MOLTEN_YELLOW_SUGAR = FLUIDS.register("flowing_molten_yellow_sugar", MoltenSugarFluid.FlowingYellow::new);
	public static final DeferredHolder<Fluid, FlowingFluid> MOLTEN_YELLOW_SUGAR = FLUIDS.register("molten_yellow_sugar", MoltenSugarFluid.SourceYellow::new);
	public static final DeferredHolder<Fluid, FlowingFluid> FLOWING_MOLTEN_LIME_SUGAR = FLUIDS.register("flowing_molten_lime_sugar", MoltenSugarFluid.FlowingLime::new);
	public static final DeferredHolder<Fluid, FlowingFluid> MOLTEN_LIME_SUGAR = FLUIDS.register("molten_lime_sugar", MoltenSugarFluid.SourceLime::new);
	public static final DeferredHolder<Fluid, FlowingFluid> FLOWING_MOLTEN_GREEN_SUGAR = FLUIDS.register("flowing_molten_green_sugar", MoltenSugarFluid.FlowingGreen::new);
	public static final DeferredHolder<Fluid, FlowingFluid> MOLTEN_GREEN_SUGAR = FLUIDS.register("molten_green_sugar", MoltenSugarFluid.SourceGreen::new);
	public static final DeferredHolder<Fluid, FlowingFluid> FLOWING_MOLTEN_CYAN_SUGAR = FLUIDS.register("flowing_molten_cyan_sugar", MoltenSugarFluid.FlowingCyan::new);
	public static final DeferredHolder<Fluid, FlowingFluid> MOLTEN_CYAN_SUGAR = FLUIDS.register("molten_cyan_sugar", MoltenSugarFluid.SourceCyan::new);
	public static final DeferredHolder<Fluid, FlowingFluid> FLOWING_MOLTEN_LIGHT_BLUE_SUGAR = FLUIDS.register("flowing_molten_light_blue_sugar", MoltenSugarFluid.FlowingLightBlue::new);
	public static final DeferredHolder<Fluid, FlowingFluid> MOLTEN_LIGHT_BLUE_SUGAR = FLUIDS.register("molten_light_blue_sugar", MoltenSugarFluid.SourceLightBlue::new);
	public static final DeferredHolder<Fluid, FlowingFluid> FLOWING_MOLTEN_BLUE_SUGAR = FLUIDS.register("flowing_molten_blue_sugar", MoltenSugarFluid.FlowingBlue::new);
	public static final DeferredHolder<Fluid, FlowingFluid> MOLTEN_BLUE_SUGAR = FLUIDS.register("molten_blue_sugar", MoltenSugarFluid.SourceBlue::new);
	public static final DeferredHolder<Fluid, FlowingFluid> FLOWING_MOLTEN_PURPLE_SUGAR = FLUIDS.register("flowing_molten_purple_sugar", MoltenSugarFluid.FlowingPurple::new);
	public static final DeferredHolder<Fluid, FlowingFluid> MOLTEN_PURPLE_SUGAR = FLUIDS.register("molten_purple_sugar", MoltenSugarFluid.SourcePurple::new);
	public static final DeferredHolder<Fluid, FlowingFluid> FLOWING_MOLTEN_MAGENTA_SUGAR = FLUIDS.register("flowing_molten_magenta_sugar", MoltenSugarFluid.FlowingMagenta::new);
	public static final DeferredHolder<Fluid, FlowingFluid> MOLTEN_MAGENTA_SUGAR = FLUIDS.register("molten_magenta_sugar", MoltenSugarFluid.SourceMagenta::new);
	public static final DeferredHolder<Fluid, FlowingFluid> FLOWING_MOLTEN_PINK_SUGAR = FLUIDS.register("flowing_molten_pink_sugar", MoltenSugarFluid.FlowingPink::new);
	public static final DeferredHolder<Fluid, FlowingFluid> MOLTEN_PINK_SUGAR = FLUIDS.register("molten_pink_sugar", MoltenSugarFluid.SourcePink::new);

	public static final DeferredHolder<Fluid, FlowingFluid> FLOWING_CHOCOLATE_MILK = FLUIDS.register("flowing_chocolate_milk", ChocolateMilkFluid.Flowing::new);
	public static final DeferredHolder<Fluid, FlowingFluid> CHOCOLATE_MILK = FLUIDS.register("chocolate_milk", ChocolateMilkFluid.Source::new);
	
	public static final DeferredHolder<Fluid, FlowingFluid> FLOWING_BLOOD = FLUIDS.register("flowing_blood", BloodFluid.Flowing::new);
	public static final DeferredHolder<Fluid, FlowingFluid> BLOOD = FLUIDS.register("blood", BloodFluid.Source::new);
}