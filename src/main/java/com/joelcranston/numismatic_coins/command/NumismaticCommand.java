package com.joelcranston.numismatic_coins.command;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.joelcranston.numismatic_coins.NumismaticCoins;
import com.joelcranston.numismatic_coins.config.ConfigOption;
import com.joelcranston.numismatic_coins.config.ConfigOptions;
import com.joelcranston.numismatic_coins.config.Configs;
import com.joelcranston.numismatic_coins.currency.CoinMath;
import com.joelcranston.numismatic_coins.network.SyncServerConfig;
import com.joelcranston.numismatic_coins.purse.Purses;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.LongArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.SharedSuggestionProvider;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

/**
 * {@code /numismatic balance} shows your own purse. Operators can also get, set, add to or
 * subtract from other players' purses, see {@code /numismatic serverworth}, and read or change the
 * server's options with {@code /numismatic config}. (NO: NumismaticCommand.)
 */
public final class NumismaticCommand {

    private static final String PLAYERS_ARGUMENT = "players";
    private static final String AMOUNT_ARGUMENT = "amount";
    private static final String OPTION_ARGUMENT = "option";
    private static final String VALUE_ARGUMENT = "value";

    private NumismaticCommand() {}

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {

        dispatcher.register(Commands.literal("numismatic")
                .then(Commands.literal("balance")
                        .executes(NumismaticCommand::showOwnBalance)
                        .then(Commands.argument(PLAYERS_ARGUMENT, EntityArgument.players())
                                .requires(Commands.hasPermission(Commands.LEVEL_GAMEMASTERS))
                                .then(Commands.literal("get").executes(NumismaticCommand::showBalances))
                                .then(amountSubcommand("set", (balance, amount) -> amount))
                                .then(amountSubcommand("add", (balance, amount) -> balance + amount))
                                .then(amountSubcommand("subtract", (balance, amount) -> balance - amount))))
                .then(Commands.literal("serverworth")
                        .requires(Commands.hasPermission(Commands.LEVEL_GAMEMASTERS))
                        .executes(NumismaticCommand::showServerWorth))
                .then(Commands.literal("config")
                        .requires(Commands.hasPermission(Commands.LEVEL_GAMEMASTERS))
                        .then(Commands.argument(OPTION_ARGUMENT, StringArgumentType.word())
                                .suggests((context, builder) -> SharedSuggestionProvider.suggest(
                                        ConfigOptions.serverSide().stream().map(ConfigOption::key), builder))
                                .executes(NumismaticCommand::showOption)
                                .then(Commands.argument(VALUE_ARGUMENT, StringArgumentType.word())
                                        .suggests((context, builder) -> SharedSuggestionProvider.suggest(
                                                serverOption(StringArgumentType.getString(context, OPTION_ARGUMENT))
                                                        .map(option -> option.type().suggestions()).orElse(List.of()), builder))
                                        .executes(NumismaticCommand::setOption)))));
    }

    private static int showOwnBalance(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {

        ServerPlayer player = context.getSource().getPlayerOrException();
        long balance = Purses.balance(player);
        long[] coins = CoinMath.split(balance);
        context.getSource().sendSuccess(() -> Component.translatable("chat.numismatic_coins.balance", coins[0], coins[1], coins[2]), false);
        return commandResult(balance);
    }

    private static int showBalances(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {

        long total = 0;
        for (ServerPlayer player : EntityArgument.getPlayers(context, PLAYERS_ARGUMENT)) {
            long balance = Purses.balance(player);
            long[] coins = CoinMath.split(balance);
            context.getSource().sendSuccess(() -> Component.translatable("chat.numismatic_coins.balance.player",
                    player.getDisplayName(), coins[0], coins[1], coins[2]), false);
            total += balance;
        }
        return commandResult(total);
    }

    private static LiteralArgumentBuilder<CommandSourceStack> amountSubcommand(String name, BalanceChange change) {

        Command<CommandSourceStack> command = context -> {
            long amount = LongArgumentType.getLong(context, AMOUNT_ARGUMENT);
            Collection<ServerPlayer> players = EntityArgument.getPlayers(context, PLAYERS_ARGUMENT);
            for (ServerPlayer player : players) {
                Purses.setBalance(player, change.apply(Purses.balance(player), amount));
                long balance = Purses.balance(player);
                context.getSource().sendSuccess(() -> Component.translatable("chat.numismatic_coins.balance_set",
                        player.getDisplayName(), balance), true);
            }
            return players.size();
        };
        return Commands.literal(name).then(Commands.argument(AMOUNT_ARGUMENT, LongArgumentType.longArg(0)).executes(command));
    }

    /** The money in every online player's purse. Offline players are not counted. */
    private static int showServerWorth(CommandContext<CommandSourceStack> context) {

        long total = 0;
        for (ServerPlayer player : context.getSource().getServer().getPlayerList().getPlayers()) {
            total += Purses.balance(player);
        }
        long serverWorth = total;
        context.getSource().sendSuccess(() -> Component.translatable("chat.numismatic_coins.server_worth", serverWorth), false);
        return commandResult(serverWorth);
    }

    private static int showOption(CommandContext<CommandSourceStack> context) {

        String key = StringArgumentType.getString(context, OPTION_ARGUMENT);
        Optional<ConfigOption<?>> option = serverOption(key);
        if (option.isEmpty()) {
            context.getSource().sendFailure(Component.translatable("chat.numismatic_coins.config.unknown", key));
            return 0;
        }
        String value = formattedValue(option.get());
        context.getSource().sendSuccess(() -> Component.translatable("chat.numismatic_coins.config.value", key, value), false);
        return 1;
    }

    /** Sets a server option, saves the file and sends the new settings to everyone online. */
    private static int setOption(CommandContext<CommandSourceStack> context) {

        String key = StringArgumentType.getString(context, OPTION_ARGUMENT);
        String text = StringArgumentType.getString(context, VALUE_ARGUMENT);
        Optional<ConfigOption<?>> option = serverOption(key);
        if (option.isEmpty()) {
            context.getSource().sendFailure(Component.translatable("chat.numismatic_coins.config.unknown", key));
            return 0;
        }
        if (!option.get().setFromText(Configs.local(), text)) {
            context.getSource().sendFailure(Component.translatable("chat.numismatic_coins.config.invalid", text, key));
            return 0;
        }
        Configs.save();
        SyncServerConfig sync = new SyncServerConfig(Configs.server());
        for (ServerPlayer player : context.getSource().getServer().getPlayerList().getPlayers()) {
            NumismaticCoins.xplat().sendToPlayer(player, sync);
        }
        String value = formattedValue(option.get());
        String messageKey = option.get().needsReload() ? "chat.numismatic_coins.config.set_needs_reload" : "chat.numismatic_coins.config.set";
        context.getSource().sendSuccess(() -> Component.translatable(messageKey, key, value), true);
        return 1;
    }

    private static Optional<ConfigOption<?>> serverOption(String key) {

        return ConfigOptions.byKey(key).filter(option -> option.section().isServerSide());
    }

    private static <T> String formattedValue(ConfigOption<T> option) {

        return option.type().format(option.get(Configs.local()));
    }

    // Brigadier results are ints; large balances are capped rather than wrapped.
    private static int commandResult(long value) {

        return (int) Math.min(value, Integer.MAX_VALUE);
    }

    @FunctionalInterface
    private interface BalanceChange {

        long apply(long balance, long amount);
    }
}
