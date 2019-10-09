package com.keya.demoelastic.artiz.infra;

import java.util.List;

public interface JobberService {
    Jobber save(Jobber book);
    List<Jobber> getAll();

}
