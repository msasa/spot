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
    public int age;
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
        System.out.println( JSpot.spotTheObject( new Adult() ).getSpotFormat() );
        System.out.println( JSpot.spotTheObject( new Adult() ).getContent() );
        System.out.println( "---" );
        System.out.println( JSpot.spotTheObject( new Adult(), "s" ).getSpotFormat() );
        System.out.println( JSpot.spotTheObject( new Adult(), "s" ).getContent() );
        System.out.println( "---" );
        System.out.println( JSpot.spotTheObject( new Adult(), false ).getSpotFormat() );
        System.out.println( JSpot.spotTheObject( new Adult(), false ).getContent() );
        System.out.println( "---" );
        System.out.println( JSpot.spotTheObject( new Adult(), false, "cst" ).getSpotFormat() );
        System.out.println( JSpot.spotTheObject( new Adult(), false, "cst" ).getContent() );
        System.out.println( "---" );
        System.out.println( JSpot.spotTheClass( Adult.class ).getSpotFormat() );
        System.out.println( JSpot.spotTheClass( Adult.class, false ).getSpotFormat() );
        System.out.println( "---" );
        System.out.println( JSpot.spotTheObjectWithParents( new Adult() ).getContent() );
        System.out.println( JSpot.spotTheObjectWithParents( new Adult(), false ).getContent() );
        System.out.println( "---" );
        System.out.println( JSpot.spotTheClassWithParents( Adult.class, "s"  ).getSpotFormat() );
        System.out.println( JSpot.spotTheClassWithParents( Adult.class, false, "s"  ).getSpotFormat() );
        System.out.println( "---" );
        System.out.println( JSpot.spotTheClassWithParents( Adult.class, false ).getSpotFormat() );
        System.out.println( JSpot.spotTheClassWithParents( Adult.class, false, "bla" ).getSpotFormat() );
    }
}
