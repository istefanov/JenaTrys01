package org.deri.iliya.jenastuff;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Iterator;

import org.openjena.atlas.json.io.JSONMaker;
import org.openjena.atlas.lib.StrUtils;

import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.query.Dataset;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.sparql.resultset.JSONOutput;
import com.hp.hpl.jena.sparql.util.NodeFactory;
import com.hp.hpl.jena.tdb.TDBFactory;
import com.hp.hpl.jena.update.GraphStore;
import com.hp.hpl.jena.update.GraphStoreFactory;
import com.hp.hpl.jena.update.UpdateExecutionFactory;
import com.hp.hpl.jena.update.UpdateFactory;
import com.hp.hpl.jena.update.UpdateProcessor;
import com.hp.hpl.jena.update.UpdateRequest;
import com.hp.hpl.jena.util.FileManager;

public class WriteSomeTriples {

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
//		writeSomeTriplesPlease02();
//		readSomeTriples();
//		tryInsertingSomeTriples();
//		tryDeletingSomeTriples();
//		readSomeTriples();
		tryModifyingSomeTriples();
	}

	public static void writeSomeTriplesPlease(){
		String directory = "./datasets1";
		Dataset ds = TDBFactory.createDataset();
		Model m = ds.getDefaultModel();
		m.createLiteral("<bah> <mu> <maikata> .");
		Iterator i = m.listStatements();
		while(i.hasNext()){
			System.out.println(i.next());
		}
		m.close();
	}
	
	public static void writeSomeTriplesPlease02(){
		String directory = "./datasets1";
		Dataset ds = TDBFactory.createDataset(directory);
		Model m = ds.getDefaultModel();
		String source = "./simpleReasoningExample.nt";
		FileManager.get().readModel(m, source, "N-TRIPLES");
		
		String q = "select ?s ?p ?o where {?s ?p ?o} limit 10";
		Query query = QueryFactory.create(q);
		QueryExecution qexec = QueryExecutionFactory.create(query, m);
		ResultSet results = qexec.execSelect();
		while(results.hasNext()){
			System.out.println(results.next());
		}
		
//		String q2 = "PREFIX dc: <http://purl.org/dc/elements/1.1/>\nINSERT { <http://example/egbook3> dc:title  \"This is an example title\" }";
//		QuerySolutionMap initialBinding = new QuerySolutionMap();
//		Query quer2 = QueryFactory.create(q2);
//		QueryExecution qexec2 = QueryExecutionFactory.create(quer2, m);
//		qexec2.execConstruct();
//		m.createLiteral("<bah> <mu> <maikata> .");
//		Iterator i = m.listStatements();
//		while(i.hasNext()){
//			System.out.println(i.next());
//		}
		
		
		m.close();
	}
	
	public static void readSomeTriples(){
		String directory = "./datasets1";
		Dataset ds = TDBFactory.createDataset(directory);
		Model m = ds.getDefaultModel();
//		String source = "./simpleReasoningExample.nt";
//		FileManager.get().readModel(m, source, "N-TRIPLES");
		
		String q = "select ?s ?p ?o where {?s ?p ?o}";
		Query query = QueryFactory.create(q);
		QueryExecution qexec = QueryExecutionFactory.create(query, m);
		ResultSet results = qexec.execSelect();
		while(results.hasNext()){
			System.out.println(results.next());
		}
		m.close();
	}
	
	public static void tryInsertingSomeTriples(){
		String directory = "./datasets1";
		Dataset ds = TDBFactory.createDataset(directory);
		Model m = ds.getDefaultModel();
		
		GraphStore graphStore = GraphStoreFactory.create(ds);
		String sparqlUpdateString = StrUtils.strjoinNL("insert data {<http://www.bahmumaikata.com/pushikurec.owl#Milk> <http://www.bahmumaikata.com/standard#type> <http://kodapishatuka.com#Drink>}");
		
		UpdateRequest request = UpdateFactory.create(sparqlUpdateString);
		UpdateProcessor proc = UpdateExecutionFactory.create(request, graphStore);
		proc.execute();
		
		String q = "select ?s ?p ?o where {?s ?p ?o}";
		Query query = QueryFactory.create(q);
		QueryExecution qexec = QueryExecutionFactory.create(query, m);
		ResultSet results = qexec.execSelect();
		while(results.hasNext()){
			System.out.println(results.next());
		}
		m.close();
	}
	
	public static void tryDeletingSomeTriples(){
		String directory = "./datasets1";
		Dataset ds = TDBFactory.createDataset(directory);
		Model m = ds.getDefaultModel();
		
		GraphStore graphStore = GraphStoreFactory.create(ds);
		String sparqlUpdateString = StrUtils.strjoinNL("delete data {<http://www.bahmumaikata.com/pushikurec.owl#Beer> <http://www.bahmumaikata.com/standard#type> <http://kodapishatuka.com#Drink>}");
		
		UpdateRequest request = UpdateFactory.create(sparqlUpdateString);
		UpdateProcessor proc = UpdateExecutionFactory.create(request, graphStore);
		proc.execute();
		
		String q = "select ?s ?p ?o where {?s ?p ?o}";
		Query query = QueryFactory.create(q);
		QueryExecution qexec = QueryExecutionFactory.create(query, m);
		ResultSet results = qexec.execSelect();
		while(results.hasNext()){
			System.out.println(results.next());
		}
		m.close();
	}
	
	public static void tryModifyingSomeTriples() throws FileNotFoundException{
		String directory = "./datasets1";
		Dataset ds = TDBFactory.createDataset(directory);
		Model m = ds.getDefaultModel();
		
		GraphStore graphStore = GraphStoreFactory.create(ds);
//		Node n = NodeFactory.parseNode("http://www.bahmumaikata.com/pushikurec.owl");
//		System.out.println(graphStore.getGraph(n));
		String sparqlUpdateString = StrUtils.strjoinNL("" +
				"modify delete {<http://www.bahmumaikata.com/pushikurec.owl#Beer> <http://www.bahmumaikata.com/standard#type> <http://kodapishatuka.com#Drink>}" +
				"insert {<http://www.bahmumaikata.com/pushikurec.owl#Whiskey> <http://www.bahmumaikata.com/standard#type> <http://kodapishatuka.com#Drink>}" +
				"where {<http://www.bahmumaikata.com/pushikurec.owl#Beer> <http://www.bahmumaikata.com/standard#type> <http://kodapishatuka.com#Drink>}");
		
		UpdateRequest request = UpdateFactory.create(sparqlUpdateString);
		UpdateProcessor proc = UpdateExecutionFactory.create(request, graphStore);
		proc.execute();
		
		String q = "select ?s ?p ?o where {?s ?p ?o}";
		Query query = QueryFactory.create(q);
		QueryExecution qexec = QueryExecutionFactory.create(query, m);
		ResultSet results = qexec.execSelect();
		String xmlString = ResultSetFormatter.asXMLString(results);
//		ResultSetFormatter.outputAsJSON(results);
		System.out.println(xmlString);
		JSONOutput jo = new JSONOutput();
		OutputStream os =new BufferedOutputStream(new FileOutputStream("file.txt"));
		jo.format(os, results);
		System.out.println(os.toString());
		System.out.println(results);
//		while(results.hasNext()){
//			System.out.println(results.next());
//		}
		m.close();
	}
	
	
	
}
