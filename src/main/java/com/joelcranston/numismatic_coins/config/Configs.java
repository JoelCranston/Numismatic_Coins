package com.joelcranston.numismatic_coins.config;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.joelcranston.numismatic_coins.NumismaticCoins;
import org.jspecify.annotations.Nullable;

/**
 * Loads and saves the config file, and holds the settings in force. A client connected to a server
 * uses that server's features and mob drops for what it shows, and its own client options.
 */
public final class Configs {

    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().serializeSpecialFloatingPointValues().create();

    private static final String FILE_NAME = NumismaticCoins.MOD_ID + ".json";

    private static NumismaticConfig local = new NumismaticConfig();
    // The joined server's settings; only ever set on a client.
    private static NumismaticConfig.@Nullable ServerSettings fromServer;

    private Configs() {}

    /** Reads the file, then writes it back so missing or new keys appear with their defaults. */
    public static void load() {

        Path path = path();
        if (Files.exists(path)) {
            try (Reader reader = Files.newBufferedReader(path)) {
                NumismaticConfig read = GSON.fromJson(reader, NumismaticConfig.class);
                if (read != null) local = withMissingSectionsFilled(read);
            } catch (IOException | JsonParseException exception) {
                NumismaticCoins.LOGGER.error("Could not read {}, using the defaults", path, exception);
                return;
            }
        }
        save();
    }

    public static void save() {

        Path path = path();
        try {
            Files.createDirectories(path.getParent());
            try (Writer writer = Files.newBufferedWriter(path)) {
                GSON.toJson(local, writer);
            }
        } catch (IOException exception) {
            NumismaticCoins.LOGGER.error("Could not write {}", path, exception);
        }
    }

    /** This side's own config: the one the file holds and the screen edits. */
    public static NumismaticConfig local() {

        return local;
    }

    /** The settings a server acts on: always its own. */
    public static NumismaticConfig.ServerSettings server() {

        return new NumismaticConfig.ServerSettings(local.features, local.mobDrops);
    }

    /** The settings a client shows: the joined server's when it has sent them, else its own. */
    public static NumismaticConfig.ServerSettings shown() {

        return fromServer != null ? fromServer : server();
    }

    public static NumismaticConfig.ClientOptions client() {

        return local.client;
    }

    /** Whether a feature is on for this server, by its key in the features section. Unknown keys read as off. */
    public static boolean isFeatureEnabled(String key) {

        return ConfigOptions.byKey(key)
                .filter(option -> option.section() == ConfigOption.Section.FEATURES)
                .map(option -> Boolean.TRUE.equals(option.get(local)))
                .orElse(false);
    }

    public static void receiveFromServer(NumismaticConfig.ServerSettings settings) {

        fromServer = settings;
    }

    // A file with a whole section missing reads that section as null.
    private static NumismaticConfig withMissingSectionsFilled(NumismaticConfig config) {

        if (config.features == null) config.features = new NumismaticConfig.Features();
        if (config.mobDrops == null) config.mobDrops = new NumismaticConfig.MobDrops();
        if (config.client == null) config.client = new NumismaticConfig.ClientOptions();
        return config;
    }

    private static Path path() {

        return NumismaticCoins.xplat().configDir().resolve(FILE_NAME);
    }
}
