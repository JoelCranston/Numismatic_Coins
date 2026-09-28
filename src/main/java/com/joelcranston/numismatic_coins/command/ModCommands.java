package com.joelcranston.numismatic_coins.command;

import com.joelcranston.numismatic_coins.NumismaticCoins;

public final class ModCommands {

    private ModCommands() {}

    public static void register() {

        NumismaticCoins.xplat().registerCommands(NumismaticCommand::register);
    }
}
