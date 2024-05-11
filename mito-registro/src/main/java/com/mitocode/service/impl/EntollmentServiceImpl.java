package com.mitocode.service.impl;

import com.mitocode.model.Enrollment;
import com.mitocode.repo.IEnrollmentRepo;
import com.mitocode.repo.IGenericRepo;
import com.mitocode.service.IEnrollmentService;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class EntollmentServiceImpl extends CRUDImpl<Enrollment, Integer> implements IEnrollmentService {

    private final IEnrollmentRepo repo;

    @Override
    protected IGenericRepo<Enrollment, Integer> getRepo() {
        return repo;
    }
}
