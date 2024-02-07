package com.example.alesiaproj.repository;

import com.example.alesiaproj.entity.CompanyInfo;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Component
public class FirebaseCompanyInfoRepository {

    public String create(CompanyInfo companyInfo) throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        DocumentReference docRef = firestore.collection("company_info").document();
        companyInfo.setId(docRef.getId());
        ApiFuture<WriteResult> collection = docRef.set(companyInfo);
        return collection.get().getUpdateTime().toString();
    }

    public Optional<CompanyInfo> getById(String documentId) throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = firestore.collection("company_info").document(documentId);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot documentSnapshot = future.get();

        if (documentSnapshot.exists()) {
            return Optional.of(documentSnapshot.toObject(CompanyInfo.class));
        }
        return Optional.empty();
    }
    public Optional<CompanyInfo> getByName(String name) throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        CollectionReference collectionReference = firestore.collection("company_info");
        Query query = collectionReference.whereEqualTo("name", name);
        ApiFuture<QuerySnapshot> future = query.get();
        QuerySnapshot querySnapshot;

        try {
            querySnapshot = future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return Optional.empty();
        }

        if (!querySnapshot.isEmpty()) {
            DocumentSnapshot documentSnapshot = querySnapshot.getDocuments().get(0);
            return Optional.of(documentSnapshot.toObject(CompanyInfo.class));
        }

        return Optional.empty();
    }
    public String deleteById(String documentId) {
        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResultApiFuture = firestore.collection("company_info").document(documentId).delete();
        return "OK " + documentId;
    }

    public String update(CompanyInfo companyInfo) throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> future = firestore.collection("company_info").document(companyInfo.getName()).set(companyInfo);
        return future.get().getUpdateTime().toString();
    }
    public String updateById(CompanyInfo companyInfo) throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> future = firestore.collection("company_info").document(companyInfo.getId()).set(companyInfo);
        return future.get().getUpdateTime().toString();
    }
    public List<CompanyInfo> getAll() throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        CollectionReference collectionReference = firestore.collection("company_info");

        ApiFuture<QuerySnapshot> future = collectionReference.get();
        QuerySnapshot querySnapshot = future.get();

        List<CompanyInfo> userInfoList = new ArrayList<>();

        for (QueryDocumentSnapshot document : querySnapshot) {
            userInfoList.add(document.toObject(CompanyInfo.class));
        }

        return userInfoList;
    }

}
