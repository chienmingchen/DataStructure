import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

abstract class DataStructureADTTest<T extends DataStructureADT<String, String>> {
	
	private T dataStructureInstance;
	
	protected abstract T createInstance();

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		dataStructureInstance = createInstance();
	}

	@AfterEach
	void tearDown() throws Exception {
		dataStructureInstance = null;
	}

	
	@Test
	void test00_empty_ds_size() {
		if (dataStructureInstance.size() != 0)
		fail("data structure should be empty, with size=0, but size="+dataStructureInstance.size());
	}
	
	// TODO: implement tests 01 - 04

	// test01_after_insert_one_size_is_one	
	// insert one key value pair into the data structure and then confirm that size() is 1.
	// Tip: use a call to fail("message string") or assertEquals(leftsidevalue,rightsidevalue) 
	// to get it count as a fail.  If no fail occurs, it counts as a pass.
	@Test
	void test01_after_insert_one_size_is_one() {
		try {
			dataStructureInstance.insert("one", "Mon");		
			assertEquals(dataStructureInstance.size(),1);
		}
		catch(Exception e) {
			fail("Test Fail, Unexpected Exception");
		}
	  }
	
	// test02_after_insert_one_remove_one_size_is_0
	// insert one key,value pair and remove it, then confirm size is 0.
	@Test
	void test02_after_insert_one_remove_one_size_is_0() {
		    
		try{
			dataStructureInstance.insert("two", "Tue");
			assertEquals(dataStructureInstance.size(),1);
	
		    dataStructureInstance.remove("two");
	
		    if (dataStructureInstance.size() != 0)
		    fail("data structure should be empty, with size=0, but size=" + dataStructureInstance.size());
		}
		catch(Exception e){
			fail("Test Fail, Unexpected Exception");
		}
	}
	  
	// test03_duplicate_exception_is_thrown
	// insert a few key,value pairs such that one of them has the same key as an earlier one.  
	// Confirm that a RuntimeException is thrown.
	@Test
	void test03_duplicate_exception_is_thrown() throws Exception {
		  
	    try {
	    	dataStructureInstance.insert("7", "7");
	    	dataStructureInstance.insert("7", "7");
	    	fail("Should throw RuntimeException");
	    } catch (RuntimeException e) {
	        // Through Runtime Exception is expected behavior, not a failure
	    } catch (Exception e) {
	    	fail("Should throw RuntimeException");
	    }
	}

	  
	// test04_remove_returns_false_when_key_not_present
    // insert some key,value pairs, then try removing a key that was not inserted.  
	// Confirm that the return value is false.
	  @Test
	  void test04_remove_returns_false_when_key_not_present() {
		  try{
			  //insert key "1" - "10"
			  for(int i=1; i<=10; i++)
				  dataStructureInstance.insert(String.valueOf(i), String.valueOf(i));
			  
			  //"key "10" is present, should be removed successfully
			  if (!dataStructureInstance.remove("10"))
				  fail("Test Fail");
			  
			  //key"11"-"20" are not preset should not be removed successfully
			  for(int i=11; i<=20; i++)
			  {
				  if (dataStructureInstance.remove("i")) //should not return true
				  fail("Test Fail");
			  }
		  }catch (Exception e) {
		    	fail("Test Fail, Unexpected Exception");
		  }
	  }
	
	// Tip: consider different numbers of inserts and removes and how different combinations of insert and removes
	// TODO: add tests to ensure that you can detect implementation that fail
	  @Test
	  void test05_inser_remove_remove() {	
		  try{
			  for(int i=1; i<=1000; i++)
				  dataStructureInstance.insert(String.valueOf(i), String.valueOf(i));
			  assertEquals(dataStructureInstance.size(),1000);
			  for(int i=1; i<1000; i++)
			  {
				  if(!dataStructureInstance.remove(String.valueOf(i))) {
					  fail("Test Fail, remove fail");
				  }
			  }
			  for(int i=1; i<1000; i++)
			  {
				  if(dataStructureInstance.remove(String.valueOf(i))) {
					  fail("Test Fail, remove problem");
				  }
			  }
					  
		  }catch (Exception e) {
		    	fail("Test Fail, Unexpected Exception");
		  }
	  }

	  //test that a key can be re-added if the key was inserted 
	  //and then removed (this should not be a duplicate)
		  @Test
		  void test06_inser_remove_insert() {	
			  try{	
				  //insert key/value, check get and contains method, check size
				  for(int i=1; i<=500; i++)
				  {
					  dataStructureInstance.insert(String.valueOf(i), String.valueOf(i));
					  if(dataStructureInstance.get(String.valueOf(i)).compareTo(String.valueOf(i))!=0)
						  fail("Test Fail, Cannot get value by key"); 
					  if(!dataStructureInstance.contains(String.valueOf(i)))
						  fail("Test Fail, Cannot find value in contains");
					  assertEquals(dataStructureInstance.size(),i);
				  }
				  //remove key/value, check get and contains method, check size
				  for(int i=1; i<=500; i++)
				  {
					  if(!dataStructureInstance.remove(String.valueOf(i)))
						  fail("Test Fail, remove failed"); 	  
					  if(dataStructureInstance.get(String.valueOf(i))!=null)
						  fail("Test Fail, remove failed"); 
					  if(dataStructureInstance.contains(String.valueOf(i)))
						  fail("Test Fail, remove failed");
					  assertEquals(dataStructureInstance.size(),500-i);
				  }
				//insert key/value again, check get and contains method, check size
				  for(int i=1; i<=500; i++)
				  {
					  dataStructureInstance.insert(String.valueOf(i), String.valueOf(i));
					  if(dataStructureInstance.get(String.valueOf(i)).compareTo(String.valueOf(i))!=0)
						  fail("Test Fail, Cannot get value by key"); 
					  if(!dataStructureInstance.contains(String.valueOf(i)))
						  fail("Test Fail, Cannot find value in contains");
					  assertEquals(dataStructureInstance.size(),i);
				  }
			  }catch (Exception e) {
			    	fail("Test Fail, Unexpected Exception");
			  }
		  }
}
