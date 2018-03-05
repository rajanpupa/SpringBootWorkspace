package com.mongo.generic.DataAccessor.controller;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mongo.generic.DataAccessor.model.CommonResponse;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.result.DeleteResult;

@RestController
@RequestMapping("/api/v1/data")
public class DeleteController {
	
	@Autowired
	MongoClient mongoClient;
	

	@DeleteMapping("/{database}/{collection}")
	public ResponseEntity<String> delete(
			@PathVariable("database")String database,
			@PathVariable("collection")String collection,
			@RequestBody List<Document> docs,
			@RequestParam(value="many", required=false, defaultValue="false")Boolean many
			){
		DeleteResult delResult;
		if(many){
			delResult = mongoClient.getDatabase(database).getCollection(collection).deleteOne(docs.get(0));
		}else{
			delResult = mongoClient.getDatabase(database).getCollection(collection).deleteMany(docs.get(0));
		}
		return new ResponseEntity<String>(delResult.getDeletedCount() + " docs deleted", HttpStatus.OK);
	}
	
	@PostMapping(value="/{database}/{collection}", 
			produces=MediaType.APPLICATION_JSON_VALUE,
			consumes=MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<String> create(
			@PathVariable("database")String database,
			@PathVariable("collection")String collection,
			@RequestBody List<Document> docs
			){
		
		mongoClient.getDatabase(database).getCollection(collection).insertMany(docs);
		return new ResponseEntity<String>("{\"result\":\"inserted\"}", HttpStatus.OK);
	}
	
	@GetMapping(value="/{database}/{collection}",
				produces=MediaType.APPLICATION_JSON_VALUE
			)
	public ResponseEntity<CommonResponse> get(
			@PathVariable("database")String database,
			@PathVariable("collection")String collection,
			@RequestParam(value="query", required=false, defaultValue="{}") String query
			){
		
		FindIterable<Document> it = mongoClient.getDatabase(database).getCollection(collection).find(Document.parse(query));
		List<Document> result = new ArrayList<>();
		MongoCursor<Document> cursor = it.iterator();
		
		while(cursor.hasNext()){
			result.add(cursor.next());
		}
		
		CommonResponse response = new CommonResponse();
		response.setValue((Object)result);
		
		return new ResponseEntity<CommonResponse>(response, HttpStatus.OK);
	}
}
