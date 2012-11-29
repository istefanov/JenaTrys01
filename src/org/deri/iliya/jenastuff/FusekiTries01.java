package org.deri.iliya.jenastuff;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;

public class FusekiTries01 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String q0 = "select ?s ?p ?o where " +
				"{service <http://localhost:3030/baaa/query>" +
				"{?s ?p ?o} " +
				"}";
		Model m = ModelFactory.createDefaultModel();
		QueryExecution qe0 = QueryExecutionFactory.create(q0,m);
//		String q = "select ?s ?p ?o where {?s ?p ?o} limit 10";
//		Query query = QueryFactory.create(q);
//		QueryExecution qe = QueryExecutionFactory.sparqlService("http://localhost:3030/baaa/query",q);
		ResultSet results = qe0.execSelect();
		while(results.hasNext()){
			System.out.println(results.next());
		}
	}
	
	public static void executeSelectOnFusekiServer(){
		String q = "select ?s ?p ?o where {?s ?p ?o} limit 10";
		Query query = QueryFactory.create(q);
		QueryExecution qe = QueryExecutionFactory.sparqlService("http://localhost:3030/baaa/query",q);
		ResultSet results = qe.execSelect();
		while(results.hasNext()){
			System.out.println(results.next());
		}
	}
	
	public static void executeSelectOnRemoteFusekiServer2(){
		String q0 = "select ?s ?p ?o where " +
				"{service <http://localhost:3030/baaa/query>" +
				"{?s ?p ?o} " +
				"}";
		Model m = ModelFactory.createDefaultModel();
		QueryExecution qe0 = QueryExecutionFactory.create(q0,m);
		ResultSet results = qe0.execSelect();
		while(results.hasNext()){
			System.out.println(results.next());
		}
	}

}
