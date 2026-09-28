package com.charleskorn.kaml

public class MissingTypeTagException(path: YamlPath) : IncorrectTypeException("Value is missing a type tag (eg. !<type>)", path)
