package net.jselby.escapists.editor.objects;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public enum Objects {
    // Doors
    DOOR(109),
    CELL_DOOR(24),
    OUTSIDE_DOOR(25),
    UTILITY_DOOR(26),
    VENT_UTILITY_DOOR(103),
    KITCHEN_DOOR(27),
    LAUNDRY_DOOR(28),
    JANITOR_DOOR(29),
    STAFF_DOOR(30),
    METALSHOP_DOOR(31),
    LIBRARY_DOOR(37),
    WOODSHOP_DOOR(38),
    MASTER_DOOR(75),
    MAIL_DOOR(83),
    GARDENING_DOOR(84),
    TAILORSHOP_DOOR(85),
    DELIVERIES_DOOR(86),

    // Cells
    PRISONER_BED_VERTICAL(1),
    PRISONER_BED_HORIZONTAL(110),
    PLAYER_BED_VERTICAL(54),
    PLAYER_BED_HORIZONTAL(111),
    PRISONER_DESK(9),
    PLAYER_DESK(55),
    TOILET_RIGHT(3),
    TOILET_LEFT(105),
    TOILET_DOWN(106),
    CABLE_TV(87),

    // Canteen
    CHAIR(2),
    FOOD_TABLE(11),
    DINING_TABLE(13),
    CUTLERY_TABLE(15),

    // Training
    BOOKSHELF(6, 0, -1),
    COMPUTER(69),
    TREADMILL(7),
    JOGGING(90),
    SKIPPING(96),
    SPEEDBAG(98, 0, -1),
    WEIGHT(8),
    PRESSUPS(91),
    PUNCHBAG(97, 0, -1),
    CHINUP(102),

    // Job items
    OVEN(4, 0, -1),
    FREEZER(10),
    WASHING_MACHINE(5, 0, -1),
    DIRTY_LAUNDRY(35),
    CLEAN_LAUNDRY(36),
    TIMBER_CHEST(41),
    PREPARED_TIMBER(42),
    METAL_CHEST(43),
    PREPARED_METAL(44),
    METAL_PRESS(45, 0, -1),
    CLEANING_SUPPLIES(47),
    DELIVERIES_TRUCK_LEFT(78),
    DELIVERIES_TRUCK_RIGHT(108),
    DELIVERIES_RED_BOX(92),
    DELIVERIES_BLUE_BOX(93),
    FABRIC_CHEST(79),
    PREPARED_CLOTHING(80),
    BOOK_CHEST(81),
    MAILROOM_FILE(82),
    GARDENING_TOOLS(89),
    JOB_BOARD(46),

    // Hospital
    MEDICAL_BED(12),
    MEDICAL_SUPPLIES(48),

    // Security
    CONTRABAND_DETECTOR_HORIZONTAL(32),
    CONTRABAND_DETECTOR_VERTICAL(104),
    CAMERA(33),
    MINES(40),
    GUARD_BED(56),
    TOWER_GUARD(57),
    CHECKPOINT_GUARD(62, 0, -1),
    CHECKPOINT(95),
    ROOF_SPOTLIGHTS(60),
    GENERATOR(65),

    // Jeep
    JEEP_RIGHT(63),
    JEEP_DOWN(64),
    JEEP_LEFT(66),
    JEEP_UP(67),

    // AI Waypoints
    GUARD_SHOWERS(14),
    GUARD_ROLLCALL(17),
    GUARD_WANDER(19),
    GUARD_CANTEEN(22),
    GUARD_GYM(39),
    PRISONER_ROLLCALL(16),
    PRISONER_WANDER(18),
    SHOWER(21),
    PRISONER_CANTEEN(23),
    NPC_SPAWN(72),
    JOB_OFFICER(73),
    MEDIC(94),

    // Vents / Roof
    VENT(50),
    VENT_SLATS_HORIZONTAL(51),
    VENT_SLATS_VERTICAL(107),
    LADDER_UP(52),
    LADDER_DOWN(53),

    // Misc.
    SINK(34),
    LIGHT(49),
    SOLITARY_BED(59),
    PRISONER_STASH(61),
    CABINET(68, 0, -1),
    VISITATION_GUEST_SEAT(70),
    VISITATION_PLAYER_SEAT(71),
    PAYPHONE(74),
    SUN_LOUNGER(88),

    // Ziplines
    ZIPLINE_START_UP(76),
    ZIPLINE_START_DOWN(99),
    ZIPLINE_START_RIGHT(100),
    ZIPLINE_START_LEFT(101),
    ZIPLINE_END(77),
    ;

    private final int id;
    private double drawXRelative;
    private double drawYRelative;

    private BufferedImage texture;
    private boolean textureLoaded;

    /**
     * Basic constructor for all objects in a ENUM
     * @param id The ID of the object
     */
    Objects(int id) {
        this.id = id;
    }

    /**
     * Basic constructor for all objects in a ENUM
     * @param id The ID of the object
     */
    Objects(int id, double drawXRelative, double drawYRelative) {
        this.id = id;
        this.drawXRelative = drawXRelative;
        this.drawYRelative = drawYRelative;
    }

    public int getID() {
        return id;
    }

    public double getDrawX() {
        return drawXRelative;
    }

    public double getDrawY() {
        return drawYRelative;
    }

    public BufferedImage getTexture() {
        if (textureLoaded) {
            return texture;
        }

        textureLoaded = true;

        // Load it, if possible
        try {
            texture = ImageIO.read(getClass()
                    .getResource("/objects/" + name().toLowerCase() + ".png"));
        } catch (IllegalArgumentException ignored) {
        } catch (Exception e) {
            e.printStackTrace();
        }

        return texture;
    }

    public WorldObject asWorldObject(int x, int y) {
        return new WorldObject.Unknown(x, y, id, 1);
    }
}