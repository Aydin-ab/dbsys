# dbsys
for the lab assignments


To make the recquired replacements sytems FIFO, LIFO and LRU-K we wrote a class for each of them using the LRU.java class as a template.


For the FIFO :
We used an ArrayList to describe the frames array as it is more practical to insert elements at the beginning or the end which is important there.
We changed the update function in order to make sure that the frames array is sorted on the arrival of the pages in the buffer.
Hence, a new frame is added at the end of the ArrayList frames.
In this way, frames[0] is the first page that arrived, frames[1] the next one etc... whatever the number of time they were pinned and unpinned.
We also changed the pick_victim function so that the replaced frame will be the first element with no pins that we find while reading the ArrayList frames.
We also added an exception BufferPoolExceededException if the buffer has no more room. This is necesseray in order to pass the test 2

For the LIFO :
We almost have the same code as FIFO. All we changed is the update function. The new element is added at the beginning of the ArrayList frames.
In this way, frames[0] is the last page that arrived, frames[1] the penultimate one etc... whatever the number of time they were pinned and unpinned.

For the LRU-K :
???




In order to instantiate and test the replacers we made, we had to modify the BufMgr.java
The BMTest2020 main uses the BufMgr construction function to instantiate the replacer method so we made the modification there, adding 3 more "else if" condition :

else if(replacerArg.compareTo("FIFO")==0)
	  {
	    replacer = new FIFO(this);
	    System.out.println("Replacer: FIFO\n");
	  }
	else if(replacerArg.compareTo("LIFO")==0)
	  {
	    replacer = new LIFO(this);
	    System.out.println("Replacer: LIFO\n");
	  }
    else if(replacerArg.compareTo("LRUK")==0)
	  {
	    replacer = new LRUK(this);
	    System.out.println("Replacer: LRUK\n");
	  }
    
    
In this way we made sure that the replacer was rightfully assigned when the BMTest2020 main use "FIFO" or "LIFO" or "LRUK" as arguments.


