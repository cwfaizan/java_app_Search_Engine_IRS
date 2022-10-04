package com.qau.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.qau.entites.Documents;
import com.qau.entites.Terms;
import com.qau.interfaces.FileHandling;
import com.qau.repositories.FileRepository;
import com.qau.repositories.FilterRepository;

@Service
public class FilterService {

	@Autowired
	private FilterRepository filterRepository;
	
	@Autowired
	private FileRepository fileRepository;
	
	private Integer docId = 0;
	private Integer classLabelId = 0;
	private Set<String> hashtab = new HashSet<>();
	private List<Terms> terms = new ArrayList<>();
	private List<String> uniqueTerms = new ArrayList<>();
	private List<String> classLabel = new ArrayList<>();
	private List<Documents> documents = new ArrayList<>();
	private Map<Integer, String> termVocabulary = new LinkedHashMap<>();
    private Map<Integer, String> documentVocabulary = new LinkedHashMap<>();
    private List<Terms> documentsFrequency = new LinkedList<>();
    
    public List<Documents> getFilteredDocuments(String datasetPath, String unFilteredDatasetPath, String classLabelPath){
    	try
        {
    		// just for show data
    		documents.clear();
    		classLabel.clear();
            fileRepository.createBufferedWriter(datasetPath);
            docId = 0;
            fileRepository.readFromFile(classLabelPath, new FileHandling() {
								
				@Override
				public void OnFileReadingComplete() {
					fileRepository.readFromFile(unFilteredDatasetPath, new com.qau.interfaces.FileHandling() {
		                @Override
		                public void OnFileRead(String document) {
		                	String[] doc = document.split("#");
		                	classLabelId = Integer.valueOf(doc[0]);
		                    document = filterRepository.specialCharactorsRemoval(doc[1]);
		                    filterRepository.stopWordsRemoval(document, new com.qau.interfaces.Filters() {

		                        @Override
		                        public void OnStopWordsRemoval(String document) {
		                            docId++;
		                            // for show data
		                            
		                            documents.add(new Documents(docId, document, classLabel.get(--classLabelId)));
		                            document = docId + "," + document+","+doc[0];
		                            fileRepository.writeToFile(document);
		                        }
		                    });
		                    if(docId%500 == 0){
		                    	fileRepository.flushBufferedWriter();
		                        System.out.println("Process complete "+docId);
		                    }  
		                }

		                @Override
		                public void OnFileReadingComplete() {
		                	fileRepository.flushBufferedWriter();
		                	fileRepository.closeBufferedWriter();
		                    System.out.println("Process complete "+docId);
		                    System.out.println("File Reading complete");
		                }
		            });
				}
				
				@Override
				public void OnFileRead(String document) {
					String label[] = document.split(" ");
					classLabel.add(label[1]);
				}
			});
            
            return documents;
        }
        catch(Exception exp){
            System.out.println(exp.getMessage());
            return null;
        }
    }
    
	public List<Terms> getTokens(String datasetPath, String uniqueTermsPath){
		try
        {
			fileRepository.createBufferedWriter(uniqueTermsPath);
            docId = 0;
            hashtab.clear();
            fileRepository.readFromFile(datasetPath, new com.qau.interfaces.FileHandling() {
                @Override
                public void OnFileRead(String document) {
                    String[] docIdSeparation = document.split(",");
                    String[] vocabulary = docIdSeparation[1].split(" ");
                    for(String term:vocabulary)
                    {
                        hashtab.add(term);
                    }
                }

                @Override
                public void OnFileReadingComplete() {
                	terms.clear();
                    uniqueTerms.clear();
                    uniqueTerms.addAll(hashtab);
                    Collections.sort(uniqueTerms);
                    for(String term:uniqueTerms){
                        docId++;
                        terms.add(new Terms(docId, term));
                        fileRepository.writeToFile(docId, term);
                        if(docId%500 == 0)
                        {
                        	fileRepository.flushBufferedWriter();
                            System.out.println("Process complete "+docId);
                        }  
                    }
                    fileRepository.flushBufferedWriter();
                    System.out.println("Process complete "+docId);
                    System.out.println("File Reading complete");
                }
            });
            return terms;
        }
        catch(Exception exp){
            System.out.println(exp.getMessage());
            return null;
        }
	}
	
	public List<Terms> getInvertedIndex(String uniqueTermsPath, String datasetPath, String invertedIndexPath){
		try
        {
            termVocabulary.clear();
            fileRepository.readFromFile(uniqueTermsPath, new com.qau.interfaces.FileHandling() {
                @Override
                public void OnFileRead(String document) {
                    String[] vocabulary = document.split(" ");
                    // reading terms and store in map (termId,termValue) for check df
                    termVocabulary.put(Integer.valueOf(vocabulary[0]), vocabulary[1]);
                }

                @Override
                public void OnFileReadingComplete() {
                    System.out.println("termVocabulary size "+termVocabulary.size());
                    // reading document and store in map(docId, docValue)
                    vocabularyRead(datasetPath, invertedIndexPath);
                }
            });
            return documentsFrequency;
        }
        catch(Exception exp){
            System.out.println(exp.getMessage());
            return null;
        }
	}
	
	private void vocabularyRead(String datasetPath, String invertedIndexPath){
        try
        {
            documentVocabulary.clear();
            fileRepository.readFromFile(datasetPath, new com.qau.interfaces.FileHandling() {
                @Override
                public void OnFileRead(String document) {
                    String[] vocabulary = document.split(",");
                    // reading document and store in map(docId, docValue)
                    documentVocabulary.put(Integer.valueOf(vocabulary[0]), vocabulary[1]);
                }

                @Override
                public void OnFileReadingComplete() {
                    System.out.println("documentVocabulary size "+documentVocabulary.size());
                    // calculate tf of each term in vocabulary and sore inverted index as follow's (termID docID:tf docID:tf)
                    invertedIndex(invertedIndexPath);
                }
            });
        }
        catch(Exception exp){
            System.out.println(exp.getMessage());
        }
        
    }
	
	private void invertedIndex(String path)
    {
        try
        {
        	documentsFrequency.clear();
            docId = 0;
            String term, document;
            fileRepository.createBufferedWriter(path);
            for (Map.Entry m:termVocabulary.entrySet())
            {
                docId++;
                StringBuilder sb = new StringBuilder();
                term = m.getValue().toString();
                for (Map.Entry n:documentVocabulary.entrySet())
                {
                    document = n.getValue().toString();
                    int tf = StringUtils.countOccurrencesOf(document, term);
                    if(tf>0)
                    {
                        sb.append(n.getKey()).append(":").append(tf).append(" ");
                    }
                }
                
                String tdf = sb.toString().trim();
                String key = m.getKey().toString();
                documentsFrequency.add(new Terms(Integer.valueOf(key), tdf));
                tdf = key + "," + tdf;
                fileRepository.writeToFile(tdf);
                if(docId%500 == 0)
                {
                	fileRepository.flushBufferedWriter();
                }
            }
            fileRepository.flushBufferedWriter();
            System.out.println("Proces Complete Inverted Index");
        }
        catch(Exception exp){
            System.out.println(exp.getMessage());
        }
        
    }
}
