package net.peanuuutz.tomlkt

import java.lang.annotation.Documented
import java.lang.annotation.RetentionPolicy

@Retention(AnnotationRetention.BINARY)
@MustBeDocumented
@Documented
@java.lang.annotation.Retention(RetentionPolicy.CLASS)
@RequiresOptIn(message = "This type should only be subclassed by tomlkt internally")
annotation class TomlSpecific(

)
