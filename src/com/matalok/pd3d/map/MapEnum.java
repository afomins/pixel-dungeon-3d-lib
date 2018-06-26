/*
 * Pixel Dungeon 3D
 * Copyright (C) 2016-2018 Alex Fomins
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

//------------------------------------------------------------------------------
package com.matalok.pd3d.map;

//------------------------------------------------------------------------------
public class MapEnum {
    //**************************************************************************
    // STATIC
    //**************************************************************************
    public static String GetNameByType(Enum<?> e) {
        return 
          (e instanceof TerrainType)       ? TerrainType.name :
          (e instanceof ItemType)          ? ItemType.name : 
          (e instanceof PlantType)         ? PlantType.name :
          (e instanceof CharType)          ? CharType.name :
          (e instanceof IconType)          ? IconType.name :
          (e instanceof BannerType)        ? BannerType.name :
          (e instanceof DashboardItemType) ? DashboardItemType.name :
          (e instanceof AvatarType)        ? AvatarType.name : 
          (e instanceof ToolbarType)       ? ToolbarType.name :
          (e instanceof StatusPaneType)    ? StatusPaneType.name :
          (e instanceof BuffType)          ? BuffType.name :
          (e instanceof PfxImage)          ? PfxImage.name : null;
    }

    //--------------------------------------------------------------------------
    public static Enum<?> GetTypeByName(String name, int idx) {
        Enum<?>[] values = 
          (name.equals(TerrainType.name)) ? TerrainType.values() :
          (name.equals(ItemType.name)) ? ItemType.values() :
          (name.equals(PlantType.name)) ? PlantType.values() :
          (name.equals(CharType.name)) ? CharType.values() : 
          (name.equals(IconType.name)) ? IconType.values() :
          (name.equals(BannerType.name)) ? BannerType.values() :
          (name.equals(DashboardItemType.name)) ? DashboardItemType.values() :
          (name.equals(AvatarType.name)) ? AvatarType.values() :
          (name.equals(ToolbarType.name)) ? ToolbarType.values() :
          (name.equals(StatusPaneType.name)) ? StatusPaneType.values() :
          (name.equals(BuffType.name)) ? BuffType.values() :
          (name.equals(PfxImage.name)) ? PfxImage.values() : null;
        return (values != null && values.length > idx) ? values[idx] : null;
    }

    //**************************************************************************
    // ENUMS
    //**************************************************************************

    //--------------------------------------------------------------------------
    // See com.watabou.pixeldungeon.levels.Terrain
    public enum TerrainType {
        //----------------------------------------------------------------------
        CHASM,                 EMPTY,             GRASS,                 EMPTY_WELL,            WALL,           // 0
        DOOR,                  OPEN_DOOR,         ENTRANCE,              EXIT,                  EMBERS,         // 5
        LOCKED_DOOR,           PEDESTAL,          WALL_DECO,             BARRICADE,             EMPTY_SP,       // 10
        HIGH_GRASS,            SECRET_DOOR,       TOXIC_TRAP,            SECRET_TOXIC_TRAP,     FIRE_TRAP,      // 15
        SECRET_FIRE_TRAP,      PARALYTIC_TRAP,    SECRET_PARALYTIC_TRAP, INACTIVE_TRAP,         EMPTY_DECO,     // 20
        LOCKED_EXIT,           UNLOCKED_EXIT,     POISON_TRAP,           SECRET_POISON_TRAP,    SIGN,           // 25
        ALARM_TRAP,            SECRET_ALARM_TRAP, LIGHTNING_TRAP,        SECRET_LIGHTNING_TRAP, WELL,           // 30
        STATUE,                STATUE_SP,         GRIPPING_TRAP,         SECRET_GRIPPING_TRAP,  SUMMONING_TRAP, // 35
        SECRET_SUMMONING_TRAP, BOOKSHELF,         ALCHEMY,               CHASM_FLOOR,           CHASM_FLOOR_SP, // 40
        CHASM_WALL,            CHASM_WATER,       TYPE_47,               WATER_TILES,           TYPE_49,        // 45
        TYPE_50,               TYPE_51,           TYPE_52,               TYPE_53,               TYPE_54,        // 50
        TYPE_55,               TYPE_56,           TYPE_57,               TYPE_58,               TYPE_59,        // 55
        TYPE_60,               TYPE_61,           TYPE_62,               WATER,                 TYPE_64;        // 60

        //----------------------------------------------------------------------
        public boolean is_trap, is_trap_visible, is_trap_active;

        //----------------------------------------------------------------------
        private TerrainType() {
            is_trap = name().contains("TRAP");
            if(is_trap) {
                is_trap_visible = !name().contains("SECRET");
                is_trap_active = !name().contains("INACTIVE");
            }
        }

        //----------------------------------------------------------------------
        public static String name = "terrain";
        private static TerrainType[] array = values();
        public static int GetSize() { return array.length; }
        public static TerrainType Get(int idx) { return (idx < 0 || idx >= array.length) ? null : array[idx]; }
        public static int GetIdx(TerrainType map_enum) { return map_enum.ordinal(); }
    };

    //--------------------------------------------------------------------------
    // See com.watabou.pixeldungeon.levels
    //     com.watabou.pixeldungeon.actors.blobs
    public enum TerrainFlags {
        //----------------------------------------------------------------------
        // Terrain
        VISITED, MAPPED, VISIBLE, FOV, PASSABLE, LOS_BLOCKING, FLAMABLE, SECRET, 
        SOLID, AVOID, WATER, PIT, DISCOVERABLE, 

        // Special flag to indicate that terrain should be updated
        PD3D_DIRTY,

        // Blob
        BLOB_ALCHEMY, BLOB_CONFUSION_GAS, BLOB_FIRE, BLOB_FOLIAGE, BLOB_FREEZING, 
        BLOB_PARALYTIC_GAS, BLOB_REGROWTH, BLOB_SACRIFICIAL_FIRE, BLOB_TOXIC_GAS, 
        BLOB_WATER_OF_AWARENESS, BLOB_WATER_OF_HEALTH, BLOB_WATER_OF_TRANSMUTATION, 
        BLOB_WEB, BLOB_WELL_WATER;

        //----------------------------------------------------------------------
        public final int flag;
        private TerrainFlags() {
            flag = (1 << ordinal());
        }
    }

    //--------------------------------------------------------------------------
    // See com.watabou.pixeldungeon.sprites.ItemSpriteSheet
    public enum ItemType {
        //--------------------------------------------------------------------------
        BONES,            ANKH,           SHORT_SWORD,     WAND_MAGIC_MISSILE, RATION,          WEAPON,           ARMOR,           RING,                // 0
        SKELETON_KEY,     IRON_KEY,       GOLDEN_KEY,      CHEST,              LOCKED_CHEST,    TOMB,             GOLD,            SHURIKEN,            // 8
        KNUCKLEDUSTER,    QUARTERSTAFF,   MACE,            DAGGER,             SWORD,           LONG_SWORD,       BATTLE_AXE,      WAR_HAMMER,          // 16
        ARMOR_CLOTH,      ARMOR_LEATHER,  ARMOR_MAIL,      ARMOR_SCALE,        ARMOR_PLATE,     SPEAR,            GLAIVE,          DART,                // 24
        RING_DIAMOND,     RING_OPAL,      RING_GARNET,     RING_RUBY,          RING_AMETHYST,   RING_TOPAZ,       RING_ONYX,       RING_TOURMALINE,     // 32
        SCROLL_KAUNAN,    SCROLL_SOWILO,  SCROLL_LAGUZ,    SCROLL_YNGVI,       SCROLL_GYFU,     SCROLL_RAIDO,     SCROLL_ISAZ,     SCROLL_MANNAZ,       // 40
        WAND_HOLLY,       WAND_YEW,       WAND_EBONY,      WAND_CHERRY,        WAND_TEAK,       WAND_ROWAN,       WAND_WILLOW,     WAND_MAHOGANY,       // 48
        POTION_TURQUOISE, POTION_CRIMSON, POTION_AZURE,    POTION_JADE,        POTION_GOLDEN,   POTION_MAGENTA,   POTION_CHARCOAL, POTION_IVORY,        // 56
        POTION_AMBER,     POTION_BISTRE,  POTION_INDIGO,   POTION_SILVER,      WAND_BAMBOO,     WAND_PURPLEHEART, WAND_OAK,        WAND_BIRCH,          // 64
        RING_EMERALD,     RING_SAPPHIRE,  RING_QUARTZ,     RING_AGATE,         SCROLL_NAUDIZ,   SCROLL_BERKANAN,  SCROLL_ODAL,     SCROLL_TIWAZ,        // 72
        STYLUS,           DEWDROP,        MASTERY,         POUCH,              TORCH,           BEACON,           KIT,             AMULET,              // 80
        SEED_FIREBLOOM,   SEED_ICECAP,    SEED_SORROWMOSS, SEED_DREAMWEED,     SEED_SUNGRASS,   SEED_EARTHROOT,   SEED_FADELEAF,   SEED_ROTBERRY,       // 88
        ARMOR_ROGUE,      ARMOR_WARRIOR,  ARMOR_MAGE,      ARMOR_HUNTRESS,     ROSE,            PICKAXE,          ORE,             SKULL,               // 96
        HOLDER,           CRYSTAL_CHEST,  BOOMERANG,       TOMAHAWK,           INCENDIARY_DART, CURARE_DART,      JAVELIN,         HOLSTER,             // 104
        PASTY,            MEAT,           STEAK,           OVERPRICED,         CARPACCIO,       SPARK_UPGRADE,    SPARK_ENCHANT,   HIDDEN,              // 112
        VIAL,             DUST,           TOKEN,           WEIGHT,             BOMB,            HONEYPOT,         KEYRING,         SMTH;                // 120

        //----------------------------------------------------------------------
        public static String name = "item";
        private static ItemType[] array = values();
        public static int GetSize() { return array.length; }
        public static ItemType Get(int idx) { return (idx < 0 || idx >= array.length) ? null : array[idx]; }
        public static int GetIdx(ItemType type) { return type.ordinal(); }
    };

    //--------------------------------------------------------------------------
    // See all com.matalok.pd3d.Pd3dSprite.RegisterCharSprites()
    public enum CharType {
        //--------------------------------------------------------------------------
        WARRIOR0,       WARRIOR1,      WARRIOR2,      WARRIOR3,      WARRIOR4,      WARRIOR5,      WARRIOR6,        // 0
        MAGE0,          MAGE1,         MAGE2,         MAGE3,         MAGE4,         MAGE5,         MAGE6,           // 7
        ROGUE0,         ROGUE1,        ROGUE2,        ROGUE3,        ROGUE4,        ROGUE5,        ROGUE6,          // 14
        HUNTRESS0,      HUNTRESS1,     HUNTRESS2,     HUNTRESS3,     HUNTRESS4,     HUNTRESS5,     HUNTRESS6,       // 21
        MIR_WARRIOR0,   MIR_WARRIOR1,  MIR_WARRIOR2,  MIR_WARRIOR3,  MIR_WARRIOR4,  MIR_WARRIOR5,  MIR_WARRIOR6,    // 28
        MIR_MAGE0,      MIR_MAGE1,     MIR_MAGE2,     MIR_MAGE3,     MIR_MAGE4,     MIR_MAGE5,     MIR_MAGE6,       // 35
        MIR_ROGUE0,     MIR_ROGUE1,    MIR_ROGUE2,    MIR_ROGUE3,    MIR_ROGUE4,    MIR_ROGUE5,    MIR_ROGUE6,      // 42
        MIR_HUNTRESS0,  MIR_HUNTRESS1, MIR_HUNTRESS2, MIR_HUNTRESS3, MIR_HUNTRESS4, MIR_HUNTRESS5, MIR_HUNTRESS6,   // 49
        ACIDIC,         ALBINO,        BANDIT,        BAT,           BEE,           BLACKSMITH,    BRUTE,           // 56
        BURNING_FIST,   CRAB,          CURSE,         DM300,         ELEMENTAL,     EYE,           FETID_RAT,       // 63
        GHOST,          GNOLL,         GOLEM,         GOO,           IMP,           KING,          LARVA,           // 70
        MIMIC,          MONK,          PIRANHA,       RAT_KING,      RAT,           ROTTING_FIST,  SCORPIO,         // 77
        SENIOR,         SHAMAN,        SHEEP,         SHIELDED,      SHOPKEEPER,    SKELETON,      SPINNER,         // 84
        STATUE,         SUCCUBUS,      SWARM,         TENGU,         THIEF,         UNDEAD,        WANDMAKER,       // 91
        WARLOCK,        WRAITH,        YOG;                                                                         // 98

        //----------------------------------------------------------------------
        public static String name = "char";
        private static CharType[] array = values();
        public static int GetSize() { return array.length; }
        public static CharType Get(int idx) { return (idx < 0 || idx >= array.length) ? null : array[idx]; }
        public static int GetIdx(CharType type) { return type.ordinal(); }
    };

    //--------------------------------------------------------------------------
    // See com.watabou.pixeldungeon.ui.Icons
    public enum IconType {
        //----------------------------------------------------------------------
        SKULL,         BUSY,         COMPASS,  PREFS,      WARNING,         // 0
        TARGET,        WATA,         WARRIOR,  MAGE,       ROGUE,           // 5
        HUNTRESS,      CLOSE,        DEPTH,    SLEEP,      ALERT,           // 10
        SUPPORT,       SUPPORTED,    BACKPACK, SEED_POUCH, SCROLL_HOLDER,   // 15
        WAND_HOLSTER,  KEYRING,      CHECKED,  UNCHECKED,  EXIT,            // 20
        CHALLENGE_OFF, CHALLENGE_ON, RESUME;                                // 25

        //----------------------------------------------------------------------
        public static String name = "icon";
        private static IconType[] array = values();
        public static int GetSize() { return array.length; }
        public static IconType Get(int idx) { return (idx < 0 || idx >= array.length) ? null : array[idx]; }
        public static int GetIdx(IconType type) { return type.ordinal(); }
    };

    //--------------------------------------------------------------------------
    // See com.watabou.pixeldungeon.effects.BannerSprites.Type;
    public enum BannerType {
        //----------------------------------------------------------------------
        PIXEL_DUNGEON, BOSS_SLAIN, GAME_OVER, SELECT_HERO, PIXEL_DUNGEON_SIGNS;

        //----------------------------------------------------------------------
        public static String name = "banner";
        private static BannerType[] array = values();
        static public int GetSize() { return array.length; }
        static public BannerType Get(int idx) { return (idx < 0 || idx >= array.length) ? null : array[idx]; }
        public static int GetIdx(BannerType type) { return type.ordinal(); }
    };

    //--------------------------------------------------------------------------
    // See com.watabou.pixeldungeon.scenes.TitleScene.DashboardItem
    public enum DashboardItemType {
        //----------------------------------------------------------------------
        PLAY, ABOUT, HIGHSCORES, BADGES;

        //----------------------------------------------------------------------
        public static String name = "dashboard-item";
        private static DashboardItemType[] array = values();
        public static int GetSize() { return array.length; }
        public static DashboardItemType Get(int idx) { return (idx < 0 || idx >= array.length) ? null : array[idx]; }
        public static int GetIdx(DashboardItemType type) { return type.ordinal(); }
    };

    //--------------------------------------------------------------------------
    // See com.watabou.pixeldungeon.scenes.SurfaceScene.Avatar
    public enum AvatarType {
        //----------------------------------------------------------------------
        WARRIOR, MAGE, ROGUE, HUNTRESS;

        //----------------------------------------------------------------------
        public static String name = "avatar";
        private static AvatarType[] array = values();
        public static int GetSize() { return array.length; }
        public static AvatarType Get(int idx) { return (idx < 0 || idx >= array.length) ? null : array[idx]; }
        public static int GetIdx(AvatarType type) { return type.ordinal(); }
    };

    //--------------------------------------------------------------------------
    // See com.watabou.pixeldungeon.ui.Toolbar
    public enum ToolbarType {
        //----------------------------------------------------------------------
        WAIT, SEARCH, INFO, INVENTORY, QUICK;

        //----------------------------------------------------------------------
        public static String name = "toolbar";
        private static ToolbarType[] array = values();
        public static int GetSize() { return array.length; }
        public static ToolbarType Get(int idx) { return (idx < 0 || idx >= array.length) ? null : array[idx]; }
        public static int GetIdx(ToolbarType type) { return type.ordinal(); }
    };

    //--------------------------------------------------------------------------
    // See com.watabou.pixeldungeon.ui.StatusPane
    public enum StatusPaneType {
        //----------------------------------------------------------------------
        HERO, MENU, BORDER, DEPTH;

        //----------------------------------------------------------------------
        public static String name = "status-pane";
        private static StatusPaneType[] array = values();
        public static int GetSize() { return array.length; }
        public static StatusPaneType Get(int idx) { return (idx < 0 || idx >= array.length) ? null : array[idx]; }
        public static int GetIdx(StatusPaneType type) { return type.ordinal(); }
    };

    //--------------------------------------------------------------------------
    // See com.watabou.pixeldungeon.ui.BuffIndicator
    public enum BuffType {
        //----------------------------------------------------------------------
        MIND_VISION, LEVITATION, FIRE, POISON, PARALYSIS, HUNGER,
        STARVATION, SLOW, OOZE, AMOK, TERROR, ROOTS,
        INVISIBLE, SHADOWS, WEAKNESS, FROST, BLINDNESS,
        COMBO, FURY, HEALING, ARMOR, HEART,
        LIGHT, CRIPPLE, BARKSKIN, IMMUNITY, BLEEDING,
        MARK, DEFERRED, VERTIGO, RAGE, SACRIFICE;

        //----------------------------------------------------------------------
        public static String name = "buff";
        private static BuffType[] array = values();
        public static int GetSize() { return array.length; }
        public static BuffType Get(int idx) { return (idx < 0 || idx >= array.length) ? null : array[idx]; }
        public static int GetIdx(BuffType type) { return type.ordinal(); }
    };

    //--------------------------------------------------------------------------
    // See com.watabou.pixeldungeon.plants
    public enum PlantType {
        //----------------------------------------------------------------------
        FIREBLOOM, ICECAP, SORROWMOSS, DREAMWEED, SUNGRASS, EARTHROOT, 
        FADELEAF, ROTBERRY;

        //----------------------------------------------------------------------
        public static String name = "plant";
        private static PlantType[] array = values();
        public static int GetSize() { return array.length; }
        public static PlantType Get(int idx) { return (idx < 0 || idx >= array.length) ? null : array[idx]; }
        public static int GetIdx(PlantType type) { return type.ordinal(); }
    };

    //--------------------------------------------------------------------------
    // Image (particles.png)
    public enum PfxImage {
        //----------------------------------------------------------------------
        // Pfx
        PFX_CIRCLE, PFX_SQUARE10, PFX_WEB90, PFX_WEB0, PFX_WEB45, P5, P6, P7,

        // com.watabou.pixeldungeon.effects.Effects.Type
        FX_RIPPLE, FX_LIGHTNING, FX_WOUND, FX_RAY, P12, P13, P14, P15,

        // com.watabou.pixeldungeon.effects.Speck
        SPECK_HEALING, SPECK_STAR, SPECK_LIGHT, SPECK_QUESTION, SPECK_UP, SPECK_SCREAM, SPECK_BONE, SPECK_WOOL, 
        SPECK_ROCK, SPECK_NOTE, SPECK_CHANGE, SPECK_HEART, SPECK_BUBBLE, SPECK_STEAM,  SPECK_COIN, P31,
        P32, P33, P34, P35, P36, P37, P38, P39,

        // com.watabou.pixeldungeon.ui.Icons
        ICON_SLEEP, ICON_ALERT, P42, P43, P44, P45, P46, P47, 

        // com.watabou.pixeldungeon.effects
        SPELL_FOOD, SPELL_MAP, SPELL_CHARGE, SPELL_MASTERY, P52, P53, P54, P55,

        // Misc
        HP, SHADOW;

        //----------------------------------------------------------------------
        public static String name = "pfx-image";
        private static PfxImage[] array = values();
        public static int GetSize() { return array.length; }
        public static PfxImage Get(int idx) { return (idx < 0 || idx >= array.length) ? null : array[idx]; }
        public static int GetIdx(PfxImage type) { return type.ordinal(); }
    };

    //--------------------------------------------------------------------------
    // Misc events
    public enum EventType {
        //----------------------------------------------------------------------
        MARKER_SEARCH,
        CAMERA_SHAKE,

        PFX_EARTH,
        PFX_LEAF,
        PFX_SPEC,
        PFX_FLAME,
        PFX_SPARK;

        //----------------------------------------------------------------------
        public static String name = "event";
        private static EventType[] array = values();
        public static int GetSize() { return array.length; }
        public static EventType Get(int idx) { return (idx < 0 || idx >= array.length) ? null : array[idx]; }
        public static int GetIdx(EventType type) { return type.ordinal(); }
    };
}
