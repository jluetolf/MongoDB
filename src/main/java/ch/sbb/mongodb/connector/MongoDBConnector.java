package ch.sbb.mongodb.connector;

import ch.sbb.mongodb.objects.Customer;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.stereotype.Component;

@Component
public class MongoDBConnector {

  public MongoDBConnector() {

    ConnectionString connString = new ConnectionString(
        "mongodb://localhost:27017"
    );
    MongoClientSettings settings = MongoClientSettings.builder()
                                                      .applyConnectionString(connString)
                                                      .retryWrites(true)
                                                      .build();
    MongoClient mongoClient = MongoClients.create(settings);
    MongoDatabase database = mongoClient.getDatabase("test");

    var collection = database.getCollection("customer");


    FindIterable<Document> iterDoc = collection.find();
    MongoCursor<Document> dbc = iterDoc.iterator();

//    while (dbc.hasNext()) {
//      try {
//        //Product product = mapper.readValue("{\"_id\": {\"$oid\": \"5f3ce53e71ad2232242b3bac\"}, \"name\": \"joerg\", \"price\": 12.99}", Product.class);
//        JsonParser jsonParser = new JsonFactory().createParser(dbc.next().toJson());
//        ObjectMapper mapper = new ObjectMapper();
//
//        Customer customer = mapper.readValue(jsonParser, Customer.class);
//
//      } catch (Exception e) {
//        e.printStackTrace();
//      }
//    }
  }
}
