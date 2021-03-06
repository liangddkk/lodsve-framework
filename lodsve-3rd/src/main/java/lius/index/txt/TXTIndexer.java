package lius.index.txt;

/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */



import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import lius.config.LiusField;
import lius.index.Indexer;

import org.apache.log4j.Logger;



/**

 * @author Rida Benjelloun (ridabenjelloun@gmail.com)

 */


public class TXTIndexer extends Indexer{

    static Logger logger = Logger.getRootLogger();

    public int getType() {
        return 1;
    }

    public boolean isConfigured() {
        boolean ef = false;
        if(getLiusConfig().getTxtFields() != null)
            return ef = true;
        return ef;
    }

    public Collection getConfigurationFields() {
        return getLiusConfig().getTexFields();
    }

    public String getContent(){
        StringBuffer sb = new StringBuffer();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(getStreamToIndex()));
            String line =  null;
            while((line = br.readLine()) != null){
                sb.append(line);
                sb.append(" ");
            }
        }
        catch (FileNotFoundException ex) {
            logger.error(ex.getMessage());
        }
        catch (IOException ex1) {
            logger.error(ex1.getMessage());
        }
        return sb.toString();
    }


    public Collection getPopulatedLiusFields() {
        Collection coll = new ArrayList();
        Iterator it = getLiusConfig().getTxtFields().iterator();
        while (it.hasNext()) {
            Object field = it.next();
            if (field instanceof LiusField) {
                LiusField lf = (LiusField) field;
                if (lf.getGet() != null) {
                    if (lf.getGet().equalsIgnoreCase("content")) {
                        String content = getContent();
                        lf.setValue(content);
                        coll.add(lf);
                    }
                }
            }
            else{
                coll.add(field);
            }
        }
        return coll;
    }

}