package org.example;

import com.ibm.cloud.cloudant.v1.Cloudant;
import com.ibm.cloud.cloudant.v1.model.*;
import com.ibm.cloud.sdk.core.security.BasicAuthenticator;
import com.ibm.cloud.sdk.core.service.exception.NotFoundException;

import java.io.IOException;
import java.util.*;

import static javax.swing.UIManager.put;

public class Couchdb {
    public static void main(String[] args) throws IOException {
        try {
            BasicAuthenticator authenticator = new BasicAuthenticator.Builder()
                    .username("admin")
                    .password("admin")
                    .build();
//  Create a client with `CLOUDANT` default service name ============
        Cloudant client = new Cloudant(Cloudant.DEFAULT_SERVICE_URL, authenticator);

//  Get database information for "test_participation_evals" =========================
        String dbName = "test_participation_evals";

        GetDatabaseInformationOptions dbInformationOptions =
                new GetDatabaseInformationOptions.Builder(dbName).build();

        DatabaseInformation dbInformationResponse = client
                .getDatabaseInformation(dbInformationOptions)
                .execute()
                .getResult();


//            Creating new document called fuqua

      /*      String fuquaDocId = "fuqua";
            Document fuquaDocument = new Document();
            fuquaDocument.setId(fuquaDocId);
            fuquaDocument.put("name", "Rajita Maharjan");
            fuquaDocument.put("Employed Since", 2019);
            PostDocumentOptions createDocumentOptions = new PostDocumentOptions.Builder()
                    .db(dbName)
                    .document(fuquaDocument)
                    .build();

            DocumentResult updateDocumentResponse = client
                    .postDocument(updateDocumentOptions)
                    .execute()
                    .getResult();*/

//        Updating document
            dbName = "test_participation_evals";
            GetDocumentOptions documentInfoOption = new GetDocumentOptions.Builder()
                    .db(dbName)
                    .docId("fuqua")
                    .build();
           try{
               Document document = client
                       .getDocument(documentInfoOption)
                       .execute()
                       .getResult();

               document.put("address", "100 Fuqua Drive");
               document.removeProperty("Employed Since");

               PostDocumentOptions updateDocumentOptions = new PostDocumentOptions.Builder()
                       .db(dbName)
                       .document(document)
                       .build();

               DocumentResult updateDocumentResponse = client
                       .postDocument(updateDocumentOptions)
                       .execute()
                       .getResult();

               document.setRev(updateDocumentResponse.getRev());
               System.out.println("You have updated the document:\n" + document);
           } catch (NotFoundException nfe) {
               System.out.println("Cannot update document because" + "fuqua" + "document was not found");
           }

           // Delete the document

            dbName = "test_participation_evals";
           String docID = "fuqua";
           GetDocumentOptions documentInfoOptions = new GetDocumentOptions.Builder()
                   .db(dbName)
                   .docId(docID)
                   .build();
           try{
               Document document  = client
                       .getDocument(documentInfoOptions)
                       .execute()
                       .getResult();

               DeleteDocumentOptions deleteDocumentOptions = new DeleteDocumentOptions.Builder()
                       .db(dbName)
                       .docId(docID)
                       .rev(document.getRev())
                       .build();
               DocumentResult deleteDocumentResponse = client
                       .deleteDocument(deleteDocumentOptions)
                       .execute()
                       .getResult();
               if(deleteDocumentResponse.isOk()){
                   System.out.println("The document has been deleted.");
               }
           } catch (NotFoundException nfe){
               System.out.println("Cannot delete document" + "document wasn't found");
           }





/*//  3. Get "example" document out of the database by document id ===========
      GetDocumentOptions getDocOptions = new GetDocumentOptions.Builder()
              .db(dbName)
              .docId("10_points_per")
              .build();

        Document documentExample = client
                .getDocument(getDocOptions)
                .execute()
                .getResult();

        System.out.println("Document retrieved from database:\n" +
                documentExample);*/



/*
//            1. Get all documents of type "team_eval_config".
            Map<String, Object> selectorSearchByTypeTec = Collections.singletonMap("type","team_eval_config");
            PostFindOptions findOptions0 = new PostFindOptions.Builder().db(dbName).selector(selectorSearchByTypeTec).build();
            FindResult response0 = client.postFind(findOptions0).execute().getResult();
            System.out.println(response0);


//            2. Get all documents of type "data".  You should get 1 document back.
            Map<String,Object> selectorSearchByTypeData= Collections.singletonMap("type","data");
            PostFindOptions findOptions1 = new PostFindOptions.Builder().db(dbName).selector(selectorSearchByTypeData).build();
            FindResult response1 = client.postFind(findOptions1).execute().getResult();
            System.out.println(response1);

//            3. Get all documents that are NOT of type "team_eval_config".  You should get 1 document back.
            Map<String,Object> selectorSearchByTypeNETeamEval= Collections.singletonMap("type",Collections.singletonMap("$ne","team_eval_config"));
            PostFindOptions findOptions = new PostFindOptions.Builder().db(dbName).selector(selectorSearchByTypeNETeamEval).build();
            FindResult response= client.postFind(findOptions).execute().getResult();
            System.out.println(response);


//       4.Get all documents of type "team_eval_config" AND of uuid "10pp".

            Map<String, Object> mapType = Collections.singletonMap("type", "team_eval_config");
            Map<String, Object> mapUuid = Collections.singletonMap("uuid","10pp");
            ArrayList<Map<String, Object>> arrayList= new ArrayList<>();
            arrayList.add(mapType);
            arrayList.add(mapUuid);
            //System.out.println(arrayList);


            Map<String,Object> s2= Collections.singletonMap("$and",arrayList);
           // System.out.println(s2);
            PostFindOptions findOptions3 = new PostFindOptions.Builder().db(dbName).selector(s2).build();
            FindResult response3= client.postFind(findOptions3).execute().getResult();
            System.out.println(response3);


//       5. Get all documents of type "team_eval_config" OR type "data".
            Map<String, Object> mapByType = Collections.singletonMap("type", "team_eval_config");
            Map<String, Object> mapByUuid = Collections.singletonMap("type","data");
            ArrayList<Map<String, Object>> arrayList1= new ArrayList<>();
            arrayList1.add(mapByType);
            arrayList1.add(mapByUuid);
            Map<String,Object> s3= Collections.singletonMap("$or",arrayList1);
            PostFindOptions findOptions4 = new PostFindOptions.Builder().db(dbName).selector(s3).build();
            FindResult response4= client.postFind(findOptions4).execute().getResult();
            System.out.println(response4);
*/


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

