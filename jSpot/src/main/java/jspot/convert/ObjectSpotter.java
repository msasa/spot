package jspot.convert;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import java.util.StringJoiner;

/**
 * Used for getting data out from an object instance.
 * Created by Saša Milenković on 2016/12/5
 */
public class ObjectSpotter {

    /**
     * Gets data from given object's fields with the same name as those in the given list at the same sequence
     *
     * @param obj            Object which, in general case, would have the same fields defined in attributesList
     * @param attributesList sequence of names of fields which would be found inside obj. Arrays and other
     *                       collections have name surrounded with brackets (e.g. [fieldName])
     * @return SPOT format of the given object's data.
     */
    public static String getAttributesValues( Object obj, List< String > attributesList ) {

        StringJoiner values = new StringJoiner( JSpot.ATTRIBUTES_SEPARATOR, JSpot.CLASS_SEPARATOR_LEFT,
                JSpot.CLASS_SEPARATOR_RIGHT );
        for ( String attribute : attributesList ) {
            String fieldName = attribute.trim().replaceFirst( "/^[\\[]/", "" ).replaceFirst( "/[\\]]$/", "" );
            Field field;
            try {
                field = obj.getClass().getDeclaredField( fieldName );
                field.setAccessible( true );
                Object o = field.get( obj );
                if ( null != o ) {
                    if ( field.getType().isArray() ) {
                        for ( Object element: ( Object[] ) o ){
                            values.add( element.toString() );
                        }
                    } else if ( Collection.class.isAssignableFrom( field.getType() ) ){
                        for ( Object element: ( Collection<?> ) o ){
                            values.add( element.toString() );
                        }
                    }
                    else {
                        values.add( o.toString() );
                    }
                } else {
                    values.add( "" );
                }
            } catch ( NoSuchFieldException e ) {
                // If field is not found, then continue with the for loop.
                continue;
            } catch ( IllegalAccessException e ) {
                // This happens when there is a SecurityManager
                e.printStackTrace();
            }
        }
        return values.toString();
    }

    /**
     * Gets data from given object's fields with the same name as those in the given list at the same sequence
     *
     * @param obj             Object which, in general case, would have the same fields defined in attributesList
     * @param classDefinition SpotClass which has attributeList which is sequence of names of fields which would be
     *                        found inside obj. Arrays and other collections have name surrounded with brackets (e.g.
     *                        [fieldName]). The same object is returned, with content replaced.
     * @return SpotClass is the same instance which was given (classDefinition) but with the given object's data in its
     * content field.
     */
    public static SpotClass getAttributesValues( Object obj, SpotClass classDefinition ) {
        classDefinition.setContent( getAttributesValues( obj, classDefinition.getAttributesList() ) );
        return classDefinition;
    }
}
