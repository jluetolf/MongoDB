
package ch.sbb.mongodb.client;
/**
 * A class encapsulating Access to a MongoDB database instance, providing CRUD access.
 */

import com.mongodb.BasicDBObject;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DocumentDbFacade {

  @Value("${spring.data.mongodb.database}")
  protected String dbName;

  private static final Logger LOGGER = LoggerFactory.getLogger(DocumentDbFacade.class);

  private MongoDatabase db = null;

  public DocumentDbFacade(String connURL) {
    ConnectionString connString = new ConnectionString(connURL);
    MongoClientSettings settings = MongoClientSettings.builder()
                                                      .applyConnectionString(connString).retryWrites(false).build();
    MongoClient mongoClient = MongoClients.create(settings);
    db = mongoClient.getDatabase("test_db");
    LOGGER.info("Connected to MongoDatabase with " + connString.toString());
  }

  private DocumentDbFacade() {
    // not to be used.
  }

  public List<String> getCollections() {
    MongoIterable<String> collectionNames = db.listCollectionNames();
    List<String> stringList = toList(collectionNames);
    LOGGER.debug("Found these collections in the database:");
    for (String coll : stringList) {
      LOGGER.debug("  - " + coll);
    }
    return stringList;
  }

  public List<Document> getDocuments(String collectionName, String fieldName, String findRegex) {
    MongoCollection<Document> collection = db.getCollection(collectionName);
    BasicDBObject regexQuery = new BasicDBObject();
    regexQuery.put(fieldName, new BasicDBObject("$regex", findRegex));
    FindIterable<Document> documents = collection.find(regexQuery);
    List<Document> documentList = toList(documents);
    LOGGER.debug("Found " + documentList.size() + " documents in collection '" + collectionName
        + "' for field '" + fieldName + "' matching '" + findRegex + "'.");
    for (Document doc : documentList) {
      LOGGER.debug("  - " + doc);
    }
    return documentList;
  }

  public void putDocument(String collectionName, Map<String, Object> map) {
    MongoCollection<Document> collection = db.getCollection(collectionName);
    collection.insertOne(new Document(map));
    LOGGER.debug("Inserted " + map + " into collection '" + collectionName + "'.");
  }

  public void putDocuments(String collectionName, List<Map<String, Object>> listOfMaps) {
    MongoCollection<Document> collection = db.getCollection(collectionName);
    List<Document> listOfDocuments = new ArrayList<Document>();
    for (Map<String, Object> map : listOfMaps) {
      listOfDocuments.add(new Document(map));
    }
    collection.insertMany(listOfDocuments);
    LOGGER.debug("Inserted " + listOfDocuments.size() + " documents into collection '" + collectionName + "'.");
  }

  private <T> List<T> toList(Iterable<T> iterable) {
    List<T> list = new LinkedList<T>();
    Iterator<T> iterator = iterable.iterator();
    while (iterator.hasNext()) {
      list.add(iterator.next());
    }
    return list;
  }

  public MongoDatabase getDB() {
    return db;
  }
}
