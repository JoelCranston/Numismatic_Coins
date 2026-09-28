package kotlinx.serialization.internal

import kotlin.reflect.KClass
import kotlinx.serialization.SerializationException

@JvmName(name = "throwSubtypeNotRegistered")
internal fun throwSubtypeNotRegistered(subClassName: String?, baseClass: KClass<*>): Nothing {
   val scope: java.lang.String = "in the polymorphic scope of '${baseClass.getSimpleName()}'";
   throw new SerializationException(
      if (subClassName == null)
         "Class discriminator was missing and no default serializers were registered $scope."
         else
         "Serializer for subclass '$subClassName' is not found $scope.\nCheck if class with serial name '$subClassName' exists and serializer is registered in a corresponding SerializersModule.\nTo be registered automatically, class '$subClassName' has to be '@Serializable', and the base class '${baseClass.getSimpleName()}' has to be sealed and '@Serializable'."
   );
}

@JvmName(name = "throwSubtypeNotRegistered")
internal fun throwSubtypeNotRegistered(subClass: KClass<*>, baseClass: KClass<*>): Nothing {
   var var10000: java.lang.String = subClass.getSimpleName();
   if (var10000 == null) {
      var10000 = java.lang.String.valueOf(subClass);
   }

   throwSubtypeNotRegistered(var10000, baseClass);
   throw new KotlinNothingValueException();
}
