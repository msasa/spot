package jspot.convert;

/**
 * Created by Sasa Milenkovic on 2016/11/30
 * This is the main class which holds main interface for API usage
 */
public class JSpot {

    public static final String CLASS_NAME_SEPARATOR = " ";
    public static final String CLASS_SEPARATOR_LEFT = "<";
    public static final String CLASS_SEPARATOR_RIGHT = ">";
    public static final String CLASS_ALIAS_SEPARATOR = "@";
    public static final String ATTRIBUTES_SEPARATOR = "|";
    public static final String ATTRIBUTES_ARRAY_LEFT_SEPARATOR = "[";
    public static final String ATTRIBUTES_ARRAY_RIGHT_SEPARATOR = "]";
    public static final String CLASS_CONTENT_START = CLASS_SEPARATOR_LEFT;

    public static final String REFERENCE_SYMBOL = "$";
    public static final String REFERENCE_LEFT_SEPARATOR = "{";
    public static final String REFERENCE_RIGHT_SEPARATOR = "}";
    public static final String REFERENCE_START = REFERENCE_SYMBOL.concat( REFERENCE_LEFT_SEPARATOR );

    public static final String ESCAPE_SEQUENCE_LEFT_SEPARATOR = "~";
    public static final String ESCAPE_SEQUENCE_RIGHT_SEPARATOR = "~";

    /**
     * Instances should NOT be constructed.
     */
    private JSpot() {
    }

    /**
     * Converts Java object to the SPOT format. It converts just attributes of the given objects, but not its parents.
     *
     * @param o object to convert
     * @return a string having object's definition in SPOT format
     */
    public static SpotClass spotTheObject( Object o ) {
        return spotTheObject( o, null );
    }

    /**
     * Converts Java object to the SPOT format. It converts attributes of the given object and its parents, but not
     * the root Java Object's attributes. Also assigns an alias reference to the object.
     *
     * @param o     object to convert
     * @param alias alias used as the reference to the object o. Both, name and alias, can be used equally.
     * @return a string having object's definition in SPOT format
     */
    public static SpotClass spotTheObject( Object o, String alias ) {
        SpotClass spotted = spotTheClass( o.getClass(), alias );
        return ObjectSpotter.getAttributesValues( o, spotted );
    }

    /**
     * Converts Java object to SPOT format using simple name. It converts attributes of the given object and its
     * parents, but not the root Java Object's attributes.
     *
     * @param o object to convert
     * @return a string having object's definition in SPOT format
     */
    public static SpotClass spotTheObjectWithParents( Object o ) {
        return spotTheObjectWithParents( o, null );
    }

    /**
     * Converts Java object to SPOT format using simple name. It converts attributes of the given object and its
     * parents, but not the root Java Object's attributes. Also assigns an alias reference to the object.
     *
     * @param o     object to convert
     * @param alias alias used as the reference to the object o. Both, name and alias, can be used equally.
     * @return a string having object's definition in SPOT format
     */
    public static SpotClass spotTheObjectWithParents( Object o, String alias ) {
        SpotClass spotted = spotTheClassWithParents( o.getClass(), alias );
        return ObjectSpotter.getAttributesValues( o, spotted );
    }

    /**
     * Converts Java class to SPOT format using simple name. It converts just attributes of the given class,
     * but not its parents.
     *
     * @param clazz class to convert
     * @return a string having class definition in SPOT format
     */
    public static SpotClass spotTheClass( Class< ? > clazz ) {
        return spotTheClass( clazz, null );
    }

    /**
     * Converts Java class to SPOT format with ability to choose between class simple or full name. It converts
     * just attributes of the given class, but not its parents. Also assigns an alias reference to the class.
     *
     * @param clazz class to convert
     * @param alias alias used as the reference to the class clazz. Both, name and alias, can be used equally.
     * @return a string having class definition in SPOT format
     */
    public static SpotClass spotTheClass( Class< ? > clazz, String alias ) {
        return ( new ClassSpotter() ).getAttributes( clazz, alias );
    }

    /**
     * Converts Java class to SPOT format using simple name. It converts attributes of the given class and its
     * parents, but not the root Java Object's attributes.
     *
     * @param clazz class to convert
     * @return a string having class definition in SPOT format
     */
    public static SpotClass spotTheClassWithParents( Class< ? > clazz ) {
        return spotTheClassWithParents( clazz, null );
    }

    /**
     * Converts Java class to the SPOT format. It converts attributes of the given class and its parents, but not the
     * root Java Object's attributes. Also assigns an alias reference to the class.
     *
     * @param clazz class to convert
     * @param alias alias used as the reference to the class clazz. Both, name and alias, can be used equally.
     * @return a string having object's definition in SPOT format
     */
    public static SpotClass spotTheClassWithParents( Class< ? > clazz, String alias ) {
        return ( new ClassSpotter() ).getParentAttributes( clazz, alias );
    }
}
