package io.ktor.client.plugins.api

import io.ktor.client.plugins.HttpClientPlugin

public interface ClientPlugin<PluginConfig> : HttpClientPlugin<PluginConfig, ClientPluginInstance<PluginConfig>>
