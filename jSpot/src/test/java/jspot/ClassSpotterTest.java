package jspot;

import java.util.HashMap;
import java.util.List;

interface Person {
    float height = 2.02f;
    Character gender = 'M';
    Person[] siblings = null;
    List< Adult > parents = null;
}

abstract class AbstractPerson implements Person {

    boolean naturallyConcieved;

    public String getSuite() {
        return "Grey Suite";
    }
}

class Adult extends AbstractPerson {
    public int age = 40;
    public List< Person > children;
    public HashMap< String, String > address;
}

/**
 * Created by Sasa on 2016/11/30
 */
public class ClassSpotterTest extends Adult {
    public float javni;
    protected int zasticeni;
    double nema;
    char gender;
    private String privatni;

    public ClassSpotterTest() {}
    void nekiMetod() {}

    public static void main( String[] args ) {
        System.out.println( JSpot.spotTheObject( new ClassSpotterTest() ));
        System.out.println( "---" );
        System.out.println( JSpot.spotTheObject( new ClassSpotterTest() ).getSpotFormat() );
        System.out.println( JSpot.spotTheObject( new ClassSpotterTest() ).getContent() );
        System.out.println( "---" );
        System.out.println( JSpot.spotTheObject( new ClassSpotterTest(), "s" ).setFullNamePrinting( true ) );
        System.out.println( "---" );
        System.out.println( JSpot.spotTheObject( new ClassSpotterTest(), "s" ).getSpotFormat() );
        System.out.println( JSpot.spotTheObject( new ClassSpotterTest(), "s" ).getContent() );
        System.out.println( "---" );
        System.out.println( JSpot.spotTheObjectWithParents( new ClassSpotterTest(), "cst" ).getSpotFormat() );
        System.out.println( JSpot.spotTheObjectWithParents( new ClassSpotterTest(), "cst" ).getContent() );
        System.out.println( "---" );
        System.out.println( JSpot.spotTheObjectWithParents( new ClassSpotterTest(), "cst" ) );
        System.out.println( "---" );
        System.out.println( JSpot.spotTheClass( ClassSpotterTest.class ) );
        System.out.println( JSpot.spotTheClass( ClassSpotterTest.class ).getSpotFormat() );
        System.out.println( "---" );
        System.out.println( JSpot.spotTheClassWithParents( ClassSpotterTest.class, "sasa" ).getSpotFormat() );
        System.out.println( JSpot.spotTheClassWithParents( ClassSpotterTest.class, "sasa" ) );
    }
}
