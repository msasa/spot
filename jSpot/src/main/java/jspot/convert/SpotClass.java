package jspot.convert;

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

    private final List< String > attributesList = new CopyOnWriteArrayList<>();
    private String PACKAGE_SEPARATOR = "";
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

    /**
     * Returns class simple name decorated with package name on the left side for omitting package @see {getClassName()}
     * @return package.name.ClassName
     */
    public String getFullName() {
        if ( !getClassPackage().isEmpty() ) {
            PACKAGE_SEPARATOR = ".";
        }
        return getClassPackage().concat( PACKAGE_SEPARATOR ).concat( getClassName() );
    }

    /**
     * Checks if @see{toString()} will return format without package name
     * @return true if it'll print simpleName
     */
    public boolean isSimpleNamePrinting() {
        return simpleNamed;
    }

    /**
     * Set up format of the class name relative to simple name @see{setFullNamePrinting( boolean printSimpleName )}
     * @param printSimpleName switch between simple and full mode for className
     * @return
     */
    public SpotClass setSimpleNamePrinting( boolean printSimpleName ) {
        simpleNamed = printSimpleName;
        return this;
    }

    /**
     * Checks if @see{toString()} will return format with package name
     * @return true if it'll print fullName
     */
    public boolean isFullNamePrinting() {
        return !simpleNamed;
    }

    /**
     * Set up format of the class name relative to full name @see{setSimpleNamePrinting( boolean printSimpleName )}
     * @param printFullName switch between simple and full mode for className
     * @return
     */
    public SpotClass setFullNamePrinting( boolean printFullName ) {
        simpleNamed = !printFullName;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder( isFullNamePrinting() ? getFullName() : getClassName() )
                .append( JSpot.CLASS_NAME_SEPARATOR ).append( getSpotFormat() );

        if ( null != getAlias() ) {
            sb.append( JSpot.CLASS_ALIAS_SEPARATOR ).append( getAlias() );
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
