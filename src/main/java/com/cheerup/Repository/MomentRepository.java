package com.cheerup.Repository;



import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cheerup.entity.Moment;

@Repository
public interface MomentRepository extends MongoRepository<Moment,String> {

}
