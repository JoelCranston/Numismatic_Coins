package com.charleskorn.kaml

public class EmptyYamlDocumentException(message: String, path: YamlPath) : YamlException(message, path, null, 4)
