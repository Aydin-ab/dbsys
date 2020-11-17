/* File FIFO.java */

package bufmgr;

import java.util.ArrayList;
import java.util.Collections;


  /**
   * class FIFO is a subclass of class Replacer using LRU
   * algorithm for page replacement
   */
class FIFO extends  Replacer {

  /**
   * private field
   * An array to hold number of frames in the buffer pool. First indexes are the first frames which came.
   */

    private ArrayList<Integer> frames;
 
  /**
   * private field
   * number of frames used
   */   
  private int  nframes;

  /**
   * This put the given frame to the end of the list.
   * @param frameNo	the frame number
   */
  private void update(int frameNo)
  {
    frames.add((Integer) frameNo);
  }

  /**
   * Calling super class the same method
   * Initializing frames with number of buffer allocated
   * by buffer manager
   * set number of frame used to zero
   *
   * @param	mgr	a BufMgr object
   * @see	BufMgr
   * @see	Replacer
   */
    public void setBufferManager( BufMgr mgr )
     {
        super.setBufferManager(mgr);
	int numBuffers = mgr.getNumBuffers();
	frames = new ArrayList<Integer>(Collections.nCopies(numBuffers, 0));

	nframes = 0;
     }

/* public methods */

  /**
   * Class constructor
   * Initializing frames pinter = null.
   */
    public FIFO(BufMgr mgrArg)
    {
      super(mgrArg);
      frames = null;
    }
  
  /**
   * call super class the same method
   * pin the page in the given frame number 
   *
   * @param	 frameNo	 the frame number to pin
   * @exception  InvalidFrameNumberException
   */
 public void pin(int frameNo) throws InvalidFrameNumberException
 {
    super.pin(frameNo);
 }

  /**
   * Finding a free frame in the buffer pool
   * or choosing a page to replace using FIFO policy
   *
   * @return 	return the frame number
   *		return -1 if failed
   */

 public int pick_victim()
 throws BufferPoolExceededException
 {
   int numBuffers = mgr.getNumBuffers();
   int frame;
   // Finding a free frame
    if ( nframes < numBuffers ) {
        frame = nframes++;
        frames.set(frame,frame);
        state_bit[frame].state = Pinned;
        (mgr.frameTable())[frame].pin();
        return frame;
    }
    
    // Choosing a page to replace using FIFO policy. First In First Out. The first in is frames[0].
    for ( int i = 0; i < numBuffers; ++i ) {
         frame = frames.get(i);
        if ( state_bit[frame].state != Pinned ) {
            state_bit[frame].state = Pinned;
            (mgr.frameTable())[frame].pin();
            update(frame);
            return frame;
        }
    }
    
    // If no page available (every pages are pinned). Return an error BufferPoolExceededException
    throw new BufferPoolExceededException(null, "BUFMGR: BUFFER_EXCEEDED.");
 }
 
  /**
   * get the page replacement policy name
   *
   * @return	return the name of replacement policy used
   */  
    public String name() { return "FIFO"; }
 
  /**
   * print out the information of frame usage
   */  
 public void info()
 {
    super.info();

    System.out.print( "FIFO REPLACEMENT");
    
    for (int i = 0; i < nframes; i++) {
        if (i % 5 == 0)
	System.out.println( );
	System.out.print( "\t" + frames.get(i));
        
    }
    System.out.println();
 }
  
}
