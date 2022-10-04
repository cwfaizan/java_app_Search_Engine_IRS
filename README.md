# Search-Engine-IRS-in-java
## Information Retrieval Systems
### [Task 1]
Consider a textual document collection of news articles. Make a collection of 500 to 1000 news articles. Collection of news articles should represent different classes like international
news articles, national news articles, regional news, sports news, entertainment news, etc. Total number of classes should be 10. Perform pre-processing on this collection (removing of
punctuation marks, tokenization, words normalization, stop word removing, stemming, lemmatization). Create a vocabulary and generate a text file storing to index each word type
as follows:<br/>
![image](https://user-images.githubusercontent.com/73745377/193879514-4ac1f006-e78d-495a-a955-64e9ed4a5723.png)
<br/><br/><br/>

### [Task 2]<br/>
Construct an inverted index for news article collection to store and retrieve variation of tf-idf values of terms within each document used in context of SMART notation. Apply different
weighting schemes on document and query side (ddd.qqq): (a) nnn.atc, (b) ntc.atc. Consider classes of news articles as queries test set. So in this case we'll have 10 queries. So for each of
the above weighting scheme, we have 2 files: one for textual news article collection and the other for queries collection (class labels of news articles). The output of this task are 3 files: 2
for news article document collection (for nnn and ntc) and 1 for queries collection (for atc).<br/><br/>

### Format to follow for this task:<br/>
termID docID:tf docID:tf …..<br/>
3 1:4 7:4 9:1<br/><br/>

It means that the term indexed at 3 is occurring 4 times in document 1 (1:4), occurring 4 times in document 7 (7:4) and occurring once in document 9 (9:1).<br/><br/>


### [Task 3]<br/>
Give a web interface to search for news articles on given 10 queries test collection. For each out of 10 queries, calculate a cosine similarity score between each document and query. Rank
documents according to the score and display top 20 result set of documents for each query. Evaluate this IR System and compute precision and recall value for 10 different queries based
on class labels. Plot a precision-recall values curve. Also compute MAP for 10 queries.<br/><br/>

### [Task 4]<br/>
Write a 5-page report to explain each and every step involved in all of these three tasks. The report should also give an analysis on results of documents returned in terms of precision and recall values.

## UI & Result<br/>
![a 01](https://user-images.githubusercontent.com/73745377/193881166-f0227f56-cd63-470b-949e-9cb5d8451a85.png)
![a 02](https://user-images.githubusercontent.com/73745377/193881225-63c7dc7d-33f2-463f-b548-50a97e9ef15c.png)
