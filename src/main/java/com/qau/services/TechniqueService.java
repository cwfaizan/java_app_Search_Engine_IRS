package com.qau.services;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.qau.entites.DocumentInfo;
import com.qau.repositories.FileRepository;
import com.qau.repositories.FilterRepository;
import com.qau.repositories.PorterStemmer;

@Service
public class TechniqueService {

	private Integer queryId = 0;
	private Integer docId = 0;
	private Integer maxTF = 1, df;
	private Integer totalNumberOfDocument = 0;
	
	@Autowired
	private PorterStemmer stemmer;
	@Autowired
	private FileRepository fileRepository;
	@Autowired
	private FilterRepository filterRepository;
	private List<DocumentInfo> documentsFrequency = new LinkedList<>();
	private Set<String> hashtab = new HashSet<>();
    private List<String> uniqueTerms = new ArrayList<>();
    private List<String> documents = new LinkedList<>();
    private List<String> invertedIndex = new ArrayList<>();
    private Map<String, Double> weight = new TreeMap<>();
    private List<String> queryStemTerms = new LinkedList<>();
    private List<String> documentStemTerms = new LinkedList<>();
    private DecimalFormat dformat = new DecimalFormat(".##");
    private Set<String> hashset = null;
    
	public List<DocumentInfo> getCalculationNNN(String datasetPath, String nnnPath){
		try
        {
			// for show data only
			documentsFrequency.clear();
            documents.clear();

            fileRepository.readFromFile(datasetPath, new com.qau.interfaces.FileHandling() {
                @Override
                public void OnFileRead(String document) {
                    // reading documents and store in array for check tf
                    documents.add(document);
                }

                @Override
                public void OnFileReadingComplete() {
                    System.out.println("documents reading complete");
                    // calculate tf and save
                    calculateNNN(nnnPath);
                }
            });
            return documentsFrequency;
        }
        catch(Exception exp){
            System.out.println(exp.getMessage());
            return documentsFrequency;
        }
	}
	
	public List<DocumentInfo> getCalculationNTC(String datasetPath, String uniqueTermsPath, String invertedIndexPath, String ntcPath){
		try
        {
			// for show data only
			documentsFrequency.clear();
            documents.clear();
            uniqueTerms.clear();
            invertedIndex.clear();

            fileRepository.readFromFile(datasetPath, new com.qau.interfaces.FileHandling() {
                @Override
                public void OnFileRead(String document) {
                    String[] vocabulary = document.split(",");
                    // reading documents and store in array for check df
                    documents.add(vocabulary[1]);
                }

                @Override
                public void OnFileReadingComplete() {
                    System.out.println("documents reading complete");
                    // read df or unique words/terms
                    fileRepository.readFromFile(uniqueTermsPath, new com.qau.interfaces.FileHandling() {
                        @Override
                        public void OnFileRead(String document) {
                            String[] vocabulary = document.split(" ");
                            // reading documents and store in array for check df
                            uniqueTerms.add(vocabulary[1]);
                        }

                        @Override
                        public void OnFileReadingComplete() {
                            System.out.println("unique Terms reading complete");
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
                                	calculateNTC(ntcPath);
                                }
                            });
                        }
                    });
                }
            });
            return documentsFrequency;
        }
        catch(Exception exp){
            System.out.println(exp.getMessage());
            return documentsFrequency;
        }
		
	}
	
	public List<DocumentInfo> getCalculationATC(String datasetPath, String uniqueTermsPath, String invertedIndexPath, String atcPath){
		try
		{
			documentsFrequency.clear();
			List<String> qry = new ArrayList<>();
	        qry.add("athletics news");
	        qry.add("business news");
	        qry.add("cricket news");
	        qry.add("entertainment news");
	        qry.add("football news");
	        qry.add("politics news");
	        qry.add("rugby news");
	        qry.add("sport news");
	        qry.add("tech news");
	        qry.add("tennis news");
	        queryId = 0;
	        fileRepository.createBufferedWriter(atcPath);
	        for(String query:qry)
	        {
	            queryId++;
	            callATC(query, datasetPath, uniqueTermsPath, invertedIndexPath, atcPath);
	        }
	        fileRepository.closeBufferedWriter();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return documentsFrequency;
	}
	
	private void calculateNNN(String nnnPath){
		try
		{
			fileRepository.createBufferedWriter(nnnPath);
			for(String doc:documents){
				String[] document = doc.split(",");
				docId = Integer.valueOf(document[0]);
				hashset = new HashSet<>(Arrays.asList(document[1].split(" ")));
				uniqueTerms.clear();
				uniqueTerms.addAll(hashset);
				Collections.sort(uniqueTerms);
				for(String term:uniqueTerms){
					Integer tf = StringUtils.countOccurrencesOf(document[1], term);
					StringBuilder normSB = new StringBuilder();
                    normSB.append(docId).append(" ")
                    .append(term).append(" ").append(tf);
                    documentsFrequency.add(new DocumentInfo(docId, term, Double.valueOf(tf)));
                    fileRepository.writeToFile(normSB.toString());
				}
			}
			fileRepository.closeBufferedWriter();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private void calculateNTC(String ntcPath)
    {
        try
        {
            totalNumberOfDocument = documents.size();
            System.out.println("totalNumberOfDocument "+totalNumberOfDocument);
            docId = 0;
            fileRepository.createBufferedWriter(ntcPath);
            for(String doc:documents)
            {
                docId++;
                hashtab.clear();
                documentStemTerms.clear();
                String[] documentTerms = doc.trim().split(" ");
                StringBuilder sb = new StringBuilder();
                String stemString;
                for(String term:documentTerms)
                {
                    stemString = term;
                    hashtab.add(term);
                    sb.append(stemString).append(" ");
                }
                stemString = sb.toString().trim();
                documentStemTerms.addAll(hashtab);
                Collections.sort(documentStemTerms);

                weight.clear();
                double docLength = 0.0;
                for(String term:documentStemTerms)
                {
                    Integer tf = StringUtils.countOccurrencesOf(stemString, term);
                    df = calculateDF(term);
                    double idf = Double.valueOf(dformat.format(Math.log10(totalNumberOfDocument/df)));
                    double wt = 0;
                    if(tf == 0 || idf == 0)
                        wt = 0;
                    else
                        wt = Double.valueOf(dformat.format(idf * tf));
                    weight.put(term, wt);
                    docLength = docLength + (wt*wt);
                }
                docLength = Double.valueOf(dformat.format(Math.sqrt(docLength)));
                
                for (Map.Entry m:weight.entrySet())
                {
                    StringBuilder normSB = new StringBuilder();
                    normSB.append(m.getKey().toString()).append(" ");
                    double norm = (Double.valueOf(m.getValue().toString()))/docLength;
                    normSB.append(dformat.format(norm));
                    documentsFrequency.add(new DocumentInfo(docId, m.getKey().toString(), Double.valueOf(dformat.format(norm))));
                    fileRepository.writeToFile((docId+" "+normSB.toString()));
                }
            }
            fileRepository.closeBufferedWriter();
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
	
	private void callATC(String query, String datasetPath, String uniqueTermsPath, String invertedIndexPath, String atcPath)
    {
        try
        {
            documents.clear();
            uniqueTerms.clear();
            invertedIndex.clear();

            fileRepository.readFromFile(datasetPath, new com.qau.interfaces.FileHandling() {
                @Override
                public void OnFileRead(String document) {
                    String[] vocabulary = document.split(",");
                    // reading documents and store in array for check df
                    documents.add(vocabulary[1]);
                }

                @Override
                public void OnFileReadingComplete() {
                    System.out.println("documents reading complete");
                    // read df or unique words/terms
                    fileRepository.readFromFile(uniqueTermsPath, new com.qau.interfaces.FileHandling() {
                        @Override
                        public void OnFileRead(String document) {
                            String[] vocabulary = document.split(" ");
                            // reading documents and store in array for check df
                            uniqueTerms.add(vocabulary[1]);
                        }

                        @Override
                        public void OnFileReadingComplete() {
                            System.out.println("unique Terms reading complete");
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
                                }
                            });
                        }
                    });
                }
            });
        }
        catch(Exception exp){
            System.out.println(exp.getMessage());
        }
    }
	
	private void calculateATC(String query)
    {
        try
        {
            weight.clear();
            hashtab.clear();
            queryStemTerms.clear();
            query = filterRepository.specialCharactorsRemoval(query);
            filterRepository.stopWordsRemoval(query, new com.qau.interfaces.Filters() {
                @Override
                public void OnStopWordsRemoval(String document) {
                	
                	totalNumberOfDocument = documents.size();
                    System.out.println("totalNumberOfDocument "+totalNumberOfDocument);
                    String[] queryTerms = document.trim().split(" ");
                    maxTF = 1;
                    for(String term:queryTerms)
                    {
                        hashtab.add(term);
                        int tf = StringUtils.countOccurrencesOf(document, term);
                        if(tf>maxTF)
                            maxTF = tf;
                    }
                    queryStemTerms.addAll(hashtab);
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
                        StringBuilder normSB = new StringBuilder();
                        normSB.append(queryId).append(" ").append(m.getKey().toString()).append(" ");
                        double norm = (Double.valueOf(m.getValue().toString()))/docLength;
                        normSB.append(dformat.format(norm));
                        documentsFrequency.add(new DocumentInfo(queryId, m.getKey().toString(), Double.valueOf(dformat.format(norm))));
                        fileRepository.writeToFile(normSB.toString());
                    }
                }
            });
        }
        catch(Exception exp){
            System.out.println(exp.getMessage());
        }
        
    }
}
