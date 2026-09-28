package com.joelcranston.numismatic_coins.config;

/**
 * Everything in {@code config/numismatic_coins.json}. Plain fields so Gson can fill them: a key
 * missing from the file keeps the default given here. Features and mob drops are the server's
 * settings, sent to each client that joins; the client options only affect the local player.
 */
public final class NumismaticConfig {

    public Features features = new Features();
    public MobDrops mobDrops = new MobDrops();
    public ClientOptions client = new ClientOptions();

    /** Parts of the mod a server can switch off. Off removes the way to get something, never the thing. */
    public static final class Features {

        public boolean piggyBanks = true;
        public boolean shops = true;
        public boolean pawnShops = true;
        public boolean villagerTrades = true;
        public boolean chestLoot = true;
        public boolean mobDrops = true;
        public boolean deathPenalty = true;
    }

    /** Scales the per-mob base values that datapacks give. (NO: NumismaticOverhaulConfigModel.) */
    public static final class MobDrops {

        public double multiplier = 1.0;
        public boolean scaleOnHealth = false;
        public double healthScaleReduction = 1.0;
    }

    /** Where the purse button sits on each screen, as offsets from its usual place, and where money messages show. */
    public static final class ClientOptions {

        public MoneyMessageLocation moneyMessageLocation = MoneyMessageLocation.ACTION_BAR;
        public int inventoryPurseX = 0;
        public int inventoryPurseY = 0;
        public int creativePurseX = 0;
        public int creativePurseY = 0;
        public int merchantPurseX = 0;
        public int merchantPurseY = 0;
    }

    /** The server-side part, as sent to clients. */
    public record ServerSettings(Features features, MobDrops mobDrops) {}

    public enum MoneyMessageLocation {
        ACTION_BAR, CHAT, DISABLED
    }
}
