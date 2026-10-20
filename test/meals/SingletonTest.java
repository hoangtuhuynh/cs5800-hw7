package meals;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import org.junit.jupiter.api.Test;

class SingletonTest {

    @Test
    void abstractFactoryIsASingleton() {
        assertSame(MacronutrientAbstractFactory.getInstance(), MacronutrientAbstractFactory.getInstance());
    }

    @Test
    void eachMacronutrientFactoryIsASingleton() {
        assertSame(CarbFactory.getInstance(), CarbFactory.getInstance());
        assertSame(ProteinFactory.getInstance(), ProteinFactory.getInstance());
        assertSame(FatFactory.getInstance(), FatFactory.getInstance());
    }

    @Test
    void abstractFactoryHandsOutTheSingletonFactories() {
        MacronutrientAbstractFactory abstractFactory = MacronutrientAbstractFactory.getInstance();

        assertSame(CarbFactory.getInstance(), abstractFactory.getFactory(Category.CARBS));
        assertSame(ProteinFactory.getInstance(), abstractFactory.getFactory(Category.PROTEIN));
        assertSame(FatFactory.getInstance(), abstractFactory.getFactory(Category.FATS));
    }

    @Test
    void singletonConstructorsArePrivate() {
        assertOnlyPrivateConstructors(MacronutrientAbstractFactory.class);
        assertOnlyPrivateConstructors(CarbFactory.class);
        assertOnlyPrivateConstructors(ProteinFactory.class);
        assertOnlyPrivateConstructors(FatFactory.class);
    }

    private static void assertOnlyPrivateConstructors(Class<?> type) {
        for (Constructor<?> constructor : type.getDeclaredConstructors()) {
            assertTrue(Modifier.isPrivate(constructor.getModifiers()),
                    type.getSimpleName() + " has a non-private constructor");
        }
    }
}
