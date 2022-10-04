package com.qau.entites;

import java.io.InputStream;
import java.util.Properties;

import org.springframework.stereotype.Service;

@Service
public class Config {

	private String stopWordsPath;
	private String uniqueTermsPath;
    private String invertedIndexPath;
    private String unFilteredDSPath;
    private String classLabelPath;
    private String datasetPath;
    private String atcPath;
    private String nnnPath;
    private String ntcPath;
    private Properties prop = new Properties();
    private InputStream input = null;
        
    public Config(){
	try 
        {
            input = Config.class.getClassLoader().getResourceAsStream("com/qau/config/config.properties");
            // load a properties file
            prop.load(input);
            
            stopWordsPath		=	prop.getProperty("com.qau.stopWordsPath");
            uniqueTermsPath		=	prop.getProperty("com.qau.uniqueTermsPath");
            invertedIndexPath	=	prop.getProperty("com.qau.invertedIndexPath");
            unFilteredDSPath	=	prop.getProperty("com.qau.unFilteredDSPath");
            classLabelPath		=	prop.getProperty("com.qau.classLabelPath");
            datasetPath			=	prop.getProperty("com.qau.datasetPath");
            atcPath				=	prop.getProperty("com.qau.atcPath");
            nnnPath				=	prop.getProperty("com.qau.nnnPath");
            ntcPath				=	prop.getProperty("com.qau.ntcPath");

            // get the property value and print it out
            /*System.out.println(prop.getProperty("com.qau.uniqueTermsPath"));
            System.out.println(prop.getProperty("com.qau.invertedIndexPath"));
            System.out.println(prop.getProperty("com.qau.datasetPath"));
            System.out.println(prop.getProperty("com.qau.atcPath"));
            System.out.println(prop.getProperty("com.qau.nnnPath"));
            System.out.println(prop.getProperty("com.qau.ntcPath"));*/
	} 
        catch (Exception ex)
        {
            ex.printStackTrace();
	} 
        finally
        {
            if (input != null)
            {
                try 
                {
                        input.close();
                } catch (Exception e) {
                        e.printStackTrace();
                }
            }
	}
    }
    

    public String getClassLabelPath() {
		return classLabelPath;
	}



	public String getStopWordsPath() {
		return stopWordsPath;
	}


	public String getUnFilteredDSPath() {
		return unFilteredDSPath;
	}

	/**
     * @return the uniqueTermsPath
     */
    public String getUniqueTermsPath() {
        return uniqueTermsPath;
    }

    /**
     * @return the invertedIndexPath
     */
    public String getInvertedIndexPath() {
        return invertedIndexPath;
    }

    /**
     * @return the datasetPath
     */
    public String getDatasetPath() {
        return datasetPath;
    }

    /**
     * @return the atcPath
     */
    public String getAtcPath() {
        return atcPath;
    }

    /**
     * @return the nnnPath
     */
    public String getNnnPath() {
        return nnnPath;
    }

    /**
     * @return the ntcPath
     */
    public String getNtcPath() {
        return ntcPath;
    }
}
