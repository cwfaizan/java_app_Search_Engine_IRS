package com.qau.services;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qau.entites.PrecisionRecall;
import com.qau.repositories.FileRepository;

@Service
public class MapService {

	@Autowired
	private FileRepository fileRepository;
	private List<PrecisionRecall> precisionRecall = new ArrayList<>();
	private List<String> labels = new LinkedList<>();
	private Integer totalLabel;
	private Double relevant;
	private Double nonRelevant;
	private Double precision;
	private Double recall;
	private Double totalPrecision;
	private Double totalRecall;
	private Integer totalRetrieved;
	private int label;
	
	public List<PrecisionRecall> getMap(int totalRelevant, String labelPath, String notationPath){
		precisionRecall.clear();
		labels.clear();
		totalLabel = 0;
		totalPrecision = 0.0;
		totalRecall = 0.0;
		try
		{
			fileRepository.readFromFile(labelPath, new com.qau.interfaces.FileHandling() {
                @Override
                public void OnFileRead(String document) {
                    // reading documents and store in array for check df
                    String[] doc = document.split(" ");
                    labels.add(doc[1]);
                }

                @Override
                public void OnFileReadingComplete() {
                	totalLabel = labels.size();
                	for(int i = 0; i<totalLabel; i++){
                		label = i;
                		relevant = 0.0;
                		nonRelevant = 0.0;
                		precision = 0.0;
                		recall = 0.0;
                		fileRepository.readFromFile(notationPath+(i+1), new com.qau.interfaces.FileHandling() {
                            @Override
                            public void OnFileRead(String document) {
                                // reading documents and store in array for check df
                            	String[] vocabulary = document.split(" ");
                                // reading documents and store in array for check df
                                if(vocabulary[1].equalsIgnoreCase("R"))
                                	relevant++;
                                else
                                	nonRelevant++;
                            }

                            @Override
                            public void OnFileReadingComplete() {
                            	totalRetrieved = (int) (relevant + nonRelevant);
                            	precision = relevant/totalRetrieved;
                            	recall = relevant/totalRelevant;
                            	totalPrecision += precision;
                            	totalRecall += recall;
                            	precisionRecall.add(new PrecisionRecall(labels.get(label)+" Precision: ", String.valueOf(precision)));
                            	precisionRecall.add(new PrecisionRecall(labels.get(label)+" Recall: ", String.valueOf(recall)));
                            }
                        });
                	}
                }
            });
			precisionRecall.add(new PrecisionRecall("Macro Average Precision: ", String.valueOf(totalPrecision/totalLabel)));
        	precisionRecall.add(new PrecisionRecall("Macro Average Recall: ", String.valueOf(totalRecall/totalLabel)));
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return precisionRecall;
	}
}
