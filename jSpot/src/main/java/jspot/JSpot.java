package jspot;

import org.apache.commons.lang3.StringUtils;

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
    public static final String CLASS_CONTENT_START = CLASS_NAME_SEPARATOR.concat( CLASS_SEPARATOR_LEFT );

    public static final String REFERENCE_SYMBOL = "$";
    public static final String REFERENCE_LEFT_SEPARATOR = "{";
    public static final String REFERENCE_RIGHT_SEPARATOR = "}";
    public static final String REFERENCE_START = REFERENCE_SYMBOL.concat( REFERENCE_LEFT_SEPARATOR );

    public static final String ESCAPE_SEQUENCE_LEFT_SEPARATOR = "~";
    public static final String ESCAPE_SEQUENCE_RIGHT_SEPARATOR = "~";

    /**
     * Instances should NOT be constructed.
     */
    private JSpot() {}

    /**
     * Converts Java object to SPOT format using simple name. It converts just attributes of the given objects,
     * but not its parents.
     *
     * @param o object to convert
     * @return a string having object's definition in SPOT format
     */
    public static String spotTheObject( Object o ) {
        return spotTheClass( o.getClass(), true, null );
    }

    /**
     * Converts Java object to SPOT format using simple name. It converts just attributes of the given objects,
     * but not its parents.
     *
     * @param o object to convert
     * @param alias alias used as the reference to the object o. Both, name and alias, can be used equally.
     * @return a string having object's definition in SPOT format
     */
    public static String spotTheObject( Object o, String alias ) {
        return spotTheClass( o.getClass(), true, alias );
    }

    /**
     * Converts Java object to SPOT format with ability to choose between object's simple or full name.  It converts
     * attributes of the given object and its parents, but not the root Java Object's attributes.
     *
     * @param o          object to convert
     * @param simpleName true or false, where false prepends package name to the simple name
     * @return a string having object's definition in SPOT format
     */
    public static String spotTheObject( Object o, boolean simpleName ) {
        return spotTheClass( o.getClass(), simpleName, null );
    }

    /**
     * Converts Java object to SPOT format with ability to choose between object's simple or full name.  It converts
     * attributes of the given object and its parents, but not the root Java Object's attributes.
     *
     * @param o          object to convert
     * @param simpleName true or false, where false prepends package name to the simple name
     * @param alias alias used as the reference to the object o. Both, name and alias, can be used equally.
     * @return a string having object's definition in SPOT format
     */
    public static String spotTheObject( Object o, boolean simpleName, String alias ) {
        return spotTheClass( o.getClass(), simpleName, alias );
    }

    /**
     * Converts Java object to SPOT format using simple name. It converts attributes of the given object and its
     * parents, but not the root Java Object's attributes.
     *
     * @param o object to convert
     * @return a string having object's definition in SPOT format
     */
    public static String spotTheObjectWithParents( Object o ) {
        return spotTheClassWithParents( o.getClass(), true, null );
    }

    /**
     * Converts Java object to SPOT format using simple name. It converts attributes of the given object and its
     * parents, but not the root Java Object's attributes.
     *
     * @param o object to convert
     * @param alias alias used as the reference to the object o. Both, name and alias, can be used equally.
     * @return a string having object's definition in SPOT format
     */
    public static String spotTheObjectWithParents( Object o, String alias ) {
        return spotTheClassWithParents( o.getClass(), true, alias );
    }

    /**
     * Converts Java object to SPOT format with ability to choose between object's simple or full name. It converts
     * attributes of the given object and its parents, but not the root Java Object's attributes.
     *
     * @param o          object to convert
     * @param simpleName true or false, where false prepends package name to the simple name
     * @return a string having object's definition in SPOT format
     */
    public static String spotTheObjectWithParents( Object o, boolean simpleName ) {
        return spotTheClassWithParents( o.getClass(), simpleName, null );
    }

    /**
     * Converts Java object to SPOT format with ability to choose between object's simple or full name. It converts
     * attributes of the given object and its parents, but not the root Java Object's attributes.
     *
     * @param o          object to convert
     * @param simpleName true or false, where false prepends package name to the simple name
     * @param alias alias used as the reference to the object o. Both, name and alias, can be used equally.
     * @return a string having object's definition in SPOT format
     */
    public static String spotTheObjectWithParents( Object o, boolean simpleName, String alias ) {
        return spotTheClassWithParents( o.getClass(), simpleName, alias );
    }

    /**
     * Converts Java class to SPOT format using simple name. It converts just attributes of the given class,
     * but not its parents.
     *
     * @param clazz class to convert
     * @return a string having class definition in SPOT format
     */
    public static String spotTheClass( Class< ? > clazz ) {
        return spotTheClass( clazz, true, null );
    }

    /**
     * Converts Java class to SPOT format using simple name. It converts just attributes of the given class,
     * but not its parents.
     *
     * @param clazz class to convert
     * @param simpleName true or false, where false prepends package name to the simple name
     * @return a string having class definition in SPOT format
     */
    public static String spotTheClass( Class< ? > clazz, boolean simpleName ) {
        return spotTheClass( clazz, simpleName, null );
    }

    /**
     * Converts Java class to SPOT format using simple name. It converts just attributes of the given class,
     * but not its parents.
     *
     * @param clazz class to convert
     * @param alias alias used as the reference to the class clazz. Both, name and alias, can be used equally.
     * @return a string having class definition in SPOT format
     */
    public static String spotTheClass( Class< ? > clazz, String alias ) {
        return spotTheClass( clazz, true, alias );
    }

    /**
     * Converts Java class to SPOT format with ability to choose between class simple or full name. It converts
     * just attributes of the given class, but not its parents.
     *
     * @param clazz      class to convert
     * @param simpleName true or false, where false prepends package name to the simple name
     * @param alias alias used as the reference to the class clazz. Both, name and alias, can be used equally.
     * @return a string having class definition in SPOT format
     */
    public static String spotTheClass( Class< ? > clazz, boolean simpleName, String alias ) {
        SpotClass spotted = ( new ClassSpotter() ).getAttributes( clazz, alias );
        String result;
        if ( simpleName ) {
            result = spotted.getClassName().concat( spotted.getSpotFormat() );
        } else {
            result = spotted.getFullName().concat( spotted.getSpotFormat() );
        }
        if ( !StringUtils.isEmpty( spotted.getAlias() ) ) {
            result = result.concat( JSpot.CLASS_ALIAS_SEPARATOR ).concat( spotted.getAlias() );
        }
        return result;

    }

    /**
     * Converts Java class to SPOT format using simple name. It converts attributes of the given class and its
     * parents, but not the root Java Object's attributes.
     *
     * @param clazz class to convert
     * @return a string having class definition in SPOT format
     */
    public static String spotTheClassWithParents( Class< ? > clazz ) {
        return spotTheClassWithParents( clazz, true, null );
    }

    /**
     * Converts Java class to SPOT format using simple name. It converts attributes of the given class and its
     * parents, but not the root Java Object's attributes.
     *
     * @param clazz class to convert
     * @param simpleName true or false, where false prepends package name to the simple name
     * @return a string having class definition in SPOT format
     */
    public static String spotTheClassWithParents( Class< ? > clazz, boolean simpleName ) {
        return spotTheClassWithParents( clazz, simpleName, null );
    }

    /**
     * Converts Java class to SPOT format using simple name. It converts attributes of the given class and its
     * parents, but not the root Java Object's attributes.
     *
     * @param clazz class to convert
     * @param alias alias used as the reference to the class clazz. Both, name and alias, can be used equally.
     * @return a string having class definition in SPOT format
     */
    public static String spotTheClassWithParents( Class< ? > clazz, String alias ) {
        return spotTheClassWithParents( clazz, true, alias );
    }

    /**
     * Converts Java class to SPOT format with ability to choose between class simple or full name.  It converts
     * attributes of the given class and its parents, but not the root Java Object's attributes.
     *
     * @param clazz      object to convert
     * @param simpleName true or false, where false prepends package name to the simple name
     * @param alias alias used as the reference to the class clazz. Both, name and alias, can be used equally.
     * @return a string having object's definition in SPOT format
     */
    public static String spotTheClassWithParents( Class< ? > clazz, boolean simpleName, String alias ) {
        SpotClass spotted = ( new ClassSpotter() ).getParentAttributes( clazz, alias );
        String result;
        if ( simpleName ) {
            result = spotted.getClassName().concat( spotted.getSpotFormat() );
        } else {
            result = spotted.getFullName().concat( spotted.getSpotFormat() );
        }
        if ( !StringUtils.isEmpty( spotted.getAlias() ) ) {
            result = result.concat( JSpot.CLASS_ALIAS_SEPARATOR ).concat( spotted.getAlias() );
        }
        return result;
    }
}
