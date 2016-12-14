package jspot.convert;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.StringJoiner;

/**
 * Created by Sasa Milenkovic on 2016/12/03.
 * <p>
 * This class encapsulates the logic of creating SPOT format of the given object or class. It uses reflection to do
 * that. It can return the complete list of class attributes respecting all the parents and interfaces, or just
 * attributes specific to the class definition.
 * Since SPOT format is using sequence of appearence as useful data, than all the methods returns both, SPOT format
 * of the class and a list of attributes to represent the sequence.
 */
public class ClassSpotter {
    private final SpotClass spotted = new SpotClass();

    /**
     * Returns a comma separated list of all the attributes members inside the given class and its parents.
     * That goes for all the parents except the Java root class - Object.
     *
     * @param clazz is the class which needs to be analyzed
     * @return SpotClass that contains attributes written twice:
     * <li>in a String (as the pair key) of comma-separated attributes, where all collections are surrounded with the
     * brackets ('[' and ']')</li>
     * <li>in a List (as the pair value) to maintain the same sequence, needed for the data extraction</li>
     */
    protected SpotClass getParentAttributes( Class< ? > clazz, String alias ) {
        if ( null != clazz.getPackage() ) {
            spotted.setClassPackage( clazz.getPackage().getName().trim() );
        } else {
            spotted.setClassPackage( "" );
        }
        if ( null != clazz.getSimpleName() ) {
            spotted.setClassName( clazz.getSimpleName().trim() );
        } else {
            spotted.setClassName( "" );
        }
        while ( clazz.getSuperclass() != null ) {
            getInterfaceAttributes( clazz );
            getAttributes( clazz, alias );
            clazz = clazz.getSuperclass();
        }
        spotted.setSpotFormat( spotTheResultList() );
        return spotted;
    }

    /**
     * Returns a comma separated list of all the attributes members inside the given class, but not its parents.
     * Attributes of the implemented interfaces are not shown also.
     *
     * @param clazz is the class which needs to be analyzed
     * @return Map that contains attributes written twice:
     * <li>in a String (as the pair key) of comma-separated attributes, where all collections are surrounded with the
     * brackets ('[' and ']')</li>
     * <li>in a List (as the pair value) to maintain the same sequence, needed for the data extraction</li>
     */
    protected SpotClass getAttributes( final Class< ? > clazz ) {

        if ( StringUtils.isEmpty( spotted.getClassName() ) ) {
            if ( null != clazz.getPackage() ) {
                spotted.setClassPackage( clazz.getPackage().getName().trim() );
            }
            if ( null != clazz.getSimpleName() ) {
                spotted.setClassName( clazz.getSimpleName().trim() );
            }
        }

        for ( Field f : clazz.getDeclaredFields() ) {
            // allow to add just unique attributes names
            String arrayed = JSpot.ATTRIBUTES_ARRAY_LEFT_SEPARATOR.concat( f.getName() )
                    .concat( JSpot.ATTRIBUTES_ARRAY_RIGHT_SEPARATOR );
            if ( !spotted.getAttributesList().contains( f.getName() )
                    && !spotted.getAttributesList().contains( arrayed ) ) {
                if ( f.getType().isArray() || Collection.class.isAssignableFrom( f.getType() ) ) {
                    spotted.getAttributesList().add( arrayed );
                } else {
                    spotted.getAttributesList().add( f.getName() );
                }
            }
        }
        spotted.setSpotFormat( spotTheResultList() );
        return spotted;
    }

    protected SpotClass getAttributes( final Class< ? > clazz, String alias ) {
        getAttributes( clazz );
        spotted.setAlias( alias );
        return spotted;
    }

    /**
     * Uset to get attributes from interfaces of the given class.
     *
     * @param clazz is the class which needs to be analyzed
     */
    private void getInterfaceAttributes( final Class< ? > clazz ) {
        for ( Class ifc : clazz.getInterfaces() ) {
            getAttributes( ifc );
        }
    }

    /**
     * Used to pack attributes list in a string where elements are separated by @see{JSpot.ATTRIBUTES_SEPARATOR}
     *
     * @return
     */
    private String spotTheResultList() {
        if ( !spotted.getAttributesList().isEmpty() ) {
            StringJoiner spot = new StringJoiner( JSpot.ATTRIBUTES_SEPARATOR, JSpot.CLASS_CONTENT_START,
                    JSpot.CLASS_SEPARATOR_RIGHT );
            for ( String el : spotted.getAttributesList() ) {
                spot.add( el );
            }
            return spot.toString();
        }
        return "";
    }
}
