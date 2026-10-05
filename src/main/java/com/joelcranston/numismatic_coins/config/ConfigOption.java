package com.joelcranston.numismatic_coins.config;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * One editable setting, described once for both the config screen and {@code /numismatic config}.
 *
 * @param key          the name in the file and the command, e.g. {@code piggyBanks}
 * @param section      the file section and screen heading it belongs to
 * @param type         how its value is parsed, shown and bounded
 * @param needsReload  whether a change only applies after the world is reloaded
 */
public record ConfigOption<T>(String key, Section section, Type<T> type, boolean needsReload,
                              Function<NumismaticConfig, T> getter, BiConsumer<NumismaticConfig, T> setter) {

    public T get(NumismaticConfig config) {

        return this.getter.apply(config);
    }

    public void set(NumismaticConfig config, T value) {

        this.setter.accept(config, value);
    }

    /** Parses and sets a typed-in value. Returns false, changing nothing, when it does not parse or is out of range. */
    public boolean setFromText(NumismaticConfig config, String text) {

        Optional<T> value = this.type.parse(text);
        value.ifPresent(parsed -> set(config, parsed));
        return value.isPresent();
    }

    public String translationKey() {

        return "config.numismatic_coins." + this.section.key() + "." + this.key;
    }

    public enum Section {
        FEATURES("features", true), MOB_DROPS("mobDrops", true), CLIENT("client", false);

        private final String key;
        private final boolean isServerSide;

        Section(String key, boolean isServerSide) {

            this.key = key;
            this.isServerSide = isServerSide;
        }

        public String key() {

            return this.key;
        }

        /** Server-side sections are the server's to set, and are sent to clients. */
        public boolean isServerSide() {

            return this.isServerSide;
        }

        public String translationKey() {

            return "config.numismatic_coins." + this.key;
        }
    }

    /** A value kind: parsing typed text, and showing a value. */
    public sealed interface Type<T> {

        Optional<T> parse(String text);

        String format(T value);

        /** Every value, for kinds with few enough to cycle through; empty for numbers. */
        default List<T> values() {

            return List.of();
        }

        /** What a command suggests for this kind. */
        default List<String> suggestions() {

            return values().stream().map(this::format).toList();
        }
    }

    public record BooleanType() implements Type<Boolean> {

        @Override
        public Optional<Boolean> parse(String text) {

            return switch (text.toLowerCase(Locale.ROOT)) {
                case "true", "on" -> Optional.of(true);
                case "false", "off" -> Optional.of(false);
                default -> Optional.empty();
            };
        }


        @Override
        public List<Boolean> values() {

            return List.of(true, false);
        }

        @Override
        public String format(Boolean value) {

            return value.toString();
        }
    }

    public record DoubleType(double minimum, double maximum) implements Type<Double> {

        @Override
        public Optional<Double> parse(String text) {

            try {
                double value = Double.parseDouble(text);
                return value >= this.minimum && value <= this.maximum ? Optional.of(value) : Optional.empty();
            } catch (NumberFormatException exception) {
                return Optional.empty();
            }
        }


        @Override
        public String format(Double value) {

            return value.toString();
        }
    }

    public record IntType(int minimum, int maximum) implements Type<Integer> {

        @Override
        public Optional<Integer> parse(String text) {

            try {
                int value = Integer.parseInt(text);
                return value >= this.minimum && value <= this.maximum ? Optional.of(value) : Optional.empty();
            } catch (NumberFormatException exception) {
                return Optional.empty();
            }
        }


        @Override
        public String format(Integer value) {

            return value.toString();
        }
    }

    public record EnumType<E extends Enum<E>>(Class<E> enumClass) implements Type<E> {

        @Override
        public Optional<E> parse(String text) {

            return Arrays.stream(this.enumClass.getEnumConstants())
                    .filter(constant -> constant.name().equalsIgnoreCase(text))
                    .findFirst();
        }


        @Override
        public List<E> values() {

            return List.of(this.enumClass.getEnumConstants());
        }

        @Override
        public String format(E value) {

            return value.name().toLowerCase(Locale.ROOT);
        }
    }
}
