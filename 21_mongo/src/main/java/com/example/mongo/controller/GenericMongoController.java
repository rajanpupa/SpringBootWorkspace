package com.example.mongo.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;

@RestController
@RequestMapping("/v1")
public class GenericMongoController {
	
	@Autowired
	MongoClient mongoClient;

	
	@PostMapping("/db/{db}/coll/{coll}")
	public ResponseEntity<String> insert(
			@PathVariable("db")String db,
			@PathVariable("coll")String collection,
			@RequestBody String jsonString
			){
		
		System.out.println("---- Inside the controller method");
		Document doc =  Document.parse(jsonString);
		
		mongoClient.getDatabase(db).getCollection(collection).insertOne( doc);
		return new ResponseEntity<String>( doc.get("_id").toString(), HttpStatus.CREATED);
		
	}
	
	@GetMapping("/db/{db}/coll/{coll}/")
	public ResponseEntity<List> read(
			@PathVariable("db")String db,
			@PathVariable("coll")String collection,
			@RequestParam("query")String queryStr
			){
		
		System.out.println("---- Inside the controller method");
		Document query =  Document.parse(queryStr);
		
		FindIterable<Document> cursor = mongoClient.getDatabase(db).getCollection(collection).find(query);
		MongoCursor<Document> it = cursor.iterator();
		List<Document> docList = new ArrayList<>();
		
		while(it.hasNext()){
			docList.add(it.next());
		}
		
		return new ResponseEntity<List>( docList, HttpStatus.OK);
	}
	
	@PutMapping("/db/{db}/coll/{coll}/")
	public ResponseEntity<String> update(){
		@PathVariable("db")String db,
		@PathVariable("coll")String coll,
		@RequestBody UpdateRequest updateRequest
	}
		
	
	
}
