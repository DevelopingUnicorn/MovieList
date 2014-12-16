
package at.lib.mediainfo;


import com.sun.jna.Platform;


public class MediaInfoException extends RuntimeException {
	
	public MediaInfoException(LinkageError e) {
		this(String.format("Unable to load %d-bit native library 'mediainfo'", Platform.is64Bit() ? 64 : 32), e);
                e.printStackTrace();
	}
	

	public MediaInfoException(String msg, Throwable e) {
		super(msg, e);
                e.printStackTrace();
	}
	
}
