package com.hackathonNerds.repository;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import com.hackathonNerds.entity.User;

public class UserRepositoryImpl implements UserRep {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getUsers(String firstParameter) {
        StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("StoredProcName");

        // Set the parameters of the stored procedure.
        String firstParam = "firstParam";
        storedProcedure.registerStoredProcedureParameter(firstParam, String.class, ParameterMode.IN);
        storedProcedure.setParameter(firstParam, firstParameter);

        // Call the stored procedure.
        List<Object[]> storedProcedureResults = storedProcedure.getResultList();

        return storedProcedureResults.stream().map(result -> new User((Integer) result[0], (String) result[1]))
                .collect(Collectors.toList());

    }
}
