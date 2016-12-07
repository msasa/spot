package jspot;

import org.apache.commons.lang3.StringUtils;

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
    private boolean simpleNamed = true;
    private String classPackage = "";
    private String className = "";
    private String spotFormat = "";
    private String alias = "";
    private String content = "";

    /**
     * Can be instantiated just from within the package
     */
    protected SpotClass() {
    }

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

    public String getContent() {
        return content;
    }

    protected void setContent( final String content ) {
        this.content = content;
    }

    public String getFullName() {
        if ( !getClassPackage().isEmpty() ) {
            PACKAGE_SEPARATOR = ".";
        }
        return getClassPackage().concat( PACKAGE_SEPARATOR ).concat( getClassName() );
    }

    public boolean isSimpleNamePrinting() {
        return simpleNamed;
    }

    public SpotClass setSimpleNamePrinting( boolean printSimpleName ) {
        simpleNamed = printSimpleName;
        return this;
    }

    public boolean isFullNamePrinting() {
        return !simpleNamed;
    }

    public SpotClass setFullNamePrinting( boolean printFullName ) {
        simpleNamed = !printFullName;
        return this;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer( isFullNamePrinting() ? getFullName() : getClassName() );

        sb.append( JSpot.CLASS_NAME_SEPARATOR ).append( getSpotFormat() );

        if ( null != getAlias() ) {
            sb.append( JSpot.CLASS_ALIAS_SEPARATOR).append( getAlias() );
        }
        sb.append( StringUtils.LF );

        if ( StringUtils.isEmpty( getContent() ) ) {
            return sb.toString();
        }

        if ( null != getAlias() ) {
            sb.append( getAlias() );
        } else {
            if ( isSimpleNamePrinting() ) {
                sb.append( getClassName() );
            } else {
                sb.append( getFullName() );
            }
        }
        sb.append( JSpot.CLASS_NAME_SEPARATOR ).append( getContent() );
        return sb.toString();
    }
}
