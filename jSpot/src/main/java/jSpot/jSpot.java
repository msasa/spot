package jSpot;

/**
 * This is the main class which holds main interface for API usage
 */
public class jSpot {

    /*
     * Instances should NOT be constructed.
     */
    private jSpot() {
    }

    /**
     * Converts Java object to SPOT format using simple name. It converts just attributes of the given objects,
     * but not its parents.
     *
     * @param o object to convert
     * @return a string having object's definition in SPOT format
     */
    public static String spotTheObject(Object o) {
        return spotTheClass(o.getClass(), true);
    }

    /**
     * Converts Java object to SPOT format with ability to choose between object's simple or full name.  It converts
     * attributes of the given object and its parents, but not the root Java Object's attributes.
     *
     * @param o          object to convert
     * @param simpleName true or false, where false prepends package name to the simple name
     * @return a string having object's definition in SPOT format
     */
    public static String spotTheObject(Object o, boolean simpleName) {
        return spotTheClass(o.getClass(), simpleName);
    }

    /**
     * Converts Java object to SPOT format using simple name. It converts attributes of the given object and its
     * parents, but not the root Java Object's attributes.
     *
     * @param o object to convert
     * @return a string having object's definition in SPOT format
     */
    public static String spotTheObjectWithParents(Object o) {
        return spotTheClassWithParents(o.getClass(), true);
    }

    /**
     * Converts Java object to SPOT format with ability to choose between object's simple or full name. It converts
     * attributes of the given object and its parents, but not the root Java Object's attributes.
     *
     * @param o          object to convert
     * @param simpleName true or false, where false prepends package name to the simple name
     * @return a string having object's definition in SPOT format
     */
    public static String spotTheObjectWithParents(Object o, boolean simpleName) {
        return spotTheClassWithParents(o.getClass(), simpleName);
    }

    /**
     * Converts Java class to SPOT format using simple name. It converts just attributes of the given class,
     * but not its parents.
     *
     * @param clazz class to convert
     * @return a string having class definition in SPOT format
     */
    public static String spotTheClass(Class<?> clazz) {
        return spotTheClass(clazz, true);
    }

    /**
     * Converts Java class to SPOT format with ability to choose between class simple or full name. It converts
     * just attributes of the given class, but not its parents.
     *
     * @param clazz      class to convert
     * @param simpleName true or false, where false prepends package name to the simple name
     * @return a string having class definition in SPOT format
     */
    public static String spotTheClass(Class<?> clazz, boolean simpleName) {
        if (simpleName) {
            return String.format("%s:<%s>", clazz.getSimpleName(), new ClassSpotter().getAttributes(clazz).getKey());
        }
        return String.format("%s:<%s>", clazz.getName(), new ClassSpotter().getAttributes(clazz).getKey());
    }

    /**
     * Converts Java class to SPOT format using simple name. It converts attributes of the given class and its
     * parents, but not the root Java Object's attributes.
     *
     * @param clazz class to convert
     * @return a string having class definition in SPOT format
     */
    public static String spotTheClassWithParents(Class<?> clazz) {
        return spotTheClassWithParents(clazz, true);
    }

    /**
     * Converts Java class to SPOT format with ability to choose between class simple or full name.  It converts
     * attributes of the given class and its parents, but not the root Java Object's attributes.
     *
     * @param clazz      object to convert
     * @param simpleName true or false, where false prepends package name to the simple name
     * @return a string having object's definition in SPOT format
     */
    public static String spotTheClassWithParents(Class<?> clazz, boolean simpleName) {
        if (simpleName) {
            return String.format("%s:<%s>", clazz.getSimpleName(), new ClassSpotter().getParentAttributes(clazz).getKey());
        }
        return String.format("%s:<%s>", clazz.getName(), new ClassSpotter().getParentAttributes(clazz).getKey());
    }

}
