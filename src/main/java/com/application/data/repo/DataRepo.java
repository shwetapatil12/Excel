package com.application.data.repo;

import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.stereotype.Repository;

import com.application.data.model.Data;
@Repository
public interface DataRepo extends CouchbaseRepository<Data, Integer> {

}
