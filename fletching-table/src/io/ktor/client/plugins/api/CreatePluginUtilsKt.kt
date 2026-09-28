package io.ktor.client.plugins.api

public fun <PluginConfigT : Any> createClientPlugin(name: String, createConfiguration: () -> Any, body: (ClientPluginBuilder<Any>) -> Unit): ClientPlugin<Any> {
   return new ClientPluginImpl(name, createConfiguration, body);
}

public fun createClientPlugin(name: String, body: (ClientPluginBuilder<Unit>) -> Unit): ClientPlugin<Unit> {
   return createClientPlugin(name, CreatePluginUtilsKt::createClientPlugin$lambda$0, body);
}

fun `createClientPlugin$lambda$0`(): Unit {
   return Unit.INSTANCE;
}
