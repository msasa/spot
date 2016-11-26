package jSpot;

import java.io.File;

/**
 * This is the main class which holds main interface for API usage
 */
public class jSpot {
	
	private static jSpot instance = null;
	
	private jSpot(){}
	
	public static jSpot initDefinitions( final File dataDefinition ){
		if ( instance == null ){
			synchronized (instance){
				if (instance == null){
					instance = new jSpot();
				}
			}
		}
		return instance;
	}
}
