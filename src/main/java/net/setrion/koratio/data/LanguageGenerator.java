package net.setrion.koratio.data;

import java.util.function.Supplier;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.data.LanguageProvider;
import net.setrion.koratio.Koratio;
import net.setrion.koratio.registry.KoratioBiomes;
import net.setrion.koratio.registry.KoratioBlocks;
import net.setrion.koratio.registry.KoratioEntityType;
import net.setrion.koratio.registry.KoratioItems;
import net.setrion.koratio.registry.KoratioScrolls;
import net.setrion.koratio.registry.KoratioSoundEvents;
import net.setrion.koratio.scroll.Scroll;

public abstract class LanguageGenerator extends LanguageProvider {

	public LanguageGenerator(PackOutput output, String language) {
		super(output, Koratio.MOD_ID, language);
	}

	@Override
	protected void addTranslations() {
		addMisc();
		addAdvancements();
		addItems();
		addEnchantments();
		addBlocks();
		addBiomes();
		addEntities();
		addSounds();
		addScrolls();
	}
	
	protected abstract void addMisc();
	protected abstract void addAdvancements();
	protected abstract void addItems();
	protected abstract void addEnchantments();
	protected abstract void addBlocks();
	protected abstract void addBiomes();
	protected abstract void addEntities();
	protected abstract void addSounds();
	protected abstract void addScrolls();
	
	public void addBiome(Supplier<? extends ResourceKey<Biome>> key, String name) {
        add(key.get(), name);
    }

    public void add(ResourceKey<Biome> key, String name) {
        add(key.location().toLanguageKey("biome"), name);
    }
    
    public void addSubtitle(Supplier<? extends SoundEvent> key, String name) {
        add(key.get(), name);
    }

    public void add(SoundEvent key, String name) {
        add(key.getLocation().toLanguageKey("subtitles").replaceFirst("koratio.entity.koratio", "koratio.entity"), name);
    }
    
    public void addScroll(Supplier<? extends Scroll> key, String name, String text) {
    	add("scroll."+key.get().getName()+".title", name);
    	add("scroll."+key.get().getName()+".text", text);
    }
    
    public void add(Scroll key, String name, String text) {
    	add("scroll."+key.getName()+".title", name);
    	add("scroll."+key.getName()+".text", text);
    }
	
	public static class English extends LanguageGenerator {

		public English(PackOutput output) {
			super(output, "en_us");
		}
	
		@Override
		protected void addMisc() {
			add("itemGroup.koratio.main", "Koratio");
			add("itemGroup.koratio.fantasia", "Koratio - Fantasia");
			add("itemGroup.koratio.demonicio", "Koratio - Demonicio");
			
			add("scroll.not_readable", "Not Readable");
			add("scroll.readable", "Readable");
			add("scroll.no_data", "This scroll is too damaged to decrypt. It can be destroyed");
			
			add("decrypting_book.power", "Decrypting Power: ");
			add("container.decrypting", "Decrypting");
			add("decrypting.chance", "Chance: ");
		}
		
		@Override
		protected void addAdvancements() {
			add("advancements.fantasia.root.title", "Fantasia");
			add("advancements.fantasia.root.desc", "Discover a new Type of Portal and see where it takes you");
			add("advancements.demonicio.root.title", "Demonicio");
			add("advancements.demonicio.root.desc", "Find a way into the mirror world of Fantasia");
		}
		
		@Override
		protected void addItems() {
			add(KoratioItems.SCROLL.get(), "Scroll");
			add(KoratioItems.DECRYPTING_BOOK.get(), "Decrypting Book");
			add(KoratioItems.BETTER_DECRYPTING_BOOK.get(), "Better Decrypting Book");
			add(KoratioItems.FANTASTIC_DECRYPTING_BOOK.get(), "Fantastic Decrypting Book");
			
			add(KoratioItems.RAW_PANGO.get(), "Raw Pango");
			add(KoratioItems.CRACKED_PANGO.get(), "Cracked Pango");
			add(KoratioItems.SPIKED_PORKCHOP.get(), "Spiked Porkchop");
			add(KoratioItems.COOKED_SPIKED_PORKCHOP.get(), "Cooked Spiked Porkchop");
			add(KoratioItems.PANGO_BOAT.get(), "Pango Boat");
			add(KoratioItems.PANGO_CHEST_BOAT.get(), "Pango Chest Boat");
			add(KoratioItems.RUGONA_BOAT.get(), "Rugona Boat");
			add(KoratioItems.RUGONA_CHEST_BOAT.get(), "Rugona Chest Boat");
			add(KoratioItems.VARESO_BOAT.get(), "Vareso Boat");
			add(KoratioItems.VARESO_CHEST_BOAT.get(), "Vareso Chest Boat");
			
			add(KoratioItems.CHOCOLATE_MILK_BUCKET.get(), "Chocolate Milk Bucket");
			
			add(KoratioItems.RAINBOW_GEM.get(), "Rainbow Gem");
			add(KoratioItems.WITHER_BONE.get(), "Wither Bone");
			
			add(KoratioItems.RED_SUGAR.get(), "Red Sugar");
			add(KoratioItems.BLUE_SUGAR.get(), "Blue Sugar");
			add(KoratioItems.YELLOW_SUGAR.get(), "Yellow Sugar");
			add(KoratioItems.GREEN_SUGAR.get(), "Green Sugar");
			
			add(KoratioItems.RAINBOW_GEM_HELMET.get(), "Rainbow Gem Helmet");
			add(KoratioItems.RAINBOW_GEM_CHESTPLATE.get(), "Rainbow Gem Chestplate");
			add(KoratioItems.RAINBOW_GEM_LEGGINGS.get(), "Rainbow Gem Leggings");
			add(KoratioItems.RAINBOW_GEM_BOOTS.get(), "Rainbow Gem Boots");
			add(KoratioItems.RAINBOW_GEM_SWORD.get(), "Rainbow Gem Sword");
			add(KoratioItems.RAINBOW_GEM_SHOVEL.get(), "Rainbow Gem Shovel");
			add(KoratioItems.RAINBOW_GEM_AXE.get(), "Rainbow Gem Axe");
			add(KoratioItems.RAINBOW_GEM_PICKAXE.get(), "Rainbow Gem Pickaxe");
			add(KoratioItems.RAINBOW_GEM_HOE.get(), "Rainbow Gem Hoe");
			
			add(KoratioItems.BONE_HELMET.get(), "Bone Helmet");
			add(KoratioItems.BONE_CHESTPLATE.get(), "Bone Chestplate");
			add(KoratioItems.BONE_LEGGINGS.get(), "Bone Leggings");
			add(KoratioItems.BONE_BOOTS.get(), "Bone Boots");
			add(KoratioItems.BONE_SWORD.get(), "Bone Sword");
			add(KoratioItems.BONE_SHOVEL.get(), "Bone Shovel");
			add(KoratioItems.BONE_AXE.get(), "Bone Axe");
			add(KoratioItems.BONE_PICKAXE.get(), "Bone Pickaxe");
			add(KoratioItems.BONE_HOE.get(), "Bone Hoe");
			
			add(KoratioItems.WITHER_BONE_HELMET.get(), "Wither Bone Helmet");
			add(KoratioItems.WITHER_BONE_CHESTPLATE.get(), "Wither Bone Chestplate");
			add(KoratioItems.WITHER_BONE_LEGGINGS.get(), "Wither Bone Leggings");
			add(KoratioItems.WITHER_BONE_BOOTS.get(), "Wither Bone Boots");
			add(KoratioItems.WITHER_BONE_SWORD.get(), "Wither Bone Sword");
			add(KoratioItems.WITHER_BONE_SHOVEL.get(), "Wither Bone Shovel");
			add(KoratioItems.WITHER_BONE_AXE.get(), "Wither Bone Axe");
			add(KoratioItems.WITHER_BONE_PICKAXE.get(), "Wither Bone Pickaxe");
			add(KoratioItems.WITHER_BONE_HOE.get(), "Wither Bone Hoe");
			
			add(KoratioItems.UNICORN_CAT_SPAWN_EGG.get(), "Unicorn Cat Spawn Egg");
			add(KoratioItems.FIRE_BAT_SPAWN_EGG.get(), "Fire Bat Spawn Egg");
			add(KoratioItems.ICE_BAT_SPAWN_EGG.get(), "Ice Bat Spawn Egg");
			add(KoratioItems.THUNDER_BAT_SPAWN_EGG.get(), "Thunder Bat Spawn Egg");
			add(KoratioItems.JUMSTEM_SPAWN_EGG.get(), "Jumstem Spawn Egg");
			add(KoratioItems.SPIKY_PIG_SPAWN_EGG.get(), "Spiky Pig Spawn Egg");
			add(KoratioItems.DEMONIC_SOLDIER_SPAWN_EGG.get(), "Demonic Soldier Spawn Egg");
		}
		
		@Override
		protected void addEnchantments() {
		}
		
		@Override
		protected void addBlocks() {
			add(KoratioBlocks.FANTASIA_PORTAL.get(), "Fantasia Portal");
			add(KoratioBlocks.DECRYPTING_TABLE.get(), "Decrypting Table");
			
			add(KoratioBlocks.RAINBOW_ROSE.get(), "Rainbow Rose");
			add(KoratioBlocks.RAINBOW_ALLIUM.get(), "Rainbow Allium");
			add(KoratioBlocks.RAINBOW_LILY_OF_THE_VALLEY.get(), "Rainbow Lily of the Valley");
			add(KoratioBlocks.COOKIE_FLOWER.get(), "Cookie Flower");
			add(KoratioBlocks.WHITE_SUGARGLASS_FLOWER.get(), "White Sugarglass Flower");
			add(KoratioBlocks.BLUE_SUGARGLASS_FLOWER.get(), "Blue Sugarglass Flower");
			add(KoratioBlocks.GREEN_SUGARGLASS_FLOWER.get(), "Green Sugarglass Flower");
			add(KoratioBlocks.YELLOW_SUGARGLASS_FLOWER.get(), "Yellow Sugarglass Flower");
			add(KoratioBlocks.RED_SUGARGLASS_FLOWER.get(), "Red Sugarglass Flower");
			
			add(KoratioBlocks.PANGO_PLANKS.get(), "Pango Planks");
			add(KoratioBlocks.PANGO_LOG.get(), "Pango Log");
			add(KoratioBlocks.STRIPPED_PANGO_LOG.get(), "Stripped Pango Log");
			add(KoratioBlocks.PANGO_WOOD.get(), "Pango Wood");
			add(KoratioBlocks.STRIPPED_PANGO_WOOD.get(), "Stripped Pango Wood");
			add(KoratioBlocks.PANGO_LEAVES.get(), "Pango Leaves");
			add(KoratioBlocks.PANGO_SLAB.get(), "Pango Slab");
			add(KoratioBlocks.PANGO_STAIRS.get(), "Pango Stairs");
			add(KoratioBlocks.PANGO_SAPLING.get(), "Pango Sapling");
			add(KoratioBlocks.PANGO_FENCE.get(), "Pango Fence");
			add(KoratioBlocks.PANGO_SIGN.get(), "Pango Sign");
			add(KoratioBlocks.PANGO_HANGING_SIGN.get(), "Pango Hanging Sign");
			add(KoratioBlocks.PANGO_CHEST.get(), "Pango Chest");
			add(KoratioBlocks.PANGO_BUTTON.get(), "Pango Button");
			add(KoratioBlocks.PANGO_PRESSURE_PLATE.get(), "Pango Pressure Plate");
			add(KoratioBlocks.PANGO_DOOR.get(), "Pango Door");
			add(KoratioBlocks.PANGO_TRAPDOOR.get(), "Pango Trapdoor");
			add(KoratioBlocks.PANGO_FENCE_GATE.get(), "Pango Fence Gate");
			
			add(KoratioBlocks.RUGONA_PLANKS.get(), "Rugona Planks");
			add(KoratioBlocks.RUGONA_LOG.get(), "Rugona Log");
			add(KoratioBlocks.STRIPPED_RUGONA_LOG.get(), "Stripped Rugona Log");
			add(KoratioBlocks.RUGONA_WOOD.get(), "Rugona Wood");
			add(KoratioBlocks.STRIPPED_RUGONA_WOOD.get(), "Stripped Rugona Wood");
			add(KoratioBlocks.RUGONA_LEAVES.get(), "Rugona Leaves");
			add(KoratioBlocks.RUGONA_SLAB.get(), "Rugona Slab");
			add(KoratioBlocks.RUGONA_STAIRS.get(), "Rugona Stairs");
			add(KoratioBlocks.RUGONA_SAPLING.get(), "Rugona Sapling");
			add(KoratioBlocks.RUGONA_FENCE.get(), "Rugona Fence");
			add(KoratioBlocks.RUGONA_SIGN.get(), "Rugona Sign");
			add(KoratioBlocks.RUGONA_HANGING_SIGN.get(), "Rugona Hanging Sign");
			add(KoratioBlocks.RUGONA_CHEST.get(), "Rugona Chest");
			add(KoratioBlocks.RUGONA_BUTTON.get(), "Rugona Button");
			add(KoratioBlocks.RUGONA_PRESSURE_PLATE.get(), "Rugona Pressure Plate");
			add(KoratioBlocks.RUGONA_DOOR.get(), "Rugona Door");
			add(KoratioBlocks.RUGONA_TRAPDOOR.get(), "Rugona Trapdoor");
			add(KoratioBlocks.RUGONA_FENCE_GATE.get(), "Rugona Fence Gate");
			
			add(KoratioBlocks.VARESO_PLANKS.get(), "Vareso Planks");
			add(KoratioBlocks.VARESO_LOG.get(), "Vareso Log");
			add(KoratioBlocks.STRIPPED_VARESO_LOG.get(), "Stripped Vareso Log");
			add(KoratioBlocks.VARESO_WOOD.get(), "Vareso Wood");
			add(KoratioBlocks.STRIPPED_VARESO_WOOD.get(), "Stripped Vareso Wood");
			add(KoratioBlocks.VARESO_LEAVES.get(), "Vareso Leaves");
			add(KoratioBlocks.VARESO_SLAB.get(), "Vareso Slab");
			add(KoratioBlocks.VARESO_STAIRS.get(), "Vareso Stairs");
			add(KoratioBlocks.VARESO_SAPLING.get(), "Vareso Sapling");
			add(KoratioBlocks.VARESO_FENCE.get(), "Vareso Fence");
			add(KoratioBlocks.VARESO_SIGN.get(), "Vareso Sign");
			add(KoratioBlocks.VARESO_HANGING_SIGN.get(), "Vareso Hanging Sign");
			add(KoratioBlocks.VARESO_CHEST.get(), "Vareso Chest");
			add(KoratioBlocks.VARESO_BUTTON.get(), "Vareso Button");
			add(KoratioBlocks.VARESO_PRESSURE_PLATE.get(), "Vareso Pressure Plate");
			add(KoratioBlocks.VARESO_DOOR.get(), "Vareso Door");
			add(KoratioBlocks.VARESO_TRAPDOOR.get(), "Vareso Trapdoor");
			add(KoratioBlocks.VARESO_FENCE_GATE.get(), "Vareso Fence Gate");
			
			add(KoratioBlocks.PURPLE_MUSHROOM.get(), "Purple Mushroom");
			add(KoratioBlocks.PURPLE_MUSHROOM_BLOCK.get(), "Purple Mushroom Block");
			add(KoratioBlocks.GREEN_MUSHROOM.get(), "Green Mushroom");
			add(KoratioBlocks.GREEN_MUSHROOM_BLOCK.get(), "Green Mushroom Block");
			
			add(KoratioBlocks.RAINBOW_GEM_BLOCK.get(), "Block of Rainbow Gem");
			
			add(KoratioBlocks.SUGAR_BLOCK.get(), "Block of Sugar");
			add(KoratioBlocks.RED_SUGAR_BLOCK.get(), "Block of Red Sugar");
			add(KoratioBlocks.BLUE_SUGAR_BLOCK.get(), "Block of Blue Sugar");
			add(KoratioBlocks.YELLOW_SUGAR_BLOCK.get(), "Block of Yellow Sugar");
			add(KoratioBlocks.GREEN_SUGAR_BLOCK.get(), "Block of Green Sugar");
		}
			
		@Override
		protected void addEntities() {
			add(KoratioEntityType.UNICORN_CAT.get(), "Unicorn Cat");
			add(KoratioEntityType.FIRE_BAT.get(), "Fire Bat");
			add(KoratioEntityType.ICE_BAT.get(), "Ice Bat");
			add(KoratioEntityType.THUNDER_BAT.get(), "Thunder Bat");
			add(KoratioEntityType.BOAT.get(), "Boat");
			add(KoratioEntityType.CHEST_BOAT.get(), "Chest Boat");
			add(KoratioEntityType.JUMSTEM.get(), "Jumstem");
			add(KoratioEntityType.MUSHROOM_SPORE.get(), "Mushroom Spore");
			add(KoratioEntityType.SPIKY_PIG.get(), "Spiky Pig");
			add(KoratioEntityType.DEMONIC_SOLDIER.get(), "Demonic Soldier");
		}

		@Override
		protected void addBiomes() {
			add(KoratioBiomes.FANTASIA_FIELDLANDS, "Fantasia Fieldlands");
			add(KoratioBiomes.AMETHYST_FIELDS, "Amethyst Fields");
			add(KoratioBiomes.MUSHROOM_FOREST, "Mushroom Forest");
			add(KoratioBiomes.GILDED_FOREST, "Gilded Forest");
			add(KoratioBiomes.CANDY_CANE_VALLEY, "Candy Cane Valley");
			add(KoratioBiomes.RIVER, "River");
			add(KoratioBiomes.DEPTHS_OF_FANTASIA, "Depths of Fantasia");
			add(KoratioBiomes.SKYLANDS, "Skylands");
			add(KoratioBiomes.NIGHTMARE_FOREST, "Nightmare Forest");
		}

		@Override
		protected void addSounds() {
			add(KoratioSoundEvents.CRYSTALLIZE_AMBIENT.get(), "Crystallize trembles");
			add(KoratioSoundEvents.CRYSTALLIZE_HURT.get(), "Crystallize hurts");
			add(KoratioSoundEvents.CRYSTALLIZE_DEATH.get(), "Crystallize dies");
			
			add(KoratioSoundEvents.SPIKY_PIG_AMBIENT.get(), "Spiky Pig oinks");
			add(KoratioSoundEvents.SPIKY_PIG_HURT.get(), "Spiky Pig hurts");
			add(KoratioSoundEvents.SPIKY_PIG_DEATH.get(), "Spiky Pig dies");
		}

		@Override
		protected void addScrolls() {
			add(KoratioScrolls.A_NEW_WORLD, "A new world", "");
			add(KoratioScrolls.DEMONS, "Demons..", "");
		}
	}
	
	public static class German extends LanguageGenerator {

		public German(PackOutput output) {
			super(output, "de_de");
		}

		@Override
		protected void addMisc() {			
		}
		
		@Override
		protected void addAdvancements() {			
		}
		
		@Override
		protected void addItems() {
		}

		@Override
		protected void addEnchantments() {
		}

		@Override
		protected void addBlocks() {
		}

		@Override
		protected void addBiomes() {
		}

		@Override
		protected void addEntities() {
		}

		@Override
		protected void addSounds() {			
		}

		@Override
		protected void addScrolls() {			
		}
	}
}