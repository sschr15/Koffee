package codes.som.koffee.sugar

import codes.som.koffee.ClassAssembly
import codes.som.koffee.MethodAssembly
import codes.som.koffee.insns.jvm.aload_0
import codes.som.koffee.insns.jvm.invokespecial
import codes.som.koffee.types.TypeLike

context(ClassAssembly, MethodAssembly)
public fun call_super(
    superClass: TypeLike? = this@ClassAssembly.superClass,
    vararg parameterTypes: TypeLike,
    name: String = "<init>",
    returnType: TypeLike = this@MethodAssembly.void,
) {
    if (superClass != null) {
        // not Object, therefore call the super constructor
        aload_0
        invokespecial(superClass, name, returnType, *parameterTypes)
    } // else, Object has no super constructor therefore do nothing
}