package jSpot;

import java.lang.reflect.Field;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Sasa on 12/3/2016.
 */
public class ClassSpotter {
    private String comma = "";

    /**
     * Returns a comma separated list of all the attributes members inside the given class and its parents.
     * That goes for all the parents except the Java root class - Object. Attributes of the implemented
     * interfaces are not shown also.
     *
     * @param clazz is the class which needs to be analyzed
     * @return Map that contains attributes written twice:
     * <li>in a String (as the pair key) of comma-separated attributes, where all collections are surrounded with the
     * brackets ('[' and ']')</li>
     * <li>in a List (as the pair value) to maintain the same sequence, needed for the data extraction</li>
     */
    protected AbstractMap.SimpleImmutableEntry< String, List< String > > getParentAttributes( Class< ? > clazz ) {
        Class< ? > parent = clazz.getSuperclass();
        List< String > attributes = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        while ( parent.getSuperclass() != null ) {
            AbstractMap.SimpleImmutableEntry< String, List< String > > itsAttributes = getAttributes( parent );
            sb.append( itsAttributes.getKey() );
            attributes.addAll( itsAttributes.getValue() );
            parent = parent.getSuperclass();
        }
        return new AbstractMap.SimpleImmutableEntry<>( sb.toString(), attributes );
    }

    /**
     * Returns a comma separated list of all the attributes members inside the given class, but not its parents.
     * Attributes of the implemented interfaces are not shown also.
     * @param clazz is the class which needs to be analyzed
     * @return Map that contains attributes written twice:
     * <li>in a String (as the pair key) of comma-separated attributes, where all collections are surrounded with the
     * brackets ('[' and ']')</li>
     * <li>in a List (as the pair value) to maintain the same sequence, needed for the data extraction</li>
     */
    protected AbstractMap.SimpleImmutableEntry< String, List< String > > getAttributes( Class< ? > clazz ) {
        StringBuilder sb = new StringBuilder();
        List< String > attributes = new ArrayList<>();
        for ( Field f : clazz.getDeclaredFields() ) {
            sb.append( comma );
            comma = ",";
            attributes.add( f.getName() );
            if ( f.getType().isArray() || Collection.class.isAssignableFrom( f.getType() ) ) {
                sb.append( '[' ).append( f.getName() ).append( ']' );
            } else {
                sb.append( f.getName() );
            }
        }
        return new AbstractMap.SimpleImmutableEntry<>( sb.toString(), attributes );
    }

}
