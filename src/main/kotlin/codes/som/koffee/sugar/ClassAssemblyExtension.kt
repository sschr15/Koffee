package codes.som.koffee.sugar

import codes.som.koffee.ClassAssembly
import codes.som.koffee.MethodAssembly
import codes.som.koffee.modifiers.Modifiers
import codes.som.koffee.sugar.MethodAssemblyExtension.call_super
import codes.som.koffee.types.TypeLike
import org.objectweb.asm.tree.MethodNode
import java.lang.IllegalArgumentException

public object ClassAssemblyExtension {
    /**
     * Creates a new constructor method.
     */
    public fun ClassAssembly.init(access: Modifiers, vararg parameterTypes: TypeLike,
                                  superClass: TypeLike = Object::class, routine: MethodAssembly.() -> Unit): MethodNode {
        if (access.containsOther(public + private + protected + package_private)) {
            throw IllegalArgumentException("Method 'init' has illegal modifiers! " +
                    "Legal modifiers include: public, private, protected, and package private."
            )
        }

        return method(access, "<init>", void, *parameterTypes) {
            call_super(superClass)
            routine()
        }
    }

    /**
     * Creates a class init method.
     */
    public fun ClassAssembly.clinit(routine: MethodAssembly.() -> Unit): MethodNode {
        return method(static, "<clinit>", void, routine = routine)
    }
}
