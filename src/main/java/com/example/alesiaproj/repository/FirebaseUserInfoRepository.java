package com.example.alesiaproj.repository;

import com.example.alesiaproj.entity.CompanyInfo;
import com.example.alesiaproj.entity.UserInfo;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Component
public class FirebaseUserInfoRepository {

    public String create(UserInfo userInfo) throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        DocumentReference docRef = firestore.collection("user_info").document();
        userInfo.setId(docRef.getId());
        ApiFuture<WriteResult> collection = docRef.set(userInfo);
        return collection.get().getUpdateTime().toString();
    }

    public Optional<UserInfo> getById(String documentId) throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = firestore.collection("user_info").document(documentId);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot documentSnapshot = future.get();

        if (documentSnapshot.exists()) {
            return Optional.of(documentSnapshot.toObject(UserInfo.class));
        }
        return Optional.empty();
    }

    public String deleteById(String documentId) {
        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResultApiFuture = firestore.collection("user_info").document(documentId).delete();
        return "OK " + documentId;
    }
    public String deleteByEmail(String email) throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        CollectionReference collectionReference = firestore.collection("user_info");
        Query query = collectionReference.whereEqualTo("email", email);
        ApiFuture<QuerySnapshot> querySnapshotApiFuture = query.get();
        QuerySnapshot querySnapshot;
        try {
            querySnapshot = querySnapshotApiFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return "Error";
        }

        for (QueryDocumentSnapshot document : querySnapshot) {
            ApiFuture<WriteResult> deleteResultApiFuture = document.getReference().delete();
        }

        return "OK " + email;
    }
    public Optional<UserInfo> getByEmail(String email) throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        CollectionReference collectionReference = firestore.collection("user_info");
        Query query = collectionReference.whereEqualTo("email", email);
        ApiFuture<QuerySnapshot> querySnapshotApiFuture = query.get();
        QuerySnapshot querySnapshot;

        try {
            querySnapshot = querySnapshotApiFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return Optional.empty();
        }

        if (!querySnapshot.isEmpty()) {
            DocumentSnapshot documentSnapshot = querySnapshot.getDocuments().get(0);
            return Optional.of(documentSnapshot.toObject(UserInfo.class));
        }
        return Optional.empty();
    }
    public String update(UserInfo userInfo) throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> future = firestore.collection("user_info").document(userInfo.getName()).set(userInfo);
        return future.get().getUpdateTime().toString();
    }

    public List<UserInfo> getAll() throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        CollectionReference collectionReference = firestore.collection("user_info");

        ApiFuture<QuerySnapshot> future = collectionReference.get();
        QuerySnapshot querySnapshot = future.get();

        List<UserInfo> userInfoList = new ArrayList<>();

        for (QueryDocumentSnapshot document : querySnapshot) {
            userInfoList.add(document.toObject(UserInfo.class));
        }

        return userInfoList;
    }
    public Optional<CompanyInfo> getByCompanyName(String companyName) throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        CollectionReference collectionReference = firestore.collection("company_info");

        Query query = collectionReference.whereEqualTo("name", companyName);

        ApiFuture<QuerySnapshot> future = query.get();
        QuerySnapshot querySnapshot = future.get();

        for (QueryDocumentSnapshot document : querySnapshot) {
            CompanyInfo companyInfo = document.toObject(CompanyInfo.class);
            return Optional.of(companyInfo);
        }

        return Optional.empty();
    }
}
