package jspot;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Sasa Milenkovic on 2016/12/03.
 * <p>
 * POJO bean representing SPOT format of a Java class. Constructor and all the setters are protected to disallow changes
 * outside of the API.
 */
public class SpotClass {

    private static String PACKAGE_SEPARATOR = "";
    private final List< String > attributesList = new CopyOnWriteArrayList<>();
    private String classPackage = "";
    private String className = "";
    private String spotFormat = "";
    private String alias = "";

    /**
     * Can be instantiated just from within the package
     */
    protected SpotClass() {}

    public String getClassName() {
        return className;
    }

    protected void setClassName( final String className ) {
        this.className = className;
    }

    public String getClassPackage() {
        return classPackage;
    }

    protected void setClassPackage( final String classPackage ) {
        this.classPackage = classPackage;
    }

    public List< String > getAttributesList() {
        return attributesList;
    }

    public String getSpotFormat() {
        return spotFormat;
    }

    protected void setSpotFormat( final String s ) {
        spotFormat = s;
    }

    public String getAlias() {
        return alias;
    }

    protected void setAlias( final String alias ) {
        this.alias = alias;
    }

    public String getFullName() {
        if ( !getClassPackage().isEmpty() ) {
            PACKAGE_SEPARATOR = ".";
        }
        return String.format( "%s%s%s", getClassPackage(), PACKAGE_SEPARATOR, getClassName() );
    }
}
