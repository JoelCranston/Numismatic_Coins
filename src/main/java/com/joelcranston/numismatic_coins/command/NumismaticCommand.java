package com.joelcranston.numismatic_coins.command;

import java.util.Collection;

import com.joelcranston.numismatic_coins.currency.CoinMath;
import com.joelcranston.numismatic_coins.purse.Purses;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.LongArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

/**
 * {@code /numismatic balance} shows your own purse. Operators can also get, set, add to or
 * subtract from other players' purses, and see {@code /numismatic serverworth}. (NO: NumismaticCommand.)
 */
public final class NumismaticCommand {

    private static final String PLAYERS_ARGUMENT = "players";
    private static final String AMOUNT_ARGUMENT = "amount";

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
                        .executes(NumismaticCommand::showServerWorth)));
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

    // Brigadier results are ints; large balances are capped rather than wrapped.
    private static int commandResult(long value) {

        return (int) Math.min(value, Integer.MAX_VALUE);
    }

    @FunctionalInterface
    private interface BalanceChange {

        long apply(long balance, long amount);
    }
}
