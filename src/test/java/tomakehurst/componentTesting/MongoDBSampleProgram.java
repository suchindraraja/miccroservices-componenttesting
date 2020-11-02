package tomakehurst.componentTesting;

import java.util.Iterator;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class MongoDBSampleProgram
{
	public static void main(String[] args)
	{
		MongoClient mc=new MongoClient("localhost",27017);  
	    MongoDatabase db=mc.getDatabase("kalamdb"); 
	    MongoCollection<Document> col=db.getCollection("holidays");
	    //Get count of documents in a collection
	    System.out.println("Count of documents in that collection is "+col.count());
	    //Get first document and display
	    Document doc1=col.find().first();
	    System.out.println(doc1.toJson());
	    //Get all documents and display
	    FindIterable<Document> l1=col.find();
		Iterator i1=l1.iterator();
		while(i1.hasNext()) 
		{
			System.out.println(i1.next());
		}
	    //Get documents by using filters(like "where" clause in SQL) and display
		FindIterable<Document> l2=col.find(Filters.and(Filters.eq("year","2020"),Filters.regex("cc","CA")));
		Iterator i2=l2.iterator();
		while(i2.hasNext()) 
		{
			System.out.println(i2.next());
		}
	    //Get first document in matched documents with given filters
		Document doc2=col.find(Filters.and(Filters.eq("year","2020"),Filters.regex("cc","CA"))).first();
		System.out.println(doc2.toJson());
	}

}
