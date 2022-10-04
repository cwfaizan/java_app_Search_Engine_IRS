package com.qau.services;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.qau.entites.PrecisionRecall;
import com.qau.repositories.FileRepository;
import com.qau.repositories.FilterRepository;

@Service
public class SimilarityService {

	private Double relevant;
	private Double nonRelevant;
	private Double precision;
	private Double recall;
	private Integer totalRetrieved;
	private Integer docId = 0;
	private Integer maxTF = 1, df;
	private Integer totalNumberOfDocument = 0;
	private List<String> uniqueTerms = new ArrayList<>();
	private List<String> invertedIndex = new ArrayList<>();
	private Integer labelID = 0;
	@Autowired
	private FileRepository fileRepository;
	@Autowired
	private FilterRepository filterRepository;
	private Map<Integer, Map<String, Double>> documentTermsWt = new HashMap<>();
	private Map<String, Double> documentTermWt = null;
	private Map<String, Double> queryTermsWt = new HashMap<>();
	private List<String> queryTerms = new ArrayList<>();
	private List<String> documents = new ArrayList<>();
	private List<String> rankedDocuments = new ArrayList<>();
    private Map<String, Double> weight = new TreeMap<>();
    private List<String> queryStemTerms = new LinkedList<>();
	private Set<String> hashset = new HashSet<>();
	private DecimalFormat dformat = new DecimalFormat(".##");
	private List<String> topRanked = null;
	private List<String> ranked = new ArrayList<>();
	private List<PrecisionRecall> precisionRecall = new ArrayList<>();
	private Boolean firstEntery;
	
	public List<String> getRankedDocuments(String classLabel, Integer totalRelevant, String query, String classLabelPath, String documentPath, String datasetPath, String uniqueTermsPath, String invertedIndexPath, String measurePath){
		try
        {
			// 1.read datasetPath for atc and (nnn,ntc) as well
			// 2.read uniqueTermsPath get term id for search df
			// 3.read invertedIndexPath search or get df
			// 4.read documentPath (nnn or ntc) get docId, term, and weights
			// 5.read classLabelPath to get Class Id
			
            rankedDocuments.clear();
            documents.clear();
            uniqueTerms.clear();
            invertedIndex.clear();
            ranked.clear();
            firstEntery = true;
            labelID = 0;
            relevant = 0.0;
    		nonRelevant = 0.0;
    		precision = 0.0;
    		recall = 0.0;
    		
            fileRepository.readFromFile(datasetPath, new com.qau.interfaces.FileHandling() {
                @Override
                public void OnFileRead(String document) {
                    // reading documents and store in array for check df
                    documents.add(document);
                }

                @Override
                public void OnFileReadingComplete() {
                    fileRepository.readFromFile(uniqueTermsPath, new com.qau.interfaces.FileHandling() {
                        @Override
                        public void OnFileRead(String document) {
                            String[] vocabulary = document.split(" ");
                            // reading documents and store in array for check df
                            uniqueTerms.add(vocabulary[1]);
                        }

                        @Override
                        public void OnFileReadingComplete() {
                            // read df or unique words/terms
                            fileRepository.readFromFile(invertedIndexPath, new com.qau.interfaces.FileHandling() {
                                @Override
                                public void OnFileRead(String document) {
                                    String[] vocabulary = document.split(",");
                                    // reading documents and store in array for check df
                                    invertedIndex.add(vocabulary[1]);
                                }

                                @Override
                                public void OnFileReadingComplete() {
                                	calculateATC(query);
                                	documentTermsWt.clear();
                                    // read df or unique words/terms
                                    fileRepository.readFromFile(documentPath, new com.qau.interfaces.FileHandling() {
                                        @Override
                                        public void OnFileRead(String document) {
                                            // reading documents and store in array for check df
                                            setDocumentTermsWt(document);
                                        }

                                        @Override
                                        public void OnFileReadingComplete() {
                                            System.out.println("reading complete");
                                            fileRepository.readFromFile(classLabelPath, new com.qau.interfaces.FileHandling() {
                                                @Override
                                                public void OnFileRead(String document) {
                                                    // reading documents and store in array for check df
                                                    String[] doc = document.split(" ");
                                                    if(doc[1].trim().equalsIgnoreCase(classLabel))
                                                    	labelID = Integer.valueOf(doc[0]);
                                                }

                                                @Override
                                                public void OnFileReadingComplete() {
                                                    System.out.println("reading complete");
                                                    //atc             5555555555555555555
                                                    calculateCosineSimilarity();
                                                }
                                            });
                                        }
                                    });
                                }
                            });
                        }
                    });
                }
            });
            topRanked = rankedDocuments.subList(0, 20);
            fileRepository.createBufferedWriter(measurePath);
            for(String similarity:topRanked)
            {
            	// 0.53->docScore, 20->docID, doc, 10-> labelID
            	String[] data = similarity.split(",");
            	if(labelID == Integer.valueOf(data[3])){
            		relevant++;
            		fileRepository.writeToFile(data[1]+" R");
            	}
            	else{
            		nonRelevant++;
            		fileRepository.writeToFile(data[1]+" N");
            	}
            	ranked.add(data[2]);
            }
            totalRetrieved = (int) (relevant + nonRelevant);
        	precision = relevant/totalRetrieved;
        	recall = relevant/totalRelevant;
        	String result = "Precision: " + precision + " Recall: " + recall;
        	fileRepository.writeToFile(result);
        	fileRepository.closeBufferedWriter();
            topRanked.clear();
        	ranked.add(result);
        }
        catch(Exception exp){
            exp.printStackTrace();
        }
		return ranked;
	}
	
	public List<String> getRankedDocuments(int id, String documentPath, String queryPath, String datasetPath, String measurePath){
		try
        {
            rankedDocuments.clear();
            documents.clear();
            firstEntery = true;
            
            fileRepository.readFromFile(datasetPath, new com.qau.interfaces.FileHandling() {
                @Override
                public void OnFileRead(String document) {
                    // reading documents and store in array for check df
                    documents.add(document);
                }

                @Override
                public void OnFileReadingComplete() {
                    System.out.println("documents reading complete");
                    documentTermsWt.clear();
                    // read df or unique words/terms
                    fileRepository.readFromFile(documentPath, new com.qau.interfaces.FileHandling() {
                        @Override
                        public void OnFileRead(String document) {
                            // reading documents and store in array for check df
                            setDocumentTermsWt(document);
                        }

                        @Override
                        public void OnFileReadingComplete() {
                            System.out.println("reading complete");
                            queryTermsWt.clear();
                            queryTerms.clear();
                            // read df or unique words/terms
                            fileRepository.readFromFile(queryPath, new com.qau.interfaces.FileHandling() {
                                @Override
                                public void OnFileRead(String document) {
                                    String[] vocabulary = document.split(" ");
                                    // reading documents and store in array for check df
                                    Integer newDocId = Integer.valueOf(vocabulary[0]);
                                    if(newDocId == id){
                                        queryTerms.add(vocabulary[1]);
                                        queryTermsWt.put(vocabulary[1], Double.valueOf(vocabulary[2]));
                                    }
                                }

                                @Override
                                public void OnFileReadingComplete() {
                                    System.out.println("reading complete ");
                                    calculateCosineSimilarity();
                                }
                            });
                        }
                    });
                }
            });
            topRanked = rankedDocuments.subList(0, 20);
            fileRepository.createBufferedWriter(measurePath);
            for(String similarity:topRanked)
            {
            	// 0.53->docScore, 20->docID, doc, 10-> label
            	String[] data = similarity.split(",");
            	if(id == Integer.valueOf(data[3]))
            		fileRepository.writeToFile(data[1]+" R");
            	else
            		fileRepository.writeToFile(data[1]+" N");
            }
            fileRepository.closeBufferedWriter();
        }
        catch(Exception exp){
            exp.printStackTrace();
        }
		return topRanked;
	}
	
	public List<PrecisionRecall> getPrecisionRecall(int id, int totalRelevant, String documentPath){
		relevant = 0.0;
		nonRelevant = 0.0;
		precision = 0.0;
		recall = 0.0;
		precisionRecall.clear();
		try
		{
			fileRepository.readFromFile(documentPath, new com.qau.interfaces.FileHandling() {
                @Override
                public void OnFileRead(String document) {
                    String[] vocabulary = document.split(" ");
                    // reading documents and store in array for check df
                    if(vocabulary[1].equalsIgnoreCase("R"))
                    	relevant++;
                    else
                    	nonRelevant++;
                    precisionRecall.add(new PrecisionRecall("document: "+vocabulary[0], vocabulary[1]));
                }

                @Override
                public void OnFileReadingComplete() {
                	totalRetrieved = (int) (relevant + nonRelevant);
                	precision = relevant/totalRetrieved;
                	recall = relevant/totalRelevant;
                	precisionRecall.add(new PrecisionRecall("Total Relevant: ", relevant.toString()));
                	precisionRecall.add(new PrecisionRecall("Total Irrelevant: ", nonRelevant.toString()));
                	precisionRecall.add(new PrecisionRecall("Precision: ", String.valueOf(precision)));
                	precisionRecall.add(new PrecisionRecall("Recall: ", String.valueOf(recall)));
                }
            });
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return precisionRecall;
	}
	
	private void setDocumentTermsWt(String document){
        try
        {
            String[] vocabulary = document.split(" ");
            // reading documents and store in array for check df
            Integer newDocId = Integer.valueOf(vocabulary[0]);
            if(firstEntery){
                docId = newDocId;
                documentTermWt = new HashMap<>();
                documentTermWt.put(vocabulary[1], Double.valueOf(vocabulary[2]));
                firstEntery = false;
            }
            else if(newDocId == docId){
                documentTermWt.put(vocabulary[1], Double.valueOf(vocabulary[2]));
            }
            else{
                documentTermsWt.put(docId, documentTermWt);
                docId = newDocId;
                documentTermWt = new HashMap<>();
                documentTermWt.put(vocabulary[1], Double.valueOf(vocabulary[2]));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private void calculateCosineSimilarity(){
        documentTermWt = new HashMap<>();
        for (Map.Entry m:documentTermsWt.entrySet())
        {
            documentTermWt.clear();
            docId = (Integer) m.getKey();
            documentTermWt = (Map<String, Double>) m.getValue();
            hashset.clear();
            hashset.addAll(queryTerms);
            for(Map.Entry n:documentTermWt.entrySet())
                hashset.add((String) n.getKey());
            Double score = 0.0;
            for(String uTerm:hashset)
            {
                Double queryWt = queryTermsWt.get(uTerm);
                Double documentWt = documentTermWt.get(uTerm);
                if(queryWt != null && documentWt != null)
                {
                    score = score + (queryWt * documentWt);
                }
            }
            docId--;
            // 0.53->docScore, 20->docID, doc, 10-> label
            rankedDocuments.add((dformat.format(score)+","+documents.get(docId)));
        }
        Collections.sort(rankedDocuments, Collections.reverseOrder());
    }
    
    private void calculateATC(String query)
    {
        try
        {
        	queryTerms.clear();
            hashset.clear();
            queryTermsWt.clear();
            queryStemTerms.clear();
            weight.clear();
            query = filterRepository.specialCharactorsRemoval(query);
            filterRepository.stopWordsRemoval(query, new com.qau.interfaces.Filters() {
                @Override
                public void OnStopWordsRemoval(String document) {
                	
                	totalNumberOfDocument = documents.size();
                    System.out.println("totalNumberOfDocument "+totalNumberOfDocument);
                    String[] qryTerms = document.trim().split(" ");
                    maxTF = 1;
                    for(String term:qryTerms)
                    {
                        hashset.add(term);
                        int tf = StringUtils.countOccurrencesOf(document, term);
                        if(tf>maxTF)
                            maxTF = tf;
                    }
                    queryStemTerms.addAll(hashset);
                    Collections.sort(queryStemTerms);

                    double docLength = 0.0;
                    for(String term:queryStemTerms)
                    {
                        Integer tf = StringUtils.countOccurrencesOf(document, term);
                        double augment = 0.5 + ((0.5*tf)/maxTF);
                        df = calculateDF(term);
                        double idf = Double.valueOf(dformat.format(Math.log10(totalNumberOfDocument/df)));
                        double wt = 0;
                        if(tf == 0 || idf == 0)
                            wt = 0;
                        else
                            wt = Double.valueOf(dformat.format(idf * augment));
                        weight.put(term, wt);
                        docLength = docLength + (wt*wt);
                    }
                    docLength = Double.valueOf(dformat.format(Math.sqrt(docLength)));

                    for (Map.Entry m:weight.entrySet())
                    {
                        double norm = (Double.valueOf(m.getValue().toString()))/docLength;
                        queryTerms.add(m.getKey().toString());
                        queryTermsWt.put(m.getKey().toString(), Double.valueOf(dformat.format(norm)));
                    }
                }
            });
        }
        catch(Exception exp){
            System.out.println(exp.getMessage());
        }
    }
    
    private Integer calculateDF(String term)
    {
        try
        {
            Integer termId = uniqueTerms.indexOf(term);
            String str = invertedIndex.get(termId);
            String[] df = str.split(" ");
            return df.length;
        }
        catch(Exception exp){
            return 0;
        }
    }
}
